package Polyporphism;

public class Polymorphism1 {
    public static void main(String[] args) {

        // 원래 우리가 사용하던 방식
        Car car = new Car();
        car.start();
        car.stop();

        SportCar sportcar = new SportCar();
        sportcar.transform();

        Car sportcar2 = new SportCar();
//        sportcar2.transform(); // 에러 발생!!

    }
}

class Car {
    String model;
    String color;


    void start () {
        System.out.println("시동을 걸었습니다.");
    }

    void stop() {
        System.out.println("멈췄습니다.");
    }
}

class SportCar extends Car {

    void transform() {
        System.out.println("오픈카 모드!");
    }
}