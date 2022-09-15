@FunctionalInterface
interface MyFunctionalInterface {
    public void accept(int x); // 추상 메서드가 단 하나
}

public class LamdaExample2 {
    public static void main(String[] args) throws Exception {

        MyFunctionalInterface example = x -> { // 매개변수가 있는 람다식
            int result = x * 5;
            System.out.println(result);
        };

        // 람다식을 다시 익명객체로
        MyFunctionalInterface example2 = new MyFunctionalInterface() {
            public void accept(int x) {
                int result = x * 5;
                System.out.println(result);
            }
        };


        example.accept(2);
        example2.accept(2);
    }
}


