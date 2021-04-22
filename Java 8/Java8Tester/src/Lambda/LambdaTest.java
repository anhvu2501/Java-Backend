package Lambda;

public class LambdaTest {
    //Created Functional Interface
    interface MathOperation {
        int operation(int a, int b);
    }

    //Created Functional Interface
    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    final static String salutation = "Hello! ";

    public static void main(String[] args) {
        LambdaTest Ltest = new LambdaTest();

        //With type declaration
        MathOperation addition = (int a, int b) -> a + b;

        //Without type declaration
        MathOperation subtraction = (a, b) -> a - b;

        //With return statement along with curly braces
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        //Without return statement and without curly braces
        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + Ltest.operate(10, 5, addition));
        System.out.println("10 - 5 = " + Ltest.operate(10, 5, subtraction));
        System.out.println("10 * 5 = " + Ltest.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + Ltest.operate(10, 5, division));

        //Without parenthesis
        GreetingService greetService1 = message -> System.out.println("Hello " + message);

        //With parenthesis
        GreetingService greetService2 = message -> System.out.println("Hello " + message);
        greetService1.sayMessage("Mahesh");
        greetService2.sayMessage("Suresh");

        GreetingService greetingService3 = message -> System.out.println(salutation + message);
        greetingService3.sayMessage("Vu Ho");
    }

}
