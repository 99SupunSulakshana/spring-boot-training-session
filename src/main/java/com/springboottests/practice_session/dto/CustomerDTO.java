package com.springboottests.practice_session.dto;

import jakarta.persistence.Column;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {
    private String customerName;
    private String customerAddress;
    private double customerSalary;
    private ArrayList<String> contactNumber;
    private String nic;
    private boolean active;

    public CustomerDTO() {
    }

    public CustomerDTO(String customerName, String customerAddress, double customerSalary, ArrayList<String> contactNumber, String nic, boolean active) {
        this.nic = nic;
        this.contactNumber = contactNumber;
        this.customerSalary = customerSalary;
        this.customerAddress = customerAddress;
        this.customerName = customerName;
        this.active = active;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    public ArrayList<String> getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(ArrayList<String> contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerSalary=" + customerSalary +
                ", contactNumber=" + contactNumber +
                ", nic='" + nic + '\'' +
                ", active=" + active +
                '}';
    }
}
