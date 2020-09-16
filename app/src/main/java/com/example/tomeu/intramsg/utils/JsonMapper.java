package com.example.tomeu.intramsg.utils;

import com.example.tomeu.intramsg.databaseObjects.Departament;
import com.example.tomeu.intramsg.databaseObjects.Empleat;
import com.example.tomeu.intramsg.databaseObjects.Log;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.databaseObjects.Pertany;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomeu on 02/03/2017.
 */

public class JsonMapper {

    public static Log mapLog(JSONObject ob) throws JSONException {
        Log result = new Log();
        result.setIdlog(ob.getInt("_idlog"));
        result.setTaula(ob.getString("taula"));
        result.setOperacio(ob.getString("operacio"));
        result.setId1(ob.getInt("id"));
        result.setId2(!ob.isNull("id2")? ob.getInt("id2"): 0);
        result.setDatetime(ob.getString("data"));
        return result;
    }

    public static Empleat mapEmpleat(JSONObject ob) throws JSONException {
        Empleat result = new Empleat();
        result.setIdempl(ob.getInt("_idempl"));
        result.setNomempl(ob.getString("nomempl"));
        result.setEmail(ob.getString("email"));
        result.setFoto(ob.getString("foto"));
        result.setBaixa(ob.getString("baixa"));
        result.setUser(ob.getString("user"));
        return result;
    }

    public static Departament mapDepartament(JSONObject ob) throws JSONException {
        Departament result = new Departament();
        result.setIddep(ob.getInt("_iddep"));
        result.setNomdep(ob.getString("nomdep"));
        return result;
    }

    public static Missatge mapMissatge(JSONObject ob) throws JSONException {
        Missatge result = new Missatge();
        result.setIdmiss(ob.getInt("_idmiss"));
        result.setEmplorigen(ob.getInt("emplorigen_"));
        result.setEmpldesti(ob.getInt("empldesti_"));
        result.setDatorigen(ob.getString("datorigen"));
        result.setDatdesti(ob.getString("datdesti"));
        result.setAsusmpte(ob.getString("assumpte"));
        result.setMissatge(ob.getString("missatge"));
        return result;
    }
}
