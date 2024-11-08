package com.vn.sbit.SpringMVC.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id") //category_id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name",unique = true,columnDefinition = "VARCHAR(255) COLLATE utf8mb4_unicode_ci")
    private String categoryName;

    @Column(name = "category_status")
    private Boolean categoryStatus;

    @OneToMany(mappedBy = "category",
            cascade ={
            CascadeType.REFRESH,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST} )
    private Set<Product> products;

    public Category() {
    }

    public Category(Long id, String categoryName, Boolean categoryStatus) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryStatus = categoryStatus;
    }

    //?????? Bug to String Category - vì products -> lỗi khi OneToMany ToString
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryStatus=" + categoryStatus +
                '}';
    }



}
