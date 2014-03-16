package com.example.share2save.model;
import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{

    private Long id;

    private String password;
    private String email;
    private Date lastLogin;
    private String token;
    private int authLevel;

    public User(){
        // BEWARE !!
        // the no arg constructor is for Hibernate / JPA mapping ONLY
        // there may be places in the code where we assume the user has an email/password
        // it seems overly verbose to create all sorts of mapping classes just to check this
        // assumption
    }

    public User(String email, String password){
        // please use this constructor
        this.password = password;
        this.email = email;
    }



    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAuthLevel() {
        return authLevel;
    }

    public void setAuthLevel(int authLevel) {
        this.authLevel = authLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
