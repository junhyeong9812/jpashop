package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(indexes = @Index())
//인덱스도 다 넣는 편 객체보고 jpql을 작성할 때 사용할 index를 바로 사용할 수도 있다.
//가급적 매핑을 다 적는 게 좋다.
public class Member {
    @Id @GeneratedValue
    //(strategy = GenerationType.AUTO) 생략 가능
    @Column(name="MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;

    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();

    //setter는 생성자를 이용하는게 좋음
    //setter는 유지보수가 안좋다.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
