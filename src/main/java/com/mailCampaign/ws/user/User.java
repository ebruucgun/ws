package com.mailCampaign.ws.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    private  Long id;

    private String userName;
    private String surname;
    private String password;


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
