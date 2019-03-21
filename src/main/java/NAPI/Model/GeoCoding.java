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
 * for the routing calculation. It provides a set of possible addresses
 * that match most with the given input address, for example if the
 * address is not complete or is not unique.
 *
 * The parameter <code>limit</code> specifies the maximum number of
 * results by the GeocodingAPI. The results are the guessed addresses
 * according to the input string.
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
    private int limit;

    List<GeocodingLocation> gcl;

    /**
     * Konstruktor
     *
     * @param address String with given address
     * @param limit limits number of guessed addresses to choose from
     */

    protected GeoCoding(String address, int limit) {
        geocode = new GeocodingApi();
        language = "en";
        this.limit = limit;
        this.gcl = convertAddressToGCL(address);
    }
     /**
      * This methods converts a list of addresses into coordinates
      * usind the GeocodingAPI.
      * @param address start and destination strings
      * @return List of coordinates (as Strings)
      */
    private List<GeocodingLocation> convertAddressToGCL(String address)
    {
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata

        GeocodingResponse result = new GeocodingResponse();
        try {
            result = geocode.geocodeGet(key, address, language, limit, reverse, "", provider);

        } catch (ApiException e) {
            if(e.getCause() instanceof UnknownHostException)
                throw new IllegalArgumentException("Could not connect to network.");
            else
                throw new IllegalArgumentException(e.getResponseBody());
        }
        limit = result.getHits().size();
        return result.getHits();
    }

    /**
     * This methods converts a number of adresses into coordinates
     * @return List of coordinates (as Strings)
     */
    public List<String> getCoordinates()
    {
        Boolean reverse = false; // Boolean | Set to true to do a reverse Geocoding request, see point parameter
        String provider = "default"; // String | Can be either, default, nominatim, opencagedata

        List<String> points = new ArrayList<>();
        for(int i = 0; i<limit; i++)
        {
            points.add(getCoordinateAt(i));
        }
        return points;
    }

    public List<String> getAddresses()
    {
        List<String> addresses = new ArrayList<>();
        for(int i = 0; i<limit; i++)
        {
            addresses.add(getAddressAt(i));
        }

        return addresses;
    }

    public String getCoordinateAt(int location)
    {
        double lat = gcl.get(location).getPoint().getLat();
        double lng = gcl.get(location).getPoint().getLng();

        return (Double.toString(lat) + "," + Double.toString(lng));
    }

    public String getAddressAt(int location)
    {
        String understoodAddress = "";
        GeocodingLocation output = gcl.get(location);
        if (output.getCountry() != null) {
            understoodAddress = output.getCountry();
            if (output.getCity() != null) {
                understoodAddress = understoodAddress + ", " + output.getCountry();
                if (output.getPostcode() != null) {
                    understoodAddress = understoodAddress + ", " + output.getPostcode();
                    if (output.getCity() != null) {
                        understoodAddress = understoodAddress + " " + output.getCity();
                        if (output.getStreet() != null) {
                            understoodAddress = understoodAddress + ", " + output.getStreet();
                            if (output.getHousenumber() != null) {
                                understoodAddress = understoodAddress + " " + output.getHousenumber();
                            } else {
                                understoodAddress = understoodAddress + " " + output.getName();
                            }
                        } else {
                            understoodAddress = understoodAddress + ", " + output.getName();
                        }
                    } else {
                        understoodAddress = understoodAddress + " " + output.getName();
                    }
                } else {
                    understoodAddress = understoodAddress + ", " + output.getName();
                }
            } else {
                understoodAddress = understoodAddress + ", " + output.getName();
            }
        } else {
            understoodAddress = understoodAddress + " " + output.getName();
        }
        return understoodAddress;
    }


}