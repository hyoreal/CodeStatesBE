package Polyporphism;

public class Polymorphism5 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal instanceof Object);
        System.out.println(animal instanceof Animal);
        System.out.println(animal instanceof Bat); // 자손타입의 참조변수로 조상 인스턴스를 참조할 수 없음

        Animal cat = new Cat();
        System.out.println(cat instanceof Object);
        System.out.println(cat instanceof Animal);
        System.out.println(cat instanceof Cat);
        System.out.println(cat instanceof Bat);
    }
}

class Animal {};

class Bat extends Animal{};

class Cat extends Animal{};