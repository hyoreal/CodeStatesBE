import java.util.Arrays;

public class Practice {

    public static void main(String[] args) {

        int[] temperature = {25, 29, 30, 31, 26, 30, 33};


        // 30도 이상의 온도가 몇 개인지 계산해주는 스트림

        long count = Arrays.stream(temperature)
                .filter(temp -> temp >= 30)
                .count();

        System.out.println(count);

    }
}
