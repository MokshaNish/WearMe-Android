package com.example.dressnice.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dressnice.Model.Product;
import com.example.dressnice.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView name;
    private TextView price;
    private ImageView image;
    private Button btnAddToCart;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productdetailscardview);

        init();
        viewProductDetails();
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

        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

}
