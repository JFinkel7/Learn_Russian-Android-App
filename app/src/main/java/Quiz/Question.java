package Quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jfinkelstudios.mobile.learnrussianbeta.MainActivity;

import java.util.concurrent.ThreadLocalRandom;

import es.dmoral.toasty.Toasty;

@SuppressLint("StaticFieldLeak")
public class Question extends QuizConfiguration {
    //*
    // Error Message
    private static final String ERROR = "ERROR ";
    private static final String INFO = "INFO";
    private static final String NULL_ERROR = "NULL REFERENCE ERROR FOUND";
    private static final String STRING_ARRAY_ERROR = "-> Length of String Array = 0 OR NULL";
    private static final String ARRAY_LENGTH_NOT_ZERO = "Length Of An Array Is Not 0";
    private static final String GENERATE_RANDOM_QUESTION = "AT -> (generateRandomQuestion";
    private static final String GENERATE_ENGLISH_TRANSLATION = "AT -> (generateRandomQuestion";
    // Strings
    private static final String NO_TEXT = "";
    private static final String QUESTION_ERROR = "Wrong Choice";
    private static final String EMPTY_FIELD = "Empty Field";
    // Integers
    private int START_PROGRESS_POINT = 0;
    // Context
    private Context context;
    // Class Sound Effects
    private SoundEffects soundEffects;
    //*

    // Constructor
    public Question(Context context, SoundEffects soundEffects, final int LANG1_RESOURCE_ID, final int LANG2_RESOURCE_ID) {
        setContext(context);
        setEnglishQuestion_ResourceID(LANG1_RESOURCE_ID);
        setRussianQuestion_ResourceID(LANG2_RESOURCE_ID);
        this.soundEffects = soundEffects;
    }

    // *** OVERLOADED ***
    public Question(Context context, final int LANG1_RESOURCE_ID, final int LANG2_RESOURCE_ID) {
        setContext(context);
        setEnglishQuestion_ResourceID(LANG1_RESOURCE_ID);
        setRussianQuestion_ResourceID(LANG2_RESOURCE_ID);
    }

    // *** OVERLOADED ***
    public Question(Context context, final int LANGUAGE_RESOURCE_ID) {
        setContext(context);
        setEnglishQuestion_ResourceID(LANGUAGE_RESOURCE_ID);
    }

    // *** OVERLOADED ***
    public Question(Context context) {
        setContext(context);
    }

    // *** OVERLOADED ***
    public Question() {}


    public boolean isQuestionCorrect(String userInput, int stringArrayResourceID) {
        String[] array_questions = getContext().getResources().getStringArray(stringArrayResourceID);
        if (array_questions.length != 0) {
            for (String index : array_questions) {
                if (userInput.equals(index.toLowerCase().trim())) {
                    return (true);
                }
            }
            Log.i(INFO, ARRAY_LENGTH_NOT_ZERO);
        } else {
            throw new ArithmeticException(STRING_ARRAY_ERROR);
        }
        return (false);
    }

