package Lambda;

interface ExecutionFunction2 {
    public int execute (int a, int b);
}

class MathUtils2 {
    public int sum (int a, int b) {
        return a + b;
    }

    public int minus (int a, int b) {
        return a - b;
    }
}

public class MethodReferenceType2 {
    //Have to be static in order to call this method without creating an instance
    public static int doAction (int a, int b, ExecuteFunction function) {
        return function.execute(a, b);
    }
    public static void main(String[] args) {
        int a = 10;
        int b = 7;

        MathUtils2 obj = new MathUtils2(); //Have to create an instance to use sum and minus methods
        int sum = doAction(a, b, obj::sum);
        System.out.println(a + " + " + b + " = " + sum); // 10 + 7 = 17

        int minus = doAction(a, b, obj::minus);
        System.out.println(a + " - " + b + " = " + minus); // 10 - 7 = 3
    }

}
