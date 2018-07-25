package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // TODO: Declare member variables here:
    Button mTrueButton;
    Button mFalseButton;
    TextView mQuestionTextView; // member variable for the text View
    TextView mScoreTextView;
    int mIndex; // stores nr of question that we want fetch from the
    // question bank
    int mQuestion;
    int mScore; // tracks number of correctly answered questions
    ProgressBar mProgressBar;




    // TODO: Uncomment to create question bank
    // creating a new array that contains all the questions and answers
    // member of an array are true/false questions.
    // IN FACT we are creating trueFalse objects at the same time,
    // when we are creating array. We are calling trueFalse constructor
    // 13 times.
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            // R.string.question_1 --> we provide resource ID for question text,
            // the we provide correct answer.
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };




    // TODO: Declare constants here

    //we will store the amount that progress bar fills up as a constant
    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil( 100.0 / mQuestionBank.length);





    // EXAMPLE
    // same as:
    // int [] myIntArray = new int[] {2,4,6}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // we will create the link between mTrueButton
        // and the button element in the layout xml file
        mTrueButton = (Button) findViewById(R. id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        // calling findViewByID to fetch a text view from the
        // layout file and store it int mQuestionTextView
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        // HOW TO DISPLAY SOMETHINFG IN TEXT VIEW????
        // A: we can use setText method, and provide the text for
        // display as a parameter.

        mScoreTextView = (TextView) findViewById(R.id.score); // relates score text with on screen TextView
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);



        // variable that is containing first variable
        TrueFalse firstQuestion = mQuestionBank[mIndex]; // we don't store our questions as
        // strings of characters, we store them as resource ID

        mQuestion = firstQuestion.getQuestionID();

        //example of setting a text on the textView:
        mQuestionTextView.setText(mQuestion); // "setText" accepts both,
        // strings of characters and resource IDs.



        //now we should specify when it detects a click
        // we should set a listenner on the TRUE button
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //WHEN LISTENER DETECTS A TAP ON THE BUTTON, IT RESPONDS
                // BY FIREING onClick METHOD. iT IS CALLED A "CALLBACK".
                checkAnswer(true);
                updateQuestion();
            }
        });


        // for false button, the shortcut is going to be taken.
        //in the first case we have created a variable with a name "myListener" and set that
        // listenner on true button.
        // for false button we are still creating onClickListener but in this case it doesn't have
        // a name - it is anonymous. You can't reffere to it by name.
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestion();
            }
        });


        // EXAMPLE
        // If I want to create a new True or False question, first I have to supply a type
        //whih in this case is "TrueFalse", then name and then I will call a constructor
        //TrueFalse exampleQuestion = new TrueFalse(R.string.question_1, true);


    }

    private void updateQuestion(){
        mIndex = (mIndex + 1) % mQuestionBank.length; //!!! using % we make mIndex to be set to 0
        // again when it reaches nr 13

        mQuestion = mQuestionBank[mIndex].getQuestionID();

        //setting a text on the textView:
        mQuestionTextView.setText(mQuestion); // "setText" accepts both,
        // strings of characters and resource IDs.
        mProgressBar.incrementProgressBy(PROGRESS_BAR_INCREMENT);
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
    }

    private void checkAnswer(boolean userSelection) {
        // retrieve correct answer and store it in the variable
        boolean correctAnswer = mQuestionBank[mIndex].isAnswer();

        if(userSelection == correctAnswer){
            Toast.makeText(getApplicationContext(), R.string.correct_toast, Toast.LENGTH_SHORT).show();
            mScore += 1;
        } else{
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast, Toast.LENGTH_SHORT).show();

        }

    }





}
