package com.calindra.geocode.Entity;
import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private int streetNumber;
    private String route;
    private String locality;
    private String administrativeAreaLevel2;
    private String administrativeAreaLevel1;
    private String country;
    private int postalCode;

    public Address(int streetNumber, String route, String locality, String administrativeAreaLevel2, String administrativeAreaLevel1, String country, int postal_code) {
        this.streetNumber = streetNumber;
        this.route = route;
        this.locality = locality;
        this.administrativeAreaLevel2 = administrativeAreaLevel2;
        this.administrativeAreaLevel1 = administrativeAreaLevel1;
        this.country = country;
        this.postalCode = postal_code;
    }

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAdministrativeAreaLevel2() {
        return administrativeAreaLevel2;
    }

    public void setAdministrativeAreaLevel2(String administrativeAreaLevel2) {
        this.administrativeAreaLevel2 = administrativeAreaLevel2;
    }

    public String getAdministrativeAreaLevel1() {
        return administrativeAreaLevel1;
    }

    public void setAdministrativeAreaLevel1(String administrativeAreaLevel1) {
        this.administrativeAreaLevel1 = administrativeAreaLevel1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postal_code) {
        this.postalCode = postal_code;
    }
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", streetNumber=" + streetNumber +
                ", route='" + route + '\'' +
                ", locality='" + locality + '\'' +
                ", administrativeAreaLevel2='" + administrativeAreaLevel2 + '\'' +
                ", administrativeAreaLevel1='" + administrativeAreaLevel1 + '\'' +
                ", country='" + country + '\'' +
                ", postal_code=" + postalCode +
                '}';
    }
}
