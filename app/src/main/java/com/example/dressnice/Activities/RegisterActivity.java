package com.example.dressnice.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.APICLIENT;
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

    List<Product> products;

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

        String fname = firstName.getText().toString();  //getText() get the content inside editText & toString() convert it to string format.
        String lname = lastName.getText().toString();
        String emailAddress = email.getText().toString();
        String pwd = password.getText().toString();
        String contactNumber = contactNo.getText().toString();


        validateRegister(fname, lname, emailAddress, pwd, contactNumber);

    }

    private void validateRegister(String fname, String lname, String emailAddress, String pwd, String contactNumber) {

//        if (fname.isEmpty() || lname.isEmpty() || emailAddress.isEmpty() || pwd.isEmpty() || contactNumber.isEmpty()) {
//
//            Toast.makeText(this, "Fill the registration form ", Toast.LENGTH_SHORT).show();
//
//
//            String n = fname;
//            String e = lname;
//            String p = emailAddress;
//            String c = pwd;
//            String o = contactNumber;
//
//
//
//            User user = new User(n, e, p, c, o);
//
//            UserService userService = APICLIENT.getClient().create(UserService.class);
//            Call<JSONObject> call = (Call<JSONObject>) userService.saveUserDetails(user);
//
//            call.enqueue(new Callback<JSONObject>() {
//                @Override
//                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                    if (response.isSuccessful()) {
//                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        try {
//                            Toast.makeText(getApplicationContext(), response.body().getString("User registered successfully"), Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e1) {
//                            e1.printStackTrace();
//                        }
//
//                    } else {
//                        try {
//                            Toast.makeText(getApplicationContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
//                        } catch (IOException e) {
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<JSONObject> call, Throwable t) {
//
//                }
//            });
//        }
    }
}

