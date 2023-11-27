package com.example.project_oline_shopping.ViewPager_Shopping;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {
     private List<ViewPagerItem> viewPagerItemList ;

     public ViewPagerAdapter(FragmentManager fm, List<ViewPagerItem> viewPagerItemList) {
         super(fm);
        this.viewPagerItemList = viewPagerItemList;
    }

    @Override
    public Fragment getItem(int position) {
        return viewPagerItemList.get(position).getFragment();
    }

    @Override
    public int getCount() {
            return viewPagerItemList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return viewPagerItemList.get(position).getName();
    }

}
