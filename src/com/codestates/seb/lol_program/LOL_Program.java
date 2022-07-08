package com.codestates.seb.lol_program;

public class LOL_Program {
  public static void main(String[] args) {
    //TODO:
    System.out.println("[안내] TPRG 스타크래프트 시작합니다.");

    System.out.println("[안내] 자신의 유닛 정보를 입력해 주세요.");

    LoL_char user1 = new LoL_char(); //유저자신의 정보받기
    String[] user1info = user1.user_create(); // 유닛생성메소드
    user1.user_print(user1info);

    System.out.println("========================================");

    System.out.println("[안내] 상대 유닛 정보를 입력해 주세요.");
    LoL_char user2 = new LoL_char();
    String[] user2info = user2.user_create();
    user2.user_print(user2info);
    System.out.println("========================================");


    //공격
    user1.attack(user1.user_info_int(user1info), user2.user_info_int(user2info) );

  }
}