import exception.NotEnoughWordsInList;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ConcatenatedWordsSortedListTest {

    private FileWriter fileWriter;

    @Before
    public void setUp() throws Exception {
        fileWriter = new FileWriter("text.txt");
    }

    @Test
    public void assertZeroSymbolFileNameThrowsFileNotFoundException() {
        String fileName = "";
        assertThrows(FileNotFoundException.class, () -> {
            ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList();
            concatenatedWordsSortedList.new LongestConcatenatedWordsDto(fileName);
        });
    }

    @Test
    public void assertNullThrowsNullPointerException() {
        String str = null;
        assertThrows(NullPointerException.class, () -> {
            ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList();
            concatenatedWordsSortedList.new LongestConcatenatedWordsDto(str);
        });
    }

    @Test
    public void assertThreeWordsListThrowsNotEnoughWordsInList() throws IOException {
        fileWriter.write("do car cardo");
        fileWriter.close();
        assertThrows(NotEnoughWordsInList.class, () -> {
            ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList();
            concatenatedWordsSortedList.new LongestConcatenatedWordsDto("text.txt");
        });
    }

    @Test
    public void assertZeroConcatenatedWordsThrowsNotEnoughWordsInList() throws IOException {
        fileWriter.write("do car war sir apple");
        fileWriter.close();
        assertThrows(NotEnoughWordsInList.class, () -> {
            ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList();
            concatenatedWordsSortedList.new LongestConcatenatedWordsDto("text.txt");
        });
    }

    @Test
    public void assertCorrectNumberOfWords() throws IOException {
        fileWriter.write("do car war carwar docarwar");
        fileWriter.close();
        ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList();
        ConcatenatedWordsSortedList.LongestConcatenatedWordsDto dto =
                concatenatedWordsSortedList.new LongestConcatenatedWordsDto("text.txt");
        assertEquals("docarwar", dto.getFirstLongestWord());
        assertEquals("carwar", dto.getSecondLongestWord());
        assertEquals(2, dto.getConcatenatedWordsSize());
    }
}
