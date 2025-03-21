package com.apple.shopExample.item;

import com.apple.shopExample.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString //toString을 알아서 lombok이 만들어줌
@Entity
@Getter
@Setter
public class Item {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) //Autoincrement
    private Long id;

    @Column(length=10000)
    private String title;
    private String price;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id") // 외래 키로 member_id를 사용
    private Member member;

    public Item(){}

    public Item(String title, String price){
        this.title=title;
        this.price=price;
    }

    public Long getId(){return id;}
    public String getTitle(){return title;}
    public String getPrice(){return price;}

    // object의 변수들을 한번에 출력
    public String toString(){
        return this.title+this.price;
    }

}

