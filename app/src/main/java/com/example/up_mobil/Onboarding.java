package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Onboarding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
    }

    public void onClickRegister(View v)
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void onClickLogin(View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}