package com.example.tomeu.intramsg.clientApi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tomeu.intramsg.databaseObjects.Departament;
import com.example.tomeu.intramsg.databaseObjects.Empleat;
import com.example.tomeu.intramsg.databaseObjects.JsonParseable;
import com.example.tomeu.intramsg.databaseObjects.Log;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.databaseObjects.Pertany;
import com.example.tomeu.intramsg.utils.DBClient;
import com.example.tomeu.intramsg.utils.JsonMapper;
import com.orm.SugarRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomeu on 02/03/2017.
 */

public class Sincronitza {

    public static void actualitzaBdd(Context context, VolleyResponseHandler handler){
        final VolleyResponseHandler hnd = handler;
        Log darrerLog = DBClient.selectLastLog();
        int lastId = 0;
        if(darrerLog != null){
            lastId = darrerLog.getIdlog();
        }
        String urlLog = String.format(Config.GET_LOG, Integer.toString(lastId));
        JsonObjectRequest usuarisRequest = new JsonObjectRequest
                (Request.Method.GET, urlLog, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray resultList= response.getJSONArray("result");
                                    JSONObject resultOb = null;
                                    JSONObject data = null;
                                    Log log = null;
                                    boolean hasData = false;
                                    for(int i = 0; i < resultList.length(); i++){
                                        resultOb = (JSONObject) resultList.get(i);
                                        log = JsonMapper.mapLog((JSONObject)resultOb.get("log"));
                                        hasData = resultOb.getBoolean("hasdata");
                                        data = hasData? (JSONObject) resultOb.get("data"): null;
                                        exectutaAccioLog(log, data);
                                        log.save();
                                    }
                                    hnd.success(response);
                                }
                                catch (JSONException e) {
                                    android.util.Log.e("VolleyError", e.getMessage());
                                    hnd.failure(new VolleyError(e));
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                hnd.failure(error);
                            }
                        }
                );
        MySingleton.getInstance(context).addToRequestQueue(usuarisRequest);
    }

    private static void exectutaAccioLog(Log log, JSONObject data) throws JSONException {
        switch (log.getOperacio()){
            case "insert":
                insertaRegistre(log, data);
                break;
            case "update":
                // TOMEEEEEEUUUUUU!!!!!
                if(log.getTaula().equals("missatge")){
                    insertaRegistre(log, data);
                }else{
                    actualizaRegistre(log, data);
                }
                break;
            case "delete":
                borraRegistre(log);
                break;
        }
    }

    private static void insertaRegistre(Log log, JSONObject data) throws JSONException {
        SugarRecord record = null;

        switch (log.getTaula()) {
            case "departament":
                if (data != null){
                    record = JsonMapper.mapDepartament(data);
                }
                break;
            case "empleat":
                if(data != null) {
                    record = JsonMapper.mapEmpleat(data);
                }
                break;
            case "pertany":
                record = new Pertany(log.getId1(), log.getId2());
                break;
            case "missatge":
                if(data != null) {
                    record = JsonMapper.mapMissatge(data);
                }
                break;
        }
        if(record != null){
            record.save();
        }
    }

    private static void actualizaRegistre(Log log, JSONObject data) throws JSONException {
        Pertany aux;
        JsonParseable record = (JsonParseable) DBClient.getRecordFromLog(log);
        if(record != null) {

            if (log.getTaula() == "pertany") {
                aux = (Pertany) record;
                aux.setIddep(log.getId1());
                aux.setIdempl(log.getId2());
                record = aux;
            }
            else {
                record.updateFromJson(data);
            }

            ((SugarRecord) record).save();
        }
    }


    private static void borraRegistre(Log log){
        SugarRecord record = DBClient.getRecordFromLog(log);
        if(record != null){
            record.delete();
        }
    }
}
