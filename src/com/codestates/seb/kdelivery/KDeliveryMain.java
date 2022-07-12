package com.codestates.seb.kdelivery;

import jdk.internal.org.jline.utils.Log;

import java.util.Scanner;

// 클래스를 정의 합니다.
public class KDeliveryMain {
  /**
   * 음식점 등록 개수, 음식 주문 가능 횟수, 리뷰 등록 가능 횟수 정의
   * */
  private static final int SHOP_MAX = 5;
  private static final int ORDER_MAX= 5;
  private static final int FEEDBACK_MAX = ORDER_MAX;


  /**
   * 배열을 담을 수 있는 객체 생성
   * 사용 범위, 객체 타입, 객체 이름
   */
  private Shop[] shops;
  private Order[] orders;
  private Feedback[] feedbacks;

  // 해당 변수를 제어하는 Idx변수를 정의하고 초기화


  private static final Scanner s = new Scanner(System.in); // 사용자의 입력을 받는 객체 생성

  /**
   * @KDeliveryMainV1() : 매장 정보, 주문 정보, 리뷰 정보 초기화
   * initValues() 메서드 사용
   * */
  private void KDeliveryMainV1(){
    initValues();
  }


  /**
   * @initValues() : 객체에 저장될 수 있는 크기 지정
   * SHOP_MAX, ORDER_MAX, FEEDBACK_MAX = 5
   * */

  private void initValues() {
    shops = new Shop[SHOP_MAX];
    orders = new Order[ORDER_MAX];
    feedbacks = new Feedback[FEEDBACK_MAX];
  }


  /**
   * @close() : 프로그램 종료를 위해 사용되는 메서드
   * 사용자가 종료를 선언하면 동작합니다.
   * main()에서 활용됩니다.
   * */
  static void close(){

  }
  /**
   * selectMainMenu() : 기능을 나열하며, 사용자가 원하는 기능을 정수로 받습니다.
   */
  private void selectMainMenu(){
    System.out.println("[치킨의 민족 프로그램 V1]");
    System.out.println("-".repeat(30));
    System.out.println("0) 로그인하기");
    System.out.println("1) 회원가입하기");
    System.out.println("2) [사장님용] 음식점 등록하기");
    System.out.println("3) [고객님과 사장님용] 음식점 별점 조회하기");
    System.out.println("4) [고객님용] 음식 주문하기");
    System.out.println("5) [고객님용] 별점 등록하기");
    System.out.println("6) 프로그램 종료하기");
    System.out.println("-".repeat(30));
  }

  private void chickenProgram(int num){
    switch (num) {
      case 0 : login(); break;
      case 1 : joinMenu(); break;
      case 2 :
        selectAddShopMenu(); break;
      case 3 :
        selectDashboardMenu(); break;
      case 4 :
        selectOrderMenu(); break;
      case 5 :
        selectFeedbackMenu(); break;
      case 6 : close(); break;
    }
  }


  private int selectNumber(){
    System.out.println("[시스템] 무엇을 도와드릴까요?");
    return Integer.parseInt(s.nextLine());
  }
  /**
   * @return
   * @selectAddShopMenu() : 음식점의 정보를 등록합니다.
   * @shops : 가게 정보를 저장합니다.
   * @shopIdx : 가게 정보의 인덱스
   */
  //회원가입 정보등록


  private void joinMenu(){
    Login login = new Login();

    System.out.println("[안내] 회원가입을 시작하겠습니다.");
    System.out.println("[안내] ID를 입력해주세요.");
    String id = s.nextLine();
    System.out.println("[안내] 비밀번호를 입력해주세요.");
    String passWord = s.nextLine();
    System.out.println("[안내] 비밀번호를 확인하겠습니다. 다시 한번 입력해주세요.");
    String passWordCheck = s.nextLine();

    if (!passWord.equals(passWordCheck)) {
        System.out.println("[경고] 비밀번호가 다릅니다.");
    }

    Login.addLoginList(id,passWordCheck);


    System.out.println("[안내] 회원가입이 완료되었습니다.");
  }


  private void login(){
    System.out.println("[안내] ID를 입력해주세요.");
    String inputID = s.nextLine();
    System.out.println("[안내] 비밀번호를 입력해주세요.");
    String inputPassword = s.nextLine();

    Login.checkLoginInfo(inputID, inputPassword);
  }




