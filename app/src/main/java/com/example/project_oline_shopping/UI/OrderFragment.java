package com.example.project_oline_shopping.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_oline_shopping.R;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment implements ProductClickListener, View.OnClickListener {

    Button Done;
    private RecyclerView recyclerView;
    private List<Product> products;
    private orderAdapter orderAdapter_object;
    private Database database;

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView = view.findViewById(R.id.recyclerView4);

        products = new ArrayList<>();

        database = new Database(getActivity());
        products = database.ReturnCart();

        orderAdapter_object = new orderAdapter(getActivity(), products, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(orderAdapter_object);


        Done = view.findViewById(R.id.done);
        Done.setOnClickListener((OnClickListener) this);



        return view;
    }


    @Override
    public void onRecyclerViewClick(int pos) {
        Product product = products.get(pos);
        Toast.makeText(getActivity(), product.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAddClick(int pos) {
        database.IncreaseQuantity(products.get(pos).getName());
        products.get(pos).increasequantety();
        orderAdapter_object.notifyDataSetChanged();

    }

    @Override
    public void onDeleteClick(int pos) {

        if (products.get(pos).getQuantity() == 1) {
            Toast.makeText(getContext(), products.get(pos).getName() + " deleted from shopping card", Toast.LENGTH_LONG).show();
            database.RemoveItem(products.get(pos));
            products.remove(pos);
            orderAdapter_object.notifyDataSetChanged();

        } else if (products.get(pos).getQuantity() > 1) {
            database.DecreaseQuantity(products.get(pos).getName());
            products.get(pos).decreasequantety();
            orderAdapter_object.notifyDataSetChanged();
        }


    }


    @Override
    public void onClick(View view) {
        double totalPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            totalPrice += Double.valueOf(products.get(i).getPrice()) * products.get(i).getQuantity();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setMessage("Do you want to Buy this products ?" + "\nTotal price: " + totalPrice + " EGP");

        builder.setTitle("Check out");

        builder.setCancelable(false);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { getFragmentManager().beginTransaction().replace(R.id.viewpager, new OrderFragment()).commit();
            Toast.makeText(getActivity(), "your order has been submited", Toast.LENGTH_LONG).show();
            database.EmptyCart();
            products.clear();
            orderAdapter_object.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getFragmentManager().beginTransaction().replace(R.id.viewpager, new OrderFragment()).commit(); }
                        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}