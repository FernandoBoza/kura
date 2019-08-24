package com.fernandoboza.kura.hospapi.Hospital;

import com.fernandoboza.kura.hospapi.Procedures.Procedures;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    private String zipcode;
    private String city;
    private String state;
    private double lat;
    private double lng;
    @OneToMany(mappedBy="hospital")
    private List<Procedures> procedures = new ArrayList<>();

    public Hospital() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<Procedures> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedures> procedures) {
        this.procedures.addAll(procedures);
    }
}
