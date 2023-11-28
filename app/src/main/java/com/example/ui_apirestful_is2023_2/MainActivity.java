package com.example.ui_apirestful_is2023_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity
        extends AppCompatActivity
        implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Login(View view){
        EditText txtusr = (EditText)findViewById(R.id.txtUsr);
        EditText txtclave = (EditText)findViewById(R.id.txtClave);

        //https://revistas.uteq.edu.ec/ws/login.php?usr=cristian&pass=123
        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtusr.getText().toString() +
                     "&pass=" + txtclave.getText().toString();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url, datos,
                MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtResp = (TextView)findViewById(R.id.txtRespuesta);
        txtResp.setText(result);
    }


    public void LoginWithVolley(View view){
        RequestQueue queue = Volley.newRequestQueue(this);
        EditText txtusr = (EditText)findViewById(R.id.txtUsr);
        EditText txtclave = (EditText)findViewById(R.id.txtClave);
        String url = "https://revistas.uteq.edu.ec/ws/login.php?usr=" + txtusr.getText().toString() +
                "&pass=" + txtclave.getText().toString();
        TextView txtResp = (TextView)findViewById(R.id.txtRespuesta);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtResp.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtResp.setText("Error " + error.getMessage());
                    }
                });
        queue.add(stringRequest);

    }
}