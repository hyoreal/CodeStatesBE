package Polyporphism;

public class Polymorphism2 {

    public static void main(String[] args) {
        Friend friend = new Friend(); // 객체 타입과 참조변수 타입의 일치
        BoyFriend boyfriend = new BoyFriend();

        Friend girlfriend = new GirlFriend(); // 상위클래스 타입의 참조변수로 하위클래스 객체 참조 OK
//        GirlFriend girlFriend2 = new Friend(); // 반대는 불가!

        friend.friendInfo();
        boyfriend.friendInfo();
//        girlfriend.friendInfo();
    }
}

//참조변수의 다형성 예시

class Friend {
    public void friendInfo() {
        System.out.println("나는 당신의 친구입니다.");
    }
}

class BoyFriend extends Friend {

    public void friendInfo() { // 메서드 오버라이딩
        System.out.println("나는 당신의 남자친구입니다.");
    }
}

class GirlFriend extends Friend {
    public void friendInfo() { //메서드 오버라이딩
        System.out.println("나는 당신의 여자친구입니다.");
    }
}