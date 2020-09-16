package com.example.tomeu.intramsg.clientApi;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Tomeu on 09/02/2017.
 */

public class APILogin {
    private Context context;
    public APILogin(Context context){
        this.context = context;
    }

    public void entra(String username, String password, VolleyResponseHandler handler ){

        HashMap<String, String> json = new HashMap<>();
        json.put(Config.USERNAME, username);
        json.put(Config.PASSWORD, password);
        JSONObject peticio = new JSONObject(json);
        final VolleyResponseHandler loginHandler = handler;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
        (Request.Method.POST, Config.LOGIN_URL, peticio,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        loginHandler.success(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loginHandler.failure(error);
                    }
                }
        );

        MySingleton.getInstance(context).addToRequestQueue(jsObjRequest);
    }

}
