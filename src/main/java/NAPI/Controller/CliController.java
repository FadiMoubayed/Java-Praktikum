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
    Model model;
    Routing rt;
    List<GeoCoding> gc;


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
        model = new Model();
        List<String> coordinates = new ArrayList<>();
        for(int i = 0; i<addresses.size(); i++) {
            gc.add(model.calculateGC(addresses.get(i), 1));
            coordinates.add(gc.get(i).getCoordinateAt(0));
        }

        rt = model.calculateRoute(addresses, vehicle);
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
