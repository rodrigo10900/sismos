package com.rodrigo.sismos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rodrigo.sismos.C;
import com.rodrigo.sismos.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by rodrigo on 13/12/17.
 */

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imgIcono = (ImageView) findViewById(R.id.imgIcono);
        Animation animacion = AnimationUtils.loadAnimation(this, R.anim.splash_icon);
        animacion.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(ActivitySplash.this, MainActivity.class);
                        startActivity(intent);
                        ActivitySplash.this.finish();
                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask, C.k.SPLASH_DELAY);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        imgIcono.startAnimation(animacion);
    }
}
