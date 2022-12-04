package com.example.miroslavmilosevicnbsoft;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {

    private Context mContext;
    private List<ModelClass> mData;

    public CustomAdaptor(Context mContext, List<ModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.price.setText(mData.get(position).getPrice());
        holder.brand.setText(mData.get(position).getBrand());
        holder.name.setText(mData.get(position).getName());

        Glide
                .with(mContext)
                .load(mData.get(position).getImg())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name,brand,price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.priceId);
            brand=itemView.findViewById(R.id.brandId);
            name=itemView.findViewById(R.id.nameId);
            imageView=itemView.findViewById(R.id.imgId);
        }
    }

}

