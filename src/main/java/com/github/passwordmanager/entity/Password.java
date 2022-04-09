package com.github.passwordmanager.entity;

import javax.persistence.*;

@Entity
@Table(name = "Passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Category")
    private String category;

    @Column(name = "Pass")
    private String pass;

    @Column(name = "Login")
    private String login;

    public Password() {
    }

    public Password(String name, String category, String pass, String login) {
        this.name = name;
        this.category = category;
        this.pass = pass;
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPass() {
        return pass;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}