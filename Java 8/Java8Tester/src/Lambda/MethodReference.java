package Lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MethodReference {
    public static void main(String[] args) {
        List names = new ArrayList();

        names.add("Vu");
        names.add("Hoang");
        names.add("Duc");
        names.add("Toan");
        names.add("Trung");
        Collections.sort(names, String::compareTo);
        names.forEach(System.out::println); //Method reference

        //4 types:
        //1) Refer to a Static Method
        //2) Refer to a Instance Method of one specified object
        //3) Refer to a Instance Method of one object of specified type
        //4) Refer to a Constructor

//        //NOTES:
//        1) Chúng ta có thể sử dụng Method References để thay thế cho các Lambda Expression khi
//        Lamba gọi một phương thức nào đó đã được định nghĩa sẵn.
//        2) Chúng ta không thể truyền tham số cho các Method References, phải sử dụng đi kèm với Functional Interfaces.
    }
}
