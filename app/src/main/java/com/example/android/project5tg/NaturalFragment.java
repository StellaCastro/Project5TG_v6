package com.example.android.project5tg;

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


public class NaturalFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_layout, container, false);

        //the array list that will be presented on the screen
        final ArrayList<Place> placesToVisit = new ArrayList<Place>();
        placesToVisit.add(new Place("Yunque Rain Forest","Lagerst Rainforest in the Caribbean",
                R.drawable.yunque9, "geo:18.352236, -65.800307"));
        placesToVisit.add(new Place("Bioluminicent Lagoon","One of the 5 bioluminescent places in the world.",
                R.drawable.bioluminesent_bay, "geo:18.376533, -65.623541"));
        placesToVisit.add(new Place("Dry Forest","Small forest filled with succulent plant and trees.",
                R.drawable.bosque_seco, "geo:17.983777, -66.869323"));
        placesToVisit.add(new Place("Flamenco Beach","The 3rd most beutifull beach in the world,",
                R.drawable.flamenco_beach, "geo:18.328684, -65.318981"));
        placesToVisit.add(new Place("Window Cave","Scenic natural cave on a limestone cliff",
                R.drawable.cuevaentana,"geo:18.374817, -66.692269"));
        placesToVisit.add(new Place("Tamarindo Beach","Crystal clear water beach great for snorkeling.",
                R.drawable.tamarindo,"geo:18.318457, -65.317815"));



        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesToVisit, R.color.secret_color);
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
