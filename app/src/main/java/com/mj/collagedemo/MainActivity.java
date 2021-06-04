package com.mj.collagedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String sEmail, sPassword;
    TextView textViewEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        sEmail = intent.getStringExtra("EMAIL");
        sPassword = intent.getStringExtra("PASSWORD");

        textViewEmail = findViewById(R.id.tv_email);
        textViewEmail.setText(sEmail);
    }
}