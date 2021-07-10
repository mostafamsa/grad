package com.example.mostafaproject;

public class ok {

    private String location1,location2,location,k;

    private String Usrid;

    public ok(String location1 , String location2, String location, String Usrid, String k) {

        this.location1 = location1;
        this.location2 = location2;
        this.location = location;
        this.Usrid = Usrid;
        this.k = k;

    }

    public ok() {
    }

    public void setUsrid(String Usrid) {
        this.Usrid = Usrid;
    }

    public String getUsrid() {
        return Usrid;
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
        this.location2 = location2;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k =k;
    }
}