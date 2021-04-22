import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class joinString {
    //Join the second, third and forth strings of the list into a single string,
    //where each word is separated by a hyphen (-). Print the resulting string.

    public static void joinStr () {
        List<String> list = Arrays.asList("Vu", "Duc", "Hoang", "Quynh", "Quang", "Huy");
        System.out.println(list.stream().skip(1).limit(3).collect(Collectors.joining("-")));
    }

    public static void main(String[] args) {
        joinStr();
    }
}
