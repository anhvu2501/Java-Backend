package Java2BlogExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise6 {
    public static void main(String[] args) {
        List<Employees> list = new ArrayList<>();
        list.add(new Employees("Vu", 20));
        list.add(new Employees("Huy", 22));
        list.add(new Employees("Duc", 21));
        list.add(new Employees("Toan", 27));
        list.add(new Employees("Hoang", 29));
        list.add(new Employees("Quynh", 26));
        list.add(new Employees("Quang", 25));

        String collect = list.stream()
                .map(employees -> employees.getName())
                .collect(Collectors.joining(","));

        System.out.println(collect);
    }
}
