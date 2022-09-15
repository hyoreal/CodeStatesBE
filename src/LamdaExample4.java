@FunctionalInterface
interface Example {
    void shout();
}

//함수형 인터페이스의 사용
public class LamdaExample4 {

    public static void main(String[] args) {

        Example normalExample = new Example() { //익명클래스로 shout 메서드 구현
            @Override
            public void shout() {
                System.out.println("익명클래스로 shout 메서드 구현");
            }
        };

        Example lamdaExample = () -> System.out.println("람다식으로 Example의 shout 메서드 구현"); //람다식으로 Example의 shout 메서드 구현

        Example example = getAction(); // 반환 타입이 Example인 메서드

        normalExample.shout();
        lamdaExample.shout();
        example.shout();

        act(lamdaExample); //매개변수 타입이 Example인 메서드
        act(() -> System.out.println("act 메서드로 호출한 람다식"));
    }

    static Example getAction() { // 반환 타입이 Example인 메서드 -> 람다를 반환할 수 있음
        Example example = () -> System.out.println("반환타입이 Example인 메서드");
        return example;
    }

    static void act(Example example) { //매개변수 타입이 Example인 메서드 -> 람다를 매개변수로 전달
        example.shout();
    }

}
