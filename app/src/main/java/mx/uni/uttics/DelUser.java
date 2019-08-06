package mx.uni.uttics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class DelUser extends AppCompatActivity {

    private EditText ed_del;
    private TextView nameusr,backusr,tl,sb;
    private LinearLayout lin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_del_user);

        //DELETE SSL (DONT USE IN PRODUCTION) - is very vulnerable -
        new NukeSSLCerts().nuke();

        //import font
        Typeface Mlight = Typeface.createFromAsset(getAssets(), "fonts/MLight.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(),"fonts/MMedium.ttf");
        Typeface Vidaloka = Typeface.createFromAsset(getAssets(), "fonts/Vidaloka.ttf");

        //set reference
        ed_del = (EditText) findViewById(R.id.ed_name_user);
        nameusr = (TextView) findViewById(R.id.txtNameUser);
        tl = (TextView) findViewById(R.id.titlepage);
        sb = (TextView) findViewById(R.id.subtitlepage);
        backusr = (TextView) findViewById(R.id.backUser);
        lin = (LinearLayout) findViewById(R.id.linLayout);

        //customize font
        nameusr.setTypeface(Vidaloka);
        tl.setTypeface(Vidaloka);
        backusr.setTypeface(MMedium);
        sb.setTypeface(MMedium);

        //button accion
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //Delete user
                    DeleteUser();
            }
        });

        //click back user
        backusr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(DelUser.this, login.class);
                startActivity(myIntent);
            }
        });
    }

    private void DeleteUser() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest jsonObjRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.base_url),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Snackbar.make(lin, "Éxito:" + response, Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                        backusr.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("volley", "Error: " + error.getMessage());
                        error.printStackTrace();
                        Snackbar.make(lin, "Error:" + error.getMessage(), Snackbar.LENGTH_LONG)
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
                String contentName = ed_del.getText().toString();

                params.put("key","N3Kusp5VuL1ww3JuL9ZGBrb4MnsF4dKEGcvsZxZZwCdyo");
                params.put("action","del");
                params.put("user",contentName);
                return params;
            }

        };
        queue.add(jsonObjRequest);
    }
}
