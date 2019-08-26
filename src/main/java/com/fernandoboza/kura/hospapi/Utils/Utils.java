package com.fernandoboza.kura.hospapi.Utils;

import com.fernandoboza.kura.hospapi.Hospital.Hospital;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.data.geo.Point;

import java.io.IOException;
import java.util.Optional;

public class Utils {

    public static Hospital createLatCord(Hospital hosp) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyB7XZM9ZU0jM3SAnFxfLes_8OXOQ0ugI9I").build();
        GeocodingResult[] results = GeocodingApi.geocode(context, hosp.getAddress() + hosp.getCity() + hosp.getState() + hosp.getZipcode()).await();
        hosp.setLat(results[0].geometry.location.lat);
        hosp.setLng(results[0].geometry.location.lng);
        return hosp;
    }

    public static double[] ZipodePoint(String zipcode) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyB7XZM9ZU0jM3SAnFxfLes_8OXOQ0ugI9I").build();
        GeocodingResult[] results = GeocodingApi.geocode(context, zipcode).await();
        return new double[]{results[0].geometry.location.lat, results[0].geometry.location.lng};
    }

    public static <T> T getFromOptional(Optional<T> opt) {
        return (T) opt.orElse(null);
    }

    public static double haversine(double s_lat, double s_lng, double e_lat, double e_lng) {
        // Earth radius 3959 miles
        final double R = 3959; // For Kilometers use 3959
        double dLat = Math.toRadians(e_lat - s_lat);
        double dLon = Math.toRadians(e_lng - s_lng);
        s_lat = Math.toRadians(s_lat);
        e_lat = Math.toRadians(e_lat);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(s_lat) * Math.cos(e_lat);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

//    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
//        final double R = 3959; // Miles
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//        lat1 = Math.toRadians(lat1);
//        lat2 = Math.toRadians(lat2);
//
//        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
//        double c = 2 * Math.asin(Math.sqrt(a));
//        return R * c;
//    }

}
