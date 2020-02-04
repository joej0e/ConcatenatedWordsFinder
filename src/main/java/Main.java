import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please provide file name...");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        String[] result = findConcatenatedWords(fileName);
        System.out.println("Longest concatenated word is: " + result[0]);
        System.out.println("Second longest concatenated word is: " + result[1]);
        System.out.println("Concatenated words array size is: " + result[2]);
    }

    private static String[] findConcatenatedWords(String fileName) throws IOException {
        List<String> words = Lists.newArrayList(Files.asCharSource(new File(fileName), Charsets.UTF_8).read().split(" "));
        words.sort(Comparator.comparingInt(String::length).reversed());
        List<String> concatenatedWords = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            String wordToCheck = words.get(i);
            for (int j = 0; j < words.size(); j++) {
                String blinkingWord = words.get(j);
                if (blinkingWord.equals(words.get(i))) {
                    continue;
                }
                if (wordToCheck.contains(blinkingWord)) {
                    if (!wordToCheck.equals(words.get(i))) {
                        if (wordToCheck.length() > blinkingWord.length()) {
                            wordToCheck = wordToCheck.replace(blinkingWord, "");
                        }
                        if (wordToCheck.equals(blinkingWord)) {
                            concatenatedWords.add(words.get(i));
                        }
                    }
                    wordToCheck = wordToCheck.replace(blinkingWord, "");
                }
            }
        }
        concatenatedWords.sort(Comparator.comparingInt(String::length).reversed());
        String[] result = new String[3];
        result[0] = concatenatedWords.get(0);
        result[1] = concatenatedWords.get(1);
        result[2] = Integer.toString(concatenatedWords.size());
        return result;
    }

}
