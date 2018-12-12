package com.example.android.ayodolen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.android.ayodolen.Model.EditUser;
import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends AppCompatActivity {
    EditText edNama,edUsername,edPassword;
    String id_user;
    Button btEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        edNama = (EditText) findViewById(R.id.edtNama);
        edUsername = (EditText) findViewById(R.id.edtUsername);
        edPassword = (EditText) findViewById(R.id.edtPasswd);
        btEdit = (Button) findViewById(R.id.btnEdit);

        android.support.v7.widget.Toolbar tb = findViewById(R.id.toolbarEditUser);
        setSupportActionBar(tb);
        getSupportActionBar().setTitle("");

        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final SessionManagement s1 = new SessionManagement(getApplicationContext());
        Intent i = getIntent();
        id_user = i.getStringExtra("id_user");
        edNama.setText(i.getStringExtra("nama"));
        edUsername.setText(i.getStringExtra("username"));

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface mApiInterface =
                        ApiClient.getClient().create(ApiInterface.class);
                Call<EditUser> editUser = mApiInterface.editUser(id_user,edUsername.getText().toString(),edPassword.getText().toString(),edNama.getText().toString());
                editUser.enqueue(new Callback<EditUser>() {
                    @Override
                    public void onResponse(Call<EditUser> call, Response<EditUser> response) {
                        String status = response.body().getStatus();
                        if (status.equals("sukses")){
                            User user = response.body().getmUser();
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            User user = response.body().getmUser();
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<EditUser> call, Throwable t) {

                    }
                });

            }
        });


    }
}
