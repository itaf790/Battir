package com.project.battirtourguideapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImagesListAdapter extends ArrayAdapter<ImageUploadInfo> {
    private static final String TAG = "ImagesListAdapter";
    private Context mContext;
    private int mResource;
    public ImagesListAdapter(Context context, int resource, ArrayList<ImageUploadInfo> list) {
        super(context, resource, list);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the image information
        final String imageDesc = getItem(position).getImageDesc();
        String imageName = getItem(position).getImageName();
        String imageUrl1 = getItem(position).getImageURL1();
        final String imageUrl2 = getItem(position).getImageURL2();

        //Create the employee object with the information
        ImageUploadInfo ImageInfo = new ImageUploadInfo(imageDesc,imageName,
                                                        imageUrl1,imageUrl2);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(this.mResource, parent,false);

        TextView imgName = convertView.findViewById(R.id.image_name);
        ImageView imgView= convertView.findViewById(R.id.image_View);

        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),ImageDetailsActivity.class);
                intent.putExtra("url2",imageUrl2);
                intent.putExtra("desc",imageDesc);
                view.getContext().startActivity(intent);
            }
        });

        //Set the image name
        imgName.setText(imageName);

        //Loading image from Glide library.
        Glide.with(convertView.getContext()).load(imageUrl1).into(imgView);

        return convertView;
    }
}
