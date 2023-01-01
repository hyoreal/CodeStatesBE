package abstraction;

interface Cover {
    void call();
}

public class Interface4 {
    public static void main(String[] args) {
        User2 user2 = new User2();

        user2.callProvider(new Provider2());
        user2.callProvider(new Provider3());
    }
}


class User2 {
    public void callProvider(Cover cover) {
        cover.call();
    }
}


class Provider2 implements Cover {
    public void call() {
        System.out.println("무야호~");
    }
}

class Provider3 implements Cover {
    public void call() {
        System.out.println("얏호!");
    }
}