package com.example.android.ayodolen;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.android.ayodolen.Session.SessionManagement;

public class MainActivity extends AppCompatActivity {

    private Button mButtonDaftar;
    private Button mButtonMasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        mButtonMasuk = (Button) findViewById(R.id.buttonMasuk);
        mButtonDaftar = (Button) findViewById(R.id.buttonDaftar);
        final SessionManagement s1 = new SessionManagement(this);
        if(s1.isLoggedIn()){
            goToActivity();
        }
        //click
        mButtonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        mButtonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    private void goToActivity(){
        Intent i = new Intent(getApplicationContext()
                ,HomeActivity.class);
        startActivity(i);
    }
}
