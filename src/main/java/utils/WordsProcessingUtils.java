package utils;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import exception.NotEnoughWordsInList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class WordsProcessingUtils {
    public static String[] findLongestConcatenatedWords(String fileName) throws IOException {
        List<String> words = createWordsList(fileName);
        if (words.size() < 4) {
            throw new NotEnoughWordsInList(
                    "Please, provide a file with more than three words in it");
        }
        List<String> concatenatedWords = createConcatenatedWordsSortedArray(words);
        if (concatenatedWords.size() < 2) {
            throw new NotEnoughWordsInList(
                    "Unfortunately there are not enough concatenated words in your file");
        }
        String[] result = new String[3];
        result[0] = concatenatedWords.get(0);
        result[1] = concatenatedWords.get(1);
        result[2] = Integer.toString(concatenatedWords.size());
        return result;
    }

    private static List<String> createWordsList(String fileName) throws IOException {
        CharSource charSource = Files.asCharSource(
                new File(fileName), Charsets.UTF_8);
        Set<String> wordsSet = Sets.newHashSet(charSource.read().split(" "));
        List<String> words = Lists.newArrayList(wordsSet);
        words.sort(Comparator.comparingInt(String::length).reversed());
        return words;
    }

    private static List<String> createConcatenatedWordsSortedArray(List<String> words) {
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
        return concatenatedWords;
    }
}
