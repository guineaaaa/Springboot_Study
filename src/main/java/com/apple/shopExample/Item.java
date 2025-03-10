package com.apple.shopExample;

import jakarta.persistence.*;
import lombok.ToString;

@ToString //toString을 알아서 lombok이 만들어줌
@Entity
public class Item {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) //Autoincrement
    private Long id;
    @Column(length=10000)
    private String title;
    private String price;

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

