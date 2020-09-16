package com.example.tomeu.intramsg.clientApi;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by Tomeu on 09/02/2017.
 */

public interface VolleyResponseHandler {
    void success(JSONObject response);
    void failure(VolleyError error);
}
