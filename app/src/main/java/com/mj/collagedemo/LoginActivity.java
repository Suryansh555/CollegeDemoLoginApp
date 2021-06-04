package com.mj.collagedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
        initListners();
        initObject();
    }

    private void initObject() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    private void initListners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initViews() {

        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = findViewById(R.id.textViewLinkRegister);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void verifyFromSQLite() {


        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,
                "Enter Email")) {
            return;
        }

        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail,
                textInputLayoutEmail, "Enter Valid Email")) {
            return;
        }

        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword,
                textInputLayoutPassword, "Enter  Password"))
        {
            return;
        }
        if (databaseHelper.loginUser(textInputEditTextEmail.getText().toString().trim(),
                textInputEditTextPassword.getText().toString().trim())) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            intent.putExtra("PASSWORD", textInputEditTextPassword.getText().toString().trim());

            startActivity(intent);
        } else {

            Toast.makeText(getApplicationContext(), "Wrong Email and Password",
                    Toast.LENGTH_LONG).show();
        }
    }


}