package com.shruti.moodleapp;

/**
 * Created by jayesh on 25/6/17.
 */

public class Quizlist {

    private int id;
    private String quizname;

    public Quizlist() {}

    public Quizlist(int id, String quizname) {
        this.id = id;
        this.quizname = quizname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuizname() {
        return quizname;
    }

    public void setQuizname(String quizname) {
        this.quizname = quizname;
    }



}
