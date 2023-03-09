public class LamdaExample1 {
    public static void main(String[] args) {

        Object obj = new Object() {     // 익명 객체 -> 객체의 선언과 생성을 동시에!
            int sum(int num1, int num2) {
                return num1 + num1;
            }
        };

        Controller controller = (num1, num2) -> num1 + num2;
        System.out.println(controller.sum(1, 10));

    }
}

interface Controller {
    public abstract int sum(int num1, int num2);
}