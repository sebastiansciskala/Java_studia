package taskonev2;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;
public class TaskOneV2 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanline;
        File handler;
        PrintWriter writer;
        String filename;
        String new_filename;
        boolean OK = false;
        do
            try {
                System.out.println("Podaj ścieżkę pliku wraz z rozszerzeniem");
                filename = scanner.nextLine();
                new_filename = filename.substring(0, filename.lastIndexOf('.')) + ".stat";
                handler = new File(filename);
                scanline = new Scanner(handler);
                if (!scanline.hasNextLine())
                    throw new Exception("Plik jest pusty");
                else {

                    int word = 0;
                    int sentence = 0;
                    int letter = 0;
                    int vowel = 0;
                    int consonant = 0;
                    while(scanline.hasNextLine()) {
                        String line = scanline.nextLine();
                        letter += countLetters(line);
                        vowel += countVowels(line);
                        consonant += countConsonants(line);
                        word += countWords(line);
                        sentence += countSentences(line);
                    }
                    writer = new PrintWriter(new_filename);
                    writer.println("W odczytanym pliku znaleziono:");
                    writer.println("Wyrazy: " + word);
                    writer.println("Poprawne zdania: " + sentence);
                    writer.println("Litery: " + letter);
                    writer.println("Samogłoski: " + vowel);
                    writer.println("Spółgłoski: " + consonant);
                    writer.close();
                    System.out.println("Został utworzoyny plik o nazwie: " + new_filename);
                    OK = true;
                }    

            } catch (FileNotFoundException ex) {
                System.err.println("Prawdopodobnie nie ma takiego pliku");
                
            } catch (Exception ex) {
                System.out.println("Zadanie nie zostało wykonane z powodu błędu:\n" 
                       +ex.getMessage());
            }
        while(!OK);
    }
    
    private static boolean checkLetter(char letter) {
        ArrayList<Character> letters = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++)
            letters.add(c);
        for (char c = 'A'; c <= 'Z'; c++) 
            letters.add(c);
        if (letters.contains(letter))
            return true;
        else
            return false;
    }
    
    private static int countLetters(String line) {
        int letter = 0;
        for(int i = 0; i < line.length(); i++) {
            if(checkLetter(line.charAt(i)))
                letter++;
        }
        return letter;
            
    }
    
    private static int countVowels(String line) {
        ArrayList<Character> vowels = new ArrayList<Character>(
        Arrays.asList('A','E','I','O','U','Y','a','e','i','o','u','y'));
        int vovel = 0;
        for(int i = 0; i < line.length(); i++) {
            if (checkLetter(line.charAt(i)))
                if (vowels.contains(line.charAt(i)))
                    vovel++;    
        }
        return vovel;
    }
    
    
    private static int countConsonants(String line) {
        ArrayList<Character> vowels = new ArrayList<Character>(
        Arrays.asList('A','E','I','O','U','Y','a','e','i','o','u','y'));
        int consonant = 0;
        for(int i = 0; i < line.length(); i++) {
            if (checkLetter(line.charAt(i)))
                if (!vowels.contains(line.charAt(i)))
                    consonant++;    
        }
        return consonant;
    }
    
    private static int countWords(String line) {
        int word = 0;
        String[] words = line.split(" ");
        for(String i : words){
            if(checkLetter(i.charAt(0)))
                word++;
        } 
        return word;    
    }
    
    private static int countSentences(String line) {
        int sentence = 0;
        for(int i = 0; i < line.length(); i++) {
            if (i > 0 && line.charAt(i-1) !='.'){
                if (line.charAt(i) == '.') {
                    if ((i+1 < line.length() && line.charAt(i+1) != '.')
                    || (i+1 == line.length()))
                        sentence++;
                }
            }
        }
        return sentence;
    }
    
    
}