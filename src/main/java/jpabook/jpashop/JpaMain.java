package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jakarta.persistence.*;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import java.util.List;

public class JpaMain {
    //psvm을 통해 바로 생성 가능
    public static void main(String[] args) {
        //동작 확인
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("hello");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();
        try {
//            Order order = entityManager.find(Order.class,1L);

            //절절하지 않은 방식
//            Long memberId = order.getMemberId();
//            entityManager.find(Member.class,memberId);

            //적절한 방식
//            Member member = order.getMember();

            //연관 관계 매핑 확인
//            Order order = new Order();
//            order.addOrderItem(new OrderItem());
            //편의 메소드를 통해 오더 객체에 오더아이템을 넣을 수 있다.

            //리스트를 안쓴다면? 양방향 설정을 안한다면?
            Order order = new Order();
            entityManager.persist(order);
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            entityManager.persist(orderItem);

            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();


    }
}

