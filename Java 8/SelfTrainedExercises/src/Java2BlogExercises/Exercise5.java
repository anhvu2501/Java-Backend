package Java2BlogExercises;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise5 {
    public static void main(String[] args) {
        List<Employees> list = new ArrayList<>();
        list.add(new Employees("Vu", 20));
        list.add(new Employees("Huy", 22));
        list.add(new Employees("Duc", 21));
        list.add(new Employees("Toan", 27));
        list.add(new Employees("Hoang", 29));
        list.add(new Employees("Quynh", 26));
        list.add(new Employees("Quang", 25));

//        List<Employees> sorted = list.stream()
//                .sorted((Comparator.comparingInt(Employees::getAge)))
//                .collect(Collectors.toList());
//
//        for (Employees e : sorted)
//            System.out.println(e.toString());

        list.stream()
                .sorted(Comparator.comparingInt(Employees::getAge))
                .forEach(System.out::println);
    }
}
