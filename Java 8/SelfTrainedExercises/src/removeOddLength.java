import java.util.ArrayList;
import java.util.List;

public class removeOddLength {
    //Remove the words that have odd lengths from the list.
    public static void removeOdd () {
//        List<String> list = Arrays.asList("Vu", "Cuong", "Duc", "Huy", "Hoang", "Quang"); => returns a fixed-size list.
//        Hence cannot remove or add element.
        List<String> list = new ArrayList<>();
        list.add("Vu");
        list.add("Huy");
        list.add("Cuong");
        list.add("Duc");
        list.add("Hoang");
        list.add("Quang");
        list.add("Nhan");
        list.removeIf(s -> s.length() % 2 != 0);
        System.out.println(list);
    }

    public static void main(String[] args) {
        removeOdd();
    }
}
