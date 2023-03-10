package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class main_page extends AppCompatActivity {

    final static String userVariableKey = "USER_VARIABLE";
    private AdapterQoute AdapterQ;
    private List<MaskQuotes> listQ = new ArrayList<>();
    private AdapterFeelings AdapterF;
    private List<MaskFeelings> listF = new ArrayList<>();
    ImageView image;
    TextView nameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ListView ivProducts = findViewById(R.id.lvQuotes);


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