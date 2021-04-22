package Stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamSource {
    //Stream pipelines here consist of 2 part: Stream Source and Terminal Operation. Dont have Intermediate Operations
    public static void streamFromArray() {
        String[] languages = {"Java", "C++", "JavaScript", "Python", "PHP"};
        Stream<String> testStream1 = Arrays.stream(languages);
        testStream1.forEach(System.out::println);

        Stream<String> testStream2 = Stream.of(languages); //Stream source
        testStream2.forEach(System.out::println); //Terminal Operation
    }

    public static void streamFromCollections() {
        List<String> items = new ArrayList<>();
        items.add("Java");
        items.add("C++");
        items.add("C#");
        items.add("PHP");
        items.add("Python");
        items.add("JavaScript");
        items.stream().forEach(System.out::println); //items.StreamSource.TerminalOperation
    }

    public static void streamUsingGenerate() {
        Stream<String> stream = Stream.generate(() -> "Anh Vu").limit(3);
        String[] testStrArr = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(testStrArr)); //Output: [Anh Vu, Anh Vu, Anh Vu]
    }

    public static void streamUsingIterate() {
        Stream<Long> iterateNumbers = Stream.iterate(1L, n -> n + 1).limit(5);
        iterateNumbers.forEach(System.out::println);  //Output: 12345
    }

    public static void streamUsingRegex () {
        String str = "Welcome, Anh Vu";
        Pattern.compile(",").splitAsStream(str).forEach(System.out::print); //Output: Welcome Anh Vu
    }

    public static void getCollectionUsingStreamCollection () {
        Stream <String> stream = Stream.of("Java", "C++", "Python", "JavaScript", "Ruby");
        List <String> languages = stream.collect(Collectors.toList());
        System.out.println(languages);
    }

    public static void getArrayUsingStreamtoArray () {
        Stream <String> stream = Stream.of("Java", "C++", "Python", "JavaScript", "Ruby");
        String[] languages = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(languages));
    }

    public static void main(String[] args) {
        //Stream Source for Primitive
        IntStream.range(1, 4).forEach(System.out::println);

        IntStream.of(1, 2, 3).forEach(System.out::println);

        DoubleStream.of(1.5, 2.5, 3.5).forEach(System.out::println);

        LongStream.range(1, 4).forEach(System.out::println);

        LongStream.of(1, 2, 3).forEach(System.out::println);

        //Stream for other data structures
        System.out.println("Stream From Array");
        streamFromArray();
        System.out.println("Stream From Collections");
        streamFromCollections();
        System.out.println("Stream Using Generate");
        streamUsingGenerate();
        System.out.println("Stream Using Iterate");
        streamUsingIterate();
        System.out.println("Stream Using Regex API");
        streamUsingRegex();
        System.out.println("Get Collection Using Stream Collection");
        getCollectionUsingStreamCollection();
        System.out.println("Get Array Using stream.toArray(EntryType[]:: new)");
        getArrayUsingStreamtoArray();
    }
}
