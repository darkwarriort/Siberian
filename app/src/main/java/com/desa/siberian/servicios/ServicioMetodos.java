package com.desa.siberian.servicios;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.desa.siberian.R;
import com.desa.siberian.app.AppController;

import org.json.JSONObject;

public class ServicioMetodos {

    public interface CallBackConsulta{
        public void onError(Object response);
        public void onSuccess(Object response);
        public Context getContext();

    }
    public static void GET (final String url, final CallBackConsulta callback ){
        final ProgressDialog mProgressDialog ;
        mProgressDialog = new ProgressDialog(callback.getContext());

        mProgressDialog.getWindow().setBackgroundDrawable(new

                ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.my_progress);

        JsonObjectRequest strReq  = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        if(mProgressDialog!=null){
                            mProgressDialog.dismiss();
                        }
                        System.out.println("GET :" +response);
                        callback.onSuccess(response);

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(mProgressDialog!=null){
                            mProgressDialog.dismiss();
                        }

                        callback.onError(null);
                    }
                }
        );
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, "GET");

    }
}
