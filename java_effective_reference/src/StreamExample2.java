public class StreamExample2 {
    public static void main(String[] args) {

        // 생략
//        IntStream intStream = IntStream.rangeClosed(1, 10);
//        System.out.println(intStream);
//        intStream.skip(5).forEach(System.out::println);

//        IntStream intStream2 = IntStream.of(1, 2, 2, 3, 3, 4, 5, 5, 7, 7, 7, 8);
//        intStream2.distinct() // 중복제거 // 1, 2, 3, 4, 5 ,7,8
//                .filter(i -> i % 2 == 0) // 필터링  // 2,4,8
//                .forEach(System.out::println); // 출력(최종연산)

//            IntStream ints = new Random().ints();
//            IntStream ints = new Random().ints(5);
//            IntStream ints = new Random().ints(5, 10);
//            ints.forEach(System.out::println);


//        List<String> names = Arrays.asList("kimcoding", "javalee", "ingikim", "kimcoding");
//        names.stream()
//                .map(s -> s.toUpperCase())
//                .forEach(n->System.out.println(n));

//        IntStream intStream3 = IntStream.of(1, 2, 2, 3, 3, 4, 5, 5, 7, 7, 7, 8);
//
//        int sum = intStream3.filter(i -> i % 2 == 0)
//                .peek(i -> System.out.println(i))
//                .sum();
//
//        System.out.println("sum = " + sum);

    }
}
