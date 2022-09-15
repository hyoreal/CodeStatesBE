import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExample5 {

    public static void main(String[] args) {
        List<Student> totalList = Arrays.asList(
                new Student("김코딩", 10, Student.Gender.Male),
                new Student("김인기", 8, Student.Gender.Male),
                new Student("이자바", 9, Student.Gender.Female)
        );

        List<Student> maleList = totalList.stream()
                .filter(s -> s.getGender() == Student.Gender.Male) // 남자만 추리고
                .collect(Collectors.toList()); // 리스트로 뽑아달라!

        maleList.forEach((n) -> System.out.println(n.getName()));
    }
}


class Student {
    public enum Gender {Male, Female};
    private String name;
    private int score;
    private Gender gender;

    public Student(String name, int score, Gender gender) {
        this.name = name;
        this.score = score;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public Gender getGender() {
        return gender;
    }
}