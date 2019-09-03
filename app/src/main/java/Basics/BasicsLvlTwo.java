package Basics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jfinkelstudios.mobile.learnrussianbeta.R;

import java.util.Objects;

import Quiz.Question;
import Quiz.Solace;
import Quiz.SoundEffects;
import maes.tech.intentanim.CustomIntent;

public class BasicsLvlTwo extends AppCompatActivity {
    //*************
    // Views
    private TextView txtView_RussianQuestion;
    private EditText editTxt_UserInput;
    private ProgressBar progressBar;
    // Data Types
    private final double CORRECT_ANSWER_POINT = 10;
    private final double INCORRECT_ANSWER_POINT = 10;
    private final double WIN_NUMBER_POINT = 150;
    // Resource ID's
    private static final int ENGLISH_QUESTION_RESOURCE_ID = R.array.QEnglish_BasicsLvl2;
    private static final int RUSSIAN_QUESTION_RESOURCE_ID = R.array.QRussian_BasicsLvl2;
    // Intent Animations Strings
    private static final String INTENT_ANIMATION_FADE_IN_AND_OUT = "fadein-to-fadeout";
    // Classes
    private Question question;
    private SoundEffects soundEffects;

    //*************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics1_level2);
        //*
        /*****TEXT TO SPEECH*****/
        Solace.initializeTextToSpeech(BasicsLvlTwo.this);
        /*****FindView By ID'S*****/
        txtView_RussianQuestion = findViewById(R.id.txtView_RussianQuestion);
        editTxt_UserInput = findViewById(R.id.editTxt_UserInput);
        Button btn_Confirm = findViewById(R.id.BtnConfirmQuestion);
        progressBar = findViewById(R.id.toolbarProgressBar);
        Toolbar levelOneToolbar = findViewById(R.id.toolbar_ActivityLevelOne);
        /*****Text To Speech On Touch Event*****/
        Question.isQuestionTouched(txtView_RussianQuestion);
        /*****Sound Effects*****/
        soundEffects = new SoundEffects(BasicsLvlTwo.this, 2);
        soundEffects.setSuccessSound(R.raw.sound_success1);
        soundEffects.setFailureSound(R.raw.sound_error_2);
        /*****TOOLBAR CONFIGURATION*****/
        setSupportActionBar(levelOneToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        /*****QUESTION CLASS*****/
        question = new Question(BasicsLvlTwo.this, soundEffects, ENGLISH_QUESTION_RESOURCE_ID, RUSSIAN_QUESTION_RESOURCE_ID);
        question.generateRandomQuestion(txtView_RussianQuestion);
        /*****CALLED ACTIVITY METHODS*****/
        btn_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                question.setTextViewQuestion(txtView_RussianQuestion);
                question.setProgressBar(progressBar);
                question.setCorrectPoints(CORRECT_ANSWER_POINT);
                question.setInCorrectPoints(INCORRECT_ANSWER_POINT);
                question.setWinNumber(WIN_NUMBER_POINT);
                question.setEditTextUserInput(editTxt_UserInput);
                question.check_Question();
            }
        });
        //*
    }


    // ** Inflates Toolbar Menu **
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ** Toolbar Item Selection Actions **
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.exit) {
            startActivity(new Intent(BasicsLvlTwo.this, BasicsMainMenu.class));
            CustomIntent.customType(BasicsLvlTwo.this, BasicsLvlTwo.INTENT_ANIMATION_FADE_IN_AND_OUT);

            // IF They Exit Subtract Some Points 0
            return (true);
        } else if (item.getItemId() == R.id.translationInfo) {
            question.generateLanguageTranslation(txtView_RussianQuestion);
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Solace.shutdownSpeech();
        soundEffects.disposeSoundEffects();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Solace.stopSpeech();
        soundEffects.disposeSoundEffects();
    }

    @Override
    public void onBackPressed() {
    }
}
