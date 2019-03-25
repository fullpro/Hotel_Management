package com.example.dell.hotel_management;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Room> mData;

    public RecyclerViewAdapter(Context mContext, List<Room> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.room_type.setText(mData.get(position).getRoomType());
        holder.room_desc.setText(mData.get(position).getDescription());
        holder.img_room_thumbnail.setImageResource(mData.get(position).getThumbnail());

        //set click listener

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //passing data to booking activity
                Intent intent = new Intent(mContext,BookingActivity.class);
                intent.putExtra("RoomType",mData.get(position).getRoomType());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());

                //start booking activity
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView room_type, room_desc;
        ImageView img_room_thumbnail;

        public MyViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardview);
            room_type = (TextView)itemView.findViewById(R.id.Room_type);
            room_desc = (TextView) itemView.findViewById(R.id.Room_desc);
            img_room_thumbnail = (ImageView) itemView.findViewById(R.id.Room_type_image);


        }
    }



}
