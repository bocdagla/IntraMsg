package com.example.tomeu.intramsg.databaseObjects;

import com.orm.SugarRecord;
import com.orm.dsl.MultiUnique;

import org.json.JSONObject;

/**
 * Created by Tomeu on 09/02/2017.
 */

public class Pertany extends SugarRecord implements JsonParseable{

    private int idempl;
    private int iddep;

    public Pertany() {
    }

    public Pertany(int _idempl_, int _iddep_) {
        this.idempl = _idempl_;
        this.iddep = _iddep_;
    }

    public int getIdempl() {
        return idempl;
    }

    public void setIdempl(int idempl) {
        this.idempl = idempl;
    }

    public int getIddep() {
        return iddep;
    }

    public void setIddep(int iddep) {
        this.iddep = iddep;
    }

    @Override
    public void updateFromJson(JSONObject json) {

    }
}
