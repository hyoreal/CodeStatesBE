public class LamdaExample1 {
    public static void main(String[] args) {

        Object obj = new Object() {     // 익명 객체 -> 객체의 선언과 생성을 동시에!
            int sum(int num1, int num2) {
                return num1 + num1;
            }
        };


        ExampleFunction ef = (num1, num2) -> num1 + num2; // 객체기 때문에 참조변수 필요. 하지만 문제발생!
////
        System.out.println( ef.sum(10, 15));



    }
}

interface ExampleFunction {
    public abstract int sum(int num1, int num2);
}