package encapsulation;
import encapsulation.example.AccessModifier;

/**
 * # 3
 * 접근 제어자 관련 예제입니다.
 * encapsulation.example 패키지의 AccessModifier 클래스를 참고하시기 바랍니다.
 * 해당 클래스에 선언된 네 가지 변수에 각각 접근이 가능한가 확인해주세요.
 */

// AccessModifier 클래스를 확장한(상속받은) AccessTestInheritance 입니다.
// 상위클래스와 하위클래스의 패키지를 비교해보세요.
// 네 가지 변수에 접근이 잘 되는지 확인해보시고, 접근이 되는 혹은 되지 않는 변수들엔 각각의 이유가 무엇인지 확인해보세요.
// Hint: 접근 제어자 중엔 패키지가 다르면 접근이 되지 않는 제어자도 있고,
//       패키지가 달라도 (상속관계의) 하위클래스엔 접근이 가능한 제어자가 있습니다.
public class AccessTestInheritance extends AccessModifier {
  public void printAll() {
//    System.out.println(privateVariable);
//    System.out.println(defaultVariable);
    System.out.println(protectedVariable);
    System.out.println(publicVariable);
  }
}

class printTest {

  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    AccessTestInheritance accessTestInheritance = new AccessTestInheritance();
    accessTestInheritance.printAll();
  }
}
