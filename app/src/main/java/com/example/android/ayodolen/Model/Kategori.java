package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

public class Kategori {
    @SerializedName("id_kategori") String id_kategori;
    @SerializedName("kategori_wisata") String kategori_wisata;
    @SerializedName("image") String image;

    public String getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(String id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getKategori_wisata() {
        return kategori_wisata;
    }

    public void setKategori_wisata(String kategori_wisata) {
        this.kategori_wisata = kategori_wisata;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
