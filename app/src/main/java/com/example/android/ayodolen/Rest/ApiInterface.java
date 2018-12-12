package com.example.android.ayodolen.Rest;

import com.example.android.ayodolen.Model.EditUser;
import com.example.android.ayodolen.Model.GetWisata;
import com.example.android.ayodolen.Model.KategoriResponse;
import com.example.android.ayodolen.Model.RegistrasiUser;
import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.RegisterActivity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by user on 15/11/2018.
 */

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> loginRequest(@Field("username") String username,
                                    @Field("password") String password);


    @FormUrlEncoded
    @POST("register")
    Call<RegistrasiUser> registrasiUser(@Field("username") String username,
                                        @Field("password") String password,
                                        @Field("nama") String nama);

//    @FormUrlEncoded
//    @POST("login")
//    Call<UserResponse> loginRequest(@Field("username") String username,
//                                    @Field("password") String password);

    @FormUrlEncoded
    @PUT("register")
    Call<EditUser> editUser( @Field("id_user") String id_user,
                                  @Field("username") String username,
                                  @Field("password") String password,
                                  @Field("nama") String nama);


    //semua wisata
    @GET("wisata") Call<GetWisata> getWisata();

    //wisata kategori
    @FormUrlEncoded
    @POST("wisata/kategori") Call<GetWisata> getWisataKategori(@Field("id_kategori") String id_kategori);


    //kategori
    @GET("kategori") Call<KategoriResponse> getKategori();








}
