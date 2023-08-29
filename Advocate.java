package com.advocate.model;
public class Advocate {
    private int advocateId;
    private String name;
    private String specialization;
    public Advocate(int advocateId, String name, String specialization) {
        this.advocateId = advocateId;
        this.name = name;
        this.specialization = specialization;
    }
    public Advocate(int advocateId) {
        this.advocateId = advocateId;
    }
    public int getAdvocateId() {
        return advocateId;
    }
    public void setAdvocateId(int advocateId) {
        this.advocateId = advocateId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    @Override
    public String toString() {
        return "Advocate [advocateId=" + advocateId + ", name=" + name + ", specialization=" + specialization + "]";
    }
}
