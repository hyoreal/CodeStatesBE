package com.codestates.seb.kdelivery;

public class Login {
    //회원가입정보 저장
    private static final String[] IDList = new String[5];
    private static final String[] passWordList = new String[5];

    public static void addLoginList(String ID, String passWord){
        for (int i = 0; i < 5; i++){
            if(IDList[i] == null){
                IDList[i] = ID;
                passWordList[i] = passWord;
                return;
            }
        }
    }

    //아이디 및 비번 확인
    public static void checkLoginInfo(String ID, String password){
        for(int i = 0; i < IDList.length; i++){
            if(!IDList[i].equals(ID) || !passWordList[i].equals(password)){
              System.out.println("[안내] ID 또는 비밀번호가 틀렸습니다.");
                System.out.println("[안내] 다시 로그인해주세요.");
            } else {
                System.out.println("[안내] 치킨의 민족에 오신걸 환영합니다.");
            }
            return;
        }
    }


    //로그인 안하고 메뉴 선택시
    public static void notLogin(){
        for(int j = 0; j < IDList.length; j++){
            if(IDList[j] == null){
                System.out.println("[안내] 로그인 후 이용해주세요.");
            }
            return;
        }
    }
}
