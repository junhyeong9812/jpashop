package jpabook.jpashop;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

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
            Order order = entityManager.find(Order.class,1L);

            //절절하지 않은 방식
//            Long memberId = order.getMemberId();
//            entityManager.find(Member.class,memberId);

            //적절한 방식
            Member member = order.getMember();
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            entityManager.close();
        }
        entityManagerFactory.close();


    }
}

