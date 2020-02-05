import utils.WordsProcessingUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please, provide a file name...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String[] result = WordsProcessingUtils.findLongestConcatenatedWords(fileName);
        System.out.println("Longest concatenated word is: " + result[0]);
        System.out.println("Second longest concatenated word is: " + result[1]);
        System.out.println("Concatenated words array size is: " + result[2]);
    }

}
