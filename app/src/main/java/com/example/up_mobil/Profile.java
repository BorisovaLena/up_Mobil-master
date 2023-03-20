package com.example.up_mobil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class Profile extends AppCompatActivity {

    ImageView image;
    TextView Name;
    OutputStream outputStream;
    AdapterPhoto pAdapter;
    List<MaskPhoto> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.avatar);
        Name = findViewById(R.id.textView13);
        Name.setText(Onboarding.Name);
        new AdapterQoute.DownloadImageTask((ImageView) image).execute(Onboarding.image);

        GridView lvPtoto = findViewById(R.id.lvPhoto);
        pAdapter = new AdapterPhoto(Profile.this, list);
        lvPtoto.setAdapter(pAdapter);
        GetImages();
        lvPtoto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MaskPhoto mask = list.get(i);
                if(mask.getImage() == null)
                {
                    addImage();
                }

            }
        });
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

    public void onClickListen(View v)
    {
        Intent intent = new Intent(this, Listen.class);
        startActivity(intent);
    }

    public void onClickLogin(View v)
    {
        SharedPreferences prefs = getSharedPreferences( "Date", Context.MODE_PRIVATE);
        prefs.edit().putString("image", "").apply();
        prefs.edit().putString("Name", "").apply();
        startActivity(new Intent(this, Login.class));
    }

    private void GetImages()
    {
        File dir = new File(getApplicationInfo().dataDir + "/MyFiles/");
        dir.mkdirs();
        list.clear();
        pAdapter.notifyDataSetInvalidated();
        String path = getApplicationInfo().dataDir + "/MyFiles";
        File directory = new File(path);
        File[] files = directory.listFiles();
        int j = 0;
        for (int i = 0; i < files.length; i++)
        {
            Long last = files[i].lastModified();
            MaskPhoto tempProduct = new MaskPhoto(
                    j,
                    files[i].getAbsolutePath(),
                    files[i],
                    getFullTime(last)
            );
            list.add(tempProduct);
            pAdapter.notifyDataSetInvalidated();
        }
        MaskPhoto tempProduct = new MaskPhoto(
                j,
                null,
                null,
                "null"
        );
        list.add(tempProduct);
        pAdapter.notifyDataSetInvalidated();
    }

    public static final String getFullTime(final long timeInMillis)
    {
        final SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        final Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timeInMillis);
        c.setTimeZone(TimeZone.getDefault());
        return format.format(c.getTime());
    }

    public void addImage()
    {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        someActivityResultLauncher.launch(photoPickerIntent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bitmap bitmap = null;
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Uri selectedImage = result.getData().getData();
                        try
                        {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        File dir = new File(getApplicationInfo().dataDir + "/MyFiles/");
                        dir.mkdirs();
                        File file = new File(dir, System.currentTimeMillis() + ".jpg");
                        try {
                            outputStream = new FileOutputStream(file); // Отправка данных в файл на диске
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(Profile.this, "Изображение успешно сохранено", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(Profile.this, "При сохранение изображения возникла ошибка!", Toast.LENGTH_LONG).show();
                        }
                        GetImages();
                    }
                }
            });
}