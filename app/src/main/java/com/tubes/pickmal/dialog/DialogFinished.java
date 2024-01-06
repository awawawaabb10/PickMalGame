package com.tubes.pickmal.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tubes.pickmal.R;
public class DialogFinished extends Dialog {
    public Activity activity;
    public TextView tvSkor, tvNama;
    public ImageButton btnOk;
    public String s;
    MediaPlayer mediaPlayer;

    public DialogFinished(Activity a, String skor) {
        super(a);
        this.activity = a;
        this.s = skor;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_finished);
        btnOk = findViewById(R.id.btn_finish);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(activity, R.raw.click);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
                activity.finish();
            }
        });
        tvSkor = findViewById(R.id.item_tvSkor);
        tvSkor.setText(s);
    }
}
