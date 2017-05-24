package com.example.gabi2.criogenia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class VisualizarUsuarios extends AppCompatActivity {
    String[] items;
    public static final String KEY_ID = "id_usuario";
    ArrayList<String> listItems;

    ArrayAdapter<String> adapter;
    public static final String JSON_URL = "http://switchautos.com.br/getAllEmp.php";
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_usuarios);
        listView = (ListView) findViewById(R.id.usuarios);

        sendRequest();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue = (String) listView.getItemAtPosition(position);
                Intent detalhes = new Intent(VisualizarUsuarios.this, Users.class);
                detalhes.putExtra(KEY_ID, itemValue);
                startActivity(detalhes);

                // Show Alert
                //   Toast.makeText(getApplicationContext(), "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG).show();

            }
        });

    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(VisualizarUsuarios.this,error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String json){
        Users pj = new Users(json);
        pj.parseJSON();
        CustomList cl = new CustomList(this, Users.ids,Users.names,Users.emails);
        listView.setAdapter(cl);
    }



}
