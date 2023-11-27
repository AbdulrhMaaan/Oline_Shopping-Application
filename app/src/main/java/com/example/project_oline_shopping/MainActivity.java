package com.example.project_oline_shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.project_oline_shopping.UI.OrderFragment;
import com.example.project_oline_shopping.UI.ShoesFragment;
import com.example.project_oline_shopping.UI.TshirtFragment;
import com.example.project_oline_shopping.UI.jeansFragment;
import com.example.project_oline_shopping.ViewPager_Shopping.ViewPagerAdapter;
import com.example.project_oline_shopping.ViewPager_Shopping.ViewPagerItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pager adapter of categories
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getViewPagerItems());
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
    // insert fragments of categories
    private List<ViewPagerItem> getViewPagerItems() {
        List<ViewPagerItem> viewPagerItems = new ArrayList<>();
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_1), jeansFragment.newInstance()));
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_2), ShoesFragment.newInstance()));
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_3), TshirtFragment.newInstance()));
        viewPagerItems.add(new ViewPagerItem(getString(R.string.tab_4), OrderFragment.newInstance()));
        return viewPagerItems;
    }

    // Meune
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater MI = new MenuInflater(this);
        MI.inflate(R.menu.menufile, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


      /*  switch (item.getItemId()) {
            case Shopping_Card:
                Intent shopping =new Intent(Ma(), .class);
                startActivity(shopping);
                Toast.makeText(this, "Now you are in Your Shopping Card", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }*/
        return true;
    }

}
