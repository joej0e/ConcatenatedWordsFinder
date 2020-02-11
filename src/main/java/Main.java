import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Please, provide a file name...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        ConcatenatedWordsSortedList concatenatedWordsSortedList = new ConcatenatedWordsSortedList();
        ConcatenatedWordsSortedList.LongestConcatenatedWordsDto dto =
                concatenatedWordsSortedList.new LongestConcatenatedWordsDto(fileName);
        System.out.println(dto.toString());
    }

}
