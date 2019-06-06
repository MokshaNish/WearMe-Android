package com.example.dressnice.Services;

import com.example.dressnice.Model.Cart;
import com.example.dressnice.Model.CartForm;
import com.example.dressnice.Model.OrderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CartService {

    @POST("cart/add")
    OrderItem add(@Body CartForm cartForm);

    @GET("cart/user-id/{userId}")
    Call<Cart> getCartByUserId(@Path("userId") int userId);

    @GET("cart/all")
    Call <List<OrderItem>> getAllCartItems();

}
