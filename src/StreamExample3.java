import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample3 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        Stream<Integer> stream = list.stream();

//        Integer reduce = stream.reduce(0, (x, y) -> x+ y);
//        System.out.println(reduce);

//        // 스트림을 컬렉션, 배열로 변환
//        List<Integer> result = stream.filter(e -> e % 2 == 0).collect(Collectors.toList());
//        System.out.println(result);
//
//        // 통계
//        Stream<Integer> result2 = result.stream();
//        Long count = result2.collect(counting());
//        System.out.println(count);

    }
}
