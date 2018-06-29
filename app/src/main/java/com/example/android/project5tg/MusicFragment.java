package com.example.android.project5tg;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;



public class MusicFragment extends Fragment {

    private MediaPlayer myPlayer;
    private AudioManager mAudioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                myPlayer.pause();
                myPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                myPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                myPlayer.stop();
                releaseMediaPlayer();
            }
        }
        
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp){
            releaseMediaPlayer();
        }
    };


    public MusicFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_layout, container, false);


        mAudioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Place> placesToVisit = new ArrayList<Place>();
        placesToVisit.add(new Place("Bomba",
                R.drawable.bomba,R.raw.bomba));
        placesToVisit.add(new Place("Plena",
                R.drawable.plena, R.raw.plena_libre));
        placesToVisit.add(new Place("Salsa",
                R.drawable.salsa, R.raw.salsa));
        placesToVisit.add(new Place("Merengue",
                R.drawable.merengue, R.raw.merengue));
        placesToVisit.add(new Place("Bachata",
                R.drawable.bachata, R.raw.bachata));
        placesToVisit.add(new Place("Regueton",
                R.drawable.regueton, R.raw.regueton));



        PlaceAdapter adapter = new PlaceAdapter(getActivity(), placesToVisit, R.color.music_color);
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        //this method will receive the position of the item the user selected and start playing the song of the item
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Place myplace = placesToVisit.get(i);

                //requesting Audio Focus
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
                        //use the music stream
                        AudioManager.STREAM_MUSIC,
                        //use request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    myPlayer = MediaPlayer.create(getActivity(), myplace.getmMusic());
                    myPlayer.start();
                    myPlayer.setOnCompletionListener(mCompletionListener);
                }
            }

        });

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        myPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPlayer.stop();
    }

    @Override
    public void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (myPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            myPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            myPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

}
