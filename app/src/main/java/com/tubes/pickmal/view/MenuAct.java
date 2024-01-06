package com.tubes.pickmal.view;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.tubes.pickmal.R;
import com.tubes.pickmal.databinding.ActivityMenuBinding;
import com.tubes.pickmal.utils.SoundManager;
import com.tubes.pickmal.utils.TopScore;

public class MenuAct extends AppCompatActivity {
    private ActivityMenuBinding binding;
    private MediaPlayer clickedSoundEfect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        clickedSoundEfect = MediaPlayer.create(this, R.raw.click);
        SoundManager.Play(this, R.raw.jungle_theme, true);
        binding.txtTopscore.setText(""+TopScore.getTopScore(MenuAct.this));
        binding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedSoundEffect();
                startActivity(new Intent(MenuAct.this, QuizAct.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.txtTopscore.setText(""+TopScore.getTopScore(MenuAct.this));
    }

    private void clickedSoundEffect() {
        clickedSoundEfect.start();
        clickedSoundEfect.setLooping(false);
    }
}
