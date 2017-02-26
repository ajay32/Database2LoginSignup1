package com.hackingbuzz.database2loginsignup1.activites.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hackingbuzz.database2loginsignup1.activites.helpers.InputValidation;
import com.hackingbuzz.database2loginsignup1.activites.model.User;
import com.hackingbuzz.database2loginsignup1.activites.sql.DatabaseHelper;
import com.hackingbuzz.databaseloginsignup.R;


public class RegisterScreen extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterScreen.this;

    InputValidation inputValidation;
    DatabaseHelper databaseHelper;

    EditText et_name;
    EditText et_email_reg;
    EditText et_password_reg;
    EditText et_confirm_pass_reg;
    TextView login_reg;
    Button btn_register;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);

        initView();
        initListeners();
        initObjects();
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
         user = new User();

    }

    private void initListeners() {

    btn_register.setOnClickListener(this);
    login_reg.setOnClickListener(this);
    }


    private void initView() {

         et_name = (EditText) findViewById(R.id.et_name);
         et_email_reg = (EditText) findViewById(R.id.et_email_reg);
         et_password_reg = (EditText) findViewById(R.id.et_password_reg);
         et_confirm_pass_reg = (EditText) findViewById(R.id.et_confirm_pass_reg);
         login_reg = (TextView) findViewById(R.id.tv_login_reg);
         btn_register = (Button) findViewById(R.id.btn_register);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_register:
                Log.i("ButtonTapped","Yes");
                postDataToSQLite();
                break;

            case R.id.tv_login_reg:    // see if you want to log in this register activity will be finished n register acitivty will be shown
                finish();
                break;
        }
    }

    private void postDataToSQLite() {


        if (!inputValidation.isEditTextFilled(et_name, getString(R.string.error_message_name))) {
            return;
        }
        if (!inputValidation.isEditTextFilled(et_email_reg, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputValidation.isEditTextFilled(et_password_reg, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isEditTextEmail(et_email_reg,  getString(R.string.error_message_email))) {
            return;
        }

        // matching password filed and confirm password  ..if matches ..n our code will move further...
        if (!inputValidation.isEditTextMatches(et_password_reg, et_confirm_pass_reg, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(et_email_reg.getText().toString().trim())) {

            user.setName(et_name.getText().toString().trim());
            user.setEmail(et_email_reg.getText().toString().trim());
            user.setPassword(et_password_reg.getText().toString().trim());

            databaseHelper.addUser(user);

            Toast.makeText(this, R.string.success_message,Toast.LENGTH_SHORT).show();
            emptyEditText();


        } else {

            Toast.makeText(this, R.string.error_email_exists,Toast.LENGTH_SHORT).show();
        }
    }


    // after sucessfully putting user in database clear text field ..so that user get to know..he successfully created account
    private void emptyEditText() {
        et_name.setText(null);
        et_email_reg.setText(null);
        et_password_reg.setText(null);
        et_confirm_pass_reg.setText(null);
    }
}
