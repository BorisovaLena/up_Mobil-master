package com.example.up_mobil;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
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
        AdapterQ = new AdapterQoute(main_page.this, listQ);
        ivProducts.setAdapter(AdapterQ);
        new GetQuotes().execute();

        RecyclerView rvFeeling = findViewById(R.id.recyclerView);
        rvFeeling.setHasFixedSize(true);
        rvFeeling.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        AdapterF = new AdapterFeelings(listF, main_page.this);
        rvFeeling.setAdapter(AdapterF);
        new GetFeeling().execute();

        image = findViewById(R.id.ivProfile);
        new AdapterQoute.DownloadImageTask((ImageView) image).execute(Login.User.getAvatar());

        nameUser = findViewById(R.id.textView9);
        nameUser.setText(nameUser.getText().toString() + Login.User.getNickName() + "!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString(userVariableKey, "fgfggf");
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String str = savedInstanceState.getString(userVariableKey);
        nameUser.setText(str);
    }

    private class GetQuotes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/quotes");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception ex)
            {
                return  null;
            }
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listQ.clear();
                AdapterQ.notifyDataSetInvalidated();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskQuotes tempProduct = new MaskQuotes(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getString("description")
                    );
                    listQ.add(tempProduct);
                    AdapterQ.notifyDataSetInvalidated();
                }
            }
            catch (Exception exception)
            {
                Toast.makeText(main_page.this, "Ошибка!!!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetFeeling extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/feelings");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }
                return result.toString();
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listF.clear();
                AdapterF.notifyDataSetChanged();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskFeelings tempProduct = new MaskFeelings(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getInt("position"),
                            productJson.getString("image")
                    );
                    listF.add(tempProduct);
                    AdapterF.notifyDataSetChanged();
                }
                listF.sort(Comparator.comparing(MaskFeelings::getPosition));
                AdapterF.notifyDataSetChanged();
            }
            catch (Exception exception)
            {
                Toast.makeText(main_page.this, "Ошибка!!!", Toast.LENGTH_SHORT).show();
            }
        }
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