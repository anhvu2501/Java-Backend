package Stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest {
    static List<Integer> numbers = Arrays.asList(7, 2, 5, 4, 2, 1);

    public static void withoutStream() {
        long count = 0;
        for (Integer number : numbers) {
            if (number % 2 == 0)
                count++;
        }
        System.out.printf("There are %d elements that are even.\n", count);
    }

    public static void withStream() {
        long count = numbers.stream().filter(num -> num % 2 == 0).count();
        //Với phương thức withStream(), phương thức stream() trả về một luồng của tất cả các number, phương thức filter()
        //trả về một stream các các số chẵn, phương thức count() trả về số phần tử là số chẵn của stream.
        System.out.printf("There are %d elements that are even.", count);
    }



    public static void main(String[] args) {
        withoutStream();
        withStream();
    }
}
