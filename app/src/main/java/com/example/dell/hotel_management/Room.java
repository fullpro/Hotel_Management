package com.example.dell.hotel_management;

public class Room {

    private  String RoomType;
    private String Description;
    private int Thumbnail;


    public Room() {

    }

    public Room(String roomType, String description, int thumbnail) {
        RoomType = roomType;
        Description = description;
        Thumbnail = thumbnail;
    }


    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String roomType) {
        RoomType = roomType;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
