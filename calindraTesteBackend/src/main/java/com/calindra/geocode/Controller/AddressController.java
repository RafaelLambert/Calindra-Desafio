package com.calindra.geocode.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.calindra.geocode.Entity.*;
import com.calindra.geocode.Service.GeocodingService;

import java.util.*;

@RestController
@RequestMapping("/api") /*https://localhost:8085/api*/
public class AddressController {
    @Autowired
    private GeocodingService geocodingService;
    private final String API_KEY = "AIzaSyDKXcfbW5pOznoci-Zvn5YANk1mIHefen0";

    @PostMapping("/address/calculate-distance/")
    public ResponseEntity<?> calculateDistance(@RequestBody List<Address> addresses) {
        List<Location> locationsResults = new ArrayList<>();
        List<DistanceAddresses> distances;
        Location location;
        String formattedAddress;
        List<String> listFormattedAddress = new ArrayList<>();

        Geocoding geocodingResult;

        for (Address address : addresses) {
            System.out.println(address);
            /* R. Beta, 24 - Vila S.Miguel, Ocara - CE, 62755-000, Brasil*/
            String addressUrl = address.getRoute()+", "
                               +address.getStreetNumber()
                               +" - "+address.getLocality()+", "
                               +address.getAdministrativeAreaLevel1()
                               +" - "+address.getAdministrativeAreaLevel2()
                               +", "+address.getPostalCode()
                               +", "+address.getCountry();

            geocodingResult = this.geocodingService.findAddress(addressUrl, API_KEY);

            System.out.println(geocodingResult.getResults());
            location = geocodingResult.getResults().get(0).getGeometry().getLocation();
            formattedAddress = geocodingResult.getResults().get(0).getFormattedAddress();

            listFormattedAddress.add(formattedAddress);
            locationsResults.add(location);
        }

        distances = euclidianDistance(locationsResults, listFormattedAddress);

        return new ResponseEntity<>(distances, HttpStatus.OK);
    }

    public List<DistanceAddresses> euclidianDistance(List<Location> locations, List<String> listFormattedAddress ) {
        int index = 0;
        int locationSize = locations.size() - 1;
        Double distanceBetweenTwoPointsResult = 0.0d;

        List<DistanceAddresses> listDistanceAddresses = new ArrayList<>();

        while(index < locationSize){

            for (int i = index; i <= locationSize; i++) {
                if (i < locationSize) {
                    DistanceAddresses distanceAddresses = new DistanceAddresses();
                    distanceBetweenTwoPointsResult = distanceBetweenTwoPoints(locations.get(index), locations.get(i+1));

                    distanceAddresses.setDistance(distanceBetweenTwoPointsResult);
                    distanceAddresses.setAddressOne(listFormattedAddress.get(index));
                    distanceAddresses.setAddressTwo(listFormattedAddress.get(i+1));

                    listDistanceAddresses.add(distanceAddresses);
                }
            }

            index++;
        }

        return selectionSort(listDistanceAddresses);
    }

    public double distanceBetweenTwoPoints(Location locationOne, Location locationTwo){
        /*raizQuadrada( (long1-long2)^2 + (lat1 - lat2)^2)  */
    	/*119.19 km corresponde a 1 grau de curvatura da terra*/
        return (Math.sqrt( Math.pow((locationOne.getLng() - locationTwo.getLng()), 2) +  Math.pow((locationOne.getLat() - locationTwo.getLat()), 2)))*119.19;
    }

    public List<DistanceAddresses> selectionSort(List<DistanceAddresses> array) {
        for (int fixed = 0; fixed < array.size() - 1; fixed++) {
            int min = fixed;

            for (int i = min + 1; i < array.size(); i++) {
                if (array.get(i).getDistance() < array.get(min).getDistance()) {
                    min = i;
                }
            }

            if (min != fixed) {
                DistanceAddresses distanceAddressesAux = array.get(fixed);
                array.set(fixed, array.get(min));
                array.set(min, distanceAddressesAux);
            }
        }

        return array;
    }

}

