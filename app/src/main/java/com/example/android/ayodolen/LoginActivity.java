package com.example.android.ayodolen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    EditText username, password;
    Button btnLogin;
    Boolean ingatSaya = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbarLogin);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        //getActionBar().hide();
        username = findViewById(R.id.editTextUsername);
        password = findViewById(R.id.editTextPassword);
//        getActionBar().hide();

        btnLogin = findViewById(R.id.buttonYuk);
//        getActionBar().hide();
        final SessionManagement s1 = new SessionManagement(this);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface mApiInterface =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<UserResponse> mLogin = mApiInterface.loginRequest(username.getText().toString(),password.getText().toString());
                mLogin.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        String status = response.body().getStatus();
                        if (status.equals("success")){
                            User user = response.body().getUser();

                            if(ingatSaya){
                                //                            create sesion
                                s1.createLoginSession(user.getUsername().toString(),user.getPassword().toString(),true);
                            }else{
                                s1.createLoginSession(user.getUsername().toString(),user.getPassword().toString(),false);
                            }


                            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(i);
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(),"fail login",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "tidak connect",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void itemClicked(View v){
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){
            ingatSaya = true;
        }else{
            ingatSaya = false;
        }
    }

}
