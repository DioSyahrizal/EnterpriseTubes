package com.example.android.ayodolen.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ayodolen.Adapter.RecyclerAdapterWisata;
import com.example.android.ayodolen.Model.GetWisata;
import com.example.android.ayodolen.Model.Wisata;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 27/11/2018.
 */

public class SearchFragment extends Fragment {

    private ApiInterface mApiInterface;
    private MaterialSearchView searchView;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterWisata  mAdapter;
    private List<Wisata> mWisata = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search,container,false);

        Toolbar toolbar = view.findViewById(R.id.toolbarSeacrh);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Cari Wisata");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        setHasOptionsMenu(true);

        mRecyclerView = view.findViewById(R.id.rv_search);
        loadData();


        searchView = view.findViewById(R.id.search_view);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });


        return view;
    }


    public void loadData(){
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<GetWisata> getWisata = mApiInterface.getWisata();
        getWisata.enqueue(new Callback<GetWisata>() {
            @Override
            public void onResponse(Call<GetWisata> call, Response<GetWisata> response) {
                mWisata = response.body().getWisataList();

                mAdapter = new RecyclerAdapterWisata(mWisata,getContext());
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetWisata> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
    }
}
