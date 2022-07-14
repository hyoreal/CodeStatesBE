package com.codestates.seb.kdelivery;

public class Feedback {
  private String customerName;
  private String shopName;
  private String foodName;
  private int grade;

  /**
   * @Feedback() : 정보를 저장합니다
   */

  Feedback(){}

  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }

  public void setFoodName(String foodName) {
    this.foodName = foodName;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  /**
   * @getStars() : 사용자가 입력한 점수가 별점으로 전환
  }
   */

  private String getStars(){
    String stars = " ";
    for(int i = 0; i < grade; i++){
      stars = stars + "★";
    }
    return stars;
  }

  /**
   * @printInfo() : 출력
   */

  void printInfo(){
    System.out.println(customerName + " [고객님]");
    System.out.println("-".repeat(30));
    System.out.println("주문 매장 : " + shopName);
    System.out.println("주문 메뉴 : " + foodName);
    System.out.println("별점 : " + this.getStars());
  }

}
