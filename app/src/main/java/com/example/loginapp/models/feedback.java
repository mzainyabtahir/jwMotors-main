package com.example.loginapp.models;
import java.io.Serializable;

public class feedback implements Serializable{

    private String name,email,feedback;

    public String getName(){return name;}
    public void setName(String name){ this.name = name;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}

    public String getFeedback(){return feedback;}
    public void setFeedback(String feedback) {this.feedback = feedback;}

    public feedback(){}
    public feedback(String name, String email, String feedback)
    {
        this.name=name;
        this.email=email;
        this.feedback=feedback;
    }

}
