
import com.graphhopper.directions.api.client.*;
import com.graphhopper.directions.api.client.api.RoutingApi;
import com.graphhopper.directions.api.client.auth.*;
import com.graphhopper.directions.api.client.model.*;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import java.io.File;
import java.util.*;

public class GeoCoding {



    public List<String> convertAddressToCoordinates(String q, String q2){

        GeocodingApi apiInstance = new GeocodingApi();

        String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54"; // String | Get your key at graphhopper.com

        String locale = "en"; // String | Display the search results for the specified locale. Currently French (fr), English (en), German (de) and Italian (it) are supported. If the locale wasn't found the default (en) is used.
        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String point = "51.9692,7.5958"; // String | The location bias in the format 'latitude,longitude' e.g. point=45.93272,11.58803
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata

        List<String> points = new ArrayList();

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

            points.add(start);
            points.add(destination);



        } catch (ApiException e) {
            System.err.println("Exception when calling GeocodingApi#geocodeGet");
            e.printStackTrace();
        }

        return points;
    }





}
