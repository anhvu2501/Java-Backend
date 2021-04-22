package Java2BlogExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise3 {
    //Given the list of employees, find the employee whose name is John.
    public static void main(String[] args) {
        List<Employees> list = new ArrayList<>();
        list.add(new Employees("Vu", 20));
        list.add(new Employees("Huy", 22));
        list.add(new Employees("Duc", 21));
        list.add(new Employees("Toan", 27));
        list.add(new Employees("John", 29));
        list.add(new Employees("Quynh", 26));
        list.add(new Employees("Quang", 25));

        List<Employees> john = list.stream()
                .filter(employees -> employees.getName().compareTo("John") == 0)
                .collect(Collectors.toList());

        for (Employees e : john)
            System.out.println(e.toString());

    }
}
