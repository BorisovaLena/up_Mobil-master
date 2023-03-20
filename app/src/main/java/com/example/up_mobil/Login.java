package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    EditText etEmail, etPassword;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.Email);
        etPassword = findViewById(R.id.Password);

        SharedPreferences prefs = this.getSharedPreferences("Date", Context.MODE_PRIVATE);
        if(prefs != null)
        {
            etEmail.setText(prefs.getString("Email", ""));
        }
    }

    public void onClickRegister(View v)
    {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void nextMain(View v)
    {
        if(etEmail.getText().toString().equals("") || etPassword.getText().toString().equals(""))
        {
            Toast.makeText(Login.this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Pattern p = Pattern.compile("@", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(etEmail.getText().toString());
            boolean b = m.find();
            if(b)
            {
                callLogin();
            }
            else
            {
                Toast.makeText(Login.this, "Поле для Email обязательно должно содержать символ '@'", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void callLogin()
    {
        String email = String.valueOf(etEmail.getText());
        String password = String.valueOf(etPassword.getText());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        ModelUser modelSendUser = new ModelUser(email, password);
        Call<MaskUsers> call = retrofitAPI.newUser(modelSendUser);
        call.enqueue(new Callback<MaskUsers>() {
            @Override
            public void onResponse(Call<MaskUsers> call, Response<MaskUsers> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Login.this, "Пользователь с такой почтой и паролем не найден", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(response.body() != null)
                {
                    if(response.body().getToken() != null)
                    {
                        SharedPreferences prefs = getSharedPreferences( "Date", Context.MODE_PRIVATE);
                        prefs.edit().putString("Email", "" + email).apply();
                        prefs.edit().putString("image", "" + response.body().getAvatar()).apply();
                        prefs.edit().putString("Name", "" + response.body().getNickName()).apply();
                        Onboarding.image = response.body().getAvatar();
                        Onboarding.Name = response.body().getNickName();
                        Intent intent = new Intent(Login.this, main_page.class);
                        Bundle b = new Bundle();
                        intent.putExtras(b);
                        startActivity(intent);
                    }
                }
            }
            @Override
            public void onFailure(Call<MaskUsers> call, Throwable t) {
                Toast.makeText(Login.this, "Ошибка!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
