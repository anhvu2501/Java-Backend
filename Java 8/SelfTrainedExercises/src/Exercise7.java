import Java2BlogExercises.Employees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Exercise7 {
    public static void main(String[] args) {
        List<Employees> list = new ArrayList<>();
        list.add(new Employees("Vu", 20));
        list.add(new Employees("Huy", 22));
        list.add(new Employees("Duc", 21));
        list.add(new Employees("Vu", 27));
        list.add(new Employees("Vu", 29));
        list.add(new Employees("Quynh", 26));
        list.add(new Employees("Quang", 25));

        Map<String, List<Employees>> groupByName = list.stream()
                .collect(Collectors.groupingBy(Employees::getName)); //groupingBy => returns a map

        groupByName.forEach((name, employees) -> System.out.println("Name: " + name));

    }
}
