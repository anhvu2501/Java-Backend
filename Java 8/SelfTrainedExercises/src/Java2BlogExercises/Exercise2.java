package Java2BlogExercises;

import java.util.ArrayList;
import java.util.List;

public class Exercise2 {
    //Given the list of employees, find the count of employees with age greater than 25
    public static void main(String[] args) {
        List<Employees> list = new ArrayList<>();
        list.add(new Employees("Vu", 20));
        list.add(new Employees("Huy", 22));
        list.add(new Employees("Duc", 21));
        list.add(new Employees("Toan", 27));
        list.add(new Employees("Hoang", 29));
        list.add(new Employees("Quynh", 26));
        list.add(new Employees("Quang", 25));

        long count = list.stream()
                .filter(employees -> employees.getAge() >= 25)
                .count();
        System.out.println(count);
    }
}
