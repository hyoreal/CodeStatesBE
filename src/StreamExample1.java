import java.util.Arrays;
import java.util.stream.IntStream;

public class StreamExample1 {
    public static void main(String[] args) {

//        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        Stream<Integer> intStream = list.stream();
//        intStream.forEach((i) -> System.out.println(i));

//        Stream<String> strStream = Stream.of("a", "b", "c");
//        strStream.forEach(System.out::println);
//
//        Stream<String> strStream2 = Stream.of(new String[] {"a", "b", "c", "d"});
//        strStream2.forEach(System.out::println);

        int[] intArr = {1,2,3,4,5,6};
        IntStream intStream = Arrays.stream(intArr); //숫자와 관련된 경우 intStream을 사용하는 것을 권장
////        intStream.forEach(System.out::println);
//
////        System.out.println("sum=" + intStream.sum());
        System.out.println("average=" + intStream.average());

    }
}
