package com.fernandoboza.kura.hospapi.Utils;

import com.fernandoboza.kura.hospapi.Hospital.Hospital;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

import java.io.IOException;

public class Utils {

    public static Hospital createLatCord(Hospital hosp) throws InterruptedException, ApiException, IOException {
        GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyB7XZM9ZU0jM3SAnFxfLes_8OXOQ0ugI9I").build();
        GeocodingResult[] results = GeocodingApi.geocode(context, hosp.getAddress() + hosp.getCity() + hosp.getState() + hosp.getZipcode()).await();
        hosp.setLat(results[0].geometry.location.lat);
        hosp.setLng(results[0].geometry.location.lng);
        return hosp;
    }
}
