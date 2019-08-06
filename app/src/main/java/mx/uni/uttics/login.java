package mx.uni.uttics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    private TextView domain,user,pass,mail,title,sub,delusr;
    private EditText ed_dom,ed_usr,ed_psw,ed_mail;
    private LinearLayout rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //import font
        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(), "fonts/Vidaloka.ttf");

        //set reference
        domain = (TextView) findViewById(R.id.txtDomain);
        user = (TextView) findViewById(R.id.txtUser);
        pass = (TextView) findViewById(R.id.txtPass);
        mail = (TextView) findViewById(R.id.txtMail);
        title = (TextView) findViewById(R.id.titlepage);
        sub = (TextView) findViewById(R.id.subtitlepage);
        delusr = (TextView) findViewById(R.id.delUser);

        ed_dom = (EditText) findViewById(R.id.ed_domain);
        ed_usr = (EditText) findViewById(R.id.ed_user);
        ed_psw = (EditText) findViewById(R.id.ed_pass);
        ed_mail = (EditText) findViewById(R.id.ed_mail);

        rel = (LinearLayout) findViewById(R.id.linearLayout);

        //customize font
        domain.setTypeface(Vidaloka);
        user.setTypeface(Vidaloka);
        pass.setTypeface(Vidaloka);
        mail.setTypeface(Vidaloka);
        title.setTypeface(Vidaloka);
        sub.setTypeface(Mlight);
        delusr.setTypeface(Mlight);

        //DELETE SSL (DONT USE IN PRODUCTION) - is very vulnerable -
         new NukeSSLCerts().nuke();

        //button accion
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //void inputs
                CheckInputs();
                //insert users
                SendUser();
            }
        });

        //click text delete user
        delusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(login.this, DelUser.class);
                startActivity(myIntent);
            }
        });

    }

    public void CheckInputs(){
        //value string format
        String ed_dm = ed_dom.getText().toString().trim();
        String ed_us = ed_usr.getText().toString().trim();
        String ed_ps = ed_psw.getText().toString().trim();
        String ed_ml = ed_mail.getText().toString().trim();

        //Check inputs value
        if(ed_dm.isEmpty() || ed_us.length() == 0 || ed_ps.equals("") || ed_ml == null)
        {
            //EditText is empty
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Mensaje");
            builder.setMessage("Por favor ingresa todos los datos");
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Hacer cosas aqui al hacer clic en el boton de aceptar
                }
            });
            builder.show();
        }
        else
        {
            //EditText is not empty
        }
    }

    public void SendUser(){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsonObjRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.base_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = response.trim();
                        Snackbar.make(rel, "Info:" + response, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        delusr.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("volley", "Error: " + error.getMessage());
                       // error.printStackTrace();
                         Snackbar.make(rel, "Error:" + error.getMessage(), Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }) {

            @Override
            public String getBodyContentType() {

                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                String contentName = ed_usr.getText().toString();
                String contentPass = ed_psw.getText().toString();
                String contentEmail = ed_mail.getText().toString();
                String contentDomain = ed_dom.getText().toString();

                params.put("key","N3Kusp5VuL1ww3JuL9ZGBrb4MnsF4dKEGcvsZxZZwCdyo");
                params.put("action","add");
                params.put("domain",contentDomain);
                params.put("user",contentName);
                params.put("pass",contentPass);
                params.put("email",contentEmail);
                params.put("package","default");
                params.put("inode","0");
                params.put("limit_nproc","40");
                params.put("limit_nofile","0");
                params.put("server_ips","187.188.191.103");
                return params;
            }

        };
        queue.add(jsonObjRequest);
    }

}
