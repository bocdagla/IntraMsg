package com.example.tomeu.intramsg.databaseObjects;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tomeu on 02/03/2017.
 */

public interface JsonParseable {
    void updateFromJson(JSONObject json) throws JSONException;
}
