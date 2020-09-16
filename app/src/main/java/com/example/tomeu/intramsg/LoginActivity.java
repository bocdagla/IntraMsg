package com.example.tomeu.intramsg;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.tomeu.intramsg.clientApi.APILogin;
import com.example.tomeu.intramsg.clientApi.VolleyResponseHandler;
import com.example.tomeu.intramsg.utils.Preferencies;

import org.json.JSONObject;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity{

    // UI references.
    private EditText mUsername;
    private EditText mPasswordView;
    private Button mEntraBoto;
    private APILogin login;
    private Preferencies pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = new Preferencies(getApplicationContext());

        mUsername = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEntraBoto = (Button) findViewById(R.id.email_sign_in_button);

        login = new APILogin(
            getApplicationContext()
        );
        activaEventsBoto();
    }

    public void activaEventsBoto(){
        mEntraBoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mUsername.getText().toString();
                String password = mPasswordView.getText().toString();
                login.entra(username, password, getLoginHandler());
            }
        });
    }

    private void loguea(){
        Intent intent =  new Intent(getApplicationContext(), MainMissatges.class);
        startActivity(intent);
    }

    /***
     * S'encarrega de sa resposta de es fil que fa es login
     * @return Un handler per a el login
     */
    private VolleyResponseHandler getLoginHandler(){
        return new VolleyResponseHandler() {
            @Override
            public void success(JSONObject response) {
                try {
                    JSONObject result = response.getJSONObject("result");
                    if(response.getBoolean("response")){
                        pref.setToken(result.getString("token"));
                        pref.setIdempleat(Integer.parseInt(result.getString("id")));
                        loguea();
                    }
                    else{
                        Toast.makeText(
                                getApplicationContext(),
                                "El seu usuari o contrasenya son incorrectes",
                                Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Hi ha habut un error amb la conexió amb el servidor de login",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(VolleyError error) {
                Toast.makeText(
                        getApplicationContext(),
                        "Hi ha habut un error amb la conexió amb el servidor de login",
                        Toast.LENGTH_LONG).show();
            }
        };
    }
}






