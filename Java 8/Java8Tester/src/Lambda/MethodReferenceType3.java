package Lambda;

import java.util.Arrays;

public class MethodReferenceType3 {
    public static void main(String[] args) {
        String [] stringArray = {"Java", "C++", "Python", "C#","JavaScript"};
        Arrays.sort(stringArray, String :: compareToIgnoreCase);
        //Tham chiếu đến một instance method của một đối tượng tùy ý của một kiểu cụ thể
        //Syntax: Class:: instanceMethod
        for (String str : stringArray)
            System.out.println(str);
    }
}
