package com.example.android.ayodolen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ayodolen.KategoriActivity;
import com.example.android.ayodolen.Model.Kategori;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapterKategori extends RecyclerView.Adapter<RecyclerAdapterKategori.MyViewholder> {

    private List<Kategori> mKategori;
    private Context mContext;

    public RecyclerAdapterKategori(List<Kategori> mKategori, Context mContext) {
        this.mKategori = mKategori;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerAdapterKategori.MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gview_wisata, parent, false);
        RecyclerAdapterKategori. MyViewholder myViewHolder = new RecyclerAdapterKategori.MyViewholder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterKategori.MyViewholder holder, final int position) {
        holder.namaKategori.setText(mKategori.get(position).getKategori_wisata());
        Picasso.with(mContext).load(ApiClient.BASE_URL+"assets/image/kategori/"+mKategori.get(position).getImage()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent kate = new Intent(mContext,KategoriActivity.class);
                kate.putExtra("id_kategori",mKategori.get(position).getId_kategori());
                kate.putExtra("kategori",mKategori.get(position).getKategori_wisata());

                mContext.startActivity(kate);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mKategori.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView namaKategori;

        public MyViewholder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgGridWisata);
            namaKategori = itemView.findViewById(R.id.tvGridWisata);
        }
    }
}
