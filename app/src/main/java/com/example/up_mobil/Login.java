package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickRegister(View v)
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void onClickMain(View v)
    {
        Intent intent = new Intent(this, main_page.class);
        startActivity(intent);
    }
}