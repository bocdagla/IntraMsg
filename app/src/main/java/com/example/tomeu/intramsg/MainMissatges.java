package com.example.tomeu.intramsg;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tomeu.intramsg.clientApi.APIMissatge;
import com.example.tomeu.intramsg.clientApi.Sincronitza;
import com.example.tomeu.intramsg.clientApi.VolleyResponseHandler;
import com.example.tomeu.intramsg.databaseObjects.Empleat;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.utils.DBClient;
import com.example.tomeu.intramsg.utils.Preferencies;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMissatges extends AppCompatActivity {

    public static final String idMissatgeExtra = "id";
    private ListView llista;
    private ArrayList<HashMap<String, Object>> misstages;
    private  Preferencies pref;
    private FloatingActionButton nouMissatge;
    private APIMissatge mis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_missatges);

        nouMissatge = (FloatingActionButton) findViewById(R.id.fab);
        llista = (ListView) findViewById(R.id.llista);

        pref = new Preferencies(getApplicationContext());
        mis = new APIMissatge(getApplicationContext());

        setListEvents();
        setButtonEvents();
    }

    @Override
    protected void onStart(){
        super.onStart();

        actualizaBdd();
    }

    private void setListEvents() {
        llista.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getApplicationContext(), MissatgeDetailActivity.class);
                        intent.putExtra(idMissatgeExtra, misstages.get(position).get("id").toString());
                        startActivity(intent);
                    }
                }
        );
    }

    private void setButtonEvents() {
        nouMissatge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NouMissatgeActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void actualizaBdd(){

        VolleyResponseHandler hnd = new VolleyResponseHandler() {
            @Override
            public void success(JSONObject response) {
                actualizaMissatges();
            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Ha passat quolque error amb la conexió", Toast.LENGTH_SHORT).show();
            }
        };

        Sincronitza.actualitzaBdd(getApplicationContext(), hnd);
    }

    protected void actualizaMissatges(){
        mis.actualitzaBdd(pref.getIdempleat(), new VolleyResponseHandler() {
            @Override
            public void success(JSONObject response) {
                ompleLLista();
            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Ha passat quolque error amb la conexió", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void ompleLLista(){
        try {
            int userId = pref.getIdempleat();
            ArrayList<Missatge> missatges = DBClient.selectAllMissatges(userId);
            misstages = new ArrayList<>();
            Empleat emp = null;
            HashMap<String, Object> map = null;

            for(Missatge reg: missatges){
                emp = DBClient.getEmpleat(reg.getEmplorigen());
                map = new HashMap<>();
                map.put("id", reg.getIdmiss());
                map.put("assumpte", reg.getAsusmpte());
                map.put("data", reg.getDatorigen());
                map.put("enviatPer", emp.getNomempl());

                misstages.add(map);
            }

            SimpleAdapter adap = new SimpleAdapter(
                    getApplicationContext(),
                    misstages,
                    R.layout.fila_missatge,
                    new String[]{
                            "assumpte",
                            "data",
                            "enviatPer"
                    },
                    new int[]{
                            R.id.assumpte,
                            R.id.dataEnviat,
                            R.id.enviatPer
                    }
            );

            llista.setAdapter(adap);
        }catch (Exception ex){
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
        }
    }
}
