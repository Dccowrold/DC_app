package com.assoftek.splashscreen.homePage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assoftek.splashscreen.R;

public class Wealth_tab extends Fragment {

    public Wealth_tab() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View wealth=inflater.inflate(R.layout.fragment_wealth_tab, container, false);
        // Inflate the layout for this fragment
        return wealth;
    }
}