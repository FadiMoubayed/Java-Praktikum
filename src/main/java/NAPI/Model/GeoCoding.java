package NAPI.Model;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.model.GeocodingLocation;
import com.graphhopper.directions.api.client.model.GeocodingResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * This class interacts with the GeoCodingApi from the GraphhopperApi
 */
public class GeoCoding {

    private GeocodingApi geocode;
    private GeocodingResponse result;
    private String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54";
    private String language;

    /**
     *
     */
    public GeoCoding()
    {
        geocode = new GeocodingApi();
        language = "en";
    }
    /**
     * This methods converts a number of adresses into coordinates
     * @param addresses
     * @return List of coordinates (as Strings)
     */
    public List<String> convertAddressToCoordinates(List<String> addresses){

        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata

        List<String> points = new ArrayList();

        try {
            for(int i = 0; i<addresses.size();i++) {
                GeocodingResponse result = geocode.geocodeGet(key, addresses.get(i), language, limit, reverse, "", provider);

                //Getting latitude and longitude of the starting point
                double lat = result.getHits().get(0).getPoint().getLat();
                double lng = result.getHits().get(0).getPoint().getLng();

                String onePoint = Double.toString(lat) + "," + Double.toString(lng);

                points.add(onePoint);
            }


        } catch (ApiException e) {
            System.err.println("Exception when calling GeocodingApi#geocodeGet");
            e.printStackTrace();
        }

        return points;
    }

    public String understandInputAddress(String inputAdress)
    {
        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata


        String understoodAddress = "";
        try {
            GeocodingResponse result = geocode.geocodeGet(key, inputAdress, language, limit, reverse, "", provider);
            GeocodingLocation output = result.getHits().get(0);

            if(output.getCountry() != null) {
                understoodAddress = output.getCountry().toString();
            }
            if(output.getCity() != null)
            {
                understoodAddress = understoodAddress + ", " + output.getCountry().toString();
            }
            if(output.getPostcode() != null)
            {
                understoodAddress = understoodAddress + " " + output.getPostcode().toString();
            }
            if(output.getStreet() != null)
            {
                understoodAddress = understoodAddress + ", " + output.getStreet().toString();
            }
            if(output.getHousenumber() != null)
            {
                understoodAddress = understoodAddress + " " + output.getHousenumber().toString();
            }
        } catch (ApiException e) {
            System.err.println("Exception when calling GeocodingApi#geocodeGet");
            e.printStackTrace();
        }

        return understoodAddress;
    }



}