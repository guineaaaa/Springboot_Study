package com.apple.shopExample.notice;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.Date;

@ToString
@Entity
public class notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;
    public Date date;

    public String toString(){
        return this.title+this.date;
    }

}
