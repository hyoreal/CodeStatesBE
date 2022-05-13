package inheritance;

/**
 * # 3
 * super 에 관한 예제입니다.
 */
public class SuperTest {
  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    First first = new First();
    System.out.println("=============<<First>>=============");
    System.out.println("first.count = " + first.count);

    Second second = new Second();
    System.out.println("=============<<Second>>============");
    second.callNum();

    Third third = new Third();
    System.out.println("=============<<Third>>=============");
    third.callNum();
  }
}

class First {
  int count = 1;
}

// First 클래스를 확장한(상속받은) Second 클래스
class Second extends First{
  int count = 2;
  void callNum() {
    System.out.println("count = " + count);
    System.out.println("this.count = " + this.count);
    System.out.println("super.count = " + super.count);
  }
}

// Second 클래스를 확장한(상속받은) Third 클래스
class Third extends Second{
  int count = 3;
  void callNum() {
    System.out.println("count = " + count);
    System.out.println("this.count = " + this.count);
    System.out.println("super.count = " + super.count);
  }
}