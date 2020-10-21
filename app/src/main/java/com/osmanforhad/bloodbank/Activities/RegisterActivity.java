package com.osmanforhad.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.osmanforhad.bloodbank.R;

public class RegisterActivity extends AppCompatActivity {
    /**
     * variable declaration
     */
    private EditText nameEt, cityEt, bloodGroupEt, passwordEt, mobileEt;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        /***
         * initial the xml UI
         */
        nameEt = findViewById(R.id.name);
        cityEt = findViewById(R.id.city);
        mobileEt = findViewById(R.id.number);
        bloodGroupEt = findViewById(R.id.blood_group);
        passwordEt = findViewById(R.id.password);
        submitButton = findViewById(R.id.submit_button);

        /**
         * make the button clickable
         */
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * get text from user input
                 */
                String name, city, blood_group, password, mobile;
                name = nameEt.getText().toString();
                city = cityEt.getText().toString();
                mobile = mobileEt.getText().toString();
                blood_group = bloodGroupEt.getText().toString();
                password = passwordEt.getText().toString();
                /**
                 * calling the method and
                 * pass the parameter
                 */
                showMessage(name + "\n" + city + "\n" + mobile + "\n" + blood_group + "\n" + password + "\n");
            }//end of the onClick method
        });//end of the setOnClickListener
    }//end of the onCreate

    private void showMessage(String msg) {
/**
 * display message
 */
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//end of the showMessage method

}//end of the RegisterActivity class