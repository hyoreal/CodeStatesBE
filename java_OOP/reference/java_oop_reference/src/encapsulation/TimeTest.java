package encapsulation;

/**
 * # 6
 * getter, setter 관련 예제 입니다.
 * setter (set~) 를 통해 값을 저장할 때, 조건을 걸어 특정 범위 내의 값만 담고 싶다면
 * 여러분이 Java 기초에서 학습하신 조건문을 삽입할 수 있습니다.
 */
public class TimeTest {
  public static void main(String[] args) {
    // 아래 내용은 참고만 하시고, 직접 작성해서 실행해보세요!
    Time time = new Time();
    time.setHour(17);
    System.out.println("time.getHour() = " + time.getHour());
  }
}

class Time {
  private int hour;
  private int minute;
  private int second;

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    if (hour < 0 || hour > 23) return;
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    if (minute < 0 || minute > 59) return;
    this.minute = minute;
  }

  public int getSecond() {
    return second;
  }

  public void setSecond(int second) {
    if (second < 0 || second > 59) return;
    this.second = second;
  }
}