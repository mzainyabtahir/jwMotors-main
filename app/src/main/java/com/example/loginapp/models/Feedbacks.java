package com.example.loginapp.models;
import java.io.Serializable;

public class Feedbacks implements Serializable{

    private String name,email,feedback;

    public String getName(){return name;}
    public void setName(String name){ this.name = name;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}

    public String getFeedback(){return feedback;}
    public void setFeedback(String feedback) {this.feedback = feedback;}

    public Feedbacks(){}
    public Feedbacks(String name, String email, String feedback)
    {
        this.name=name;
        this.email=email;
        this.feedback=feedback;
    }

}
