package NAPI;

import java.util.List;

/**
 * this class serves as the facade to all classes that interact with GraphhopperApis
 * @autor StefanThomasFadiPaula
 */
public class RequestHandler {

    /**
     * this method gives you a list of instructions on how to travel between given adresses with a vehicle of your choice
     * @param adresses
     * @param vehicle
     * @return route as a list of instructions
     */
    public List<String> calculateRoute(List<String> adresses, String vehicle)
    {
        List<String> route = null;

        GeoCoding gc = new GeoCoding();
        Routing rt = new Routing();

        List<String> coordinates = null;
        coordinates = gc.convertAddressToCoordinates(adresses);
        route = rt.getRoute(coordinates,vehicle);
        return route;
    }
}
