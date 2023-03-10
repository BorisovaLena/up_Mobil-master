package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    ImageView image;
    TextView Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.avatar);
        Name = findViewById(R.id.textView13);
        Name.setText(Login.User.getNickName());
        new AdapterQoute.DownloadImageTask((ImageView) image).execute(Login.User.getAvatar());
    }

    public void onClickMain(View v)
    {
        Intent intent = new Intent(this, main_page.class);
        startActivity(intent);
    }

    public void onClickMenu(View v)
    {
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }

    public void onClickLogin(View v)
    {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}