package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 15/11/2018.
 */

public class Wisata {
    @SerializedName("id_wisata") private Integer id_wisata;
    @SerializedName("nama_wisata") private String nama_wisata;
    @SerializedName("id_kategori") private Integer id_kategori;
    @SerializedName("alamat") private  String alamat;
    @SerializedName("deskripsi") private String deskripsi;
    @SerializedName("longitude") private double longitude;
    @SerializedName("latitude") private double latitude;
    @SerializedName("image") private String image;

    public Wisata(Integer id_wisata, String nama_wisata, Integer id_kategori, String alamat, String deskripsi, double longitude, double latitude, String image) {
        this.id_wisata = id_wisata;
        this.nama_wisata = nama_wisata;
        this.id_kategori = id_kategori;
        this.alamat = alamat;
        this.deskripsi = deskripsi;
        this.longitude = longitude;
        this.latitude = latitude;
        this.image = image;
    }

    public Integer getId_wisata() {
        return id_wisata;
    }

    public void setId_wisata(Integer id_wisata) {
        this.id_wisata = id_wisata;
    }

    public String getNama_wisata() {
        return nama_wisata;
    }

    public void setNama_wisata(String nama_wisata) {
        this.nama_wisata = nama_wisata;
    }

    public Integer getId_kategori() {
        return id_kategori;
    }

    public void setId_kategori(Integer id_kategori) {
        this.id_kategori = id_kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
