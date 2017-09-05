package com.example.anamariapaula.myastroweather;

public class Location {
    private int id;
    private String Country;
    private String Admin1;
    private String Admin2;
    private String Admin3;
    private String Locality1;
    private String Locality2;
    private int WOEID;

    public Location() {
    }

    public Location(int noParameterRequired){
        Country = null;
        Admin1 = null;
        Admin2 = null;
        Admin3 = null;
        Locality1 = null;
        Locality2 = null;
    }

    public Location(int id, String country, String admin1, String admin2, String admin3, String locality1, String locality2, int WOEID) {
        this.id = id;
        Country = country;
        Admin1 = admin1;
        Admin2 = admin2;
        Admin3 = admin3;
        Locality1 = locality1;
        Locality2 = locality2;
        this.WOEID = WOEID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAdmin1() {
        return Admin1;
    }

    public void setAdmin1(String admin1) {
        Admin1 = admin1;
    }

    public String getAdmin2() {
        return Admin2;
    }

    public void setAdmin2(String admin2) {
        Admin2 = admin2;
    }

    public String getAdmin3() {
        return Admin3;
    }

    public void setAdmin3(String admin3) {
        Admin3 = admin3;
    }

    public String getLocality1() {
        return Locality1;
    }

    public void setLocality1(String locality1) {
        Locality1 = locality1;
    }

    public String getLocality2() {
        return Locality2;
    }

    public void setLocality2(String locality2) {
        Locality2 = locality2;
    }

    public int getWOEID() {
        return WOEID;
    }

    public void setWOEID(int WOEID) {
        this.WOEID = WOEID;
    }

}
