package com.tubes.pickmal.model;

public class QuizModel {
    private int image;
    private String[] options = new String[4];
    private int sfx;
    private int correctIndex;

    public QuizModel(int image, String[] options, int sfx, int correctIndex) {
        this.image = image;
        this.options = options;
        this.sfx = sfx;
        this.correctIndex = correctIndex;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getSfx() {
        return sfx;
    }

    public void setSfx(int sfx) {
        this.sfx = sfx;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setCorrectIndex(int correctIndex) {
        this.correctIndex = correctIndex;
    }
}
