package com.codestates.seb.kdelivery;

import java.util.Scanner;

import static jdk.internal.org.jline.utils.Colors.s;

public class Shop {
  /**
   * 등록 가능한 음식 수, 음식 초기화 변수, 가격 필드 생성
   * 매장명, 메뉴명, 가격 필드 생성
   * */

  private static final int FOOD_MAX = 5;
  private static final String EMPTY_FOOD = null;
  private static final int EMPTY_PRICE = 0;
  private static String shopName;
  private static String[] foodNames;
  private int[] prices;

  /**
   * @Shop() : 생성자 정의
   * 매장만 먼저 입력받도록 합니다.
   * 나머지 변수는 initValues() 에서 정의합니다.
   * */

  Shop(String shop) {
     this.shopName = shop;
     initValues();
  }

  public String toString(){
     return shopName;
  };

  /**
   * @initValues() : 메뉴명와 가격정보를 담는 배열 생성 및 초기화
   * EMPTY_FOOD = "", EMPTY_PRICE = 0
   */

  private void initValues(){
    foodNames = new String[FOOD_MAX];
    prices = new int[FOOD_MAX];
  }

  /**
   * @addFood() : 위 코드에서 정의된 변수를 받아 출력과 객체에 저장합니다.
   */

  void addFood(String foodName, int price){
    for(int i = 0; i < FOOD_MAX; i++){
      if(this.foodNames[i] == null){
        foodNames[i] = foodName;
        prices[i] = price;
        return;
      }
    }
  }
}
