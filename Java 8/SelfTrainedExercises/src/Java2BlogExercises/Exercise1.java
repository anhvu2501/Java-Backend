package Java2BlogExercises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Exercise1 {
    //Given a list of employees, you need to find all the employees whose age is greater than 30 and print the employee names
    public static void main(String[] args) {
        Employees e1 = new Employees("Vu", 20);
        Employees e2 = new Employees("Duc", 38);
        Employees e3 = new Employees("Hoang", 25);
        Employees e4 = new Employees("Huy", 32);
        Employees e5 = new Employees("Vinh", 30);
        Employees e6 = new Employees("Quang", 28);
        Employees e7 = new Employees("Quynh", 20);
        List<Employees> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        List<String> listName = list.stream()
                .filter(employees -> employees.getAge() >= 30)
                .map(employees -> employees.getName())
                .collect(Collectors.toList());
        System.out.println(listName);
    }
}
