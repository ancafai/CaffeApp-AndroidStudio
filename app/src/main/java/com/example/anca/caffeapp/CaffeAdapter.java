package com.example.anca.caffeapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anca on 12/12/2017.
 */

class CaffeAdapter extends RecyclerView.Adapter<CaffeAdapter.ViewHolder> {

    List<Caffe> caffes = new ArrayList<Caffe>();
    Context ctx;


    public CaffeAdapter(List<Caffe> caffes, Context ctx) {
        this.caffes = caffes;
        this.ctx = ctx;
    }


    @Override
    public CaffeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.caffe_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, ctx, caffes);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CaffeAdapter.ViewHolder holder, int position) {
        holder.name.setText(caffes.get(position).getName());
        holder.address.setText(caffes.get(position).getAddress());
        holder.phone.setText(caffes.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return caffes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView name;
        public TextView address;
        public TextView phone;
        List<Caffe> caffes = new ArrayList<Caffe>();
        Context ctx;

        public ViewHolder(View itemView, Context ctx, List<Caffe> caffes) {
            super(itemView);
            this.caffes = caffes;
            this.ctx = ctx;

            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.name_caffe_row);
            address = (TextView) itemView.findViewById(R.id.address_caffe_row);
            phone = (TextView) itemView.findViewById(R.id.phone_caffe_row);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Caffe caffe = this.caffes.get(position);
            Intent intent = new Intent(this.ctx, EditCaffeActivity.class);
            intent.putExtra("name", caffe.getName());
            intent.putExtra("address", caffe.getAddress());
            intent.putExtra("phone", caffe.getPhone());
            intent.putExtra("id", caffe.getId());
            this.ctx.startActivity(intent);
        }
    }


}
