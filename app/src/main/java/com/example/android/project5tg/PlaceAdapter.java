package com.example.android.project5tg;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> {
    private int bColor;

    public PlaceAdapter(Activity context, ArrayList<Place> placeToVisit, int colorResource){
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, placeToVisit);
        bColor=colorResource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.items, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Place currentNumber = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.name_id);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        if(currentNumber.hasDetail()){
            nameTextView.setText(currentNumber.getPlaceName());
        }else {
            nameTextView.setText(currentNumber.getPlaceName());
            nameTextView.setTextSize(30);
            nameTextView.setGravity(Gravity.CENTER_VERTICAL);


        }





        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView desTextView = (TextView) listItemView.findViewById(R.id.description_id);
        if(currentNumber.hasDetail()){
            desTextView.setText(currentNumber.getPlaceDetail());
            desTextView.setVisibility(View.VISIBLE);
        } else {
            desTextView.setVisibility(View.GONE);
        }


        //place the image in the items of the list
        //hides the image view if there is no image on the object
        //replace the location with the play icon
        ImageView placeIma = (ImageView) listItemView.findViewById(R.id.image_id);
        placeIma.setImageResource(currentNumber.getPlaceImage());

        ImageView locIcon = (ImageView) listItemView.findViewById(R.id.location_icon);
        if(currentNumber.hasSound()){
            locIcon.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        }else{
            locIcon.setImageResource(R.drawable.location);
        }
        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), bColor);
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }

}
