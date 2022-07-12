package com.codestates.seb.gugudan;
import java.util.Scanner;

public class gugudan {
  public static void main(String[] args) {
    //TODO:
    // 0. 구구단을 정의하기 전 구구단 연산을 위한 변수를 선언합니다.
    int min, max;
    min = 2;
    max = 9;
    // 1. 단을 입력합니다.
    // 클래스에 포함된 메서드를 활용하기 위해 객체 생성
    // Scanner(클래스명) s(변수명) = new(객체 생성을 위한 키워드) Scanner(클래스명)(System.in);
    Scanner s = new Scanner(System.in);

    // 사용자 입력을 받기 위한 안내 문구
    System.out.println("[안내]희망하는 구구단을 숫자로 입력해주세요 (2 ~ 9)");
    int num = Integer.parseInt(s.nextLine());

    // 입력받은 값을 출력합니다.
    // 2. 구구단은 2단에서 9단까지만 가능합니다.
    // 조건 1. 입력값이 min_gugudan(2)보다 작다.
    // 조건 2. 입력값이 max_gugudan(9)보다 크다.
    // 두 조건이 모두 참 인 경우를 판단하여 [경고] 문구 출력(OR 연산자 활용)
    if(num < min || num > max){
      System.out.println("[경고]구구단은 2단 ~ 9단 까지만 선택할 수 있습니다.");
      System.out.println("프로그램을 종료합니다.");
    } else {
    System.out.println(num + " 단 이 입력되었습니다.");
    for(int i = 1; i <= 9; i++) {
      System.out.println(num + " * " + i + " = " + (num * i));
    }
    }
  }
}
