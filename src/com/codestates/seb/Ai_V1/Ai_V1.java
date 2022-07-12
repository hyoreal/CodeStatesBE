package com.codestates.seb.Ai_V1;
import java.util.Scanner;

public class Ai_V1 {
  public static void main(String[] args) {
    //TODO:
    // 사용자의 입력을 받기 위한 Scanner 객체를 생성합니다.
    Scanner s = new Scanner(System.in);

    // 시스템 프로그램 안내 출력문을 정의합니다
    System.out.println("[인공지능 프로그램 V1]");
    System.out.println("=".repeat(30));
    System.out.printf("[System] 예측에 영향을 주는 원인(광고비)의 값을 알려주세요 (단위 원) :");
    int y = Integer.parseInt(s.next());
    System.out.println("[안내] 입력된 광고비는 " + y + " 원 입니다.");
    // 수식에 필요한 변수를 먼저 정의하여 줍니다. (※ 수식과 똑같은 변수명으로 정의 )
    int a, b, result;
    // 안내 사항에 나온 것 처럼 임의의 값으로 정의합니다.
    a = 2;
    b = 1;

    // 웹 페이지 방문자 수를 예측하기 위한 수식을 코드로 정의합니다.
    result = a * y + b;

    // 결과로 나온 값은 printf()를 통해 출력합니다.
    System.out.printf("[안내] AI의 예측(클릭 수)은 " + result + "회 입니다.");
  }
}
