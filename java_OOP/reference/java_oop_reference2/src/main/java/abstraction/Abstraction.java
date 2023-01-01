package abstraction;

public class Abstraction {
    public static void main(String[] args) {

        Animal dog = new Dog();
        dog.sound();

        Cat cat = new Cat();
        cat.sound();
    }
}

abstract class Animal {
    public String kind;

    public abstract void sound();
//    abstract void bite();
}

class Dog extends Animal {
    public Dog() {
        this.kind = "포유류";
    }

    public void sound() {
        System.out.println("멍멍멍!!");
    }
}

class Cat extends Animal {
    public Cat() {
        this.kind = "포유류";
    }

    public void sound() {
        System.out.println("야옹~~");
    }
}