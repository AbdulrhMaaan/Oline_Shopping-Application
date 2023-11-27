package com.example.project_oline_shopping.UI;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_oline_shopping.R;

import java.util.ArrayList;
import java.util.List;


public class jeansFragment extends Fragment implements ProductClickListener {


    private RecyclerView recyclerView;
    private List<Product> products =new ArrayList<>();
    private CategoryListAdabter categoryAdapter;
    private Database database;

    public static jeansFragment newInstance() {
        return new jeansFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_jeans, container, false);


        database = new Database(getActivity());
        recyclerView = rootview.findViewById(R.id.recyclerView);

        products=database.returnProducts(1);

        categoryAdapter = new CategoryListAdabter(getActivity(), products, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(categoryAdapter);

        return rootview;


    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void onRecyclerViewClick(int pos) {
        Product product = products.get(pos);
        Toast.makeText(getActivity(), product.getName(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAddClick(int pos) {
        if(database.VerifyProduct(products.get(pos).getName()))
        {
            database.IncreaseQuantity(products.get(pos).getName());
        }
        else
        {
            database.AddItem(products.get(pos));

        }
        Toast.makeText(getContext(), products.get(pos).getName() + " Added to shopping cart", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onDeleteClick(int pos) {

    }
}