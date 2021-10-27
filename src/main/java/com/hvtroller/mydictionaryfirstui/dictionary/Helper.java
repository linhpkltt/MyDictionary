/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hvtroller.mydictionaryfirstui.dictionary;

import java.util.Scanner;

/**
 *
 * @author Khanh Linh
 */
public class Helper {
    public Scanner scanner = new Scanner(System.in);
    
    public int inputNumber(){
        while(true){
            String input = scanner.nextLine();
            if(isNumber(input)){
                return Integer.parseInt(input.trim());
            } else {
                System.out.println("Please enter a number");
            } 
        }
    }
    
    public int inputPositiveNumber(){
        int number = inputNumber();
        if(number <= 0){
            System.out.println("Please enter a positive number");
            return inputPositiveNumber();
        } else {
            return number;
        }
    }
    
    public boolean isNumber(String s) {
        try{
            int number = Integer.parseInt(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    
    
    public boolean isSingleWord(String s) {
        return !s.trim().contains("\\s");
    }
    
    public Word getWordFromLine(String s) {
        String [] input = s.split("\t");
        
        if(input.length < 2) {
            return null;
        }
        
        String word = input[0];
        String explaination = "";
        if(input.length > 2) {
             for (int i = 1; i < input.length; i++) {
                explaination += input[i] + "\t";
            }
            explaination = explaination.trim();
        } else {
            explaination = input[1];
        }
        return new Word(word, explaination);
    }
}
