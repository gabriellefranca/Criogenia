package com.example.gabi2.criogenia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CadastroServicos extends AppCompatActivity {

    private static final String URL = "http://projetosfranca.com/cadastro_servicos.php";
    public static final String NOME = "Nome";
    public static final String DURACAO = "Tempo_Duracao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_servicos);
    }

    public void SendData(View v)
    {
        final EditText nome=(EditText) findViewById(R.id.nome);
        final EditText duracao=(EditText) findViewById(R.id.duracao);

        final String Nome = nome.getText().toString().trim();
        final String Duracao = duracao.getText().toString().trim();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(CadastroServicos.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroServicos.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put(NOME,Nome);
                params.put(DURACAO,Duracao);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
