package com.example.android.ayodolen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.ayodolen.Adapter.RecyclerAdapterKategori;
import com.example.android.ayodolen.KategoriActivity;
import com.example.android.ayodolen.Model.Kategori;
import com.example.android.ayodolen.Model.KategoriResponse;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;

/**
 * Created by user on 27/11/2018.
 */

public class HomeFragment extends Fragment {
    FlipperLayout flipper;
    private ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Kategori> mKategori = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        flipper = view.findViewById(R.id.flipper);

        mRecyclerView = view.findViewById(R.id.rv_kategori);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        loadKategori();

        setLayout();

        return view;
    }

    private void loadKategori(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<KategoriResponse> getKategori = mApiInterface.getKategori();
        getKategori.enqueue(new Callback<KategoriResponse>() {
            @Override
            public void onResponse(Call<KategoriResponse> call, Response<KategoriResponse> response) {
                mKategori = response.body().getKategoriList();

                mAdapter = new RecyclerAdapterKategori(mKategori, getActivity());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<KategoriResponse> call, Throwable t) {

            }
        });


    }

    private void setLayout(){
        String url [] = new String[]{
                "http://macigo.com/wp-content/uploads/2017/06/Museum_Angkut_3.jpg",
                "http://macigo.com/wp-content/uploads/2017/06/Pantai_Kondang_Merak_Macigo_1.jpg",
                "http://macigo.com/wp-content/uploads/2017/06/Coban_Rondo_Macigo_1.jpg"

        };

        for(int i=0; i<3; i++){
            FlipperView fv = new FlipperView(getActivity().getBaseContext());
            fv.setImageUrl(url[i]);
            flipper.addFlipperView(fv);
        }
    }


}
