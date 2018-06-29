package com.example.android.project5tg;

public class Place {
    private String mPlaceName;
    private int mPlaceImage;
    private String mPlaceDetail = NO_DETAIL;
    private int mSampleMusic = NO_SOUND;
    private static final int NO_SOUND = -1;
    private static final String NO_DETAIL= " ";
    private String mLocation;


    public  Place (String placeName,  String placeDetail,  int placeImage, String location){
        mPlaceName = placeName;
        mPlaceDetail = placeDetail;
        mPlaceImage = placeImage;
        mLocation = location;
    }
    public  Place (String placeName,  int placeImage, int sampleMusic){
        mPlaceName = placeName;
        mPlaceImage = placeImage;
        mSampleMusic = sampleMusic;
    }
    public String getPlaceName(){
        return mPlaceName;
    }

    public String getPlaceDetail(){
        return mPlaceDetail;
    }

    public int getPlaceImage(){
        return mPlaceImage;
    }

    public int getmMusic (){
        return mSampleMusic;
    }

    public boolean hasSound (){
       return mSampleMusic != NO_SOUND;
    }
    public boolean hasDetail (){
        return mPlaceDetail != NO_DETAIL;
    }

    public String getLocation() {
        return mLocation;
    }
}
