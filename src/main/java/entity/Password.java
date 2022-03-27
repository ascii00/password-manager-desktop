package entity;

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
    private String Login;

    public Password() {
    }

    public Password(String name, String category, String pass) {
        this.name = name;
        this.category = category;
        this.pass = pass;
    }

    public Long getId() {
        return id;
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
        return Login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setLogin(String login) {
        Login = login;
    }
}
