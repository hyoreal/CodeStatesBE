package com.codestates.seb.mystorage;

import java.util.Scanner;

public class MyStorage {
  static Scanner s;
  static String[][] prod_list = new String[5][2];
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
    System.out.println("-".repeat(30));
  }

  //등록가능 리스트
  public static void stringList(){
    for(int i = 0; i < prod_list.length; i++){
      System.out.println("> " + prod_list[i][0]);
    }
  }

  //등록가능리스트 + 수량
  public static void stringIntList(){
    System.out.println("[System] 현재 등록된 물건 목록 및 수량 ▼");
    for(int i = 0; i < prod_list.length; i++){
      System.out.println("> " + prod_list[i][0] + " : " + prod_list[i][1] + "개");
    }
  }

  // selectMenu() 메서드는 사용자가 프로그램의 기능을 선택할 수 있도록 합니다.
  // 인자는 입력을 받기 위한 Scanner 클래스의 객체(s) 를 전달받습니다.
  private static int num;
  static int selectMenu(Scanner s) {
    // 해당 메서드가 호출되면 프로그램의 기능을 호출할 수 있도록 안내 메시지를 출력합니다.
    //TODO:
    System.out.printf("[System] 원하는 기능의 번호를 입력하세요 : ");
    // 프로그램의 기능은 번호(정수)로 전달받습니다.
    num = s.nextInt();
    // 해당 프로그램은 사용자가 선택한 번호(select)를 반환하도록 합니다.
    return num;
  }


  // 물건 정보(제품명 등록)
  static void prod_input(Scanner s){
    System.out.printf("[System] 제품 등록에 원하는 제품명을 입력하세요 : ");
    String prodInput = s.next();
    for(int i = 0; i < prod_list.length; i++){
      if(prod_list[i][0].equals("등록 가능")){
          prod_list[i][0] = prodInput;
          System.out.println("[System] 등록이 완료되었습니다.");
          break;
        }
    }
    System.out.println("[System] 현재 등록된 제품 목록 ▼");
    stringList();
  }

  // 물건 정보(제품명) 등록 취소
  static void prod_remove(Scanner s){
    for(int i = 0; i < prod_list.length; i++){
      System.out.printf("[System] 제품 등록 취소를 원하는 제품명을 입력하세요 : ");
      String prodInput = s.next();
      if(!prod_list[i][0].equals(prodInput)){
        System.out.println("[System] 등록되지 않은 제품입니다.");
      } else {
        System.out.println("[System] 제품 취소가 완료되었습니다.");
      }
      break;
    }
  }

  // 물건 넣기 (제품 입고)
  static void prod_amount_add(){
    s = new Scanner(System.in);
    System.out.println("[System] 물건의 수량을 추가합니다.(입고)");
    stringIntList();
    System.out.printf("[System] 수량을 추가할 제품명을 입력하세요 : ");
    String add_prod = s.next();
    for(int i = 0; i < prod_list.length; i++){
      if(prod_list[i][0].equals(add_prod)){
        s = new Scanner(System.in);
        System.out.printf("[System] 추가할 수량을 입력해주세요 : ");
        int count_prod = s.nextInt();
        System.out.println("[Clear] 정상적으로 제품의 수량 추가가 완료되었습니다.");
        prod_list[i][1] = String.valueOf(count_prod);
        stringIntList();
        break;
      } else if (!prod_list[i][0].equals(add_prod)){
        continue;
      }
        System.out.println("[System] 등록되지 않은 제품입니다.");
    }
  }

  // 물건 빼기 (제품 출고)
  static void prod_amount_decrease(){
    System.out.println("[System] 제품의 출고를 진행합니다.");
    stringIntList();
    s = new Scanner(System.in);
    System.out.printf("[System] 출고를 진행할 제품명을 입력하세요 : ");
    String out_prod = s.next();
    for(int i = 0; i < prod_list.length; i++){
      if(prod_list[i][0].equals(out_prod)){
        System.out.printf("[System] 원하는 출고량을 입력하세요 : ");
        s = new Scanner(System.in);
        int outcount = s.nextInt();
        System.out.println("[Clear] 출고가 완료되었습니다.");
        int result = Integer.parseInt(String.valueOf(prod_list[i][1])) - outcount;
        prod_list[i][1] = String.valueOf(result);
        stringIntList();
        break;
      } else if(!prod_list[i][0].equals(out_prod)){
        continue;
      }
    }
    System.out.println("[System] 등록되지 않은 제품입니다.");
  }

  //재고 조회
  static void prod_search(){
    stringIntList();
  }

  //

  // main() 메서드는 점장님의 이름을 인자(args)로 전달 받습니다.
  public static void main(String[] args){
    // 해당 프로그램의 버전, 점장님의 이름, 프로그램의 기능을 출력합니다.
    //TODO:
    System.out.println("[Item_Storage_V3]");
    System.out.println("-".repeat(30));
    System.out.println( "[System] " + args + "점장님 어서오세요.");

    for(int i = 0; i < prod_list.length; i++){
      prod_list[i][0] = "등록 가능";
      prod_list[i][1] = String.valueOf(0);
    }
    // 위에서 정의한 메서드들을 알맞게 배치하여 줍니다.

    // 사용자의 기능 입력을 받기 위한 Scanner() 메서드를 사용합니다.
    Scanner s = new Scanner(System.in);


    // 프로그래밍 요청사항 6번을 확인해 보세요.
    // 사용자가 특정 번호를 입력하기 전 까지 프로그램은 종료되지 않도록 합니다.
      // 사용자의 입력에 따라 프로그램의 기능들이 실행될 수 있도록 합니다.
      // switch() 를 사용하여 줍니다.
    while(true) {
      showMenu();
      selectMenu(s);
      switch (num) {
        case 1:
          prod_input(s);
          break;
        case 2:
          prod_remove(s);
          break;
        case 3:
          prod_amount_add();
          break;
        case 4:
          prod_amount_decrease();
          break;
        case 5:
          prod_search();
          break;
        case 6:
          System.out.println("[System] 프로그램을 종료합니다.");
          System.out.println("[System] 감사합니다.");
          break;
        default:
          System.out.println("[경고] 정확한 번호를 입력해주세요.");
          break;
      }
    }
  }
}
