package com.desa.siberian.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.desa.siberian.MainActivity;
import com.desa.siberian.R;
import com.desa.siberian.objetos.Tarjeta;
import com.desa.siberian.servicios.ServicioMetodos;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MetodoFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView ;
    Context context;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MetodoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_metodo_list, container, false);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ((MainActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,linearLayoutManager.getOrientation());

            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.addItemDecoration(dividerItemDecoration);
            recyclerView.setHasFixedSize(true);

        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ServicioMetodos.GET("http://prueba.siberian.com.ec/ws.php", new ServicioMetodos.CallBackConsulta() {
            @Override
            public void onError(Object response) {
                Toast.makeText(context,"No se pudo obtener datos", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onSuccess(Object response) {

                System.out.println(response.toString());
                try {
                    JSONObject jsonObject = new JSONObject((String) response.toString());

                    List<Tarjeta> list = Arrays.asList(new Gson().fromJson(jsonObject.getString("listadoTarjetas"),Tarjeta[].class));
                    recyclerView.setAdapter(new MetodoRecyclerViewAdapter(list, context));


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public Context getContext() {
                return context;
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
