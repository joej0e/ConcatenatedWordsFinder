import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import exception.NotEnoughWordsInList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public final class ConcatenatedWordsSortedList {

    private List<String> getConcatenatedWordsSortedList(String fileName) throws IOException {
        Set<String> wordsSet = Sets.newHashSet(Files.asCharSource(
                new File(fileName), Charsets.UTF_8).read().split(" "));
        if (wordsSet.size() < 4) {
            throw new NotEnoughWordsInList(
                    "Please, provide a file with more than three words in it");
        }
        List<String> words = Lists.newArrayList(wordsSet);
        words.sort(Comparator.comparingInt(String::length).reversed());
        List<String> concatenatedWords = createConcatenatedWordsSortedList(words);
        if (concatenatedWords.size() < 2) {
            throw new NotEnoughWordsInList(
                    "Unfortunately, there are not enough concatenated words in your file");
        }
        return concatenatedWords;
    }

    private List<String> createConcatenatedWordsSortedList(List<String> words) {
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

    public class LongestConcatenatedWordsDto {

        private final String firstLongestWord;

        private final String secondLongestWord;

        private final int concatenatedWordsSize;

        public LongestConcatenatedWordsDto(String fileName) throws IOException {
            List<String> concatenatedWords = getConcatenatedWordsSortedList(fileName);
            firstLongestWord = concatenatedWords.get(0);
            secondLongestWord = concatenatedWords.get(1);
            concatenatedWordsSize = concatenatedWords.size();
        }

        public String getFirstLongestWord() {
            return firstLongestWord;
        }

        public String getSecondLongestWord() {
            return secondLongestWord;
        }

        public int getConcatenatedWordsSize() {
            return concatenatedWordsSize;
        }

        @Override
        public String toString() {
            return "LongestConcatenatedWordsDto{" +
                    "firstLongestWord='" + firstLongestWord + '\'' +
                    ", secondLongestWord='" + secondLongestWord + '\'' +
                    ", concatenatedWordsSize=" + concatenatedWordsSize +
                    '}';
        }

    }
}
