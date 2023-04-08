package tasksecond;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.PrintWriter;
import java.util.Collections;
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
                ArrayList<Character> letters = new ArrayList<>();
                ArrayList<String> words_to_sort = new ArrayList<>();
                HashMap<String, Integer> word_count = new HashMap<>();
                if (!scanline.hasNextLine())
                    throw new Exception("Plik jest pusty");
                else {
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
                    System.out.println(word_count);
                    for(int i = Collections.max(word_count.values()); i > 0; i--) {
                        for (String key : word_count.keySet()) {
                            if (word_count.get(key) == i)
                                words_to_sort.add(key);
                        }
                        Collections.sort(words_to_sort);
                        for (String sorted : words_to_sort) {
                            System.out.println(sorted + " = " + i);  
                        }

                        
                    }

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