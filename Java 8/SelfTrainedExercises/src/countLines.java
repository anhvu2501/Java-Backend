import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class countLines {
    //Count the number of lines in the file using the BufferedReader provided

    public static void countLine(){
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("lines.txt"), StandardCharsets.UTF_8); {
                int numberOfLines = reader
                        .lines() //returns a stream the elements of which are lines read from this BufferedReader
                        .mapToInt(line -> 1)
                        .sum();
                System.out.println(numberOfLines);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        countLine();
    }
}
