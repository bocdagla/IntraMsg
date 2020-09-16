package com.example.tomeu.intramsg.databaseObjects;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomeu on 09/02/2017.
 */

public class Log extends SugarRecord implements JsonParseable{
    private int idlog;
    private String taula;
    private String operacio;
    private int id1;
    private int id2;
    private String datetime;

    public Log(){
    }

    public int getIdlog() {
        return idlog;
    }

    public void setIdlog(int idlog) {
        this.idlog = idlog;
    }

    public String getTaula() {
        return taula;
    }

    public void setTaula(String taula) {
        this.taula = taula;
    }

    public String getOperacio() {
        return operacio;
    }

    public void setOperacio(String operacio) {
        this.operacio = operacio;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public void updateFromJson(JSONObject json) throws JSONException {
        this.taula = json.getString("taula");
        this.operacio = json.getString("operacio");
        this.id1 = json.getInt("id1");
        this.id2 = json.getInt("id2");
        this.datetime = json.getString("data");
    }
}
