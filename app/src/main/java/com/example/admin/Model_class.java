package com.example.admin;

import java.security.acl.Owner;

public class Model_class {
    private String Owner_name;
    private String P_name;
    private String Longi;
    private String Lati;
    private String Address;
    private String P_space;
    private String Id;
    private String B_fee;
    private String C_fee;

    public String getB_fee() {
        return B_fee;
    }

    public void setB_fee(String b_fee) {
        B_fee = b_fee;
    }

    public String getC_fee() {
        return C_fee;
    }

    public void setC_fee(String c_fee) {
        C_fee = c_fee;
    }

    public String getId() {
        return Id;
    }

    public Model_class() {
    }

    public void setId(String id) {
        Id = id;
    }

    public Model_class(String p_space,String b_fee, String c_fee) {
        P_space = p_space;
        B_fee= b_fee;
        C_fee= c_fee;
    }

    public String getP_space() {
        return P_space;
    }

    public void setP_space(String p_space) {
        P_space = p_space;
    }



    public Model_class(String owner_name, String p_name, String longi, String lati, String address, String id) {
        Owner_name = owner_name;
        P_name = p_name;
        Longi = longi;
        Lati = lati;
        Address = address;
        Id=id;
    }

    public String getOwner_name() {
        return Owner_name;
    }

    public void setOwner_name(String owner_name) {
        Owner_name = owner_name;
    }

    public String getP_name() {
        return P_name;
    }

    public void setP_name(String p_name) {
        P_name = p_name;
    }

    public String getLongi() {
        return Longi;
    }

    public void setLongi(String longi) {
        Longi = longi;
    }

    public String getLati() {
        return Lati;
    }

    public void setLati(String lati) {
        Lati = lati;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}

