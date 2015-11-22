package com.wattscorp.foodhoodui.fragments;

import com.wattscorp.foodhoodui.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentByZipcode extends Fragment {


    /**
     * The fragment argument representing the section number for this fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_by_zipcode, container, false);
        return rootView;
    }
    
    /**
     * Returns a new instance of this fragment for the given section number.
     */
    public static FragmentByZipcode newInstance(int sectionNumber) {
        FragmentByZipcode fragment = new FragmentByZipcode();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

}
