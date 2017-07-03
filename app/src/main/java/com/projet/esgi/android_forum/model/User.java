package com.projet.esgi.android_forum.model;

import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class User extends RealmObject {

    private int id;
    private String email;
    private String firstname;
    private String lastname;

    public User(){

    }
    
    public User(int id,String email, String firstname, String lastname){
        this.id = id;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
