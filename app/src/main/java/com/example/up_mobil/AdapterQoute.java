package com.example.up_mobil;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class AdapterQoute extends BaseAdapter {

    private Context mContext;
    List<MaskQuotes> maskList;

    public AdapterQoute(Context mContext, List<MaskQuotes> maskList) {
        this.mContext = mContext;
        this.maskList = maskList;
    }

    @Override
    public int getCount() {
        return maskList.size();
    }

    @Override
    public Object getItem(int position) {
        return maskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return maskList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View v = View.inflate(mContext,R.layout.activity_item_qoute,null);
        TextView title = v.findViewById(R.id.tvTitle);
        ImageView Image = v.findViewById(R.id.Image);
        TextView description = v.findViewById(R.id.tvDescription);
        MaskQuotes maskQuote = maskList.get(position);
        title.setText(maskQuote.getTitle());
        new DownloadImageTask((ImageView) Image).execute(maskQuote.getImage());
        description.setText(maskQuote.getDescription());
        return v;
    }

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        @SuppressLint("StaticFieldLeak")
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Ошибка!!!", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}