package com.example.tomeu.intramsg.utils;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Tomeu on 10/02/2017.
 */
public class Preferencies {
    private static final String NOM_PREFERENCIES = "PreferenciesIntraMsg";
    private final static String CLAU_IDEMPL="idempl";
    private final static String CLAU_USER="user";
    private final static String CLAU_PASSWD="passwd";
    private final static String PASSWORD_VALIDAT="passwdvalidat";
    private final static String XARXA_OK="xarxaok";
    private final static String TOKEN="token";

    private int idempleat;
    private String user;
    private String password;
    private boolean passwordvalidat;
    private boolean xarxaok;
    private SharedPreferences prefs;
    private String token;

    public Preferencies(Context ctx) {
        this.prefs = ctx.getSharedPreferences(NOM_PREFERENCIES, ctx.MODE_PRIVATE);
        this.idempleat = this.prefs.getInt(CLAU_IDEMPL, -1);
        this.user = this.prefs.getString(CLAU_USER, "");
        this.password = this.prefs.getString(CLAU_PASSWD, "");
        this.passwordvalidat=this.prefs.getBoolean(PASSWORD_VALIDAT,false);
        this.xarxaok=this.prefs.getBoolean(XARXA_OK,false);
    }

    public int getIdempleat() {
        return idempleat;
    }

    public void setIdempleat(int idempleat) {
        this.idempleat = idempleat;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putInt(CLAU_IDEMPL,idempleat);
        editor.commit();
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putString(CLAU_USER,user);
        editor.commit();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putString(CLAU_PASSWD,password);
        editor.commit();
    }

    public boolean isPasswordvalidat() {
        return passwordvalidat;
    }

    public void setPasswordvalidat(boolean passwordvalidat) {
        this.passwordvalidat = passwordvalidat;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putBoolean(PASSWORD_VALIDAT,passwordvalidat);
        editor.commit();
    }

    public boolean isXarxaok() {
        return xarxaok;
    }

    public void setXarxaok(boolean xarxaok) {
        this.xarxaok = xarxaok;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putBoolean(XARXA_OK,xarxaok);
        editor.commit();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        SharedPreferences.Editor editor = this.prefs.edit();
        editor.putString(TOKEN,token);
        editor.commit();

    }
}
