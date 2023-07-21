package com.nathan.desafiomobilesillion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView username, name, age, email;
    private ImageView userImage;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Campos do XML
        username = findViewById(R.id.username);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        email = findViewById(R.id.email);
        userImage = findViewById(R.id.userImage);
        button = findViewById(R.id.button);

        //Adiciona evento de click ao botão
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchUserData();
            }
        });
    }

    private void fetchUserData() {
        OkHttpClient client = new OkHttpClient();
        String url = "https://randomuser.me/api/";

        //Construtor da requisição
        Request request = new Request.Builder().url(url).build();

        //Faz a requisição
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                RandomUser user = getUserData(myResponse);

                                // Atualiza os elementos da interface do usuário (TextViews) com as informações do usuário
                                username.setText(user.getUsername());
                                name.setText("Nome: " + user.getName());
                                age.setText("Idade: " + user.getAge());
                                email.setText("E-mail: " + user.getEmail());

                                // Usa a biblioteca Glide para carregar e exibir a imagem do usuário no ImageView
                                Glide.with(MainActivity.this)
                                        .load(user.getUserImageURL())
                                        .into(userImage);

                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            }
        });
    }

    //Método responsável por navegar no JSON e buscar os dados do usuário
    private RandomUser getUserData(String response) throws JSONException {
        String username = "", name = "", email = "", userImageURL = "";
        int age = 0;

        JSONObject json = new JSONObject(response);
        JSONArray results = json.getJSONArray("results");

        JSONObject userData = results.getJSONObject(0);
        email = userData.getString("email");

        JSONObject jsonName = userData.getJSONObject("name");
        name = jsonName.getString("first") + " " + jsonName.getString("last");

        JSONObject jsonAge = userData.getJSONObject("registered");
        age = jsonAge.getInt("age");
        String strAge = Integer.toString(age);

        JSONObject jsonUsername = userData.getJSONObject("login");
        username = jsonUsername.getString("username");

        JSONObject jsonUserImageURL = userData.getJSONObject("picture");
        userImageURL = jsonUserImageURL.getString("large");

        RandomUser user = new RandomUser(username, name, strAge, email, userImageURL);
        return user;
    }

    //Volta para a tela de apresentação
    public void navigateToHome(View view) {
        Intent in = new Intent(MainActivity.this, Home.class);
        startActivity(in);
    }
}