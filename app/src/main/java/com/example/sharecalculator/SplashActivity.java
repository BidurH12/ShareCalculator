package com.example.sharecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView logo;
    TextView text;
    RelativeLayout relative;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo=findViewById(R.id.logo);
        text=findViewById(R.id.text);
        relative=findViewById(R.id.relative);
        Intent intent=new Intent(this, MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                relative.setBackgroundResource(R.color.bidur);
            }
        },2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);

            }
        },3500);



        Animation blink= AnimationUtils.loadAnimation(this,R.anim.logo);
        logo.startAnimation(blink);


    }
}