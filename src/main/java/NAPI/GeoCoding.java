package NAPI;

import com.graphhopper.directions.api.client.*;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.api.RoutingApi;
import com.graphhopper.directions.api.client.auth.*;
import com.graphhopper.directions.api.client.model.*;

public class GeoCoding {


    public static void main(String[] args) {

        //The Geocoding API
        GeocodingApi apiInstance = new GeocodingApi();
        String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54"; // String | Get your key at graphhopper.com
        //String q = "Muenster, Stadtlohnweg 33"; // String | If you do forward geocoding, then this would be a textual description of the address you are looking for
        String q = "Institute for Geoinformatics Muenster";
        String q2 = "Muesnter houptbahnhof";
        String locale = "en"; // String | Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn't found the default (en) is used.
        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String point = "51.9692,7.5958"; // String | The location bias in the format 'latitude,longitude' e.g. point=45.93272,11.58803
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata


        //The Routing API
        RoutingApi routing = new RoutingApi();


        Boolean optimize = false;
        Boolean instructions = false;
        String vehicle = "foot";
        Boolean elevation = false;
        Boolean points_encoded = true;
        Boolean calc_points = true;
        Boolean debug = false;
        String type = "json";



        try {
            GeocodingResponse resultStartingPoint = apiInstance.geocodeGet(key, q, locale, limit, reverse, point, provider);
            GeocodingResponse resultDestination = apiInstance.geocodeGet(key, q2, locale, limit, reverse, point, provider);

            //Getting latitude and longitude of the starting point
            double latStart = resultStartingPoint.getHits().get(0).getPoint().getLat();
            double lngStart = resultStartingPoint.getHits().get(0).getPoint().getLng();

            //Getting latitude and longitude of the destination point
            double latDest = resultDestination.getHits().get(0).getPoint().getLat();
            double lngDest = resultDestination.getHits().get(0).getPoint().getLng();

            String start = Double.toString(latStart) + "," + Double.toString(lngStart);
            String destination = Double.toString(latDest) + "," + Double.toString(lngDest);
            System.out.println(start);
            System.out.println(destination);

        } catch (ApiException e) {
            System.err.println("Exception when calling GeocodingApi#geocodeGet");
            e.printStackTrace();
        }

    }
}