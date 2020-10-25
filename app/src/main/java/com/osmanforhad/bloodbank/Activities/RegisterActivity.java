package com.osmanforhad.bloodbank.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.osmanforhad.bloodbank.R;
import com.osmanforhad.bloodbank.Utils.Endpoints;
import com.osmanforhad.bloodbank.Utils.VolleySingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                 * checking user submitted data
                 */
                if (isValid(name, city, blood_group, password, mobile)) {
                    /**
                     * calling the register method and
                     * pass the param
                     */
                    register(name, city, blood_group, password, mobile);
                }//end of the if condition

            }//end of the onClick method
        });//end of the setOnClickListener
    }//end of the onCreate

    /**
     * method for connecting with server
     */
    private void register(final String name, String city, String blood_group, String password, String mobile) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Endpoints.register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("Success")) {
                    /**
                     * to display message
                     */
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                    /**
                     * for go to next screen
                     * and
                     * stop the current screen
                     */
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                } else {
                    /**
                     * to display message
                     */
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /**
                 * to display message
                 */
                Toast.makeText(RegisterActivity.this, "Something went wrong:", Toast.LENGTH_SHORT).show();
                Log.d("VOLLEY", error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("city", city);
                params.put("blood_group", blood_group);
                params.put("password", password);
                params.put("mobile", mobile);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }//end of the register method

    /**
     * verify the user input is valid or not
     */
    private boolean isValid(String name, String city, String blood_group, String password, String mobile) {
        /**
         * define blood groups
         * */
        List<String> valid_blood_groups = new ArrayList<>();
        valid_blood_groups.add("A+");
        valid_blood_groups.add("A-");
        valid_blood_groups.add("B+");
        valid_blood_groups.add("B-");
        valid_blood_groups.add("AB+");
        valid_blood_groups.add("AB-");
        valid_blood_groups.add("O+");
        valid_blood_groups.add("O-");
        /**
         *user input validation
         */

        if (name.isEmpty()) {
            showMessage("Name is empty.");
            return false;
        } else if (city.isEmpty()) {
            showMessage("City is required.");
            return false;
        } else if (!valid_blood_groups.contains(blood_group)) {
            showMessage("Blood group is invalid please choose from" + valid_blood_groups);
            return false;
        } else if (mobile.length() != 11) {
            showMessage("Invalid mobile number, number should be of 11 digits.");
            return false;
        } else if (password.isEmpty()) {
            showMessage("Password is required.");
            return false;
        }
        return true;
    }//end of the isValid method


    private void showMessage(String msg) {
/**
 * display message
 */
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }//end of the showMessage method

}//end of the RegisterActivity class