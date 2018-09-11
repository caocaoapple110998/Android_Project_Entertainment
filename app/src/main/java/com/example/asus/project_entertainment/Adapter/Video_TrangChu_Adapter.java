package com.example.asus.project_entertainment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.project_entertainment.Control.Video_TrangChu;
import com.example.asus.project_entertainment.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Video_TrangChu_Adapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Video_TrangChu> video_trangChuList;

    public Video_TrangChu_Adapter(Context context, int layout, List<Video_TrangChu> video_trangChuList) {
        this.context = context;
        this.layout = layout;
        this.video_trangChuList = video_trangChuList;
    }

    @Override
    public int getCount() {
        return video_trangChuList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class Viewholder{
        ImageView img_dong_Thumbnail;
        TextView txt_dong_Title;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Viewholder holder;

        if (view == null){
            holder = new Viewholder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.txt_dong_Title = (TextView)view.findViewById(R.id.txt_dong_Title);
            holder.img_dong_Thumbnail = (ImageView)view.findViewById(R.id.img_dong_thumbnail);
            view.setTag(holder);
        }else {
            holder = (Viewholder) view.getTag();
        }

        Video_TrangChu video_trangChu = video_trangChuList.get(i);

        holder.txt_dong_Title.setText(video_trangChu.getTitle());
        Picasso.with(context)
                .load(video_trangChu.getThumbnail())
                .into(holder.img_dong_Thumbnail);

        return view;
    }
}
