enum Level {
    LOW, // 0
    MEDIUM, // 1
    HIGH // 2
}

public class EnumTest2 {
    public static void main(String[] args) {
        Level level = Level.MEDIUM;

        Level[] allLevels = Level.values(); // 모든 열거 객체 반환

        for(Level x : allLevels) {
            System.out.printf("%s=%d%n", x.name(), x.ordinal()); // 이름과 순번 확인
        }

        Level findLevel = Level.valueOf("LOW");
        System.out.println(findLevel);


        System.out.println(Level.LOW == Level.valueOf("LOW")); // 상수 일치 여부 확인


        switch(level) {
            case LOW:
                System.out.println("낮은 레벨");
                break;
            case MEDIUM:
                System.out.println("중간 레벨");
                break;
            case HIGH:
                System.out.println("높은 레벨");
                break;
        }
    }
}
