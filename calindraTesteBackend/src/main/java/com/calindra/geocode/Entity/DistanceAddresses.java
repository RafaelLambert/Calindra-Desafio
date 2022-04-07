package com.calindra.geocode.Entity;

public class DistanceAddresses {
    private String addressOne;
    private String addressTwo;
    private Double distance;

    public DistanceAddresses(String addressOne, String addressTwo, Double distance) {
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.distance = distance;
    }

    public DistanceAddresses() {
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DistanceAddresses{" +
                "addressOne='" + addressOne + '\'' +
                ", addressTwo='" + addressTwo + '\'' +
                ", distance=" + distance +
                '}';
    }

}
