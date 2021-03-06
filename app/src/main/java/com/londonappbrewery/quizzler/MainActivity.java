package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    // onCreate() specifies what should happen when application is being launched
    //when app is launched, Bundle savedInstanceState == null
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // after storing information of current state, we need to tell the app to check the Bundle
        // when it is being created
        //
        if(savedInstanceState != null){
            // if our savedInstanceState Bundle contains some values, the we need to restore
            // the user score

            mScore = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");
            //#### folowing line will cause error because mScoreTextView is not defined yet!!!
            //mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);

        } else {
            mScore = 0;
            mIndex = 0;
        }



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

        //!!! updates scoreTextView after screen is rotated
        mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);



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

        //creating alert message
        if(mIndex == 0){
            // AlertDialog.Builder --> is a type
            // we need to provide a CONTEXT A.K.A. THE CURRENT STATE OF THE APP
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    // KEYWORD "this" REFFERES TO THE CURRENT OBJECT, WHICH HERE IS A MainActivity,
                    // SO now our AlertDialog.Builder can now get the context via MainActivity object
            //alternative way:
            //AlertDialog.Builder alert = new AlertDialog.Builder(getApplicationContext());

            //setting the title
            alert.setTitle("Game Over");
            // specifying whether the user can cancel alert dialog (i.e. tapping outside alert
            // dialog window to make it dissapear)
            alert.setCancelable(false);
            alert.setMessage("You scored " + mScore + "points !");

            // setting text fot positive choice button
            // setting onClickListener on the button
            alert.setPositiveButton("Close application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // closes the app
                    finish();
                }
            });
            //showing alert dialog to the user
            alert.show();


        }

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

    // overriding onSaveInstanceState method within MainActivity
    @Override
    public void onSaveInstanceState(Bundle outState){ // information is stored inside of a Bundle
        // as a Key-Value pair
        super.onSaveInstanceState(outState);
        // we want to store the current score information it is an int inside mScore variable

        // Key-value ..
        outState.putInt("ScoreKey", mScore);

        // to track what question the user was on
        outState.putInt("IndexKey", mIndex);

        // after storing information of current state, we need to tell the app to check the Bundle
        // when it is being created



    }






}
