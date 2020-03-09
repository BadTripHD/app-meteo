package com.example.td4;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView view = (TextView) findViewById(R.id.view);

        String previsionWeather = "https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b6907d289e10d714a6e88b30761fae22";
        final GsonRequest gsonRequest = new GsonRequest(previsionWeather, Previsions.class, null, new
                Response.Listener<Previsions>() {


                    @Override
                    public void onResponse(Previsions previsions) {
                        String resultat = "";
                        for(Prevision prevision : previsions.getPrevisionsArrayList()){
                            resultat += prevision.toString();
                        }
                        Toast.makeText(MainActivity.this, resultat, Toast.LENGTH_LONG).show();
                        view.setText(resultat);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError != null) Log.e("MainActivity", volleyError.getMessage());
            }
        });
        VolleyHelper.getInstance(getApplicationContext()).addToRequestQueue(gsonRequest);
    }


}
