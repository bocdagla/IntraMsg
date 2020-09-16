package com.example.tomeu.intramsg.databaseObjects;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomeu on 09/02/2017.
 */

public class Missatge extends SugarRecord implements JsonParseable{
    private int idmiss;
    private int emplorigen;
    private String datorigen;
    private int empldesti;
    private String datdesti;
    private String asusmpte;
    private String missatge;

    public Missatge() {
    }

    public int getIdmiss() {
        return idmiss;
    }

    public void setIdmiss(int idmiss) {
        this.idmiss = idmiss;
    }

    public int getEmplorigen() {
        return emplorigen;
    }

    public void setEmplorigen(int emplorigen) {
        this.emplorigen = emplorigen;
    }

    public String getDatorigen() {
        return datorigen;
    }

    public void setDatorigen(String datorigen) {
        this.datorigen = datorigen;
    }

    public int getEmpldesti() {
        return empldesti;
    }

    public void setEmpldesti(int empldesti) {
        this.empldesti = empldesti;
    }

    public String getDatdesti() {
        return datdesti;
    }

    public void setDatdesti(String datdesti) {
        this.datdesti = datdesti;
    }

    public String getAsusmpte() {
        return asusmpte;
    }

    public void setAsusmpte(String asusmpte) {
        this.asusmpte = asusmpte;
    }

    public String getMissatge() {
        return missatge;
    }

    public void setMissatge(String missatge) {
        this.missatge = missatge;
    }

    @Override
    public void updateFromJson(JSONObject json) throws JSONException {
        this.emplorigen = json.getInt("emplorigen_");
        this.empldesti = json.getInt("empldesti_");
        this.datorigen = json.getString("datorigen");
        this.datdesti = json.getString("datdesti");
        this.asusmpte = json.getString("assumpte");
        this.missatge = json.getString("missatge");
    }
}
