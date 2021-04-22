package Lambda;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FunctionalInterface {

    //1) Interface has only one method is Functional Interface (or Single Abstract Method)
    //2) Functional Interface can has methods of java.lang.Object class
    //3) Because access modifier of methods in interface have to be public abstract. Hence,
    //Functional Interface can not have methods of java.lang.Object which have access modifier < Public

    //4) Default and static method do not break the rules of Functional Interface

    //Predicate <T> interface is a functional interface with a method test (Object) to return a Boolean value.
    //This interface signifies that an object is tested to be true or false
    public static void eval (List <Integer> list, Predicate <Integer> predicate) {
        for (Integer n : list) {
            if (predicate.test(n)) {
                System.out.println(n + " ");
            }
        }
    }


    public static void main(String[] args) {
        List <Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("Print all numbers: ");
        eval(list, n -> true);

        System.out.println("Print even numbers: ");
        eval(list, n -> n%2 == 0);

        System.out.println("Print numbers greater than 3: ");
        eval(list, n -> n > 3);

        System.out.println("Print odd numbers: ");
        eval(list, n -> n % 2 != 0);
    }
}
