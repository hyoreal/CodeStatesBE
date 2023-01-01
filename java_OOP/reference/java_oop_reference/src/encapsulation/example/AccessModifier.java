package encapsulation.example;

/**
 * # 1
 * 접근 제어자 관련 예제입니다.
 */

public class AccessModifier {
  // 네 가지 접근제어자를 각각 사용한 변수
  private String privateVariable = "접근제한자가 private 입니다.";
  String defaultVariable = "접근제한자가 default 입니다.";
  protected String protectedVariable = "접근제한자가 protected 입니다.";
  public String publicVariable = "접근제한자가 public 입니다.";

  // 네 가지 접근제어자를 각각 출력할 수 있는 메서드 printAll()
  public void printAll () {
    System.out.println(privateVariable);
    System.out.println(defaultVariable);
    System.out.println(protectedVariable);
    System.out.println(publicVariable);
  }
}

// 접근제어자 변수들이 선언되어있는 클래스와 "다른 클래스"입니다.
// 각 변수들에 접근이 잘 되는지 확인해보시고, 안된다면 왜 안되는지 알아보세요.
// Hint: 접근제어자 중 가장 범위가 좁은 ____는 "클래스 내"에서만 접근이 가능합니다.
class AccessModifierTest {

  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    AccessModifier accessModifier = new AccessModifier();
    accessModifier.printAll();

//    System.out.println(accessModifier.privateVariable);
    System.out.println(accessModifier.defaultVariable);
    System.out.println(accessModifier.protectedVariable);
    System.out.println(accessModifier.publicVariable);
  }
}