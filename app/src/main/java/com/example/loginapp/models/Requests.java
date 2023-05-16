package com.example.loginapp.models;

public class Requests {

    public Requests(){}
    private int id;
    private String vehicleName;
    private String registrationNumber;
    private String name;
    private String phone;
    private String requiredWork;
    private String email;
    private String lat;
    private String lon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName(){return name;}
    public void setName(String name){ this.name = name;}


    public String getPhone(){return phone;}
    public void setPhone(String phone){ this.phone = phone;}

    public String getEmail(){return email;}
    public void setEmail(String email) {this.email = email;}

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleNumber(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRequiredWork(){return requiredWork;}
    public void setRequiredWork(String requiredWork){ this.requiredWork = requiredWork;}


    public String getLat(){return lat;}
    public void setLat(String lat){this.lat=lat;}

    public String getLon(){return lon;}
    public void setLon(String lon){this.lon=lon;}
    public Requests(int id, String name, String phone, String vehicleName, String registrationNumber, String requiredWork, String email, String lat, String lon) {
        this.id = id;
        this.name=name;
        this.phone=phone;
        this.vehicleName = vehicleName;
        this.registrationNumber = registrationNumber;
        this.requiredWork=requiredWork;
        this.email=email;
        this.lat=lat;
        this.lon=lon;
    }
}
