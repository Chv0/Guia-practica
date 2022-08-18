package com.example.webservice_retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.webservice_retrofit.Interface.Api_coments;
import com.example.webservice_retrofit.Model.post_Coments;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tvComents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvComents = findViewById(R.id.txtInfo);
        tvComents.setMovementMethod(new ScrollingMovementMethod());
        getPosts();
    }

    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_coments api_coments = retrofit.create(Api_coments.class);

        Call<List<post_Coments>> call = api_coments.getPosts();

        call.enqueue(new Callback<List<post_Coments>>() {
            @Override
            public void onResponse(Call<List<post_Coments>> call, Response<List<post_Coments>> response) {
                if(!response.isSuccessful()){
                    tvComents.setText("Codigo: "+response.code());
                    return;
                }

                List<post_Coments> coments_List = response.body();

                for(post_Coments coments: coments_List){
                    String content = "";
                    content += "postId: "+ coments.getPostID() + "\n";
                    content += "Id: "+ coments.getId() + "\n";
                    content += "Name: "+ coments.getName() + "\n";
                    content += "Email: "+ coments.getEmail() + "\n";
                    content += "Body: "+ coments.getBody() + "\n\n";

                    tvComents.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<post_Coments>> call, Throwable t) {
                tvComents.setText(t.getMessage());
            }
        });
    }

/*
    public void btnEnviar(View view) {
        //Creamos el Intent
        Intent intent = new Intent(MainActivity.this, act_Validar_Login.class);
        EditText txtNombre = (EditText) findViewById(R.id.txtUsr);
        EditText txtPass = (EditText) findViewById(R.id.txtPass);

        //Creamos la información a pasar entre actividades - Pares Key-Value
        Bundle b = new Bundle();
        b.putString("usr", txtNombre.getText().toString());
        b.putString("pass", txtPass.getText().toString());

        //Añadimos la información al intent
        intent.putExtras(b);

        // Iniciamos la nueva actividad
        startActivity(intent);
    }
*/
}