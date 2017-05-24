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

public class CadastroUsuarios extends AppCompatActivity {

    private static final String URL = "http://projetosfranca.com/cadastro_criogenia.php";
    public static final String USUARIO = "usuario";
    public static final String SENHA = "senha";
    public static final String NOME = "nome";
    public static final String SOBRENOME = "sobrenome";
    public static final String TELEFONE = "telefone";
    public static final String CPF = "cpf";
    public static final String RG = "rg";
    public static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_usuarios);
    }

    public void SendData(View v)
    {
        final EditText user=(EditText) findViewById(R.id.user);
        final EditText senha=(EditText) findViewById(R.id.senha);
        final EditText nome=(EditText) findViewById(R.id.nome);
        final EditText sobrenome=(EditText) findViewById(R.id.sobrenome);
        final EditText telefone=(EditText) findViewById(R.id.telefone);
        final EditText email=(EditText) findViewById(R.id.email);
        final EditText cpf=(EditText) findViewById(R.id.cpf);
        final EditText rg=(EditText) findViewById(R.id.rg);


        final String User = user.getText().toString().trim();
        final String Senha = senha.getText().toString().trim();
        final String Nome = nome.getText().toString().trim();
        final String Sobrenome = sobrenome.getText().toString().trim();
        final String Telefone = telefone.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        final String Cpf = cpf.getText().toString().trim();
        final String Rg = rg.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        Toast.makeText(CadastroUsuarios.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CadastroUsuarios.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(USUARIO,User);
                params.put(SENHA,Senha);
                params.put(NOME,Nome);
                params.put(SOBRENOME, Sobrenome);
                params.put(TELEFONE, Telefone);
                params.put(EMAIL,Email);
                params.put(CPF,Cpf);
                params.put(RG, Rg);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
