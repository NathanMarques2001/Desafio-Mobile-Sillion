package com.nathan.desafiomobilesillion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Navega para a página principal
    public void navigateToMain(View view) {
        Intent in = new Intent(Home.this, MainActivity.class);
        startActivity(in);
    }
}