  public void selectAddShopMenu() {

    /**
     * @Shop.java 의 Shop 클래스를 활용한 객체 생성
     * @public 클래스 : 동일 패키지 및 다른 패키지에서 사용가능
     * @addFood() : Shop.java 의 Shop 클래스의 addFood() 메서드
     * 해당 메서드는 매장명, 음식명, 가격을 입력받아 객체에 저장
     * 값이 저장될 때 마다 shopIdx 값 증가
     */
    System.out.println("[안내] 반갑습니다. 가맹주님!");
    System.out.println("[안내] 음식점 상호는 무엇인가요?");
//    String shopName = s.nextLine();
    Shop shop = new Shop(s.nextLine());

    System.out.println("[안내] 대표 메뉴 이름은 무엇인가요?");
    String foodName = s.nextLine();
    System.out.println("[안내] 해당 메뉴 가격은 얼마인가요?");
    int foodPrice = Integer.parseInt(s.nextLine());

    shop.addFood(foodName, foodPrice);


    System.out.println("[안내]"+ shop + "에 음식(" + foodName + ", " + foodPrice +") 추가되었습니다.");
    System.out.println("[시스템] 가게 등록이 정상 처리되었습니다.");

  }


  /**
   * @selectDashboardMenu() : 해당 메서드는 등록된 가게 정보를 출력합니다.
   * Feedback.java 파일의 클래스 및 메서드를 활용합니다.
   * */

  public void selectDashboardMenu(){
    for(Feedback feedback : feedbacks){
      if(feedback != null) {
        feedback.printInfo();
      }
    }
  }



  /**
   * @selectOrderMenu() : 주문 기능
   * 사용자의 입력을 받아 orders 객체에 저장
   * */

  public void selectOrderMenu(){
    Order order = new Order();
    System.out.println("[안내] 고객님! 메뉴 주문을 진행하겠습니다!");
    System.out.println("[안내] 주문자 이름을 알려주세요!");
    String customerName = s.nextLine();
    System.out.println("[안내] 주문할 음식점 상호는 무엇인가요?");
    String shopName = s.nextLine();
    System.out.println("[안내] 주문할 메뉴 이름을 알려주세요!");
    String foodName = s.nextLine();

    order.setCustomerName(customerName);
    order.setShopName(shopName);
    order.setFoodName(foodName);
    orderList(order);

    System.out.println();
    System.out.println("[안내] " + customerName +"님!");
    System.out.println("[안내] " + shopName + "에 " + foodName + "주문이 완료되었습니다.");
  }

  private void orderList(Order order){
    for(int i = 0; i < ORDER_MAX; i++){
      if(orders[i] == null){
        orders[i] = order;
        return;
      }
    }
  }

  /**
   * @selectFeedbackMenu() : 메뉴의 피드백을 입력받는 기능
   * */
  private void selectFeedbackMenu(){
    System.out.println("[안내] 고객님! 별점 등록을 진행합니다.");
    System.out.println("[안내] 주문자 이름은 무엇인가요?");
    String customerName = s.nextLine();
    System.out.println("[안내] 음식점 상호는 무엇인가요?");
    String shopName = s.nextLine();
    System.out.println("[안내] 주문하신 음식 이름은 무엇인가요?");
    String foodName = s.nextLine();
    System.out.println("[안내] 음식맛은 어떠셨나요? (1점 ~ 5점)");
    int grade = Integer.parseInt(s.nextLine());

    Feedback feedback = new Feedback();
    feedback.setCustomerName(customerName);
    feedback.setShopName(shopName);
    feedback.setFoodName(foodName);
    feedback.setGrade(grade);

    feedbackList(feedback);

    System.out.println("[안내] 리뷰 등록이 완료되었습니다.");
  }

  private void feedbackList(Feedback feedback){
    for(int i = 0; i< FEEDBACK_MAX; i++){
      if(feedbacks[i] == null){
        feedbacks[i] = feedback;
        return;
      }
    }
  }


  public static void main(String[] args) {
    KDeliveryMain kDeliveryMain = new KDeliveryMain();
    kDeliveryMain.KDeliveryMainV1();

    while(true){
      kDeliveryMain.selectMainMenu();
      int num = kDeliveryMain.selectNumber();

      //로그인 안된 상태로 메뉴 눌렀을때
      if(num == 2 || num == 3 || num == 4|| num == 5){
        Login.notLogin();
      }

      if(num == 6) {
        System.out.println("[안내] 이용해 주셔서 감사합니다.");
        break;
      }
      kDeliveryMain.chickenProgram(num);

    }
  }
}