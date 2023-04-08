package tasksecondv2;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class TaskSecondV2 {

    static HashMap<String, Integer> countedWord = new HashMap<>();
    
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanline;
        File handler;
        String filename;
        boolean OK = false;
        do
            try {
                System.out.println("Podaj ścieżkę pliku wraz z rozszerzeniem");
                filename = scanner.nextLine();
                handler = new File(filename);
                scanline = new Scanner(handler);
                if (!scanline.hasNextLine())
                    throw new Exception("Plik jest pusty");
                else {
                    int count;
                    while(scanline.hasNextLine()){
                        String line = scanline.nextLine();
                        String cleanLine = stringClear(line);
                        countWords(cleanLine);
                    }
                    sortingWords();    
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
    
    private static String stringClear(String line) {
        return line.replace(".", "").replace(",", "")
        .replace("?", "").replace("!", "");
    }
    
    
    private static void countWords(String cleanLine) {
        String[] words = cleanLine.split(" ");
        int count = 0;
        for(int i = 0; i < words.length; i++){
            if(checkLetter(words[i].charAt(0))) {
                if(countedWord.containsKey(words[i])) {
                    count = countedWord.get(words[i]);
                    countedWord.put(words[i], count +1);
                }
                else
                    countedWord.put(words[i], 1);
            }
        }  
    }
    
    private static void sortingWords() {
        ArrayList<String> words = new ArrayList<>();
        for(int i = Collections.max(countedWord.values()); i > 0; i--) {
        for (String key : countedWord.keySet()) {
            if (countedWord.get(key) == i)
                words.add(key);
        }
        Collections.sort(words, String.CASE_INSENSITIVE_ORDER);
        for (String sorted : words) {
            System.out.println(sorted + " = " + i);
        }
        words.clear();
        }

    }
    
}