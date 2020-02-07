import exception.NotEnoughWordsInList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class MainTest {

    private FileWriter fileWriter;

    @Before
    public void setUp() throws Exception {
        fileWriter = new FileWriter("test.txt");
    }

    @Test
    public void assertVoidFileNameThrowsFileNotFoundException() {
        String voidFileName = "";
        assertThrows(FileNotFoundException.class, () -> {
            new ConcatenatedWordsSortedList(voidFileName);
        });
    }

    @Test
    public void assertNullThrowsNullPointerException() {
        String str = null;
        assertThrows(NullPointerException.class, () -> {
            new ConcatenatedWordsSortedList(str);
        });
    }

    @Test
    public void assertThreeWordsListThrowsNotEnoughWordsInList() throws IOException {
        fileWriter.write("do car cardo");
        fileWriter.close();
        assertThrows(NotEnoughWordsInList.class, () -> {
            new ConcatenatedWordsSortedList("test.txt");
        });
    }

    @Test
    public void assertZeroConcatenatedWordsThrowsNotEnoughWordsInList() throws IOException {
        fileWriter.write("do car war sir apple");
        fileWriter.close();
        assertThrows(NotEnoughWordsInList.class, () -> {
            new ConcatenatedWordsSortedList("test.txt");
        });
    }

    @Test
    public void assertCorrectNumberOfWords() throws IOException {
        fileWriter.write("do car war carwar docarwar");
        fileWriter.close();
        ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList("test.txt");
        ConcatenatedWordsSortedList.FirstAndSecondLongestWordAndSize firstAndSecondLongestWordAndSize =
                concatenatedWordsSortedList.getFirstAndSecondLongestWordAndSize();
        assertEquals("docarwar", firstAndSecondLongestWordAndSize.getFirstLongestWord());
        assertEquals("carwar", firstAndSecondLongestWordAndSize.getSecondLongestWord());
        assertEquals(2, firstAndSecondLongestWordAndSize.getConcatenatedWordsSize());
    }


}
