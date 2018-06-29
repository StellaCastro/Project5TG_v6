package com.example.android.project5tg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class RestaurantFragment extends Fragment {







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        //the array list that will be presented on the screen
        final ArrayList<Place> placesToVisit = new ArrayList<Place>();
        placesToVisit.add(new Place("Raices","Puerto Rican style rustic theme restaurant. ",
                R.drawable.raices_restaurant, "geo:18.465411, -66.113332"));
        placesToVisit.add(new Place("Aurorita","One of the best mexican style restaurant in P.R. ",
                R.drawable.aurorita, "geo:18.415818, -66.088949"));
        placesToVisit.add(new Place("Cueva del Mar","One of the best seafood restaurant in P.R.",
                R.drawable.cueva_del_mar, "geo:18.395860, -66.106168"));
        placesToVisit.add(new Place("Levi's","Great sport bar restaurant for steak.",
                R.drawable.levis, "geo:18.453411, -66.076547"));
        placesToVisit.add(new Place("Oceano","Caribbean style restaurant with great view.",
                R.drawable.oceano, "geo:18.457382, -66.072595"));



        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesToVisit, R.color.restaurnt_color);
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        //this method will receive the position of the item the user selected and open maps showing the coordinates from the place
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place myPlace = placesToVisit.get(i);
                Uri getPlaceLocation = Uri.parse(myPlace.getLocation());
                Intent locationIntent = new Intent(Intent.ACTION_VIEW, getPlaceLocation);
                    startActivity(locationIntent);

            }

        });
        return rootView;
    }


}
