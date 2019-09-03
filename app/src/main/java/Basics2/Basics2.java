package Basics2;
/* <-- All Libraries Credit Goes To -->
 * ***> Drew Heavner   : For - Slidr Animation:      (https:github.com/r0adkll/Slidr)   <***
 * */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.jfinkelstudios.mobile.learnrussianbeta.MainActivity;
import com.jfinkelstudios.mobile.learnrussianbeta.R;
import com.r0adkll.slidr.Slidr;

import Quiz.Question;
import Quiz.Solace;
import Quiz.SoundEffects;

public class Basics2 extends AppCompatActivity {
    // Views
    private TextView txtView_RussianQuestion;
    private ProgressBar progressBar;
    private TextSwitcher txtSwitcher;
    private TextView txtView;
    // Data Types
    private static final int RUSSIAN_QUESTION_RESOURCE_ID = R.array.QRussian_Basics2;
    private static final int ENGLISH_QUESTION_RESOURCE_ID = R.array.QEnglish_Basics2;
    private static int currentProgressPoints = 0;
    private static final int CORRECT_ANSWER_POINT = 16;
    private static final int INCORRECT_ANSWER_POINT = 17;
    private static final int WIN_NUMBER_POINT = 95;
    private int stringIndex = 0;
    private String[] englishWordRow;
    private String[] russianWordRow;
    private static final String EMPTY_STRING = "";
    // Classes
    private Question basics2Question;
    private SoundEffects soundEffects;

    //*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basics2);
        /*****SETS VIEW ID'S*****/
        txtView_RussianQuestion = findViewById(R.id.txtView_RussianQuestion);
        Toolbar toolbarBasics2 = findViewById(R.id.toolbar_ActivityBasics2);
        progressBar = findViewById(R.id.toolbarProgressBar);
        txtSwitcher = findViewById(R.id.txtSwitcher);
        //*
        /*****INITIALIZE TEXT TO SPEECH*****/
        Solace.initializeTextToSpeech(Basics2.this);
        /*****Text To Speech On Touch Event*****/
        Question.isQuestionTouched(txtView_RussianQuestion);
        /*****ACTIVITY Slidr*****/
        Slidr.attach(Basics2.this);
        /*****INFLATES THE TOOLBAR*****/
        setSupportActionBar(toolbarBasics2);
        /*****Sound Effects*****/
        soundEffects = new SoundEffects(Basics2.this, 2);
        soundEffects.setSuccessSound(R.raw.sound_success1);
        soundEffects.setFailureSound(R.raw.sound_error_2);
        /*****QUESTION CLASS*****/
        basics2Question = new Question(Basics2.this, soundEffects, ENGLISH_QUESTION_RESOURCE_ID, RUSSIAN_QUESTION_RESOURCE_ID);
        basics2Question.generateRandomQuestion(txtView_RussianQuestion);
        basics2Question.setTextViewQuestion(txtView_RussianQuestion);
        // <***** LEFT OFF FROM HERE DATE: 8/30/19 -> 8:30PM *****>
        basics2Question.setProgressBar(progressBar);
        basics2Question.setCorrectPoints(CORRECT_ANSWER_POINT);
        basics2Question.setInCorrectPoints(INCORRECT_ANSWER_POINT);
        basics2Question.setWinNumber(WIN_NUMBER_POINT);
        //
        englishWordRow = Basics2.this.getResources().getStringArray(ENGLISH_QUESTION_RESOURCE_ID);
        russianWordRow = Basics2.this.getResources().getStringArray(RUSSIAN_QUESTION_RESOURCE_ID);

        //** Builds A TextView Within The TextSwitcher **
        txtSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                txtView = new TextView(Basics2.this);
                txtView.setTextSize(45);
                txtView.setTextColor(getResources().getColor(R.color.Black));
                txtView.setGravity(Gravity.CENTER_HORIZONTAL);
                return (txtView);
            }
        });
        txtView.setText(englishWordRow[stringIndex]);

    }// END OF CREATE

    // ** Gets Text From [TextSwitcher] **
    public String getTextFromTxtSwitcher() {
        this.txtSwitcher.setCurrentText(englishWordRow[stringIndex]);
        this.txtView = (TextView) txtSwitcher.getCurrentView();
        if (txtView.getText().toString().length() > 0) {
            return (txtView.getText().toString());
        }
        return (EMPTY_STRING);
    }

    // ** Returns Index Of English Words **
    public int doesEnglishWordEqual(String text) {
        int i;
        String[] englishWords = Basics2.this.getApplication().getResources().getStringArray(ENGLISH_QUESTION_RESOURCE_ID);
        for (i = 0; i < englishWords.length; i++) {
            if (englishWords[i].equals(text)) {
                return (i);
            }
        }
        return (0);
    }

    // ** [Button] Next Word **
    public void btnNextWord(View view) {
        // IF Index Reaches The-> Start Again
        if (stringIndex == englishWordRow.length - 1) {
            stringIndex = 0;
            txtSwitcher.setText(englishWordRow[stringIndex]);
        }// Else Keep Going
        else {
            txtSwitcher.setText(englishWordRow[++stringIndex]);
        }
    }

    // ** [Button] Continue **
    public void btnContinue(View view) {
        String txtViewQuestion = basics2Question.getTextViewQuestion().getText().toString();
        if (russianWordRow[doesEnglishWordEqual(getTextFromTxtSwitcher())].equals(txtViewQuestion)) {
            // IF CORRECT ↓
            if (!(currentProgressPoints >= WIN_NUMBER_POINT)) {
                soundEffects.playSuccessSound();
                basics2Question.generateRandomQuestion(txtView_RussianQuestion);
                progressBar.setProgress(currentProgressPoints += CORRECT_ANSWER_POINT);
            } else {// IF PLayer Wins -> Back To MainMenu
                Toast.makeText(Basics2.this, "You Win", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Basics2.this, MainActivity.class));
                finish();
            }
        } else { // IF WRONG ↓
            basics2Question.generateRandomQuestion(txtView_RussianQuestion);
            soundEffects.playFailureSound();
            if (!(currentProgressPoints <= 0)) {
                progressBar.setProgress(currentProgressPoints -= INCORRECT_ANSWER_POINT);
            }
        }
    }

    // ** [IMAGE Button] Shows Info **
    public void ImgBtn_ShowLanguageTranslation(View view) {
        basics2Question.generateLanguageTranslation(txtView_RussianQuestion);
    }

    // ** [IMAGE Button] EXIT'S To Main Activity **
    public void ImgBtn_ExitBasics2(View view) {
        startActivity(new Intent(Basics2.this, MainActivity.class));
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
    }


}
