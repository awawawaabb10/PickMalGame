package com.tubes.pickmal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TopScore {
    static final String KEY_TOP_SCORE ="TopScore";
    private static SharedPreferences getSharedPreference(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static int getTopScore(Context context){
        return getSharedPreference(context).getInt(KEY_TOP_SCORE,0);
    }

    public static void setTopScore(Context context, int score){
        if(score > getTopScore(context)){
            SharedPreferences.Editor editor = getSharedPreference(context).edit();
            editor.putInt(KEY_TOP_SCORE, score);
            editor.apply();
        }
    }
}
