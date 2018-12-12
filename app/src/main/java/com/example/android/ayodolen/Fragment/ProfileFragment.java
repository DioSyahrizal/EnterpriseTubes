package com.example.android.ayodolen.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ayodolen.EditUserActivity;
import com.example.android.ayodolen.HomeActivity;
import com.example.android.ayodolen.MainActivity;
import com.example.android.ayodolen.Model.User;
import com.example.android.ayodolen.Model.UserResponse;
import com.example.android.ayodolen.R;
import com.example.android.ayodolen.Rest.ApiClient;
import com.example.android.ayodolen.Rest.ApiInterface;
import com.example.android.ayodolen.Session.SessionManagement;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user on 27/11/2018.
 */

public class ProfileFragment extends Fragment {

    View v;
    Button btnLogout, btEdit;
    TextView email;
    String id_user, nama, username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        email = view.findViewById(R.id.tvEmail);
        btnLogout = view.findViewById(R.id.btnKeluar);
        btEdit = view.findViewById(R.id.btnEdit);
        final SessionManagement s1 = new SessionManagement(getContext());
        loadData(s1);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent log = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                s1.logoutUser();
                startActivity(log);

                getActivity().finish();
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditUserActivity.class);
                i.putExtra("id_user",id_user);
                i.putExtra("nama",nama);
                i.putExtra("username", username);

                startActivity(i);
            }
        });

        return view;

    }

    private void loadData(SessionManagement s1){
        ApiInterface mApiInterface =
                ApiClient.getClient().create(ApiInterface.class);
        Call<UserResponse> mLogin = mApiInterface.loginRequest(s1.getUsername(),s1.getPassword());
        mLogin.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String status = response.body().getStatus();
                if (status.equals("success")){
                    User user = response.body().getUser();
                    id_user = user.getId_user().toString();
                    nama = user.getNama();
                    username = user.getUsername();

//                            create sesion
                    email.setText(user.getNama());

                }else {
                    Toast.makeText(getContext(),"fail load data",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "tidak connect",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
