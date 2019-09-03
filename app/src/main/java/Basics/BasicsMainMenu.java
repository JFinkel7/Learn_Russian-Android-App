package Basics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jfinkelstudios.mobile.learnrussianbeta.MainActivity;
import com.jfinkelstudios.mobile.learnrussianbeta.R;
import com.r0adkll.slidr.Slidr;

import maes.tech.intentanim.CustomIntent;

public class BasicsMainMenu extends AppCompatActivity {
    // **
    private static final String INTENT_ANIMATION_UP_TO_BOTTOM = "up-to-bottom";
    private static final String INTENT_ANIMATION_BOTTOM_TO_UP = "bottom-to-up";
    private static final String INTENT_ANIMATION_RIGHT_TO_LEFT = "right-to-left";

    // **
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics1_main);
        // ** Activity Configurations **
        Slidr.attach(BasicsMainMenu.this);
    }

    //** Launches To BasicsLvlOne Activity **
    public void btn_BasicsLvlOne(View view) {
        startActivity(new Intent(BasicsMainMenu.this, BasicsLvlOne.class));
        CustomIntent.customType(BasicsMainMenu.this, BasicsMainMenu.INTENT_ANIMATION_UP_TO_BOTTOM);
    }

    //** Launches To BasicsLvlTwo Activity **
    public void btn_BasicsLvlTwo(View view) {
        startActivity(new Intent(BasicsMainMenu.this, BasicsLvlTwo.class));
        CustomIntent.customType(BasicsMainMenu.this, BasicsMainMenu.INTENT_ANIMATION_BOTTOM_TO_UP);
    }

    //** Launches To Main Activity **
    public void btn_MainActivity(View view) {
        startActivity(new Intent(BasicsMainMenu.this, MainActivity.class));
        CustomIntent.customType(BasicsMainMenu.this, BasicsMainMenu.INTENT_ANIMATION_RIGHT_TO_LEFT);

    }

    @Override
    public void onBackPressed() {
    }
}