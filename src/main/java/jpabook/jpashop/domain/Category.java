package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category extends BaseEntity{
    @Id @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Category parent;
    //상위 카테고리 매핑 셀프 매핑도 가능
    //
    @OneToMany(mappedBy = "parent")
    private List<Category> child=new ArrayList<>();
    //이렇게 하면 카테고리가 쭉 내려가는데 이걸 셀프로 연관관계로 묶는 것
    //

    @ManyToMany
    @JoinTable(
            name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID") )
    private List<Item> items = new ArrayList<>();
    //중간 테이블의 이름과 각각의 조인 컬럼을 지정한다.

//    @JoinColumn 속성
//    1.name : 매핑할 외래키 이름
//    2.referencedColumnName : 외래키가 참조하는 대상 테이블의 컬럼명
//    3.foreignKey(DDL) : 외래키 제약조건을 직접 지정할 수 있다.
//                        이 속성은 테이블을 생성할 때만 사용 가능
//    4.unique/nullable insertable 등등은 기존으 Column어노테이션과 같다.

//    @ManyToOne 속성
//    1.optional : false로 설정하면 연관된엔티티가 항상 있어야 된다.(기본값:true)
//    2.fetch : 글로벌 패치 전략을 설정한다. >>즉시로딩 및 지연로딩
//    3.cascade : 영속성 전이 기능을 사용한다. >>연관된 것들도 설정
//    4.targetEntitiy : 연관된 엔티티의 타입 정보를 설정한다. 이 기능은 거의
//                      사용되지 않는다. 컬랙션을 사용해도 제네릭으로 타입 정보를
//                      알 수 있다.

//    @OneToMany 속성
//    1. mappedby 연관관계의 주인 필드를 선택한다.
//    2. fetch :글로벌 패치 전략을 설정한다.
//    3. cascade : 영속성 전이 기능을 사용한다.
//    4.targetEntity : 연관된 엔티티의 타입 정보를 설정
    //다대일은 맵드바이가 없다. 
    //스팩상 없는게 맞다 그냥 다대일이 연관관계의 주인으로 설정해라


}
