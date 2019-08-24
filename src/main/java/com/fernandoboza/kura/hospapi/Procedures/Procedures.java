package com.fernandoboza.kura.hospapi.Procedures;

import com.fernandoboza.kura.hospapi.Hospital.Hospital;

import javax.persistence.*;


@Entity
public class Procedures {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
//    @ManyToOne(targetEntity = Hospital.class)
    private int hospital;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getHospital() {
        return hospital;
    }

    public void setHospital(int hospital) {
        this.hospital = hospital;
    }
}
