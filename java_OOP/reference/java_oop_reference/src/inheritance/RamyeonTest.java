package inheritance;

/**
 * # 5
 * 오버라이딩에 관한 예제입니다.
 */
public class RamyeonTest {
  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    Ramyeon myLunch = new Ramyeon();
    myLunch.boil();

    EggRiceCakeRamyeon myDiner = new EggRiceCakeRamyeon();
    myDiner.boil();

    떡만두햄치즈라면 myTomorrow = new 떡만두햄치즈라면();
    myTomorrow.boil();

  }
}

// 기본 라면 클래스
class Ramyeon {
  int water;
  String myeon;
  String powder;

  void boil() {
    System.out.println("물 500ml를 넣고 끓입니다.");
  }
}

// 기본 라면에 1) 재료를 추가하고 2) 물을 더 넣고싶은 "계란떡라면" 클래스
class EggRiceCakeRamyeon extends Ramyeon{
  int egg;
  String riceCake;

  void boil() {
    System.out.println("물 600ml를 넣고 끓입니다.");
  }
}

// 기본 라면에 1) 재료를 추가하고 2) 물을 더 넣고싶은 "떡만두햄치즈라면" 클래스
class 떡만두햄치즈라면 extends Ramyeon {
  String riceCake;
  String mandu;
  int Ham;
  String cheese;

  void boil() {
    System.out.println("물 700ml를 넣고 끓입니다.");
  }
}