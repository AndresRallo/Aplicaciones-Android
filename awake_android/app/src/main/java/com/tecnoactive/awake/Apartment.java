package com.tecnoactive.awake;


public class Apartment {
    private String number;
    private String owner;
    private String owner_phone;
    private String coowner;
    private String coowner_phone;
    private String parking;

    public Apartment(String number, String owner, String owner_phone, String coowner, String coowner_phone, String parking){
        this.number = number;
        this.owner = owner;
        this.owner_phone = owner_phone;
        this.coowner = coowner;
        this.coowner_phone = coowner_phone;
        this.parking = parking;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getCoowner() {
        return coowner;
    }

    public void setCoowner(String coowner) {
        this.coowner = coowner;
    }

    public String getCoowner_phone() {
        return coowner_phone;
    }

    public void setCoowner_phone(String coowner_phone) {
        this.coowner_phone = coowner_phone;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }
}