    // *** OVERLOADED ***
    public boolean isQuestionCorrect(String userInput) {
        final String TEXT_VIEW_QUESTION = getTextViewQuestion().getText().toString();
        String[] englishArray = getContext().getResources().getStringArray(getEnglishQuestion_ResourceID());
        String[] russianArray = getContext().getResources().getStringArray(getRussianQuestion_ResourceID());
        int arrayLength = englishArray.length;
        if (englishArray.length != 0) {
            for (int index = 0; index < arrayLength; index++) {
                if (englishArray[index].equals(userInput) && russianArray[index].equals(TEXT_VIEW_QUESTION)) {
                    return (true);
                }
            }
            Log.i(INFO, ARRAY_LENGTH_NOT_ZERO);
        } else {
            throw new ArithmeticException(STRING_ARRAY_ERROR);
        }
        return (false);
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void isQuestionTouched(final TextView textView) {
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (textView.isInTouchMode()) {
                    String question = textView.getText().toString();
                    Solace.speak(question);
                    return (true);
                } else return (false);
            }
        });
    }

    // *** OVERLOADED ***
    @SuppressLint("ClickableViewAccessibility")
    public void generateRandomQuestion(final TextView textView) {
        try {
            String[] stringArray = getContext().getResources().getStringArray(getRussianQuestion_ResourceID());
            if (stringArray.length != 0) {
                final int MIN = 0;
                final int MAX = stringArray.length;
                int randomValue = ThreadLocalRandom.current().nextInt(MIN, MAX);
                if (stringArray[randomValue] != null) {
                    String randomQuestion = stringArray[randomValue];
                    textView.setText(randomQuestion);
                } else {
                    throw new NullPointerException(NULL_ERROR);
                }
            } else {
                throw new ArithmeticException(STRING_ARRAY_ERROR);
            }
        } catch (Exception e) {
            Log.e(ERROR, GENERATE_RANDOM_QUESTION);
        }
    }

    // *** OVERLOADED ***
    public String generateRandomQuestion(final int resourceID) {
        try {
            String[] stringArray = getContext().getResources().getStringArray(resourceID);
            if (stringArray.length != 0) {
                final int MIN = 0;
                final int MAX = stringArray.length;
                int randomValue = ThreadLocalRandom.current().nextInt(MIN, MAX);
                if (stringArray[randomValue] != null) {
                    // RETURNS A RANDOM STRING VALUE FROM THE ARRAY
                    return (stringArray[randomValue]);
                } else {
                    throw new NullPointerException(NULL_ERROR);
                }
            }
        } catch (Exception e) {
            Log.e(ERROR, GENERATE_RANDOM_QUESTION);
        }
        return (NO_TEXT);
    }

    public void generateLanguageTranslation(TextView textView) {
        try {
            final String TEXT_VIEW_QUESTION = textView.getText().toString();
            String[] russianArrayQuestions = getContext().getResources().getStringArray(getRussianQuestion_ResourceID());
            String[] englishArrayQuestions = getContext().getResources().getStringArray(getEnglishQuestion_ResourceID());
            for (int index = 0; index < russianArrayQuestions.length; index++) {
                if (russianArrayQuestions[index].equals(TEXT_VIEW_QUESTION)) {
                    String retrievedEnglishQuestion = englishArrayQuestions[index];
                    if (!(retrievedEnglishQuestion.isEmpty())) {
                        Toasty.info(getContext(), retrievedEnglishQuestion, Toast.LENGTH_LONG, true).show();
                    } else {
                        Toast.makeText(getContext(), STRING_ARRAY_ERROR, Toast.LENGTH_SHORT).show();
                    }
                }
            }// END OF LOOP
        } catch (Exception e) {
            Log.e(ERROR, GENERATE_ENGLISH_TRANSLATION);
        }
    }

    public void check_Question() {
        // * IF INPUT IS (NOT) = EMPTY *
        String storedUserInput = getEditTextUserInput().getText().toString().toLowerCase().trim();
        if (!storedUserInput.isEmpty()) {
            generateRandomQuestion(getTextViewQuestion());
            // * IF CORRECT ↓ *
            if (isQuestionCorrect(storedUserInput, getEnglishQuestion_ResourceID())) {
                if (!(START_PROGRESS_POINT >= getWinNumber())) {
                    getProgressBar().setProgress(START_PROGRESS_POINT += getCorrectPoints());
                    soundEffects.playSuccessSound();
                    getEditTextUserInput().setText(NO_TEXT);
                } else {
                    // ** Launches Back To MainActivity ***
                    getContext().startActivity(new Intent(getContext(), MainActivity.class));
                }
            }// * IF NOT CORRECT ↓ *
            else {
                if (!(START_PROGRESS_POINT <= 0)) {
                    getProgressBar().setProgress(START_PROGRESS_POINT -= getInCorrectPoints());
                }
                soundEffects.playFailureSound();
                getEditTextUserInput().setText(NO_TEXT);
            }
        } else {// * IF INPUT IS EMPTY *
            getEditTextUserInput().requestFocus();
            // ** ↓ OPTIONAL ↓ **
            getEditTextUserInput().setError(EMPTY_FIELD);
            Toasty.error(getContext(), EMPTY_FIELD, Toast.LENGTH_SHORT, true).show();

        }
    }

    // *** OVERLOADED ***
    public void check_Question(String userInput) {
        generateRandomQuestion(getTextViewQuestion());
        // * IF CORRECT ↓ *
        if (isQuestionCorrect(userInput)) {
            if (!(START_PROGRESS_POINT >= getWinNumber())) {
                getProgressBar().setProgress(START_PROGRESS_POINT += getCorrectPoints());
                soundEffects.playSuccessSound();
                Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
            } else {
                // ** Launches Back To MainActivity ***
                getContext().startActivity(new Intent(getContext(), MainActivity.class));
            }
        }// * IF NOT CORRECT ↓ *
        else {
            if (!(START_PROGRESS_POINT <= 0)) {
                getProgressBar().setProgress(START_PROGRESS_POINT -= getInCorrectPoints());
            }
            soundEffects.playFailureSound();
            Toast.makeText(getContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
    }


    /*** SET METHODS ***/
    public void setContext(Context context) {
        this.context = context;
    }

    /*** GET METHODS ***/
    private Context getContext() {
        return (context);
    }


}// END OF CLASS



