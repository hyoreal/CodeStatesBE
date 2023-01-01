import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list);

        // 기존 방식
//        for(int i = 0; i < list.size(); i++) {
//            System.out.println("리스트 요소: " + list.get(i));
//        }

        // stream을 활용한 방식

        Stream<Integer> stream = list.stream();
        stream.forEach(el -> System.out.println("리스트 요소: " + el ));
    }

}
