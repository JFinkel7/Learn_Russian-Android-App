package Quiz;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

public class SoundEffects {
    // * ERROR STRING MESSAGES *
    private static final String SOUND_ERROR = "SOUND ERROR ->";
    private static final String SOUND_INFO = "SOUND INFO ->";
    private static final String SUCCESS_SOUND_FILE_WORKS = "-> (SUCCESS) SOUND FILE WORKS";
    private static final String SUCCESS_SOUND_FILE_ERROR = "When Playing -> SUCCESS SOUND";
    private static final String FAILURE_SOUND_FILE_WORKS = "-> (FAILURE) SOUND FILE WORKS";
    private static final String FAILURE_SOUND_FILE_ERROR = "When Playing -> FAILURE SOUND";
    private static final String WINNING_SOUND_FILE_WORKS = "-> (WINNING) SOUND FILE WORKS";
    private static final String WINNING_SOUND_FILE_ERROR = "When Playing -> WINNING SOUND";
    private static final String SOUND_EFFECT_SOUND_FILE_WORKS = "-> (SOUND EFFECT) SOUND FILE WORKS";
    private static final String SOUND_EFFECT_SOUND_FILE_ERROR = "When Playing -> SOUND EFFECT";
    // * Class Instance Variables *
    private SoundPool soundPool;
    private Context setContext;
    private int successSound;
    private int failureSound;
    private int winSound;
    private int soundEffect;

    // * Constructor *
    public SoundEffects(Context context, int maxStream) {
        this.setContext = context;
        AudioAttributes audioAttribute = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttribute).setMaxStreams(maxStream).build();
    }

    // * Overloaded Constructor *
    public SoundEffects() {}

    /***Sound Effect METHODS***/
    public void playSuccessSound() {
        if (getSuccessSound() != 0) {
            soundPool.play(getSuccessSound(), 1, 1, 0, 0, 1);
            Log.i(SOUND_INFO, SUCCESS_SOUND_FILE_WORKS);
        } else {
            Log.e(SOUND_ERROR, SUCCESS_SOUND_FILE_ERROR);
        }
    }

    public void playFailureSound() {
        if (getFailureSound() != 0) {
            soundPool.play(getFailureSound(), 1, 1, 0, 0, 1);
            Log.i(SOUND_INFO, FAILURE_SOUND_FILE_WORKS);
        } else {
            Log.e(SOUND_ERROR, FAILURE_SOUND_FILE_ERROR);
        }
    }

    public void playWinSound() {
        if (getWinSound() != 0) {
            soundPool.play(getWinSound(), 1, 1, 0, 0, 1);
            Log.i(SOUND_INFO, WINNING_SOUND_FILE_WORKS);
        } else {
            Log.e(SOUND_ERROR, WINNING_SOUND_FILE_ERROR);
        }
    }

    public void playSoundEffect() {
        if (getWinSound() != 0) {
            soundPool.play(getSoundEffect(), 1, 1, 0, 0, 1);
            Log.i(SOUND_INFO, SOUND_EFFECT_SOUND_FILE_WORKS);
        } else {
            Log.e(SOUND_ERROR, SOUND_EFFECT_SOUND_FILE_ERROR);
        }
    }

    public void disposeSoundEffects() {
        soundPool.release();
    }

    /***SET METHODS***/
    public void setSuccessSound(int soundFile) {
        this.successSound = soundPool.load(getContext(), soundFile, 1);
    }

    public void setFailureSound(int soundFile) {
        this.failureSound = soundPool.load(getContext(), soundFile, 1);
    }

    public void setWinSound(int soundFile) {
        this.winSound = soundPool.load(getContext(), soundFile, 1);
    }

    public void setSoundEffect(int soundFile) {
        this.soundEffect = soundPool.load(getContext(), soundFile, 1);
    }


    /***GET METHODS***/
    private Context getContext() {
        return (setContext);
    }

    private int getSuccessSound() {
        return (successSound);
    }

    private int getFailureSound() {
        return (failureSound);
    }

    private int getWinSound() {
        return (winSound);
    }

    public int getSoundEffect() {
        return soundEffect;
    }

}// END OF CLASS
