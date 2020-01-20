package com.androidProjects.quiz;


///***********//
// AUTHOR: Farhoud Talebi
//  SOME FEATURES:
//      1) Supports saving selected answers and index between orientation switch
//      2) Has a "random" button which will randomize the next question showed to a random one out of the 10 available
///***********//


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private  final String TAG = this.getClass().getSimpleName() + " @" + System.identityHashCode(this);

    //Multiple choice answer buttons
    private Button mAButton;
    private Button mBButton;
    private Button mCButton;
    private Button mDButton;
    private Button mEButton;
    //Enhancement
    private Button mRandomButton;

    //Question navigation buttons
    private Button mPrevButton;
    private Button mSubmitButton;
    private Button mNextButton;


    private TextView mQuestionTextView;
    //List to contain the questions
    private List<Question> mQuestions = new ArrayList<Question>();
    //List to contain the users selected answers
    private ArrayList<String> mSubmission = new ArrayList<String>(Collections.nCopies(10, "Z"));

    private int mCurrentQuestionIndex = 0;
    private final static String QUESTION_INDEX_KEY = "question_index";
    private final static String SUBMISSION_INDEX_KEY = "submission_index";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate()");

        mAButton = (Button) findViewById(R.id.a_button);
        mBButton = (Button) findViewById(R.id.b_button);
        mCButton = (Button) findViewById(R.id.c_button);
        mDButton = (Button) findViewById(R.id.d_button);
        mEButton = (Button) findViewById(R.id.e_button);

        //Enhancement
        mRandomButton = (Button) findViewById(R.id.random_button);

        mPrevButton = (Button) findViewById(R.id.prev_button);
        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mNextButton = (Button) findViewById(R.id.next_button);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);


        mQuestions.add(new Question(getString(R.string.question1)));
        mQuestions.add(new Question(getString(R.string.question2)));
        mQuestions.add(new Question(getString(R.string.question3)));
        mQuestions.add(new Question(getString(R.string.question4)));
        mQuestions.add(new Question(getString(R.string.question5)));
        mQuestions.add(new Question(getString(R.string.question6)));
        mQuestions.add(new Question(getString(R.string.question7)));
        mQuestions.add(new Question(getString(R.string.question8)));
        mQuestions.add(new Question(getString(R.string.question9)));
        mQuestions.add(new Question(getString(R.string.question10)));

//        for(Question q : mQuestions)
//            System.out.println("Q: " + q.getQuestion() + " A: " + q.getAnswer());

        if(savedInstanceState != null) {
            mCurrentQuestionIndex = savedInstanceState.getInt(QUESTION_INDEX_KEY, 0);
           //ENHANCEMENT. Saves selected answers between orientation switch
            mSubmission = savedInstanceState.getStringArrayList(SUBMISSION_INDEX_KEY);

            switch(mSubmission.get(mCurrentQuestionIndex)) {
                case "A":
                    mAButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "B":
                    mBButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "C":
                    mCButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "D":
                    mDButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "E":
                    mEButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                default:
                    clearButtons();
                    break;
            }
        }

        mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).getQuestion());


        mPrevButton.setOnClickListener(v -> {
            mCurrentQuestionIndex--;
            if(mCurrentQuestionIndex < 0) mCurrentQuestionIndex = mQuestions.size()-1;
            mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).getQuestion());
            clearButtons();

            switch(mSubmission.get(mCurrentQuestionIndex)) {
                case "A":
                    mAButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "B":
                    mBButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "C":
                    mCButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "D":
                    mDButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "E":
                    mEButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                default:
                    clearButtons();
                    break;
            }
        });

        mNextButton.setOnClickListener(v -> {
            mCurrentQuestionIndex++;
            if(mCurrentQuestionIndex >= mQuestions.size()) mCurrentQuestionIndex = 0;
            mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).getQuestion());
            clearButtons();

            switch(mSubmission.get(mCurrentQuestionIndex)) {
                case "A":
                    mAButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "B":
                    mBButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "C":
                    mCButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "D":
                    mDButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "E":
                    mEButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                default:
                    clearButtons();
                    break;
            }

        });

        mSubmitButton.setOnClickListener(v -> {
            //Submit answers and calculate score
            int score = 0;
            for(int i=0; i<10; i++) {
                if(mQuestions.get(i).getAnswer().equals(mSubmission.get(i))) score++;
            }
            String scoreText = "Score: " + String.valueOf(score) + "/10";
            Toast.makeText(MainActivity.this, scoreText, Toast.LENGTH_LONG).show();
            clearButtons();
            clearSubmission();

            switch(mSubmission.get(mCurrentQuestionIndex)) {
                case "A":
                    mAButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "B":
                    mBButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "C":
                    mCButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "D":
                    mDButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "E":
                    mEButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                default:
                    clearButtons();
                    break;
            }
        });

        //Enhancement. Shows a random question
        mRandomButton.setOnClickListener(v -> {
            //Generate a random integer between 0 and 9
            mCurrentQuestionIndex = (int)(Math.random() * 10);
            mQuestionTextView.setText(mQuestions.get(mCurrentQuestionIndex).getQuestion());
            clearButtons();

            switch(mSubmission.get(mCurrentQuestionIndex)) {
                case "A":
                    mAButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "B":
                    mBButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "C":
                    mCButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "D":
                    mDButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                case "E":
                    mEButton.setBackgroundColor(0xCDF7BB2E);
                    break;
                default:
                    clearButtons();
                    break;
            }
        });

        mAButton.setOnClickListener(v -> {
            //Handle the A multiple choice button click
            clearButtons();
            mAButton.setBackgroundColor(0xCDF7BB2E);
            mSubmission.set(mCurrentQuestionIndex, "A");
        });

        mBButton.setOnClickListener(v -> {
            //Handle the B multiple choice button click
            clearButtons();
            mBButton.setBackgroundColor(0xCDF7BB2E);
            mSubmission.set(mCurrentQuestionIndex, "B");
        });

        mCButton.setOnClickListener(v -> {
            //Handle the C multiple choice button click
            clearButtons();
            mCButton.setBackgroundColor(0xCDF7BB2E);
            mSubmission.set(mCurrentQuestionIndex, "C");
        });

        mDButton.setOnClickListener(v -> {
            //Handle the D multiple choice button click
            clearButtons();
            mDButton.setBackgroundColor(0xCDF7BB2E);
            mSubmission.set(mCurrentQuestionIndex, "D");
        });

        mEButton.setOnClickListener(v -> {
            //Handle the E multiple choice button click
            clearButtons();
            mEButton.setBackgroundColor(0xCDF7BB2E);
            mSubmission.set(mCurrentQuestionIndex, "E");
        });
    }

    //Clears all the multiple choice button selections (visually)
    private void clearButtons() {
        mAButton.setBackgroundResource(android.R.drawable.btn_default);
        mBButton.setBackgroundResource(android.R.drawable.btn_default);
        mCButton.setBackgroundResource(android.R.drawable.btn_default);
        mDButton.setBackgroundResource(android.R.drawable.btn_default);
        mEButton.setBackgroundResource(android.R.drawable.btn_default);
    }

    //Clears all selected answers
    private void clearSubmission() {
        for(int i=0; i<mSubmission.size(); i++) {
            mSubmission.set(i, "Z");
        }
    }


    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart()");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(TAG, "onResume()");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i(TAG, "onPause()");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(QUESTION_INDEX_KEY, mCurrentQuestionIndex);
        savedInstanceState.putStringArrayList(SUBMISSION_INDEX_KEY, mSubmission);
    }
}
