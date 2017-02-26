package com.hackingbuzz.database2loginsignup1.activites.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hackingbuzz.database2loginsignup1.activites.helpers.InputValidation;
import com.hackingbuzz.database2loginsignup1.activites.sql.DatabaseHelper;
import com.hackingbuzz.databaseloginsignup.R;


public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    // First thing when you run this program run your app.. and create an admin account with email  (admin@gmail.com)
    // so that you can jump to UserScreen activity when you can see record of all the users....

    private final AppCompatActivity activity = LoginScreen.this;
    EditText et_email;
    EditText et_password;
    TextView tv_signup;
    Button btn_login;
    InputValidation inputValidation;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        initView();
        initListeners();
        initObjects();
    }

    private void initObjects() {

        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
    }

    private void initListeners() {

        btn_login.setOnClickListener(this);
        tv_signup.setOnClickListener(this);
    }

    private void initView() {

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        tv_signup = (TextView) findViewById(R.id.tv_signup);
         btn_login = (Button) findViewById(R.id.btn_login);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login:
                verifyFromSQLite();
                break;

            case R.id.tv_signup:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterScreen.class);
                startActivity(intentRegister);
                break;

    }
}
    // inputValidation is object and accessing its boolean method

    private void verifyFromSQLite() {

        if (!inputValidation.isEditTextFilled(et_email, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isEditTextFilled(et_password, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isEditTextEmail(et_email, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(et_email.getText().toString().trim()
                , et_password.getText().toString().trim())) {


  

            Intent accountsIntent = new Intent(activity, LeftSlideNavigation.class);
           
            emptyInputEditText();
            startActivity(accountsIntent);


        } else {
          //  Toast.makeText(getApplicationContext(), "Wrong Email or Password ",Toast.LENGTH_SHORT).show();
        }


    }

// empty the editText fields after clicking on register or login button ..

    private void emptyInputEditText() {
        et_email.setText(null);
        et_password.setText(null);
    }
    }
