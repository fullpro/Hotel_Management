package com.example.dell.hotel_management;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private Integer [] images = {R.drawable.hotel_room1,R.drawable.hotel_room2,R.drawable.hotel_room3,R.drawable.hotel_room4,R.drawable.book_now};

    ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup cont, int pos){

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = layoutInflater.inflate(R.layout.content_main, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        imageView.setBackgroundResource(images[pos]);



        ViewPager vp = (ViewPager) cont;
        vp.addView(view, 0);
        return view;
    }



    @Override
    public void destroyItem(@NonNull ViewGroup container, int position , @NonNull Object object){

        ViewPager vp = (ViewPager) container;
        View view  = (View) object;
        vp.removeView(view);
    }
}
