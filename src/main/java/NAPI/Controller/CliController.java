package NAPI.Controller;

import NAPI.Model.Model;
import NAPI.Model.Routing;

import java.util.List;

/**
 * This class is fetching information about calculated
 * duration, distance and routing instruction of the
 * two given addresses and a specified vehicle.
 */
public class CliController {
    Model rh;
    Routing rt;

    /**
     * This method expects two adresses and information
     * about a vehicle used. For more information about
     * this look at the CliView class documentation.
     *
     * @param addresses origin and destination address
     * @param vehicle used for the route
     */

    public CliController(List<String> addresses, String vehicle)
    {
        rh = new Model();
        rt = rh.calculateRoute(addresses, vehicle);
    }

    /**
     * This method returns the total estimated time
     * of the route between the given two addresses.
     * @return estimated time for total trip
     */
    public String calcTime()
    {
        return rt.getTime();
    }

    /**
     * This method returns the total distance of
     * the route between the given two addresses.
     * @return total distance
     */
    public String calcDistance()
    {
        return rt.getDistance();
    }

    /**
     * This method returns the instructions for the
     * root between the given two addresses.
     * @return textual routing instructions
     */
    public List<String> calcInstructions()
    {
        return rt.getRoute();
    }
}
