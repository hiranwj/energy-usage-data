package io.xdoto.energyusagedata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "energy_data_usage")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "device_1")
    private double device_01;
    @Column(name = "device_2")
    private double device_02;
    @Column(name = "device_3")
    private double device_03;
    @Column(name = "device_4")
    private double device_04;

    //Default constructor
    public Device() {
        super();
    }

    //Parameterized Constructor
    public Device(int id, double device_01, double device_02, double device_03, double device_04) {
        super();
        this.id = id;
        this.device_01 = device_01;
        this.device_02 = device_02;
        this.device_03 = device_03;
        this.device_04 = device_04;
    }

    //Generate Getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getDevice_01() {
        return device_01;
    }
    public void setDevice_01(double device_01) {
        this.device_01 = device_01;
    }
    public double getDevice_02() {
        return device_02;
    }
    public void setDevice_02(double device_02) {
        this.device_02 = device_02;
    }
    public double getDevice_03() {
        return device_03;
    }
    public void setDevice_03(double device_03) {
        this.device_03 = device_03;
    }
    public double getDevice_04() {
        return device_04;
    }
    public void setDevice_04(double device_04) {
        this.device_04 = device_04;
    }
}
