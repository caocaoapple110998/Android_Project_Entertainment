package com.example.asus.project_entertainment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.project_entertainment.Control.Video_Phim;
import com.example.asus.project_entertainment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Video_Phim_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Video_Phim> video_phimList;

    public Video_Phim_Adapter(Context context, int layout, List<Video_Phim> video_phimList) {
        this.context = context;
        this.layout = layout;
        this.video_phimList = video_phimList;
    }

    @Override
    public int getCount() {
        return video_phimList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        ImageView imgThumbnail;
        TextView txtTitle;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder.txtTitle = (TextView)view.findViewById(R.id.txt_dong_Title);
            holder.imgThumbnail = (ImageView)view.findViewById(R.id.img_dong_thumbnail);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        Video_Phim video = video_phimList.get(i);

        holder.txtTitle.setText(video.getTitle());
        Picasso.with(context).load(video.getThumbnail()).into(holder.imgThumbnail);

        return view;
    }
}
