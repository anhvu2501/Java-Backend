package Lambda;

interface SayHello {
    void display (String say);
}

class Hello implements SayHello {
    public Hello (String say) {
        System.out.println(say);
    }

    @Override
    public void display(String say) {
        System.out.println(say);
    }
}

public class MethodReferenceType4 {
    public static void main(String[] args) {
        SayHello ref = Hello ::new;
        //Syntax: Class:: new
        ref.display ("Welcome!");
    }
}
