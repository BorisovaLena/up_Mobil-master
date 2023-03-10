package com.example.up_mobil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class AdapterFeelings extends RecyclerView.Adapter<AdapterFeelings.ViewHolder> {

    private List<MaskFeelings> dataModalArrayList;
    private Context context;

    public AdapterFeelings(List<MaskFeelings> dataModalArrayList, Context context) {
        this.dataModalArrayList = dataModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFeelings.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterFeelings.ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_item_feelings, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFeelings.ViewHolder holder, int position) {
        final MaskFeelings modal = dataModalArrayList.get(position);
        holder.title.setText(modal.getTitle());
        new AdapterFeelings.DownloadImageTask((ImageView) holder.image).execute(modal.getImage());
    }

    @Override
    public int getItemCount() {
        return dataModalArrayList.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.imageFeeling);
        }
    }
}