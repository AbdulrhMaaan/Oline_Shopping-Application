package com.example.project_oline_shopping.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.project_oline_shopping.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.orderViewHolder>{

    private Context context;
    private List<Product> productList;
    private ProductClickListener productClickListener;



    public orderAdapter(Context context, List<Product> products, ProductClickListener productClickListener) {
        this.context = context;
        this.productList = products;
        this.productClickListener = productClickListener;
    }

    @NonNull
    @Override
    public orderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patternforshoppingcard, parent, false);
        return new orderViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull orderViewHolder holder, int position) {

        Product product = productList.get(position);
        holder.proname.setText(product.getName());
        holder.proPrice.setText(product.getPrice());
        holder.proQuantity.setText(String.valueOf(product.getQuantity()));
        int resourceIdentifier = holder.itemView.getResources().getIdentifier(product.getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.productImage.setImageResource(resourceIdentifier);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class orderViewHolder extends RecyclerView.ViewHolder {

        TextView proname , proPrice ,proQuantity;
        ImageView productImage;
        Button deleteFromShoppingCard , addToShoppingCard;

        public orderViewHolder(@NonNull View itemView) {
            super(itemView);
            proname = itemView.findViewById(R.id.proName);
            proPrice = itemView.findViewById(R.id.proPrice);
            proQuantity=itemView.findViewById(R.id.proQuantity);
            productImage=itemView.findViewById(R.id.proImage);
            deleteFromShoppingCard=itemView.findViewById(R.id.decrease_one);
            addToShoppingCard =itemView.findViewById(R.id.increase_one);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1) {
                        productClickListener.onRecyclerViewClick(getAdapterPosition());
                    }
                }
            });
            addToShoppingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {

                        productClickListener.onAddClick(getAdapterPosition());

                    }
                }
            });
            deleteFromShoppingCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {

                        productClickListener.onDeleteClick(getAdapterPosition());

                    }
                }
            });


        }
    }
}
