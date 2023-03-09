import java.util.Arrays;

public class StreamExample4 {
    public static void main(String[] args) {
        int[] intArr = {1,2,3,4,5};

        long count = Arrays.stream(intArr).count();
        System.out.println("intArr의 전체 요소 개수 " + count);

        long sum = Arrays.stream(intArr).sum();
        System.out.println("intArr의 전체 요소 합 " + sum);

        double avg = Arrays.stream(intArr).average().getAsDouble();
        System.out.println("전체 요소의 평균값 " + avg);

        int max = Arrays.stream(intArr).max().getAsInt();
        System.out.println("최대값 " + max);

        int min = Arrays.stream(intArr).min().getAsInt();
        System.out.println("최소값 " + min);

        int first = Arrays.stream(intArr).findFirst().getAsInt();
        System.out.println("배열의 첫번째 요소 " + first);
    }
}
