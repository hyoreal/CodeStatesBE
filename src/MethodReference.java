import java.util.function.Function;

public class MethodReference {
    public static void main(String[] args) {
//        Function<String, Integer> example = (String str) -> Integer.parseInt(str);
//
//        Function<String, Integer> example = 클래스이름::메서드이름
        Function<String, Integer> example = Integer::parseInt;

        System.out.println(example.apply("1000"));
    }
}
