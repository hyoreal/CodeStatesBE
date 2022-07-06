package com.codestates.seb.calculator;
import java.util.Scanner;

public class Calculator {
  public static void main(String[] args) {
    System.out.println("===Java Calculator===");

   // 여러 번의 입력을 받고, 입력받은 만큼 계산을 할 수 있어야 합니다.
   // 반복문을 통해 여러 번의 입력을 받을 수 있습니다.
   // 사용자가 잘못된 값을 입력한 경우, 사용자에게 피드백을 줄 수 있습니다.
   // 예를 들어 사칙연산 연산자(+, -, *, /)가 아닌 다른 기호가 들어온 경우, 잘못된 입력이라는 피드백을 줄 수 있어야 합니다.

    op op = new op();

    while(true) {
    Scanner input = new Scanner(System.in);
    double num1, num2 ,choice;
    char Op;

      System.out.println("0을 누르면 종료됩니다. 계속하시려면 다른 숫자를 눌러주세요.");
      choice = input.nextInt();

      if(choice == 0){
        System.out.println("종료되었습니다.");
        break;
      }
      if(choice != 1){
        continue;
      }

      System.out.println("첫번째 숫자를 입력해주세요.");
      num1 = input.nextDouble();

      System.out.println("연산자를 입력해주세요.");
      Op = input.next().charAt(0);

      System.out.println("두번째 숫자를 입력해주세요.");
      num2 = input.nextDouble();

      double result;
      if (Op == '+') {
        result = op.plus(num1, num2);
      } else if (Op == '-') {
        result = op.minus(num1, num2);
      } else if (Op == '*') {
        result = op.multiple(num1, num2);
      } else if (Op == '/') {
        result = op.division(num1, num2);
      } else {
        System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
        continue;
      }

      System.out.println(result);
    }

  }
}
