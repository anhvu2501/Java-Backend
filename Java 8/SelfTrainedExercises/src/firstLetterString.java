import java.util.Arrays;
import java.util.List;

public class firstLetterString {
    //Create a string that consists of the first letter of each word in the list
    //of Strings provided.

    public static void firstLetter () {
        List<String> list = Arrays.asList("Vu", "Duc", "Cuong", "Huy", "Quang");

        StringBuffer firstLetters = new StringBuffer();
        list.forEach(s -> firstLetters.append(s.charAt(0)));
        System.out.println(firstLetters);
    }

    public static void main(String[] args) {
        firstLetter();
    }

}
