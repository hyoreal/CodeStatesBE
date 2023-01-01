package Polyporphism;

public class Polymorphism3 {
    public static void main(String[] args) {
        Car2 car = new Car2();
        car.giveRide();


        Vehicle vehicle = (Vehicle) car; // 상위 클래스 Vehicle 타입으로 변환(생략 가능) -> 업캐스팅
//        vehicle.giveRide(); -> 에러 출력

        Car2 car2 = (Car2) vehicle; // 하위 클래스 Car타입으로 변환(생략 불가능) -> 다운캐스팅
        car.giveRide();

//        MotorBike motorBike = (MotorBike) car2; // 상속관계가 아니므로 타입 변환 불가 -> 에러 출력
    }
}


class Vehicle {
    String model;
    String color;
    int wheels;

    void startEngine() {
        System.out.println("시동 걸기");
    }

    void accelerate() {
        System.out.println("속도 올리기");
    }

    void brake() {
        System.out.println("브레이크!");
    }
}

class Car2 extends Vehicle {
    void giveRide() {
        System.out.println("다른 사람 태우기");
    }
}

class MotorBike extends Vehicle {
    void performance() {
        System.out.println("묘기 부리기");
    }
}
