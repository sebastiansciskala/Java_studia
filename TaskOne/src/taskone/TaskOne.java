package taskone;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;

public class TaskOne {


    
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
                    ArrayList<Character> samogloski = new ArrayList<Character>(
                    Arrays.asList('A','E','I','O','U','Y','a','e','i','o','u','y'));
                    ArrayList<Character> letters = new ArrayList<>();
                    for (char c = 'a'; c <= 'z'; c++)
                        letters.add(c);
                    for (char c = 'A'; c <= 'Z'; c++) 
                        letters.add(c);
                    int word = 0;
                    int sentence = 0;
                    int letter = 0;
                    int vowel = 0;
                    int consonant = 0;
                    while(scanline.hasNextLine()){
                        String line = scanline.nextLine();
                        for(int i = 0; i < line.length(); i++) {
                            if(letters.contains(line.charAt(i))){         
                                letter++;    
                                if(samogloski.contains(line.charAt(i)))
                                    vowel++;
                                else
                                    consonant++;
                            }
                            if (i > 0 && line.charAt(i-1) !='.'){
                                if (line.charAt(i) == '.') {
                                    if ((i+1 < line.length() && line.charAt(i+1) != '.')
                                            || (i+1 == line.length()))
                                        sentence++;
                                }
                            }    
                        }
                        String[] words = line.split(" ");
                        for(int i = 0; i < words.length; i++){
                            if(letters.contains(words[i].charAt(0)))
                                word++;
                        } 
                    }
                    writer = new PrintWriter(new_filename);
                    writer.println("W odczytanym pliku znaleziono:");
                    writer.println("Wyrazy: " + word);
                    writer.println("Poprawne zdania: " + sentence);
                    writer.println("Litery: " + letter);
                    writer.println("Samogłoski: " + vowel);
                    writer.println("Spółgłoski: " + consonant);
                    writer.close();
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
    
}
