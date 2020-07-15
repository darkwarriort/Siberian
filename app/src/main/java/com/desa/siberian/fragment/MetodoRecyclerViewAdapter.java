package com.desa.siberian.fragment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.desa.siberian.R;
import com.desa.siberian.objetos.Tarjeta;

import java.util.ArrayList;
import java.util.List;

public class MetodoRecyclerViewAdapter extends RecyclerView.Adapter<MetodoRecyclerViewAdapter.ViewHolder> {

    private final List<Tarjeta> mValues;
Context context;
    public MetodoRecyclerViewAdapter(List<Tarjeta> items, Context context) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_metodo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
//        holder.mIdView.setText(mValues.get(position).id);

        if(mValues.get(position).getTipo().equals("VISA")){
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.visa));
        }else{
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.mastercard));

        }
        holder.mContentView.setText(mValues.get(position).getNumero());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (null != mListener) {
//                    // Notify the active callbacks interface (the activity, if the
//                    // fragment is attached to one) that an item has been selected.
//                    mListener.onListFragmentInteraction(holder.mItem);
//                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imageView;
        public final TextView mContentView;
        public Tarjeta mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = (ImageView) view.findViewById(R.id.imageView);
            mContentView = (TextView) view.findViewById(R.id.textView3);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
