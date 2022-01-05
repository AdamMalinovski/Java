/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adam
 */
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;

public class WordGame {
    public static void main(String args[]) throws IOException {
        gameStart();
        
    }
    
    private static final String startingWord = "empathy";
   
    private static String[] words = {startingWord};
    
    private static boolean outcome = true;

    private static boolean continueORterminate = true;

    public static void gameStart() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(format("Word game restarted with %s\n%s", startingWord, startingWord));
        do {
            System.out.print("Enter a word (caseSensitive) to play the game, such as empathy->yet->top...: ");
            String word = scanner.nextLine();
            if (!wordValid(word)) {
                System.out.println("Entered word is not in dictionary, please try again");
            } else if (isNotAlphanumeric(word)) {
                System.out.println("Entered word is invalid, please try again");
            } else {
                if (word.length() == 0) {
                    gameRestart();
                } else {
                    gameContinueOrOver(word);
                }
            }
        }
        while (outcome);
    }

    private static boolean wordValid(String word) throws IOException {
        FileInputStream fstream = new FileInputStream("C:\\Users\\adoma\\Documents\\NetBeansProjects\\mavenproject2\\src\\main\\java\\dictionary.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
        String txt;
        while ((txt = br.readLine()) != null){
        String[] lines = txt.split("\n");
        for (String str : lines) {
            if (str.trim().equalsIgnoreCase(word)) {
                return true;
            }
        }
        
    }
        return false;
        
    }


    private static void gameContinueOrOver(String word) {
        boolean ifWordNotFound = !isItemFound(word);
        if (ifWordNotFound)  {
            increaseTheSizeOfArrayIfFull();
            words[words.length - 1] = word;
            displayWordsInArray();
        } else {
            gameOver();
        }
    }

    private static boolean isItemFound(String word) {
        for (String s : words) {
            if (s.equals(word)) {
                return true;
            }
        }
        return false;
    }

    private static void increaseTheSizeOfArrayIfFull() {
        //
        String[] newWords = new String[words.length + 1];
        System.arraycopy(words, 0, newWords, 0, words.length);
        words = newWords;
    }

    public static boolean isNotAlphanumeric(String word) {
        return !word.matches("^[a-zA-Z]*$");
    }

    private static void displayWordsInArray() {
        for (String s : words) {
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }

     private static void gameOver() {
        
        System.out.println("Game over\n");
 
        outcome = false;
    }

    private static void gameRestart() {
        System.out.print("Game restarting as the entered word was empty...");
        System.out.println(format("Word game restarted with %s\n%s", startingWord, startingWord));
        words = new String[1];
        words[0] = startingWord;
    }

    
}