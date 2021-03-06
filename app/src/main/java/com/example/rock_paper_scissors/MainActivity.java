package com.example.rock_paper_scissors;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageSwitcher is;
    ImageView ivPlayer, imComputer, imPlayer;
    int[] imgArray = {R.drawable.rock, R.drawable.paper, R.drawable.scissors};
    int player = 0, computer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        is = findViewById(R.id.is);
        ivPlayer = findViewById(R.id.ivPlayer);
        imComputer = findViewById(R.id.imComputer);
        imPlayer = findViewById(R.id.imPlayer);

        setImageSwitcher();
    }

    private void setImageSwitcher() {
        is.setFactory(() -> new ImageView(getApplicationContext()));

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        is.setInAnimation(in);
        is.setOutAnimation(out);
        is.setImageResource(R.drawable.rock);
    }

    public void ibClick(View v) {
        int id = v.getId();

        if (id == R.id.ibRock) {
            player = 0;
            ivPlayer.setImageResource(R.drawable.rock);
        } else if (id == R.id.ibPaper) {
            player = 1;
            ivPlayer.setImageResource(R.drawable.paper);
        } else if (id == R.id.ibScissors) {
            player = 2;
            ivPlayer.setImageResource(R.drawable.scissors);
        }

        isTimer.start();
    }

    private void showResult() {
        String result = "";
        Animation animation = getAnimationSetting();
        int count = player - computer;

        if (count == 0) {
            result = "Draw";
            imPlayer.startAnimation(animation);
            imComputer.startAnimation(animation);
        } else if (count == 1 || count == -2) {
            result = "You Win";
            imPlayer.startAnimation(animation);
        } else if (count == 2 || count == -1) {
            result = "You Lose";
            imComputer.startAnimation(animation);
        }

        Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
    }

    private final CountDownTimer isTimer = new CountDownTimer(1500, 200) {
        @Override
        public void onTick(long millisUntilFinished) {
            computer = (int) (Math.random() * 100.0) % 3;
            is.setImageResource(imgArray[computer]);
        }

        @Override
        public void onFinish() {
            computer = (int) (Math.random() * 100.0) % 3;
            is.setImageResource(imgArray[computer]);
            showResult();
        }
    };

    private Animation getAnimationSetting() {
        Animation animation = new AlphaAnimation(1, 0);

        animation.setDuration(300);
        animation.setInterpolator(new AccelerateDecelerateInterpolator());
        animation.setRepeatCount(3);
        animation.setRepeatMode(Animation.REVERSE);

        return animation;
    }
}