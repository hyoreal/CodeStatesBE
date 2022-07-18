import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FilterName {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("김영현", "조영현", "김지효", "김지효", "김찬중", "노태윤", "윤민석", "이지성");

        String[] result = names.stream()
                .distinct() // 중복 제거
                .filter(n -> n.startsWith("김")) // "김씨 나와라~~"
                .sorted() // 정렬
                .toArray(String[]::new); // 배열

       Stream.of(result).forEach(System.out::println);

    }
}
