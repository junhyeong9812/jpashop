package jpabook.jpashop.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Delivery {
    @Id @GeneratedValue
    private long id;
    
    //배송지 주소
    private String city;
    private String street;
    private String zipcode;

    private DEliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
