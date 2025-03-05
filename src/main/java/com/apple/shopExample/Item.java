package com.apple.shopExample;

import jakarta.persistence.*;

@Entity
public class Item {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) //Autoincrement
    public Long id;

    // Column 어노테이션 - 컬럼 마다 제약 설정
    // @Column(nullable=false, unique=true)
    // @Column(columnDefinition="TEXT")
    @Column(length=10000)
    public String title;
    public Integer price;
}

