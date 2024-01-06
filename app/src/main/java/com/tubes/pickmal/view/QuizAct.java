package com.tubes.pickmal.view;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tubes.pickmal.R;
import com.tubes.pickmal.databinding.ActivityQuizBinding;
import com.tubes.pickmal.dialog.DialogFinished;
import com.tubes.pickmal.model.QuizModel;
import com.tubes.pickmal.utils.TopScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class QuizAct extends AppCompatActivity {
    private ActivityQuizBinding binding;
    private List<QuizModel> questions = new ArrayList();
    private int state = 0;
    private TextView[] optionsButton;
    private int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        optionsButton = new TextView[]{
                binding.option1,
                binding.option2,
                binding.option3,
                binding.option4,
        };
        createQuestion();
        binding.imgPlaySound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPlayer(questions.get(state).getSfx());
            }
        });
        optionsButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuiz(0);
            }
        });
        optionsButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuiz(1);
            }
        });
        optionsButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuiz(2);
            }
        });
        optionsButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerQuiz(3);
            }
        });
        Log.d("DEBUG_STATE", "Size"+questions.size());
    }
    private void answerQuiz(int idx){
        Log.d("DEBUG_STATE", "State"+state);
        if(state < questions.size()){
            if(questions.get(state).getCorrectIndex() == idx){
                soundPlayer(R.raw.sfx_correct);
                score += 10;
            } else {
                soundPlayer(R.raw.sfx_wrong);
            }
            if(state != questions.size() - 1){
                binding.imgAnimal.setImageResource(questions.get(state+1).getImage());
                String[] options = questions.get(state+1).getOptions();
                for(int i =0; i < optionsButton.length; i++){
                    optionsButton[i].setText(options[i]);
                }
            }
            state++;
        }

        if(state == questions.size()){

            String scoreStr = String.valueOf(score);
            DialogFinished skorDialog = new DialogFinished(this, scoreStr);
            skorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            skorDialog.show();
            skorDialog.setOnKeyListener((arg0, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;
                }
                return true;
            });
            Window window = skorDialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.WRAP_CONTENT);
            if(score > TopScore.getTopScore(QuizAct.this)){
                soundPlayer(R.raw.sfx_new_record);
            } else {
                soundPlayer(R.raw.sfx_congratulation);
            }

            TopScore.setTopScore(QuizAct.this, score);
        }
    }

    private void createQuestion() {
        //public QuizModel(int image, String[] options, int sfx, int correctIndex)
        questions.add(new QuizModel(R.drawable.question_sapi, new String[]{"Serigala", "Sapi", "Harimau", "Kuda"},  R.raw.sfx_sapi, 1));
        questions.add(new QuizModel(R.drawable.question_babi, new String[]{"Kucing", "Sapi", "Ayam", "Babi"},  R.raw.sfx_babi, 3));
        questions.add(new QuizModel(R.drawable.question_gajah, new String[]{"Serigala", "Rakun", "Rusa", "Gajah"},  R.raw.sfx_gajah, 3));
        questions.add(new QuizModel(R.drawable.question_harimau, new String[]{"Serigala", "Sapi", "Harimau", "Kuda"},  R.raw.sfx_harimau, 2));
        questions.add(new QuizModel(R.drawable.question_kambing, new String[]{"Kambing", "Harimau", "Sapi", "Kuda"},  R.raw.sfx_kambing, 0));
        questions.add(new QuizModel(R.drawable.question_kuda, new String[]{"Serigala", "Kuda", "Kudanil", "Rubah"},  R.raw.sfx_kuda, 1));
        questions.add(new QuizModel(R.drawable.question_kudanil, new String[]{"Kudanil", "Kuda", "Ayam", "Kambing"},  R.raw.sfx_kudanil, 0));
        questions.add(new QuizModel(R.drawable.question_rakun, new String[]{"Rakun", "Rubah", "Harimau", "Serigala"},  R.raw.sfx_rakun, 0));
        questions.add(new QuizModel(R.drawable.question_rubah, new String[]{"Serigala", "Sapi", "Rakun", "Rubah"},  R.raw.sfx_rubah, 3));
        questions.add(new QuizModel(R.drawable.question_serigala, new String[]{"Harimau", "Sapi", "Serigala", "Kuda"},  R.raw.sfx_serigala, 2));
        Collections.shuffle(questions);
        binding.imgAnimal.setImageResource(questions.get(state).getImage());
        String[] options = questions.get(state).getOptions();
        for(int i =0; i < optionsButton.length; i++){
            optionsButton[i].setText(options[i]);
        }
    }

    private void soundPlayer(int sound) {
        MediaPlayer mediaPlayer = MediaPlayer.create(QuizAct.this, sound);
        mediaPlayer.start();
        mediaPlayer.setLooping(false);
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(QuizAct.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Anda yakin ingin kembali?")
                .setContentText("Score tidak akan tersimpan jika game belum selesai")
                .setConfirmText("Ya!")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        finish();
                    }
                })
                .setCancelButton("Tidak", new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                    }
                })
                .show();
    }
}
