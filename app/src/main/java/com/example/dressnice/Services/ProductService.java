package com.example.dressnice.Services;

import com.example.dressnice.Model.Product;

import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProductService {


    @GET("products/all")
    Call<List<Product>> getProducts();

    @GET("/{id}")
    Call <Product> getProduct(@Path("id") int id);


    @POST("/all")
    Call <Product> add(@Body Product product);
    }
