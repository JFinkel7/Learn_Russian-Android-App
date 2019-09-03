/* Software Developer: Denis J Finkel || * (Learn Russian App)
 * Project Start Date: 5/25/2019

 * */
/* <-- All Libraries Credit Goes To -->
 * ***> Drew Heavner   : For - Slidr Animation:      (https:github.com/r0adkll/Slidr)   <***
 * ***> hajiyevelnur92 : For - Intent Animation:     (https://github.com/hajiyevelnur92/intentanimation) <***
 * ***> GrenderG/Toasty: For - Custom Toast Message: (https://github.com/GrenderG/Toasty) <***
 * */
package com.jfinkelstudios.mobile.learnrussianbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import Basics.BasicsMainMenu;
import Basics2.Basics2;
import Basics3.Basics3;


public class MainActivity extends AppCompatActivity {
    //**
    // - Animation
    private static final int BLINK_ANIMATION_EFFECT = R.anim._blink_effect;

    //**
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    // <-*** To Main Menu Basics Activity ***->
    public void btn_BasicsActivity(View btn_BasicsActivity) {
        //soundEffects.playSoundEffect();
        final Animation BLINK_EFFECT = AnimationUtils.loadAnimation(MainActivity.this, MainActivity.BLINK_ANIMATION_EFFECT);
        btn_BasicsActivity.startAnimation(BLINK_EFFECT);
        startActivity(new Intent(MainActivity.this, BasicsMainMenu.class));

    }

    // <-***  To Basics(II) Activity ***->
    public void btn_BasicsIIActivity(View btn_BasicsIIActivity) {
        final Animation BLINK_EFFECT = AnimationUtils.loadAnimation(MainActivity.this, MainActivity.BLINK_ANIMATION_EFFECT);
        btn_BasicsIIActivity.startAnimation(BLINK_EFFECT);
        startActivity(new Intent(MainActivity.this, Basics2.class));

    }

    // <-***  To Basics(III) Activity ***->
    public void btn_BasicsIIIActivity(View view) {
        startActivity(new Intent(MainActivity.this, Basics3.class));
    }

    @Override
    public void onBackPressed() {
    }


}// END OF CLASS
