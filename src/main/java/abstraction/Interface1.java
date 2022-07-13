package abstraction;

public interface Interface1 {
    public static final int rock = 1;
    final int scissors = 2; // "public static" 생략
    static int paper = 3; // "public final" 생략

    public abstract String getPlayingNum();
    void call(); //"public abstract" 생략
}
