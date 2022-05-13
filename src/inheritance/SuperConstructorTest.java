package inheritance;

/**
 * # 4
 * super() 에 관한 예제입니다.
 */
public class SuperConstructorTest {
  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    Volvo myCar = new Volvo("white", 4);
    System.out.println(myCar.color);
    myCar.drive();
    myCar.stop();
    myCar.charge();
  }
}

class Car {
  String color;
  int door;

  public Car(String color, int door) {
    this.color = color;
    this.door = door;
  }

  void drive() {
    System.out.println("운전을 합니다.");
  }

  void stop() {
    System.out.println("차가 멈춥니다.");
  }
}

// Car 클래스를 확장한(상속받은) Volvo 클래스
class Volvo extends Car {
  boolean electric = true;

  public Volvo(String color, int door) {
    super(color, door);
  }

  void charge() {
    System.out.println("전기 충전을 합니다.");
  }
}

// Car 클래스를 확장한(상속받은) Carnival 클래스
class Carnival extends Car {
  boolean busOnly = true;

  public Carnival(String color, int door) {
    super(color, door);
  }

  void autoOpen() {
    System.out.println("문이 자동으로 열립니다.");
  }
}