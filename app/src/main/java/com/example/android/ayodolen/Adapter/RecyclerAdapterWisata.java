package com.example.android.ayodolen.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.ayodolen.DetailWisataActivity;
import com.example.android.ayodolen.Model.Wisata;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterWisata extends RecyclerView.Adapter<RecyclerAdapterWisata.MyViewHolder> implements Filterable {

    private List<Wisata> wisata;
    private List<Wisata>  wisataFull;
    private Context mContext;



    public RecyclerAdapterWisata(List<Wisata> wisata, Context mContext) {
        this.wisata = wisata;
        this.mContext = mContext;
        wisataFull = new ArrayList<>(wisata);
    }

    @NonNull
    @Override
    public RecyclerAdapterWisata.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gview_wisata, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterWisata.MyViewHolder holder, final int position) {
        holder.namaWisata.setText(wisata.get(position).getNama_wisata());
        Picasso.with(mContext).load(ApiClient.BASE_URL+"assets/image/"+wisata.get(position).getImage()).into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(mContext,DetailWisataActivity.class);
                detail.putExtra("id_wisata", wisata.get(position).getId_wisata());
                detail.putExtra("nama_wisata",wisata.get(position).getNama_wisata());
                detail.putExtra("alamat",wisata.get(position).getAlamat());
                detail.putExtra("deskripsi",wisata.get(position).getDeskripsi());
                detail.putExtra("lt",wisata.get(position).getLatitude());
                detail.putExtra("lng",wisata.get(position).getLongitude());
                detail.putExtra("image",wisata.get(position).getImage());
                detail.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(detail);
            }
        });


    }

    @Override
    public int getItemCount() {
        return wisata.size();
    }

    @Override
    public Filter getFilter() {
        return WisataFilter;
    }

    private Filter WisataFilter = new Filter(){
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Wisata> filterWisata = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filterWisata.addAll(wisataFull);
            }else{
                String textfilter = charSequence.toString().toLowerCase().trim();
                for (Wisata w : wisataFull){
                    if(w.getNama_wisata().toLowerCase().contains(textfilter)){
                        filterWisata.add(w);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values = filterWisata;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            wisata.clear();
            wisata.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView namaWisata;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgGridWisata);
            namaWisata = itemView.findViewById(R.id.tvGridWisata);
        }
    }


}
