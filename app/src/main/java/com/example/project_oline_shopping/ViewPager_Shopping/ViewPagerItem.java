package com.example.project_oline_shopping.ViewPager_Shopping;

import androidx.fragment.app.Fragment;

public class ViewPagerItem {

private  String name ;
private Fragment f;

    public ViewPagerItem(String name, Fragment f) {
        this.name = name;
        this.f = f;
    }
public String getName() {return  name ;}
public Fragment getFragment() {return  f;}

}
