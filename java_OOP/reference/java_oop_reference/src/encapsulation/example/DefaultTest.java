package encapsulation.example;

/**
 * # 2
 * 접근 제어자 관련 예제입니다.
 * encapsulation.example 패키지의 AccessModifier 클래스를 참고하시기 바랍니다.
 * 해당 클래스에 선언된 네 가지 변수에 각각 접근이 가능한가 확인해주세요.
 */

// 다른 java 파일이지만 AccessModifier 클래스와 동일한 패키지에 존재합니다.
// 네 가지 변수에 접근할 때 차이가 있는지, 접근이 되는 혹은 되지 않는 변수들엔 각각의 이유가 무엇인지 확인해보세요.
public class DefaultTest {

  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    AccessModifier accessModifier = new AccessModifier();
//    System.out.println(accessModifier.privateVariable);
    System.out.println(accessModifier.defaultVariable);
    System.out.println(accessModifier.protectedVariable);
    System.out.println(accessModifier.publicVariable);
  }
}
