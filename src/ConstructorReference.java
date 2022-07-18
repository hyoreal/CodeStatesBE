import java.util.function.Supplier;

public class ConstructorReference {
    public static void main(String[] args) {
        //Supplier
        Supplier<Lucky> lucky = () -> new Lucky(); // 매개변수가 없는 경우
//        Supplier<Lucky> lucky = Lucky::new;

        Lucky result = lucky.get();
        System.out.println(result);

//        Function<Integer, Lucky> lucky = (i) -> new Lucky(i); //매개변수가 있는 경우
////        Function<Integer, Lucky> lucky = Lucky::new;
//
//        Lucky result = lucky.apply(20);
//        System.out.println(result.age);
    }
}

class Lucky {
//    int age;
//
//    public Lucky(int age) {
//        this.age = age;
//    }
};
