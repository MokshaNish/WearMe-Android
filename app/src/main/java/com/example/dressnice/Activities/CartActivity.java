package com.example.dressnice.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dressnice.Adapters.CartAdapter;
import com.example.dressnice.Adapters.ProductAdapter;
import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.SharedPreferenceMgr;
import com.example.dressnice.Model.Cart;
import com.example.dressnice.Model.OrderItem;
import com.example.dressnice.Model.Product;
import com.example.dressnice.R;
import com.example.dressnice.Services.CartService;
import com.example.dressnice.Services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private List<OrderItem> orderItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();
        loadRecylerViewData();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.cartRecycle);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        orderItems = new ArrayList<>();
        adapter = new CartAdapter(orderItems, this);
        recyclerView.setAdapter(adapter);
    }

    private void loadRecylerViewData() {

        SharedPreferences prefs = SharedPreferenceMgr.getSharedPrefs(this);
        int cardId = prefs.getInt("cartId", 0);

        CartService cartService = APICLIENT.getClient().create(CartService.class);
        Call<Cart> call = cartService.getCart(cardId);
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    orderItems.clear();
                    Cart cart = response.body();
                    orderItems = cart.getOrderItems();
                    adapter.setOrderItems(orderItems);
                    adapter.notifyDataSetChanged();
                } else {
//                    Log.d()
                    System.out.println(response);
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });

    }

}