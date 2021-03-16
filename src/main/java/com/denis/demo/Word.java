package com.denis.demo;

public class Word {
    public String word;
    public int wordID;

    public Word(String word, int wordID){
        this.word = word;
        this.wordID = wordID;
    }

    public String getWord(){
        return this.word;
    }

    public void setWord(String word){
        this.word = word;
    }

    public int getWordID(){
        return this.wordID;
    }

    public void setWordID(int wordID){
        this.wordID = wordID;
    }

}
