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

import java.io.ObjectInputValidation;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private  final AppCompatActivity activity = RegisterActivity.this;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutPhone;
    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextPhone;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initViews();
        initListeners();
        initObjects();

    }



    private void initViews() {
        textInputLayoutName = findViewById(R.id.textInputLayoutName);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPhone = findViewById(R.id.textInputLayoutPhone);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextName = findViewById(R.id.textInputEditTextName);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPhone = findViewById(R.id.textInputEditTextPhone);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);

        appCompatButtonRegister = findViewById(R.id.appCompatButtonRegister);
        appCompatTextViewLoginLink = findViewById(R.id.appCompatTextViewLoginLink);

    }


    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                break;
            case R.id.appCompatTextViewLoginLink:
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    // To validate the user inputs.
    private void postDataToSQLite() {
        if(!inputValidation.isInputEditTextFilled(textInputEditTextName,
                textInputLayoutName,"Enter Full Name")){
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextEmail,
                textInputLayoutEmail,"Enter Email !!")){
            return;
        }

        if(!inputValidation.isInputEditTextEmail(textInputEditTextEmail,
                textInputLayoutEmail,"Enter Valid Email")){
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextPhone,
                textInputLayoutPhone,"Enter Phone Number")){
            return;
        }

        if(!inputValidation.isInputEditTextFilled(textInputEditTextPassword,
                textInputLayoutPassword,"Enter Password")){
            return;
        }

        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());
            user.setPhone(textInputEditTextPhone.getText().toString().trim());
            databaseHelper.addUser(user);

            Toast.makeText(getApplicationContext() , "Registration Sucessfull",
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            emptyInputEditText();
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext() , "Email Already Registered",
                    Toast.LENGTH_SHORT).show();
        }





    }

    private void emptyInputEditText() {

        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextPhone.setText(null);
    }
}