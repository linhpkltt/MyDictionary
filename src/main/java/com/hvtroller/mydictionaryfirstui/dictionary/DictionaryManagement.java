/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hvtroller.mydictionaryfirstui.dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Khanh Linh
 */
public class DictionaryManagement {

    public static Dictionary dictionary = new Dictionary();
    private String INPUT_FILE = "dictionary.txt";
    private String OUTPUT_FILE = "output.txt";
    //this helper support for handling input
    private Helper helper = new Helper();

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void insertFromCommandline() {
        System.out.println("Enter the number of words: ");
        int numberOfWord = this.inputNumberOfWord();
        for (int i = 0; i < numberOfWord; i++) {
            System.out.println("Enter the english word");
            String word = inputWord();
            System.out.println("Enter the explaination");
            String explaination = inputExplaination();
            dictionary.getDictionary().put(word, new Word(word, explaination));
        }
    }

    public void insertFromFile() {
        File inputDictionary = new File(INPUT_FILE);
        System.out.println("Getting input from file " + INPUT_FILE);
        try {
            Scanner scanner = new Scanner(inputDictionary);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Word word = helper.getWordFromLine(line);
                if (word != null) {
                    dictionary.getDictionary().put(word.getWord_target(),
                            word);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File " + INPUT_FILE + " not found");
        }
    }

    public void deleteWord() {
        System.out.println("Enter word to delete");
        String wordToDelete = inputWord();
        if (dictionary.getDictionary().containsKey(wordToDelete)) {
            dictionary.getDictionary().remove(wordToDelete);
        }
    }

    public void modifyWordExplaination() {
        System.out.println("Enter word to update");
        String wordToModify = inputWord();
        System.out.println("Enter updated explaination");
        String replaceExplaination = inputExplaination();
        dictionary.getDictionary().put(wordToModify, new Word(wordToModify, replaceExplaination));
    }

    public void modifyWord() {
        System.out.println("Enter original word");
        String wordToModify = inputWord();
        System.out.println("Enter updated word");
        String newWordToModify = inputWord();
        String explaination = dictionary.getDictionary().get(wordToModify).getWord_explain();
        dictionary.getDictionary().remove(wordToModify);
        dictionary.getDictionary().put(newWordToModify, new Word(newWordToModify, explaination));
    }

    public void exportToFile() {
        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter(new File(OUTPUT_FILE));
            bw = new BufferedWriter(fw);
            System.out.println("Writing dictionary to file");
            for (Map.Entry<String, Word> entry : dictionary.getDictionary().entrySet()) {
                bw.write(entry.getKey() + "\t" + entry.getValue().getWord_explain());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private int inputNumberOfWord() {

        return helper.inputPositiveNumber();
    }

    public String inputWord() {

        String word = helper.scanner.nextLine().trim();
        return word;

    }

    private String inputExplaination() {

        return helper.scanner.nextLine().trim();

    }

    public HashMap<String, Word> dictionarySearcher(String wordToSearch) {
        HashMap<String, Word> result = new HashMap<>();
        for (String key : dictionary.getDictionary().keySet()) {
            if (key.startsWith(wordToSearch)) {
                result.put(key, dictionary.getDictionary().get(key));
            }
        }
        return result;
    }
}
