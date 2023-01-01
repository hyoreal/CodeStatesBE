package inheritance;

/*
* # 1
* 상속에 관한 예제 입니다.
*/
public class PersonTest {
  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    Programmer kimcoding = new Programmer();
    kimcoding.coding();
    kimcoding.walk();
    kimcoding.learn();
  }
}

class Person {
  String name;
  int age;

  void learn() {
    System.out.println("사람은 배웁니다.");
  }

  void walk() {
    System.out.println("사람은 걷습니다.");
  }
}

// 상속을 사용하지 않은 Dancer 클래스
class Dancer {
  String name;
  int age;
  String groupName;

  void learn() {
    System.out.println("사람은 배웁니다.");
  }

  void walk() {
    System.out.println("사람은 걷습니다.");
  }

  void dance() {
    System.out.println("댄서는 춤을 춥니다.");
  }
}

// 상속을 사용한 Programmer 클래스
class Programmer extends Person{
  String companyName;

  void coding() {
    System.out.println("프로그래머는 코딩합니다.");
  }
}