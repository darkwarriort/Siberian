package com.desa.siberian.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desa.siberian.MainActivity;
import com.desa.siberian.R;


public class Home extends Fragment {


    public Home() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_home, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToFragment(new MetodoFragment());
            }
        });
        return  view;
    }

    OnChangeFragment listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof  OnChangeFragment){
            listener = (OnChangeFragment) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
