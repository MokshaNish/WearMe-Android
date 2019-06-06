package com.example.dressnice.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dressnice.Model.OrderItem;
import com.example.dressnice.R;
import com.squareup.picasso.Picasso;

import java.util.List;

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
        public TextView price,qty;
        public ImageView image;
        public LinearLayout linearLayout;
        public Button add, sub, remove;


        public ViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.tvName);
            price = (TextView) itemView.findViewById(R.id.tvprice);
            image = (ImageView) itemView.findViewById(R.id.imageCart);
            qty = (TextView) itemView.findViewById(R.id.tvQty);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linear_row_id);
            add = (Button) itemView.findViewById(R.id.btnAdd);
            sub = (Button) itemView.findViewById(R.id.btnSub);
            remove = (Button) itemView.findViewById(R.id.btnRemove);
        }
    }

    @Override
    public void onBindViewHolder(CartAdapter.ViewHolder holder, int position) {

        final OrderItem orderItem = orderItems.get(position);
        holder.name.setText(orderItem.getProduct().getName());
        holder.qty.setText(orderItem.getProduct().getQuantity());
        holder.price.setText(String.valueOf(orderItem.getProduct().getPrice()));
        Picasso.get()
                .load(orderItem.getProduct().getImageUrl())
                .resize(150, 400)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }


    public void setProducts(List<OrderItem> orderItem) {
        this.orderItems = orderItem;
    }
}
