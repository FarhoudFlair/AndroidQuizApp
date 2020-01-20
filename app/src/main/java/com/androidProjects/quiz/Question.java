package com.androidProjects.quiz;


public class Question {
    private String mQuestion;
    private String mAnswer;

    public Question(String aQuestionAnswerString){
        //aQuestionAnswerString is expected to be of the form:
        //"Is Java an Object-Oriented Language?[Yes]"
        int index = aQuestionAnswerString.indexOf("{");
        mQuestion = aQuestionAnswerString.substring(0,index);
        mAnswer = aQuestionAnswerString.substring(index+1,aQuestionAnswerString.length()-1);
    }
    public String getQuestion(){
        return mQuestion;
    }
    public String getAnswer(){
        return mAnswer;
    }
}
