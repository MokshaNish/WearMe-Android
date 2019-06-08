package com.example.dressnice.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dressnice.R;

public class BottomNavigationBarActivity extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottomnavigationview);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        toolbar.setTitle("Shop");
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle("Shop");
                    Intent a = new Intent(BottomNavigationBarActivity.this,ProductList.class);
                    startActivity(a);
                    return true;

                case R.id.navigation_search:
                    toolbar.setTitle("Search");
                    Intent b = new Intent(BottomNavigationBarActivity.this,SearchActivity.class);
                    startActivity(b);
                    return true;

                case R.id.navigation_cart:
                    toolbar.setTitle("Cart");
                    Intent c = new Intent(BottomNavigationBarActivity.this,CartActivity.class);
                    startActivity(c);
                    return true;

                case R.id.navigation_myOrders:
                    toolbar.setTitle("My Orders");
                    Intent d = new Intent(BottomNavigationBarActivity.this,MyOrdersActivity.class);
                    startActivity(d);
                    return true;
            }
            return false;
        }
    };
}