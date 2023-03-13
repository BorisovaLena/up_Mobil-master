package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class Onboarding extends AppCompatActivity {

    public static String image;
    public static String Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        SharedPreferences prefs = this.getSharedPreferences(
                "Date", Context.MODE_PRIVATE);
        if(prefs != null)
        {
            if(!prefs.getString("NickName", "").equals(""))
            {
                image = prefs.getString("image", "");
                Name = prefs.getString("Name", "");
                startActivity(new Intent(this, main_page.class));
            }
        }
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