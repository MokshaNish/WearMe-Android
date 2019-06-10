package com.example.dressnice.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dressnice.Activities.ProductDetailActivity;
import com.example.dressnice.Client.APICLIENT;
import com.example.dressnice.Client.SharedPreferenceMgr;
import com.example.dressnice.Model.CartForm;
import com.example.dressnice.Model.OrderItem;
import com.example.dressnice.Model.Product;
import com.example.dressnice.R;
import com.example.dressnice.Services.CartService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private List<OrderItem> orderItems;
    private Context context;

    public CartAdapter(List<OrderItem> orderItems, Context context) {
        this.orderItems = orderItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customcart, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView price, qty,subTotal;
        public ImageView image;
        public LinearLayout linearLayout;
        public Button add, sub, remove;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvItemName);
            price = itemView.findViewById(R.id.tvPrice);
            image = itemView.findViewById(R.id.imageCart);
            qty = itemView.findViewById(R.id.tvQty);
            subTotal = itemView.findViewById(R.id.tvSubTotal);

            linearLayout = itemView.findViewById(R.id.linear_row_id);
            add = itemView.findViewById(R.id.btnadd);
            sub = itemView.findViewById(R.id.btnsub);



        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final OrderItem orderItem = orderItems.get(position);
        holder.name.setText(orderItem.getProduct().getName());
        holder.qty.setText(String.valueOf(orderItem.getQuantity()));
        holder.price.setText(String.valueOf(orderItem.getProduct().getPrice()));
        Picasso.get()
                .load(orderItem.getProduct().getImageUrl())
                .resize(150, 150)
                .into(holder.image);

        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add(holder, orderItem);
            }
        });

        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub(holder, orderItem);
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public void setOrderItems(List<OrderItem> orderItem) {
        this.orderItems = orderItem;
    }

    private void add(ViewHolder viewHolder, OrderItem orderItem) {
        int qty = Integer.parseInt(viewHolder.qty.getText().toString());
        qty++;
        viewHolder.qty.setText(String.valueOf(qty));
        manageCart(orderItem.getProduct(), +1);
    }

    private void sub(ViewHolder viewHolder, OrderItem orderItem) {
        int qty = Integer.parseInt(viewHolder.qty.getText().toString());
        qty--;
        viewHolder.qty.setText(String.valueOf(qty));
        manageCart(orderItem.getProduct(), -1);
    }

    public void manageCart(Product product, int qty) {

        SharedPreferences prefs = SharedPreferenceMgr.getSharedPrefs(context);
        int cardId = prefs.getInt("cartId", 0);

        CartForm cartForm = new CartForm();
        cartForm.setCartId(cardId);
        cartForm.setProduct(product);
        cartForm.setQuantity(qty);

        CartService cartService = APICLIENT.getClient().create(CartService.class);
        Call<OrderItem> call = cartService.addToCart(cartForm);

        call.enqueue(new Callback<OrderItem>() {
            @Override
            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "Successfully added to the cart", Toast.LENGTH_SHORT);
//                    Intent intent = new Intent(getApplicationContext(), CartActivity.class);
//                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<OrderItem> call, Throwable t) {

            }
        });


    }

}
