package NAPI.Controller;

import NAPI.Model.GeoCoding;
import NAPI.Model.Routing;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is fetching information about calculated
 * duration, distance and routing instruction of the
 * two given addresses and a specified vehicle using
 * the Command Line Interface.
 *
 * @author Stephan, Fadi, Thomas, Paula
 */
public class CliController
{
    private Routing routing;

    /**
     * This method expects two addresses and information
     * about a vehicle used.
     *
     * For more information about this see the CliView
     * class documentation.
     *
     * @param addresses Origin and destination address.
     * @param vehicle Used for the route.
     */
    public CliController(List<String> addresses, String vehicle)
    {
        List<String> coordinates = setUp(addresses);
        routing                  = new Routing(coordinates, vehicle);
    }

    private List<String> setUp(List<String> addresses)
    {
        List<String> coordinates     = new ArrayList<>();
        for(int i = 0; i < addresses.size(); i++) {
            coordinates.add((new GeoCoding(addresses.get(i), 1)).getCoordinateAt(0));
        }

        return coordinates;
    }

    public String getTime()
    {
        return routing.getTime();
    }

    public String getDistance()
    {
        return routing.getDistance();
    }

    public List<String> getInstructions()
    {
        return routing.getRoute();
    }
}
