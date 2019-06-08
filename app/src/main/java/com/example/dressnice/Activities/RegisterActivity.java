package com.example.dressnice.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.SharedPreferenceMgr;
import com.example.dressnice.Model.Product;
import com.example.dressnice.Model.User;
import com.example.dressnice.R;
import com.example.dressnice.Services.ProductService;
import com.example.dressnice.Services.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    EditText email, firstName, lastName, password, contactNo;
    Button join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void JoinWearMe(View view) {

        email = (EditText) findViewById(R.id.editTextemail);
        firstName = (EditText) findViewById(R.id.editTextfirstname);
        lastName = (EditText) findViewById(R.id.editTextLastName);
        password = (EditText) findViewById(R.id.editTextpassword);
        contactNo = (EditText) findViewById(R.id.editTextContactNumber);
        join = (Button) findViewById(R.id.buttonjoin);

        String fname = firstName.getText().toString().trim();  //getText() get the content inside editText & toString() convert it to string format.
        String lname = lastName.getText().toString().trim();
        String emailAddress = email.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String contactNumber = contactNo.getText().toString().trim();

        validateRegister(fname, lname, emailAddress, pwd, contactNumber);
    }

    private void validateRegister(String fname, String lname, String emailAddress, String pwd, String contactNumber) {

        if (fname.isEmpty() || lname.isEmpty() || emailAddress.isEmpty() || pwd.isEmpty() || contactNumber.isEmpty()) {
            Toast.makeText(this, "Empty fields ", Toast.LENGTH_SHORT).show();
        } else {

            User user = new User();
            user.setFirstName(fname);
            user.setLastName(lname);
            user.setEmail(emailAddress);
            user.setPassword(pwd);
            user.setContactNo(contactNumber);

            UserService userService = APICLIENT.getClient().create(UserService.class);
            Call<User> call = userService.saveUserDetails(user);

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.isSuccessful()) {

                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_SHORT);
                    } else {
                        Toast.makeText(getApplicationContext(), "Unsuccessfully registered", Toast.LENGTH_SHORT);

                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }
}

