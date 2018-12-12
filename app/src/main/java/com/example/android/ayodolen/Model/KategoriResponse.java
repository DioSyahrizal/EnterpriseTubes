package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KategoriResponse {
    @SerializedName("status") String status;
    @SerializedName("result") List<Kategori> kategoriList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Kategori> getKategoriList() {
        return kategoriList;
    }

    public void setKategoriList(List<Kategori> kategoriList) {
        this.kategoriList = kategoriList;
    }
}
