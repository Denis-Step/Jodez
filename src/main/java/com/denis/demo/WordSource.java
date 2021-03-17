package com.denis.demo;
import java.util.ArrayList;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class WordSource {
    public String filePath;
    public ArrayList<String> words;


    public WordSource (String filepath) {
        words = new ArrayList<String>();
        try {
            File wordsFile = new File(filepath);
            Scanner wordsReader = new Scanner(wordsFile);
            while (wordsReader.hasNextLine()) {
                String word = wordsReader.nextLine().replaceAll("\\s", "");
                words.add(word);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Path to Words Text Invalid");
            e.printStackTrace();
        }
    }

}
