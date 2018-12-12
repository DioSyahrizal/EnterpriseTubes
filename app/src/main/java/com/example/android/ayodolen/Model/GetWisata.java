package com.example.android.ayodolen.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetWisata {
    @SerializedName("status") String status;
    @SerializedName("result") List<Wisata> wisataList;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Wisata> getWisataList() {
        return wisataList;
    }

    public void setWisataList(List<Wisata> wisataList) {
        this.wisataList = wisataList;
    }
}
