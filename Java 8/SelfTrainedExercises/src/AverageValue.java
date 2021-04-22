import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AverageValue {
    public static Double averageValue (List<Integer> list) {
        return list.stream()
                .mapToInt(i -> i)//Get the value of each element in list
                .average()
                .getAsDouble();
    }

    //mapToInt(ToIntFunction mapper) returns an IntStream consisting of the results of applying the given
    //function to the elements of this stream.

    public static void main(String[] args) {
        List <Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.stream()
                .mapToInt(i -> i)
                .forEach(System.out::println);

//        System.out.println(averageValue(list));
    }
}
