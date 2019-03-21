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
 * This class interacts with the GeoCodingApi from the Graphhopper Api
 */
public class GeoCoding {
    private GeocodingApi geocodeAPI;
    private GeocodingResponse response;
    private String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54";
    private String language;
    private int locationChoiceLimit; // TODO check name
    private List<GeocodingLocation> geocodingLocationList;

    /**
     *
     */
    public GeoCoding(String address, int locationChoiceLimit) {
        geocodeAPI                 = new GeocodingApi();
        language                   = "en";
        this.locationChoiceLimit   = locationChoiceLimit;
        this.geocodingLocationList = convertAddressToGCL(address);
    }

     /**
     * This methods converts a number of adresses into coordinates
     * @param address start and destination strings
     * @return List of coordinates (as Strings)
     */
    private List<GeocodingLocation> convertAddressToGCL(String address)
    {
        GeocodingResponse result;

        try {
            result = geocodeAPI.geocodeGet(key, address, language, locationChoiceLimit,
                                   false, "", "default");
        } catch (ApiException e) {
            if(e.getCause() instanceof UnknownHostException)
                throw new IllegalArgumentException("Could not connect to network.");
            else
                throw new IllegalArgumentException(e.getResponseBody());
        }

        this.locationChoiceLimit = result.getHits().size();

        return result.getHits();
    }

    /**
     * This methods converts a number of adresses into coordinates
     * @return List of coordinates (as Strings)
     */
    List<String> getCoordinates()
    {
        List<String> points = new ArrayList<>(locationChoiceLimit);
        for(int i = 0; i < locationChoiceLimit; i++)
        {
            points.add(getCoordinateAt(i));
        }

        return points;
    }

    public List<String> getAddresses()
    {
        List<String> addresses = new ArrayList<>(locationChoiceLimit);
        for(int i = 0; i< locationChoiceLimit; i++)
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
        // TODO fill out
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