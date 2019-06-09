package com.example.dressnice.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.SharedPreferenceMgr;
import com.example.dressnice.Model.CartForm;
import com.example.dressnice.Model.OrderItem;
import com.example.dressnice.Model.Product;
import com.example.dressnice.Model.User;
import com.example.dressnice.R;
import com.example.dressnice.Services.CartService;
import com.example.dressnice.Services.UserService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView price;
    private ImageView image;
    private Button btnAddToCart,sharebtn;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailscardview);

        init();
        viewProductDetails();

        sharebtn = (Button)findViewById(R.id.buttonshare);
        sharebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, "MY NEW APP");
                share.putExtra(Intent.EXTRA_TEXT, " Try my new app:");
                startActivity(Intent.createChooser(share, "Share Via"));
            }
        });
    }

    private void init() {

        name = (TextView) findViewById(R.id.tvName);
        price = (TextView) findViewById(R.id.tvprice);
        image = (ImageView) findViewById(R.id.detailcustomimageView);
        btnAddToCart = (Button) findViewById(R.id.buttonaddtocart);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Gson gson = new Gson();
            product = gson.fromJson(bundle.getString("product"), Product.class);
        }
    }

    private void viewProductDetails() {
        try {

            price.setText(String.valueOf(product.getPrice()));
            name.setText(product.getName());
            Picasso.get()
                    .load(product.getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void btnAddToCart(View view) {

        SharedPreferences prefs = SharedPreferenceMgr.getSharedPrefs(this);
        int cardId = prefs.getInt("cartId", 0);

        CartForm cartForm = new CartForm();
        cartForm.setCartId(cardId);
        cartForm.setProduct(product);
        cartForm.setQuantity(1);

        CartService cartService = APICLIENT.getClient().create(CartService.class);
        Call<OrderItem> call = cartService.addToCart(cartForm);

        call.enqueue(new Callback<OrderItem>() {
            @Override
            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                if (response.isSuccessful()) {
                    btnAddToCart.setEnabled(false);
                    Toast.makeText(getApplicationContext(), "Successfully added to the cart", Toast.LENGTH_SHORT);
//                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<OrderItem> call, Throwable t) {

            }
        });



    }

    public void transfer(View view){

        Intent intent = new Intent(getApplicationContext(), CartActivity.class);
        startActivity(intent);



    }



}
