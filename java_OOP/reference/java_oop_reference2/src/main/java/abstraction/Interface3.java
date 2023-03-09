package abstraction;

public class Interface3 {
    public static void main(String[] args) {
        User user = new User();

        Provider provider = new Provider();
        user.callProvider(provider);
    }
}


class User {
    public void callProvider(Provider provider) {
        provider.call();
    }
}

class Provider {
    public void call() {
        System.out.println("무야호~");
    }
}

