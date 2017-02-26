package com.hackingbuzz.database2loginsignup1.activites.helpers;

import android.app.Activity;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


public class InputValidation {
    private Context context;

   // context just tell you . this class this connected with its main class where context is comming from
    public InputValidation(Context context) {
        this.context = context;
    }


    public boolean isEditTextFilled(EditText editText, String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty()) {
            editText.setError(message);
            hideKeyboardFrom(editText);
        }

        return true;
    }


    public boolean isEditTextEmail(EditText editText,  String message) {
        String value = editText.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            editText.setError(message);
            hideKeyboardFrom(editText);
            return false;
        }
        return true;
    }

    // matching password and confirm password

    public boolean isEditTextMatches(EditText editText1, EditText editText2, String message) {
        String value1 = editText1.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            editText1.setError(message);
            hideKeyboardFrom(editText2);
            return false;
        } else {

        }
        return true;
    }

    // method to Hide keyboard
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
