package com.example.tomeu.intramsg.databaseObjects;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomeu on 09/02/2017.
 */
public class Empleat extends SugarRecord implements JsonParseable {
    private int idempl;
    private String nomempl;
    private String email;
    private String foto;
    private String baixa;
    private String passwd;
    private String user;

    public Empleat() {
    }

    public int getIdempl() {
        return idempl;
    }

    public void setIdempl(int idempl) {
        this.idempl = idempl;
    }

    public String getNomempl() {
        return nomempl;
    }

    public void setNomempl(String nomempl) {
        this.nomempl = nomempl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getBaixa() {
        return baixa;
    }

    public void setBaixa(String baixa) {
        this.baixa = baixa;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public void updateFromJson(JSONObject json) throws JSONException {
        this.nomempl = json.getString("nomempl");
        this.email = json.getString("email");
        this.foto = json.getString("foto");
        this.baixa = json.getString("baixa");
        this.user = json.getString("user");
    }


}
