package Quiz;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;

public class Solace {
    // ***
    // * TEXT TO SPEECH *
    private static TextToSpeech questionTTS;
    // * ERROR STRING MESSAGES *
    private static final String ERROR = "ERROR->";
    private static final String TTS_ERROR_MSG = "TEXT TO SPEECH ";
    private static final String LANG_ERROR = "Language Error!";
    private static final String LANG_RU = "ru";
    private static final String EMPTY_STRING = "EMPTY STRING IS NULL OR EMPTY!";

    // ***
    public static void initializeTextToSpeech(final Context context) {
        questionTTS = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    questionTTS.setLanguage(new Locale(LANG_RU));
                } else {
                    Log.e(TTS_ERROR_MSG, LANG_ERROR);
                }
            }
        });
    }

    protected static String speak(String words) {
        if (!(words.isEmpty())) {
            if (Build.VERSION.SDK_INT >= 21) {
                questionTTS.speak(words, TextToSpeech.QUEUE_FLUSH, null, null);
                return (words);
            } else {
                questionTTS.speak(words, TextToSpeech.QUEUE_FLUSH, null);
                return (words);
            }
        } else {
            throw new RuntimeException(String.format("%s %s", ERROR, EMPTY_STRING));
        }
    }

    // Used For On Pause OR On Stop
    public static void stopSpeech() {
        questionTTS.stop();
    }

    // Used For On Destroy
    public static void shutdownSpeech() {
        questionTTS.shutdown();
    }

}
