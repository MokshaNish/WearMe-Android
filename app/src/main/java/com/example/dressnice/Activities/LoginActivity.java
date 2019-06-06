package com.example.dressnice.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.SharedPreferenceMgr;
import com.example.dressnice.Model.Cart;
import com.example.dressnice.Model.User;
import com.example.dressnice.R;
import com.example.dressnice.Services.CartService;
import com.example.dressnice.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button loginbutton, signup_button;
    EditText userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.usernametext);
        password = (EditText) findViewById(R.id.passwordtext);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        signup_button = (Button) findViewById(R.id.btnRegiser);

    }

    public void login(View view) {

        String email = userName.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if (email.isEmpty()) {
            userName.setError("Email is required");
            userName.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userName.setError("Enter a valid email");
            userName.requestFocus();
            return;
        }


        if (pwd.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if (pwd.length() < 6) {
            password.setError("Password should be at least 6 characters long");
            password.requestFocus();
            return;
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(pwd);

        UserService userService = APICLIENT.getClient().create(UserService.class);
        Call<User> call = userService.authUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {

                    User user = response.body();

                    SharedPreferences sharedPreferences = SharedPreferenceMgr.getSharedPrefs(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("userId", user.getId());
                    editor.putString("email", user.getEmail());
                    editor.commit();

                    getCart(user.getId());
                    Intent intent = new Intent(getApplicationContext(), ProductList.class);
                    startActivity(intent);


                } else {
//                    Log.d()
                    System.out.println(" You are not an regisster person" + response);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }


    public void Register(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void getCart(int userId) {

        CartService cartService = APICLIENT.getClient().create(CartService.class);
        Call<Cart> call = cartService.getCartByUserId(userId);

        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {

                    Cart cart = response.body();

                    SharedPreferences sharedPreferences = SharedPreferenceMgr.getSharedPrefs(getApplicationContext());

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("cartId", cart.getId());
                    editor.commit();

                } else {
//                    Log.d()
                    System.out.println("Cart is not found" + response);
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }


}
