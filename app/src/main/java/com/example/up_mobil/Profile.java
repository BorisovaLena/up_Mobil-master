package com.example.up_mobil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Profile extends AppCompatActivity {

    ImageView image;
    TextView Name;
    OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        image = findViewById(R.id.avatar);
        Name = findViewById(R.id.textView13);
        Name.setText(Onboarding.Name);
        new AdapterQoute.DownloadImageTask((ImageView) image).execute(Onboarding.image);
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
        SharedPreferences prefs = getSharedPreferences( "Date", Context.MODE_PRIVATE);
        prefs.edit().putString("Avatar", "").apply();
        prefs.edit().putString("NickName", "").apply();

        startActivity(new Intent(this, Login.class));
    }

    public void addImage(View view)
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
                        File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                        File dir = new File(filepath.getAbsolutePath() + "/MyFiles/");
                        dir.mkdirs();
                        File file = new File(dir, System.currentTimeMillis() + ".jpg");
                        try {
                            outputStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                            outputStream.flush();
                            outputStream.close();
                            Toast.makeText(Profile.this, "Успех!!!", Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(Profile.this, "Ошибка!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
}