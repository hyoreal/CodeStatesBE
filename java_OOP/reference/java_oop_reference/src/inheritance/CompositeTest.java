package inheritance;

/**
 * # 2
 * 포함 관계에 관한 예제입니다.
 */
public class CompositeTest {
  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    Address address1 = new Address("한국", "서울");
    Employee kimcoding = new Employee(1, "김코딩", address1);
    kimcoding.showInfo();
  }
}

// 주소(국가, 도시) 클래스
class Address {
  String country, city;

  public Address(String country, String city) {
    this.country = country;
    this.city = city;
  }
}

// 직원 클래스
class Employee {
  int id;
  String name;
  Address address;

  public Employee(int id, String name, Address address) {
    this.id = id;
    this.name = name;
    this.address = address;
  }

  void showInfo() {
    System.out.println(id + " : " + name);
    System.out.println(name + "의 주소는 " + address.country + "의 " + address.city + "입니다.");
  }
}
