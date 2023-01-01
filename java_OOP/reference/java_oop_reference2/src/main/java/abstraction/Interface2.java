package abstraction;

interface Animal2 { // 인터페이스 선언. public abstract 생략 가능.
    public abstract void cry();
//    void bite(); // 강제하는 속성
}

interface Pet {
    void play();
}

public class Interface2 {
    public static void main(String[] args) {
        Dog2 dog = new Dog2();
        Cat2 cat = new Cat2();

        dog.cry();
        dog.play();
        cat.cry();
        cat.play();
    }
}


class Dog2 implements Animal2, Pet { // Animal과 Pet 인터페이스 다중 구현
    public void cry(){ //메서드 오버라이딩
        System.out.println("멍멍!");
    }

    public void play(){ //메서드 오버라이딩
        System.out.println("원반 던지기");
    }
}

class Cat2 implements Animal2, Pet { // Animal과 Pet 인터페이스 다중 구현
    public void cry(){
        System.out.println("야옹~!");
    }

    public void play(){
        System.out.println("쥐 잡기");
    }
}