package NAPI.Model;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.GeocodingApi;
import com.graphhopper.directions.api.client.model.GeocodingLocation;
import com.graphhopper.directions.api.client.model.GeocodingResponse;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
    private GeocodingApi geocodeAPI;
    private GeocodingResponse response;
    private String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54";
    private String language;
    private int numberOfLocations;
    private List<GeocodingLocation> geocodingLocationList;

    /**
     * Konstruktor
     *
     * @param address String with given address
     * @param limit limits number of guessed addresses to choose from (DEPRECATED)
     */

    public GeoCoding(String address, int numberOfLocations) {
        geocodeAPI                 = new GeocodingApi();
        language                   = "en";
        this.numberOfLocations     = numberOfLocations;
        this.geocodingLocationList = convertAddressToGCL(address);
    }

     /**
      * This methods converts a list of addresses into coordinates
      * using the GeocodingAPI.d
      * @param address start and destination strings
      * @return List of coordinates (as Strings)
      */
    private List<GeocodingLocation> convertAddressToGCL(String address)
    {
        GeocodingResponse result;

        try {
            result = geocodeAPI.geocodeGet(key, address, language, numberOfLocations,
                                   false, "", "default");
        } catch (ApiException e) {
            if(e.getCause() instanceof UnknownHostException)
                throw new IllegalArgumentException("Could not connect to network.");
            else
                throw new IllegalArgumentException(e.getResponseBody());
        }

        this.numberOfLocations = result.getHits().size();

        return result.getHits();
    }

    /**
     * This methods converts a number of addresses into coordinates.
     * @return List of coordinates (as Strings)
     */
    List<String> getCoordinates()
    {
        List<String> points = new ArrayList<>(numberOfLocations);
        for(int i = 0; i < numberOfLocations; i++)
        {
            points.add(getCoordinateAt(i));
        }

        return points;
    }

    public List<String> getAddresses()
    {
        List<String> addresses = new ArrayList<>(numberOfLocations);
        for(int i = 0; i< numberOfLocations; i++)
        {
            addresses.add(getAddressAt(i));
        }

        return addresses;
    }

    public String getCoordinateAt(int location)
    {
        double lat = geocodingLocationList.get(location).getPoint().getLat();
        double lng = geocodingLocationList.get(location).getPoint().getLng();

        return lat + "," + lng;
    }

    public String getAddressAt(int location)
    {
        //String understoodAddress = "";
        GeocodingLocation output = geocodingLocationList.get(location);
        System.out.println(output);
        List<String> addressList = Arrays.asList(output.getCountry(),
                                                 output.getCity(),
                                                 output.getPostcode(),
                                                 output.getStreet(),
                                                 output.getHousenumber());

        StringBuilder strBuilder = new StringBuilder();
        boolean allNeqNull = true;
        for(int i = 0; i < addressList.size(); i++) {
            String addressItem = addressList.get(i);

            if (addressItem != null) {
                strBuilder.append(addressItem);
                if(!(addressItem.equals(output.getStreet()) ||
                   addressItem.equals(output.getCity()))){
                    strBuilder.append(", ");
                }
                else
                {
                    strBuilder.append(" ");
                }
            } else {
                allNeqNull = false;
                strBuilder.append(output.getName());
                break;
            }
        }

        if(allNeqNull) {
            int indexOfLastComma = strBuilder.lastIndexOf(",");
            strBuilder.delete(indexOfLastComma, indexOfLastComma+2);
        }

        /*if (output.getCountry() != null) {
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
        }*/

        return strBuilder.toString();
    }
}