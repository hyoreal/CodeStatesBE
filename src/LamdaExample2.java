@FunctionalInterface
interface MyFunctionalInterface {
    public void accept(int x); // 추상 메서드가 단 하나
}

public class LamdaExample2 {
    public static void main(String[] args) throws Exception {
        MyFunctionalInterface example;
        example = x -> { // 매개변수가 있는 람다식
            int result = x * 5;
            System.out.println(result);
        };
        example.accept(2);

        example = (x) -> System.out.println(x * 5);
        example.accept(2);
    }
}


