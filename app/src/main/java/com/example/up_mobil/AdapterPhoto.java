package com.example.up_mobil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterPhoto  extends BaseAdapter {

    private Context mContext;
    List<MaskPhoto> maskList;

    public AdapterPhoto(Context mContext, List<MaskPhoto> maskList) {
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
        MaskPhoto maskProfileImage  = maskList.get(position);
        View v = null;
        if(maskProfileImage.getImage() == null)
        {
            v = View.inflate(mContext,R.layout.item_new_photo,null);
        }
        else
        {
            v = View.inflate(mContext,R.layout.item_photo,null);
            ImageView Image = v.findViewById(R.id.photo);
            TextView time = v.findViewById(R.id.tvTime);
            if(maskProfileImage.getImage().exists()){
                Bitmap myBitmap = BitmapFactory.decodeFile(maskProfileImage.getImage().getAbsolutePath());
                Image.setImageBitmap(myBitmap);
            }
            time.setText(maskProfileImage.getDate());
        }
        return v;
    }
}
