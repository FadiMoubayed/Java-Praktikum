package NAPI;

import java.util.List;

/**
 * @autor StefanThomasFadiPaula
 */
public class RequestHandler {

    /**
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
        coordinates = gc.convertAddressToCoordinates(adresses.get(0),adresses.get(1));
        route = rt.getRoute(coordinates,vehicle);
        return route;
    }
}
