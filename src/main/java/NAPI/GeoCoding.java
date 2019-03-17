package NAPI;

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


    /**
     * This methods converts a number of adresses into coordinates
     * @param adresses
     * @return List of coordinates (as Strings)
     */
    public List<String> convertAddressToCoordinates(List<String> adresses){

        GeocodingApi apiInstance = new GeocodingApi();

        String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54"; // String | Get your key at graphhopper.com

        String locale = "en"; // String | Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn't found the default (en) is used.
        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String point = "51.9692,7.5958"; // String | The location bias in the format 'latitude,longitude' e.g. point=45.93272,11.58803
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata

        List<String> points = new ArrayList();

        try {
            for(int i = 0; i<adresses.size();i++) {
                GeocodingResponse result = apiInstance.geocodeGet(key, adresses.get(i), locale, limit, reverse, point, provider);

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

    public String understandInputAdress(String inputAdress)
    {
        GeocodingApi apiInstance = new GeocodingApi();

        String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54"; // String | Get your key at graphhopper.com

        String locale = "en"; // String | Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn't found the default (en) is used.
        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String point = "51.9692,7.5958"; // String | The location bias in the format 'latitude,longitude' e.g. point=45.93272,11.58803
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata


        String understoodAddress = "";
        try {
            GeocodingResponse result = apiInstance.geocodeGet(key, inputAdress, locale, limit, reverse, point, provider);
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

            //understoodAddress = result.getHits().get(0).toString();
                    /*
                    result.getHits().get(0).getCountry().toString()

                    + ", " + result.getHits().get(0).getPostcode().toString()
                    +  result.getHits().get(0).getStreet().toString();
                    */


        } catch (ApiException e) {
            System.err.println("Exception when calling GeocodingApi#geocodeGet");
            e.printStackTrace();
        }

        return understoodAddress;
    }



}