package NAPI.Model;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.model.GeocodingLocation;
import com.graphhopper.directions.api.client.model.GeocodingResponse;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * This class interacts with the GeocodingApi from the GraphhopperApi.
 * It will transform a given address into coordinates, which are needed
 * for the routing calculation.
 *
 * For further details visit <code>https://graphhopper.com/api/1/docs/geocoding/</code>
 *
 *  @author StephanThomasFadiPaula
 */
public class GeoCoding {

    private GeocodingApi geocode;
    private GeocodingResponse result;
    private String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54";
    private String language;

    /**
     * Constructor
     */
    protected GeoCoding()
    {
        geocode = new GeocodingApi();
        language = "en";
    }
    /**
     * This methods converts a list of addresses into coordinates
     * usind the GeocodingAPI.
     *
     * @param addresses start and destination strings
     * @return points List of coordinates (as Strings)
     */
    protected List<String> convertAddressToCoordinates(List<String> addresses){

        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata

        List<String> points = new ArrayList();
        for(int i = 0; i<addresses.size();i++) {
            GeocodingResponse result = new GeocodingResponse();
            /*
            if(addresses.get(i).isEmpty())
            {
                throw new IllegalArgumentException("address no." + (i+1) + " is empty");
            }
            else {*/
                try {

                    result = geocode.geocodeGet(key, addresses.get(i), language, limit, reverse, "", provider);

                } catch (ApiException e) {
                    if (e.getCause() instanceof UnknownHostException)
                        throw new IllegalArgumentException("Could not connect to network.");
                    else
                        throw new IllegalArgumentException(e.getResponseBody());
                }
                //Getting latitude and longitude of the starting point
                double lat = result.getHits().get(0).getPoint().getLat();
                double lng = result.getHits().get(0).getPoint().getLng();

                String onePoint = Double.toString(lat) + "," + Double.toString(lng);

                points.add(onePoint);
            //}
        }

        return points;
    }

    /**
     * This method provides a set of possible addresses that matches most
     * with the given input address, for example if the address is not
     * complete or is not unique.
     *
     * The parameter "limit" specifies the maximum number of results returned
     * by the GeocodingAPI.
     *
     * @param inputAdress is the input address string to be converted into
     *                    coordinates
     * @return understoodAddress is the adress that matches most with the given
     * input address
     */
    protected String understandInputAddress(String inputAdress)
    {
        Integer limit = 1; // Integer | Specify the maximum number of returned results
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata


        String understoodAddress = "";
        GeocodingResponse result = new GeocodingResponse();
        try {
            result = geocode.geocodeGet(key, inputAdress, language, limit, reverse, "", provider);

        } catch (ApiException e) {
            if(e.getCause() instanceof UnknownHostException)
                throw new IllegalArgumentException("Could not connect to network.");
            else
                throw new IllegalArgumentException(e.getResponseBody());
        }

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

        return understoodAddress;
    }



}