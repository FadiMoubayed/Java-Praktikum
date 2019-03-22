package NAPI.Model;

import java.util.List;

/**
 * This class serves as the facade to all classes that interact with Graphhopper Api.
 *
 * @author Stefan, Thomas, Fadi, Paula
 */
public class Model {
    /**
     * this method gives you a list of instructions on how to travel between given addresses with a vehicle of your choice
     * @param coordinates ...
     * @param vehicle ...
     * @return route as a list of instructions
     */
    public Routing calculateRoute(List<String> coordinates, String vehicle)
    {
        return new Routing(coordinates, vehicle);
    }


    /**
     * Returns a new GeoCoding instance from a address
     * @param inputAddress ...
     * @param limit ...
     * @return
     */
    public GeoCoding calculateGC(String inputAddress, int limit)
    {
        return new GeoCoding(inputAddress,limit);
    }
}
