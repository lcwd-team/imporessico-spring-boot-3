package com.lcwd.store.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "jpa_products")
public class Product {

    @Id
    private String productId;

    private String title;

    @Column(length = 100000)
    private String description;

    private int price;

    private boolean stock;

    private boolean live;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

}
