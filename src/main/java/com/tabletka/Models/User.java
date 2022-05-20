package com.tabletka.Models;

import com.tabletka.Models.DrugInCart;
import com.tabletka.repo.DrugInCartRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;



@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, phone;

    public String myKit;

    public User() {

    }

    public User(String name, String phone, String myKit) {
        this.name = name;
        this.phone = phone;
        this.myKit = myKit;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMyKit() {
        return myKit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMyKit(String myKit) {
        this.myKit = myKit;
    }
}
