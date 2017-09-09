package com.tobefranco.contactlist;

import java.io.Serializable;

/**
 * Created by Tobe on 08/09/2017.
 */

public class Contact implements Serializable{

    private String user;
    private String email;
    private String twitter;
    private String phone;
    private String birhtDate;

    public Contact(String username, String userEmail, String userTwitter, String userPhone,
                   String userBirthDate){
        user = username;
        email = userEmail;
        twitter = userTwitter;
        phone = userPhone;
        birhtDate = userBirthDate;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirhtDate() {
        return birhtDate;
    }

    public void setBirhtDate(String userBirthDate) {birhtDate = userBirthDate;}
}
