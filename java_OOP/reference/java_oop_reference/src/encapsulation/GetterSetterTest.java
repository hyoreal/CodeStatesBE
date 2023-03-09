package encapsulation;

/**
 * # 5
 * getter, setter 관련 예제입니다.
 * Worker 클래스의 필드에 직접적인 접근이 되는지 안되는지, 각각의 이유는 무엇인지 고민해보세요.
 * 접근이 되지 않는 경우, 어떻게 해야 필드에 접근하여 값을 저장하고, 읽을 수 있는지 확인해보세요.
 */
public class GetterSetterTest {

  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    Worker w = new Worker();
//    w.name = "김코딩";
    w.setName("김코딩");
    w.setAge(25);
    String wname = w.getName();
    System.out.println("name = " + wname);
    int wage = w.getAge();
    System.out.println("age = " + wage);
  }
}

class Worker {
  private String name;
  private int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}