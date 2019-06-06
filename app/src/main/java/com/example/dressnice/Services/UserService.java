package com.example.dressnice.Services;

import com.example.dressnice.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("user")
    Call<User> saveUserDetails(@Body User user);

    @POST("user/auth")
    Call<User> authUser(@Body User user);
}
