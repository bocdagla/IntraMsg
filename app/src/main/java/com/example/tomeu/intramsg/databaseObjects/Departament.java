package com.example.tomeu.intramsg.databaseObjects;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomeu on 09/02/2017.
 */

public class Departament extends SugarRecord implements JsonParseable{
    private int iddep;
    private String nomdep;


    public Departament() {
    }

    public int getIddep() {
        return iddep;
    }

    public void setIddep(int iddep) {
        this.iddep = iddep;
    }

    public String getNomdep() {
        return nomdep;
    }

    public void setNomdep(String nomdep) {
        this.nomdep = nomdep;
    }


    @Override
    public void updateFromJson(JSONObject json) throws JSONException {
        this.nomdep = json.getString("nomdep");
    }


}
