
enum
Seasons2 {SPRING, SUMMER, FALL, WINTER}

public class EnumTest {
    public static void main(String[] args) {
        Seasons2 seasons2 = Seasons2.SPRING;

        switch (seasons2) { // enum을 사용하는 경우 switch문 사용 가능
            case SPRING:
                System.out.println("봄");
                break;
            case SUMMER:
                System.out.println("여름");
                break;
            case FALL:
                System.out.println("가을");
                break;
            case WINTER:
                System.out.println("겨울");
                break;
        }
    }
}

