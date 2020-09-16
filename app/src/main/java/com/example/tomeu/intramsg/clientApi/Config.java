package com.example.tomeu.intramsg.clientApi;

/**
 * Created by Tomeu on 09/02/2017.
 */

public class Config {
    public static final String BASE_API_URL = "http://iespcasesnoves.esy.es/intramsgapi/public/";

    public static final String LOGIN_URL = BASE_API_URL +  "empleat/login";
    public static final String GET_LOG = BASE_API_URL +  "log/sync/%s";
    public static final String GET_MISSATGES = BASE_API_URL +  "/missatge/syncid/%s/%s";
    public static final String SEND_MISSATGES_URL = BASE_API_URL +  "missatge/save";

    public static final String USERNAME = "user";
    public static final String PASSWORD = "passwd";


    public static final String EMPLORIGEN = "emplorigen";
    public static final String EMPLDESTI = "empldesti";
    public static final String ASSUMPTE = "assumpte";
    public static final String MISSATGE = "missatge";
    public static final String IDDEP = "_iddep";
}
