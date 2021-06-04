package com.mj.collagedemo;

import android.app.Activity;
import android.content.Context;
import android.provider.ContactsContract;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class InputValidation {

    private  Context context;
    public InputValidation(Context context) {
        this.context = context;
    }

    //EditText Validation
    public boolean isInputEditTextFilled(TextInputEditText textInputEditText,
                                         TextInputLayout textInputLayout,
                                         String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty()){
            textInputLayout.setError(message);
            hidekeyboardFrom(textInputEditText);
            return false;
        }
        else{
        }
        textInputLayout.setErrorEnabled(false);

        return true;
    }

    //Email validation
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText,
                                         TextInputLayout textInputLayout,
                                         String message){
        String value = textInputEditText.getText().toString().trim();
        if(value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()){
            textInputLayout.setError(message);
            hidekeyboardFrom(textInputEditText);
            return false;
        }
        else{
            textInputLayout.setErrorEnabled(false);
        }


        return true;
    }


    private void hidekeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager)
                context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(),
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


}
