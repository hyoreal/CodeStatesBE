package com.codestates.seb.calculator;
import java.util.Scanner;

public class Calculator {
  public static void main(String[] args) {
    System.out.println("===Java Calculator===");

   // ���� ���� �Է��� �ް�, �Է¹��� ��ŭ ����� �� �� �־�� �մϴ�.
   // �ݺ����� ���� ���� ���� �Է��� ���� �� �ֽ��ϴ�.
   // ����ڰ� �߸��� ���� �Է��� ���, ����ڿ��� �ǵ���� �� �� �ֽ��ϴ�.
   // ���� ��� ��Ģ���� ������(+, -, *, /)�� �ƴ� �ٸ� ��ȣ�� ���� ���, �߸��� �Է��̶�� �ǵ���� �� �� �־�� �մϴ�.

    op op = new op();

    while(true) {
    Scanner input = new Scanner(System.in);
    double num1, num2 ,choice;
    char Op;

      System.out.println("0�� ������ ����˴ϴ�. ����Ͻ÷��� �ٸ� ���ڸ� �����ּ���.");
      choice = input.nextInt();

      if(choice == 0){
        System.out.println("����Ǿ����ϴ�.");
        break;
      }
      if(choice != 1){
        continue;
      }

      System.out.println("ù��° ���ڸ� �Է����ּ���.");
      num1 = input.nextDouble();

      System.out.println("�����ڸ� �Է����ּ���.");
      Op = input.next().charAt(0);

      System.out.println("�ι�° ���ڸ� �Է����ּ���.");
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
        System.out.println("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ���.");
        continue;
      }

      System.out.println(result);
    }

  }
}
