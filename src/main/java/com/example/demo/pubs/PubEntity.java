package com.example.demo.pubs;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.demo.purchase.PurchaseEntity;

@Entity
public class PubEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String location;
    private String phone;

    @OneToMany(mappedBy = "pub", 
    cascade = CascadeType.ALL, 
    fetch = javax.persistence.FetchType.LAZY,
    orphanRemoval = true)
    private List<PurchaseEntity> purchases;

    public PubEntity(){
        this.purchases = new java.util.ArrayList<PurchaseEntity>();
    }

    public PubEntity(String name, String location, String phone){
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.purchases = new java.util.ArrayList<PurchaseEntity>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

}
