package com.example.android.ayodolen;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.Model.RegistrasiUser;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    TextView login;
    EditText nama, username, pwd;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getActionBar().hide();


        Toolbar toolbar = findViewById(R.id.toolbarRegistrasi);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        nama = findViewById(R.id.inputNama);
        username = findViewById(R.id.inputUsername);
        pwd = findViewById(R.id.inputPasswd);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.lbPunyaakun);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().length() == 0 || pwd.getText().toString().length() == 0
                        || nama.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(),"Data tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }else{

                    Call<RegistrasiUser> newUser = mApiInterface.registrasiUser( username.getText().toString(),
                            pwd.getText().toString(), nama.getText().toString());

                    newUser.enqueue(new Callback<RegistrasiUser>() {
                        @Override
                        public void onResponse(Call<RegistrasiUser> call, Response<RegistrasiUser> response) {
                            if(response.body().getStatus().equals("gagal")){
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                                finish();
                            }
//                        Toast.makeText(getApplicationContext(),"Berhasil Mendaftar",Toast.LENGTH_SHORT).show();
//                        finish();
                        }

                        @Override
                        public void onFailure(Call<RegistrasiUser> call, Throwable t) {
                            Toast.makeText(getApplicationContext(),"error "+t,Toast.LENGTH_SHORT).show();
                        }
                    });

                }





            }
        });


    }


}
