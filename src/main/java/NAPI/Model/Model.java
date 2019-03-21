package NAPI.Model;

import NAPI.Model.GeoCoding;
import NAPI.Model.Routing;
import com.graphhopper.search.Geocoding;

import java.util.ArrayList;
import java.util.List;

/**
 * this class serves as the facade to all classes that interact with GraphhopperApis
 * @autor StefanThomasFadiPaula
 */
public class Model {


    /**
     * this method gives you a list of instructions on how to travel between given adresses with a vehicle of your choice
     * @param coordinates
     * @param vehicle
     * @return route as a list of instructions
     */
    public Routing calculateRoute(List<String> coordinates, String vehicle)
    {
        Routing rt = new Routing(coordinates, vehicle);
        return rt;
    }

    public GeoCoding calculateGC(String inputAddress, int limit)
    {
        return new GeoCoding(inputAddress,limit);
    }
}
