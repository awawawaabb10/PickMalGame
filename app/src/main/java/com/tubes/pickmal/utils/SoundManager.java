package com.tubes.pickmal.utils;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundManager {
    public static MediaPlayer mediaPlayer;

    public static void Play(Context ctx, int raw_id, Boolean kondisi) {
        mediaPlayer = MediaPlayer.create(ctx, raw_id);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(20, 20);

        if (kondisi) {
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
        }
    }

    public static void Pause(){
        if(mediaPlayer != null){
            mediaPlayer.pause();
        }
    }

    public static void Resume(){
        if(mediaPlayer != null){
            mediaPlayer.start();
        }
    }
}
