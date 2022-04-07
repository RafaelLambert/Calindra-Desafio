package com.calindra.geocode.Service;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.calindra.geocode.Entity.Geocoding;

@FeignClient(url= "https://maps.googleapis.com/maps/api/geocode/json" , name = "geocoding")
public interface GeocodingService {

    @GetMapping("?address={address}&key={API_KEY}")
    public Geocoding findAddress(@PathVariable("address") String address, @PathVariable("API_KEY") String API_KEY);

}