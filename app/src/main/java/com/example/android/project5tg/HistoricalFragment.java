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


public class HistoricalFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.list_layout, container, false);
        //the array list that will be presented on the screen
        final ArrayList<Place> placesToVisit = new ArrayList<Place>();
        placesToVisit.add(new Place("San Felipe Castle","Built by spain to protect the entrance to the bay",
                R.drawable.morro, "geo:18.471179, -66.123485"));
        placesToVisit.add(new Place("San Cristobal Castle","Fort built by Spay to protect the city",
                R.drawable.castillo_san_cristobal, "geo:18.467703, -66.111214"));
        placesToVisit.add(new Place("Cabo Rojo Lighthouse","Lighthouse still using its original equipment.",
                R.drawable.caborojo_lighthouse, "geo:17.938011, -67.194282"));
        placesToVisit.add(new Place("Porta Coeli Church","Oldest church in U.S. jurisdiction",
                R.drawable.porto_coeli, "geo:18.082621, -67.033598"));
        placesToVisit.add(new Place("Arecibo Observatory","One of the largest radio telescope in the world",
                R.drawable.arecibo_observatory, "geo:18.346606, -66.752830"));
        placesToVisit.add(new Place("Hacienda Buena Vista","Best Preserved coffee plantation on P.R.",
                R.drawable.hacienda_buenavista, "geo:18.084361, -66.654656"));

        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesToVisit, R.color.historica_color);
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


