package com.example.mostafaproject;

public class adminObject {
    private String location1;
    private String location2;
    private String city;
    private String k;
    private String Usrid;


    public String getK() {
        return k;
    }

    public void setTask(String k) {
        this.k = k;
    }

    public String getUsrid() {
        return Usrid;
    }

    public void setUsrid(String usrid) {
        Usrid = usrid;
    }

    public adminObject(String location1, String location2, String city, String k, String Usrid) {
        this.location1 = location1;
        this.location2 = location2;
        this.city = city;
        this.k=k;
        this.Usrid=Usrid;

    }

    public adminObject()
    {

    }
    public String getLocation1() {
        return location1;
    }

    public void setLocation1(String location1) {
        this.location1 = location1;
    }

    public String getLocation2() {
        return location2;
    }

    public void setLocation2(String location2) {
        this.location2= location2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city= city;
    }


}
