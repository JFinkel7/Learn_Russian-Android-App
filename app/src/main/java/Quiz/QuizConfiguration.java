package Quiz;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class QuizConfiguration {
    // Views
    private ProgressBar progressBar;
    private TextView textViewQuestion;
    private String storedUserInput;
    private EditText editTextUserInput;
    // Data Types
    private double correctPoints;
    private double inCorrectPoints;
    private double winNumber;
    private int englishQuestion_ResourceID;
    private int russianQuestion_ResourceID;

    // ** Constructor **
    QuizConfiguration() {}

    /***SET METHODS***/
    public void setStoredUserInput(String storedUserInput) {
        this.storedUserInput = storedUserInput;
    }

    public void setEditTextUserInput(EditText editTextUserInput) {
        this.editTextUserInput = editTextUserInput;
    }

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public void setTextViewQuestion(TextView textViewQuestion) {
        this.textViewQuestion = textViewQuestion;
    }

    public void setEnglishQuestion_ResourceID(int englishQuestion_ResourceID) {
        this.englishQuestion_ResourceID = englishQuestion_ResourceID;
    }

    public void setRussianQuestion_ResourceID(int russianQuestion_ResourceID) {
        this.russianQuestion_ResourceID = russianQuestion_ResourceID;
    }

    public void setCorrectPoints(double correctPoints) {
        this.correctPoints = correctPoints;
    }

    public void setInCorrectPoints(double inCorrectPoints) {
        this.inCorrectPoints = inCorrectPoints;
    }

    public void setWinNumber(double winNumber) {
        this.winNumber = winNumber;
    }

    /***GET METHODS***/
    public String getStoredUserInput() {
        return storedUserInput;
    }

    public EditText getEditTextUserInput() {
        return editTextUserInput;
    }

    public TextView getTextViewQuestion() {
        return textViewQuestion;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }

    public int getEnglishQuestion_ResourceID() {
        return englishQuestion_ResourceID;
    }

    public int getRussianQuestion_ResourceID() {
        return russianQuestion_ResourceID;
    }

    public double getCorrectPoints() {
        return correctPoints;
    }

    public double getInCorrectPoints() {
        return inCorrectPoints;
    }

    public double getWinNumber() {
        return winNumber;
    }
}
