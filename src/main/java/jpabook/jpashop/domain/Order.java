package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")//DB에 ORDER가 예약어로 되어 있어서 ORDERS로 많이 사용
public class Order extends BaseEntity{
    @Id @GeneratedValue
    @Column(name="ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    //라이플 사이클 매칭을 위한 Cascade 설정
    private List<OrderItem> orderItems= new ArrayList<>();
    //이렇게 편의 메소드를 통해 오더아이템 객체를 저장
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    //라이플 사이클 매칭을 위한 Cascade 설정
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    //    @Column(name="MEMBER_ID")
//    private Long memberId;
//    //객체지향스럽지 않은 설계
//    //RDB에 맞춘 설계라 볼 수 있다.
//
//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }

//    private Member member;
    //오더를 주문한 사람이 누군지 이렇게맴버 아이디가 아니라 내부에 맴버 객체가
    //존재하는 방식이여야 하지않나?

    private LocalDateTime orderDate;
    //하이버네이트에서는 이렇게 시간데이터를 가져올 수 있다.
//    orderDate같은 건 스프링부트를 사용하면 이런 데이터는
//    스프링 부트가 관례를 오버라이딩이 가능해져서
    //orderDate가 order_date로 만들도록 하이버네이트로 설정할 수 있다.
//    하이버네이트나jpa를 직접쓸때는 관례를 쓰는게 맞는데 
//    스프링부트에서 올리면 기본 설정을 order_date로 가져간다.
    //쉽게 말해서 부트는 camelCase를 읽어서 aaa_aaa같은 형식으로 변경해주는 것이
    //언더스코어 소문자방식으로 변경해서 가져간다.
    //매핑 정보를 다 적을 수도 있다.

    @Enumerated(EnumType.STRING) //String사용
    private OrderStatus status;
    //OrderStatus는 Enum으로 상태를 생성

//    이때 Enum으로 관리하면 좋은점
//스프링 애플리케이션에서 Enum 클래스를 사용해 status(상태)를 설계하는 이유는 여러 가지 이점과 관련이 있습니다. 이를 통해 코드의 가독성, 유지보수성, 안전성을 높일 수 있습니다. 주요 이유들을 다음과 같이 정리할 수 있습니다.
//
//1. 타입 안전성 (Type Safety)
//    Enum을 사용하면 미리 정의된 상수 값들만을 사용할 수 있습니다. 즉, status가 Enum으로 정의되어 있으면, 잘못된 상태 값이 들어가는 것을 컴파일 단계에서 막을 수 있습니다. 이는 문자열이나 숫자 값으로 상태를 표현할 때 발생할 수 있는 실수를 줄여줍니다.
//    예를 들어, 문자열로 상태를 관리할 경우 오타("APROVED" 대신 "APPROVED" 등)나 의미 없는 값이 들어갈 수 있지만, Enum을 사용하면 이러한 실수를 방지할 수 있습니다.
//2. 코드 가독성 향상 (Improved Readability)
//    Enum은 상태나 유형을 명확하고 직관적으로 표현할 수 있습니다. 예를 들어, OrderStatus.APPROVED, OrderStatus.PENDING와 같은 코드는 상태의 의미를 명확히 드러내어 가독성을 높입니다.
//    코드 리뷰나 유지보수 시에 상태의 의미를 쉽게 이해할 수 있어 개발자 간의 의사소통에도 도움이 됩니다.
//3. 비즈니스 로직의 중앙화 (Centralized Business Logic)
//    Enum 클래스 내에 상태와 관련된 메서드를 정의할 수 있습니다. 예를 들어, 특정 상태에 따라 수행할 로직을 Enum 안에 정의하면, 상태와 관련된 로직을 한 곳에서 관리할 수 있습니다.
//    이는 비즈니스 로직이 분산되는 것을 막아 코드 유지보수를 더 쉽게 만듭니다.
//4. 편리한 상태 관리 (Convenient State Management)
//    Enum은 상태 전환을 명확하게 관리할 수 있게 합니다. 예를 들어, Enum 내에서 상태 전환을 정의하거나 특정 상태 간 전환을 제한할 수 있습니다.
//            또한, Enum 자체가 싱글톤 패턴을 사용하므로 메모리 사용 측면에서도 효율적입니다.
//5. DB 매핑에 용이 (Ease of Database Mapping)
//    Enum은 JPA와 같은 ORM에서 쉽게 매핑할 수 있습니다. @Enumerated 어노테이션을 통해 Enum 타입을 데이터베이스의 문자열이나 숫자 타입으로 쉽게 매핑할 수 있습니다.
//    이를 통해 데이터베이스에서도 일관된 상태 값을 유지할 수 있으며, 코드와 데이터베이스 간의 매핑도 명확해집니다.
//6. 상태 그룹화 및 관리
//    상태가 많아질 경우, Enum을 사용하면 상태를 그룹화하고 명확히 관리할 수 있습니다. 예를 들어, 주문 상태(OrderStatus)를 여러 단계로 나누어 관리할 수 있으며, 이를 통해 코드 구조를 더 체계적으로 만들 수 있습니다.
//7. 유지보수성 향상 (Enhanced Maintainability)
//    새로운 상태를 추가하거나 기존 상태를 변경해야 할 때, Enum을 사용하면 중앙에서 관리할 수 있어 유지보수가 용이합니다. 상태 변경 시 코드 전체에서 일관되게 적용할 수 있습니다.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Long getMemberId() {
//        return memberId;
//    }
//
//    public void setMemberId(Long memberId) {
//        this.memberId = memberId;
//    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }


}
