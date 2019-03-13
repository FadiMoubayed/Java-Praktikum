package NAPI;

import java.util.List;

public class RequestHandler {

    public List<String> calculateRoute(List<String> adresses, String vehicle)
    {
        GeoCoding gc = new GeoCoding();
        Routing rt = new Routing();

        List<String> coordinates = gc.convertAddressToCoordinates(adresses.get(0),adresses.get(1));
        return rt.getRoute(coordinates,vehicle);
    }
}
