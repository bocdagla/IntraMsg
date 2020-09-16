package com.example.tomeu.intramsg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tomeu.intramsg.databaseObjects.Empleat;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.utils.DBClient;

public class MissatgeDetailActivity extends AppCompatActivity {

    TextView assumpte;
    TextView data;
    TextView emisor;
    TextView receptor;
    TextView texte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missatge_detail);

        assumpte = (TextView) findViewById(R.id.asunto);
        data = (TextView) findViewById(R.id.data);
        emisor = (TextView) findViewById(R.id.emisor);
        receptor = (TextView) findViewById(R.id.receptor);
        texte = (TextView) findViewById(R.id.missatge);

        Intent intent = getIntent();
        int id = Integer.parseInt(intent.getStringExtra(MainMissatges.idMissatgeExtra));
        Missatge mis = DBClient.getMissatge(id);
        Empleat empOrg = DBClient.getEmpleat(mis.getEmplorigen());
        Empleat empDest = DBClient.getEmpleat(mis.getEmpldesti());

        assumpte.setText(mis.getAsusmpte());
        data.setText(mis.getDatorigen());
        emisor.setText(empDest.getEmail());
        receptor.setText(empOrg.getEmail());
        texte.setText(mis.getMissatge());
    }
}
