package com.example.dressnice.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;


import com.example.dressnice.Adapters.ProductAdapter;
import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Model.Product;
import com.example.dressnice.R;
import com.example.dressnice.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ActionBar toolbar;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        init();
    }

    private void init() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        products = new ArrayList<>();
        adapter = new ProductAdapter(products, this);
        recyclerView.setAdapter(adapter);
        loadRecylerViewData();
    }

    private void loadRecylerViewData() {


        ProductService productService = APICLIENT.getClient().create(ProductService.class);
        Call<List<Product>> call = productService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    products.clear();
                    products = response.body();
                    adapter.setProducts(products);
                    adapter.notifyDataSetChanged();
                } else {
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                System.out.println(t.getMessage());

            }


        });

    }

}
