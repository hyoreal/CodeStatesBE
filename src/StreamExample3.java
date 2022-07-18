import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamExample3 {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> stream = list.stream();

        List<Integer> result = stream.filter(e -> e % 2 == 0).collect(Collectors.toList());
        System.out.println(result);


    }
}
