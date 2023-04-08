package tasksecond;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;
import java.util.HashMap;

public class TaskSecond {


    
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
                    ArrayList<Character> letters = new ArrayList<>();
                    HashMap<String, Integer> word_count = new HashMap<>();
                    int count;
                    for (char c = 'a'; c <= 'z'; c++)
                        letters.add(c);
                    for (char c = 'A'; c <= 'Z'; c++) 
                        letters.add(c);
                    while(scanline.hasNextLine()){
                        String line = scanline.nextLine();
                        String clean_line = line.replace(".", "").replace(",", "")
                                .replace("?", "").replace("!", "");
                        String[] words = clean_line.split(" ");
                        for(int i = 0; i < words.length; i++){
                            if(letters.contains(words[i].charAt(0))) {
                                if(word_count.containsKey(words[i])) {
                                    count = word_count.get(words[i]);
                                    word_count.put(words[i], count +1);
                                }
                                else
                                    word_count.put(words[i], 1);
                            }
                        } 
                    }
                    System.out.print(word_count);
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