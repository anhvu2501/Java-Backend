import java.util.Arrays;

public class LinearSearch {
    public static int linearSearch (Integer n, Integer[] list){
        return Arrays.stream(list)
                .filter(num -> num.equals(n))
                .findAny() //Returns the 1st element that matches the filter predicate wrapped in an Optional if such an element exists
                .orElse(-1);
    }

    public static void main(String[] args) {
        Integer n = 9;
        Integer [] list = {1, 2, 3, 4, 5, 6, 8};
        System.out.println(linearSearch(n, list));
    }
}
