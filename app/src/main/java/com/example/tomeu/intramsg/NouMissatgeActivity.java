package com.example.tomeu.intramsg;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tomeu.intramsg.clientApi.APIMissatge;
import com.example.tomeu.intramsg.clientApi.VolleyResponseHandler;
import com.example.tomeu.intramsg.databaseObjects.Empleat;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.utils.DBClient;
import com.example.tomeu.intramsg.utils.Preferencies;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NouMissatgeActivity extends AppCompatActivity {

    private Preferencies pref;
    private AutoCompleteTextView empleats;
    private EditText asumpte;
    private TextView missatge;
    private FloatingActionButton envia;


    private List<Empleat> emps;
    private int empleadoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nou_missatge);

        pref = new Preferencies(getApplicationContext());
        empleats = (AutoCompleteTextView) findViewById(R.id.autors);
        asumpte = (EditText) findViewById(R.id.assumpte);
        missatge = (EditText) findViewById(R.id.missatge);
        envia = (FloatingActionButton) findViewById(R.id.enviamissatge);

        ompleEmpleats();
        creaEventsBoto();
    }


    private void ompleEmpleats(){
        try {
            int userId = pref.getIdempleat();
            emps = DBClient.getRestaEmpleats(userId);
            ArrayList<String> empleatsVal = new ArrayList<>();

            for(Empleat reg: emps){
                empleatsVal.add(reg.getNomempl());
            }

            ArrayAdapter adap = new ArrayAdapter(
                    this, android.R.layout.select_dialog_item, empleatsVal
            );

            empleats.setAdapter(adap);
        }
        catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void creaEventsBoto(){
        empleats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                empleadoSeleccionado = position;
            }
        });

        envia.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int emplOrigen = pref.getIdempleat();
                        int emplDesti = emps.get(empleadoSeleccionado).getIdempl();
                        String asu = asumpte.getText().toString();
                        String mis = missatge.getText().toString();

                        APIMissatge missatge = new APIMissatge(getApplicationContext());
                        missatge.envia(emplOrigen, emplDesti, asu, mis, tornaHandlerMissatge());
                    }
                }
        );
    }

    private VolleyResponseHandler tornaHandlerMissatge(){
        final AppCompatActivity myself = this;
        return new VolleyResponseHandler(){

            @Override
            public void success(JSONObject response) {
                Toast.makeText(getApplicationContext(), "Se ha enviat el missatge correctament", Toast.LENGTH_LONG).show();
                myself.onBackPressed();
            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Hi ha habut quolque problema amb l'enviament del missatge", Toast.LENGTH_LONG).show();
            }
        };
    }
}
