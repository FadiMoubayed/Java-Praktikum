package NAPI.Controller;

import NAPI.Model.GeoCoding;
import NAPI.Model.Model;
import NAPI.Model.Routing;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is fetching information about calculated
 * duration, distance and routing instruction of the
 * two given addresses and a specified vehicle.
 */
public class CliController {
    private Model model;
    private Routing routing;

    /**
     * This method expects two adresses and information
     * about a vehicle used.
     * For more information about
     * this look at the CliView class documentation.
     *
     * @param addresses Origin and destination address.
     * @param vehicle Used for the route.
     */
    public CliController(List<String> addresses, String vehicle)
    {
        model                    = new Model();
        List<String> coordinates = setUp(addresses);
        routing                  = model.calculateRoute(coordinates, vehicle);
    }

    private List<String> setUp(List<String> addresses)
    {
        // TODO Check if necessary
        List<GeoCoding> geoCodesList = new ArrayList<>();
        List<String> coordinates     = new ArrayList<>();
        for(int i = 0; i < addresses.size(); i++) {
            geoCodesList.add(model.calculateGC(addresses.get(i), 1));
            coordinates.add(geoCodesList.get(i).getCoordinateAt(0));
        }

        return coordinates;
    }
    // TODO Setter & Getter nicht dokumentieren
    /**
     * This method returns the total estimated time
     * of the route between the given two addresses.
     * @return estimated time for total trip
     */
    // TODO public type getName(){ .. }
    public String calcTime()
    {
        return routing.getTime();
    }

    /**
     * This method returns the total distance of
     * the route between the given two addresses.
     * @return total distance
     */
    public String calcDistance()
    {
        return routing.getDistance();
    }

    /**
     * This method returns the instructions for the
     * root between the given two addresses.
     * @return textual routing instructions
     */
    public List<String> calcInstructions()
    {
        return routing.getRoute();
    }
}
