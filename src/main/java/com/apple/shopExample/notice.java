package com.apple.shopExample;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;
    public Date date;
}
