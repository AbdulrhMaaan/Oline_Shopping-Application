package com.example.project_oline_shopping.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oline_shopping.R;

import java.util.ArrayList;
import java.util.List;

public class JeansListAdabter extends RecyclerView.Adapter<JeansListAdabter.JeansViewHolder> {

    private List<Product> productlist = new ArrayList<>();
    private ProductClickListener productClickListener ;
    private  Context context ;

    public JeansListAdabter(Context context, List<Product> products, ProductClickListener productClickListener) {
        this.context = context;
        this.productlist = products;
        this.productClickListener = productClickListener;
    }



    @NonNull
    @Override
    public JeansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JeansViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.jeans_item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull JeansViewHolder holder, int position) {
        holder.proname.setText(productlist.get(position).getName());
        holder.proprice.setText(productlist.get(position).getPrice());
        int resourceIdentifier = holder.itemView.getResources().getIdentifier(productlist.get(position).getImage(), "drawable", holder.itemView.getContext().getPackageName());
        holder.proimage.setImageResource(resourceIdentifier);

    }
 // return number of items or number of jeans   which is 3 jeans
    @Override
    public int getItemCount() {
        return productlist.size();
    }

    public class JeansViewHolder extends RecyclerView.ViewHolder {
        TextView proname ,proprice  ;
        ImageView proimage;
        Button Addtocard;
        public JeansViewHolder(@NonNull View itemView) {

            super(itemView);
            proname=itemView.findViewById(R.id.product_Name);
            proprice=itemView.findViewById(R.id.product_Price);
            proimage = itemView.findViewById(R.id.product_Image);
            Addtocard=itemView.findViewById(R.id.add_product_To_Cart);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getAdapterPosition() != -1) {
                        productClickListener.onRecyclerViewClick(getAdapterPosition());
                    }
                }
            });
            Addtocard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {

                        productClickListener.onAddClick(getAdapterPosition());

                    }
                }
            });

        }
    }
}
