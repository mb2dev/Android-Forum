package com.projet.esgi.android_forum.model;

import com.projet.esgi.android_forum.service.retrofit.IRFGeneric;
import com.projet.esgi.android_forum.service.rfabstract.Exclude;
import com.projet.esgi.android_forum.service.rfabstract.PersistedModel;

import java.util.UUID;

import io.realm.RealmModel;
import io.realm.RealmObject;

/**
 * Created by Mickael on 28/06/2017.
 */

public class User extends PersistedModel implements RealmModel {

    private String email;
    private String firstname;
    private String lastname;
    private String password;

    public User(){

    }

    public User(String email, String firstname, String lastname, String password) {
        this(UUID.randomUUID().toString(), email, firstname, lastname, password);
    }

    public User(String id, String email, String firstname, String lastname, String password){
        super(id);
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User forLogin(){
        User u = new User();
        u.firstname = null;
        u.lastname = null;
        u.id = null;
        u.password = password;
        u.email = email;
        return u;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public String getModelNameForUrlPath() {
        return "users";
    }
}
