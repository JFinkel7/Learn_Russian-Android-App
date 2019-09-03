package Basics3;
/* <-- All Libraries Credit Goes To -->
 * ***> Drew Heavner   : For - Slidr Animation:      (https:github.com/r0adkll/Slidr)   <***
 * */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jfinkelstudios.mobile.learnrussianbeta.MainActivity;
import com.jfinkelstudios.mobile.learnrussianbeta.R;
import com.r0adkll.slidr.Slidr;

import Quiz.Question;
import Quiz.Solace;
import Quiz.SoundEffects;

public class Basics3 extends AppCompatActivity {
    //*
    // - Views
    private TextView txtView_EnglishQuestion;
    private RadioGroup radioBtnGroup;
    private ProgressBar progressBar;
    // - Classes
    private Question basics3;
    private SoundEffects soundEffects;
    // - String Arrays RESOURCE ID'S
    private static final int RUSSIAN_QUESTION_RESOURCE_ID = R.array.QRussian_Basics3;
    private static final int ENGLISH_QUESTION_RESOURCE_ID = R.array.QEnglish_Basics3;
    // - Integers
    private static int currentProgressPoints = 0;
    private static final int CORRECT_ANSWER_POINT = 25;
    private static final int INCORRECT_ANSWER_POINT = 25;
    private static final int WINNING_POINT = 95;

    //*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics3);
        //*
        /*****ACTIVITY Slidr*****/
        Slidr.attach(Basics3.this);
        /*****TEXT TO SPEECH INITIALIZATION*****/
        Solace.initializeTextToSpeech(Basics3.this);
        /*****FindView By ID'S*****/
        progressBar = findViewById(R.id.toolbarProgressBar);
        txtView_EnglishQuestion = findViewById(R.id.txtView_EnglishQuestion);
        radioBtnGroup = findViewById(R.id.radioGroup);
        /*****TOOLBAR CONFIGURATION*****/
        Toolbar levelOneToolbar = findViewById(R.id.toolbar_ActivityLevelOne);
        setSupportActionBar(levelOneToolbar);
        /*****Sound Effects*****/
        soundEffects = new SoundEffects(Basics3.this, 2);
        soundEffects.setSuccessSound(R.raw.sound_success1);
        soundEffects.setFailureSound(R.raw.sound_error_2);
        /*****QUESTION CLASS*****/
        basics3 = new Question(Basics3.this, soundEffects, RUSSIAN_QUESTION_RESOURCE_ID, ENGLISH_QUESTION_RESOURCE_ID);
        basics3.generateRandomQuestion(txtView_EnglishQuestion);
        /*****Text To Speech*****/
        Question.isQuestionTouched(txtView_EnglishQuestion);
        //*
    }

    // Returns Russian Word Index
    public int doesRussianWordEqual(String radioBtnTxt) {
        int i;
        String[] russianWords = Basics3.this.getApplication().getResources().getStringArray(RUSSIAN_QUESTION_RESOURCE_ID);
        for (i = 0; i < russianWords.length; i++) {
            if (russianWords[i].equals(radioBtnTxt)) {
                return (i);
            }
        }
        return (0);
    }

    public void isRadioBtnCorrect(String txtViewWord, String radioBtnTxt) {
        String[] englishWords = Basics3.this.getApplication().getResources().getStringArray(ENGLISH_QUESTION_RESOURCE_ID);
        // If Correct ↓
        if (englishWords[doesRussianWordEqual(radioBtnTxt)].equals(txtViewWord)) {
            if (!(currentProgressPoints >= WINNING_POINT)) {
                basics3.generateRandomQuestion(txtView_EnglishQuestion);
                soundEffects.playSuccessSound();
                progressBar.setProgress(currentProgressPoints += CORRECT_ANSWER_POINT);
            } else {// IF PLayer Wins -> Back To MainMenu
                Toast.makeText(Basics3.this, "You Win", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Basics3.this, MainActivity.class));
                finish();
            }
        } else {// IF Wrong ↓
            basics3.generateRandomQuestion(txtView_EnglishQuestion);
            soundEffects.playFailureSound();
            if (!(currentProgressPoints <= 0)) {
                progressBar.setProgress(currentProgressPoints -= INCORRECT_ANSWER_POINT);
            }
        }
    }

    // Button To Continue To The Next Question
    public void btn_Continue(View view) {
        int getRadioBtnIds = this.radioBtnGroup.getCheckedRadioButtonId();
        // Checks If RadioButton Has Been At Least Selected
        if (getRadioBtnIds != -1) {
            RadioButton radioButton = findViewById(getRadioBtnIds);
            final String ENGLISH_TXT_VIEW_WORD = this.txtView_EnglishQuestion.getText().toString();
            final String RADIO_BUTTON_TXT = radioButton.getText().toString();
            isRadioBtnCorrect(ENGLISH_TXT_VIEW_WORD, RADIO_BUTTON_TXT);
        } else {
            new AlertDialog.Builder(Basics3.this)
                    .setTitle("ERROR")
                    .setMessage("Please Select Choice")
                    .create().show();
        }
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

}
