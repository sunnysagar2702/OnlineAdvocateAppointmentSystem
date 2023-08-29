package com.advocate.model;
import java.util.Date;
public class Appointment {
    private int id;
    private int appointmentId;
    private Date appointmentDate;
    private Customer customer;
    private Advocate advocate;
    private String status;
    public Appointment(int appointmentId, Date appointmentDate, Customer customer, Advocate advocate) {
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.customer = customer;
        this.advocate = advocate;
        this.status = "Scheduled";
    }
    public Appointment() {
        this.id = 0; 
        this.appointmentDate = new Date();
        this.customer = null; 
        this.advocate = null; 
        this.status = ""; 
    }
    public int getAppointmentId() {
        return appointmentId;
    }
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    public Date getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Advocate getAdvocate() {
        return advocate;
    }
    public void setAdvocate(Advocate advocate) {
        this.advocate = advocate;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    @Override
    public String toString() {
        return "Appointment [appointmentId=" + appointmentId + ", appointmentDate=" + appointmentDate +
                ", customer=" + customer + ", advocate=" + advocate + ", status=" + status + "]";
    }
}
