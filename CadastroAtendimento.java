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

public class CadastroAtendimento extends AppCompatActivity {

    private static final String URL = "http://projetosfranca.com/cadastro_atendimento.php";
    public static final String NOME= "usuario";
    public static final String SERVICO = "Nome";
    public static final String OBSERVACAO = "Observacao";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_atendimento);
    }

    public void SendData(View v)
    {
        final EditText nome=(EditText) findViewById(R.id.nome);
        final EditText observacao =(EditText) findViewById(R.id.observacao);
        final EditText servico =(EditText) findViewById(R.id.servico);


        final String Nome = nome.getText().toString().trim();
        final String Servico = servico.getText().toString().trim();
        final String Observacao = observacao.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(CadastroAtendimento.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroAtendimento.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(NOME,Nome);
                params.put(SERVICO,Servico);
                params.put(OBSERVACAO,Observacao);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
