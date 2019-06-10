package com.example.dressnice.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dressnice.Activities.ProductDetailActivity;
import com.example.dressnice.Model.Product;
import com.example.dressnice.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {

    private List<Product> products;
    private Context context;
    private List<Product> filteredProducts;

    public ProductAdapter(List<Product> products, Context context) {
        this.products = products;
        this.context = context;
        this.filteredProducts = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView price;
        public ImageView image;
        public LinearLayout linearLayout;


        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textViewHeading);
            price = itemView.findViewById(R.id.textPrice);
            image = itemView.findViewById(R.id.imageViewList);
            linearLayout = itemView.findViewById(R.id.linear_row_id);
        }

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Product product = filteredProducts.get(position);
        holder.name.setText(product.getName());
        holder.price.setText(String.valueOf(product.getPrice()));
        Picasso.get()
                .load(product.getImageUrl())
                .resize(150, 400)
                .into(holder.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailActivity.class);
                Gson gson = new Gson();
                String productObject = gson.toJson(product);
                intent.putExtra("product", productObject);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredProducts.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredProducts = products;
                } else {
                    List<Product> filteredList = new ArrayList<>();
                    for (Product row : products) {

                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteredProducts = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredProducts;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredProducts = (ArrayList<Product>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setProducts(List<Product> products) {
        this.filteredProducts = products;
        this.products = products;
    }
}
