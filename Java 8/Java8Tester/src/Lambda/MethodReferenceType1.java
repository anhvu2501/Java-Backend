package Lambda;

@java.lang.FunctionalInterface
interface ExecuteFunction {
    public int execute (int a, int b);
}

class MathUtils {
    public static int sum (int a, int b) {
        return a + b;
    }

    public static int minus (int a, int b) {
        return a - b;
    }
}

public class MethodReferenceType1 {
    public static int doAction (int a, int b, ExecuteFunction function) {
        return function.execute(a, b);
    }

    public static void main(String[] args) {
        int a = 10;
        int b = 7;

        int sum = doAction (a, b, MathUtils :: sum); //Use to pass parameters a, b into sum method
        System.out.println(a + " + " + b + " = " + sum);

        int subtract = doAction(a, b, MathUtils ::minus); //Use to pass parameters a, b into minus method
        System.out.println(a + " - " + b + " = " + subtract);

    }
}
