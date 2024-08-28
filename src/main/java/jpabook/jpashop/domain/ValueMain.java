package jpabook.jpashop.domain;

public class ValueMain {
    public static void main(String[] args) {
        //int, double 같은 기본 타입(primitive type)은 절대 공유X
//        int a =10;
//        int b = a; //이때 a 자체가 b에 들어가는 게 아니라 a의 값이 복사되서 b로 들어가는 것
//        a=20;
//위 내용에서
//        a= 20
//        b= 10
//으로 a와 b는 서로 값을 공유하지 않는다

//        - Integer같은 래퍼 클래스나 String 같은 특수한 클래스는 공유
//        가능한 객체이지만 변경X
//
//                >>레퍼런스를 끌고 가는데 이건 공유가 되는 것
//        Integer a = new Integer(10);
//        Integer b = a;
//        이때 a의 주소값(참조값)만 b로 넘어간다.
//                그래서
//        a=10
//        b=10
//        이라는 결과가 된다.
//                이때
//        a.setValue(20)으로 a의 클래스 내부값을 20으로 바꾼다면
//        b도 20이 될 것이다.
//                왜냐하면 레퍼런스(주소값)가 넘어가서 둘 다 같은 인스턴스를 공유하기 때문이다.
    }
}
