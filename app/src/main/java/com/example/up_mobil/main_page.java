package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class main_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void onClickProfile(View v)
    {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    public void onClickMenu(View v)
    {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}