import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommaSeparated {
    public static String getString (List<Integer> list) {
        return list.stream()
                .map(i -> i % 2 == 0 ? "e" + i : "o" + i)
                //If list.get(i) % 2 == 0 => "e" + list.get(i)
                .collect(Collectors.joining(","));
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        System.out.println(getString(list));
    }
}
