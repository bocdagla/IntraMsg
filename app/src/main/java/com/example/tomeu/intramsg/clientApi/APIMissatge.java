package com.example.tomeu.intramsg.clientApi;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.tomeu.intramsg.databaseObjects.Log;
import com.example.tomeu.intramsg.databaseObjects.Missatge;
import com.example.tomeu.intramsg.utils.DBClient;
import com.example.tomeu.intramsg.utils.JsonMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Moanrat on 13/03/2017.
 */

public class APIMissatge {
    private Context context;

    public APIMissatge(Context context){
        this.context = context;
    }

    public void envia(int emplOrigen, int emplDesti, String assumpte, String missatge, VolleyResponseHandler handler ){

        HashMap<String, String> json = new HashMap<>();
        json.put(Config.EMPLORIGEN, Integer.toString(emplOrigen));
        json.put(Config.EMPLDESTI, Integer.toString(emplDesti));
        json.put(Config.ASSUMPTE, assumpte);
        json.put(Config.MISSATGE, missatge);
        JSONObject peticio = new JSONObject(json);


        final VolleyResponseHandler messageHandler = handler;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, Config.SEND_MISSATGES_URL, peticio,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                messageHandler.success(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                messageHandler.failure(error);
                            }
                        }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

    public void actualitzaBdd(int empl, VolleyResponseHandler handler){
        final VolleyResponseHandler hnd = handler;
        Missatge darrer = DBClient.selectLastMissatge();
        int lastId = 0;
        if(darrer != null){
            lastId = darrer.getIdmiss();
        }
        String urlLog = String.format(Config.GET_MISSATGES, Integer.toString(empl), Integer.toString(lastId));
        JsonObjectRequest usuarisRequest = new JsonObjectRequest
                (Request.Method.GET, urlLog, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray resultList= response.getJSONArray("result");
                                    Missatge mis = null;
                                    for(int i = 0; i < resultList.length(); i++){
                                        mis = JsonMapper.mapMissatge((JSONObject) resultList.get(i));
                                        mis.save();
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
}
