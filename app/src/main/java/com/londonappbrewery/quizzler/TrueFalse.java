package com.londonappbrewery.quizzler;

// this class will be template for any quiz question
// It will have to hold 2 peaces of information: question text and answer

public class TrueFalse {

    // member variable for the question text
    //it is an "int" beause it will hold resource id, which is a whole nr that will point to the
    // question in the "strings.xml"
    private int mQuestionID;

    private boolean mAnswer;

    // we need a way to making true/false object. We will use "constructor"
    // this bit of code will create a new questions
    //  constructor's neme is the same as the class'
    // we want our constructor to set values of mQuestionID and mAnswer
    // equal to the value of inputs, that are supplyed when constructor is called.
    public TrueFalse(int questionResourceID, boolean trueOrFalse){
        mQuestionID = questionResourceID;
        mAnswer = trueOrFalse;
    }


    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
