import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Uppercase {

    public static List<String> upperCase (List<String> list) {
        return list.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
    //map(): produces a new stream after applying a function to each element
    //of the original stream. The new stream could be of different type

    public static List<String> lowerCase (List<String> list) {
        return list.stream()
                .map(s -> s.toLowerCase())
                .collect(Collectors.toList());
    }


    public static List<String> lowerCaseOddLength (List<String> list) {
        return list.stream()
                .filter(s -> s.length() % 2 != 0)
                .map(String ::toUpperCase)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("anh vu dep trai");
        list.add("ho tran anh vu");
        list.add("HO TRAN ANH VU A3K45");
        list.add("ANH VU DEP TRAI A3K45");
        list.add("DUC");
        list.add("VU");
        list.add("QuaNg");
        System.out.println(upperCase(list));
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println(lowerCase(list));

        System.out.println(lowerCaseOddLength(list));

    }
}
