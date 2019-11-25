package com.addressparser.address;

import com.google.gson.annotations.SerializedName;

public class Address {
    private String streetName;
    private String housenumber;

    @SerializedName("StreetName")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @SerializedName("HouseNumber")
    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }
}
