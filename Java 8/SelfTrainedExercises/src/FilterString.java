import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterString {
    public static List<String> search (List<String> list) {
        return list.stream()
                .filter(s -> s.startsWith("a"))
                .filter(s -> s.length() == 3)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("anh Vu dep Trai");
        list.add("anh");
        System.out.println(search(list));
        list.stream()
                .filter(s -> s.startsWith("a"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::println); //return "anh"
    }
}
