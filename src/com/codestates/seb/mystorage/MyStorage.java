package com.codestates.seb.mystorage;

import java.util.Scanner;

public class MyStorage {

  // showMenu() 메서드가 호출되면 프로그램의 기능들을 보여줄 수 있도록 정의합니다.
  static void showMenu(){
    // 사용자를 위해 해당 기능들 앞에 번호를 넣어 알아보기 쉽도록 합니다.
    //TODO:
    System.out.println("[System] 해당 프로그램의 기능입니다.");
    System.out.println("1. 물건 정보(제품명) 등록하기");
    System.out.println("2. 물건 정보(제품명) 등록 취소하기");
    System.out.println("3. 물건 넣기 (제품 입고)");
    System.out.println("4. 물건 빼기 (제품 출고)");
    System.out.println("5. 재고 조회");
    System.out.println("6. 프로그램 종료");
  }

  // selectMenu() 메서드는 사용자가 프로그램의 기능을 선택할 수 있도록 합니다.
  // 인자는 입력을 받기 위한 Scanner 클래스의 객체(s) 를 전달받습니다.
  static int num;
  static int selectMenu(){
    // 해당 메서드가 호출되면 프로그램의 기능을 호출할 수 있도록 안내 메시지를 출력합니다.
    //TODO:
    System.out.println("-".repeat(30));
    System.out.printf("[System] 원하는 기능의 번호를 입력하세요 : ");
    // 프로그램의 기능은 번호(정수)로 전달받습니다.
    Scanner s = new Scanner(System.in);
    num = s.nextInt();
    // 해당 프로그램은 사용자가 선택한 번호(select)를 반환하도록 합니다.
    return num;
  }

  // main() 메서드는 점장님의 이름을 인자(args)로 전달 받습니다.
  public static void main(String[] args){
    // 해당 프로그램의 버전, 점장님의 이름, 프로그램의 기능을 출력합니다.
    //TODO:
    System.out.println("[Item_Storage_V3]");
    System.out.println("-".repeat(30));
    System.out.println( "[System] " + args + "점장님 어서오세요.");


    // 위에서 정의한 메서드들을 알맞게 배치하여 줍니다.
    showMenu();
    selectMenu();
    // 사용자의 기능 입력을 받기 위한 Scanner() 메서드를 사용합니다.
    Scanner s = new Scanner(System.in);


    // 프로그래밍 요청사항 6번을 확인해 보세요.
    // 사용자가 특정 번호를 입력하기 전 까지 프로그램은 종료되지 않도록 합니다.
      // 사용자의 입력에 따라 프로그램의 기능들이 실행될 수 있도록 합니다.
      // switch() 를 사용하여 줍니다.

    switch (num) {
      case 1:
        System.out.print(1);
        break;
      case 2:
        System.out.print(2);
        break;
      case 3:
        System.out.print(3);
        break;
      case 4:
        System.out.print(4);
        break;
      case 5:
        System.out.print(5);
        break;
      case 6:
        System.out.println("[System] 프로그램을 종료합니다.");
        System.out.println("[System] 감사합니다.");
        break;
      default:
        System.out.println("[경고] 정확한 번호를 입력해주세요.");
    }

  }
}
