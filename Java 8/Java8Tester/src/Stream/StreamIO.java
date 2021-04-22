package Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class StreamIO {
    public static void main(String[] args) {
        String filename = "lines.txt";
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream //start only one stream source
                    .onClose(() -> System.out.println("Done!")) //Being called when close() method is called on Stream
                    .filter(s -> s.startsWith("line3")) //
                    .forEach(System.out::println);
            //There can have several Intermediate operations.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
