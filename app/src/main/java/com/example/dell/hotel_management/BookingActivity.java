package com.example.dell.hotel_management;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class BookingActivity extends AppCompatActivity {

    private TextView tvRoomType,tvDescription;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        tvRoomType = (TextView) findViewById(R.id.txtRoomType);
        tvDescription = (TextView) findViewById(R.id.txtDescription);
        img = (ImageView) findViewById(R.id.roomThumbnail);

        //Recieve data
        Intent intent =  getIntent();
        String RoomType = intent.getExtras().getString("RoomType");
        String Description = intent.getExtras().getString("Description");
        int image = intent.getExtras().getInt("Thumbnail");

        //Setting values
        tvRoomType.setText(RoomType);
        tvDescription.setText(Description);
        img.setImageResource(image);
    }
}
