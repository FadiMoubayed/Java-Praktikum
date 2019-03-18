package NAPI.Controller;

import NAPI.Model.RequestHandler;
import NAPI.Model.Routing;

import java.util.List;

public class CliController {
    RequestHandler rh;
    Routing rt;
    public CliController(List<String> addresses, String vehicle)
    {
        rh = new RequestHandler();
        rt = rh.calculateRoute(addresses, vehicle);
    }
    public long calcTime()
    {
        return rt.getTime();
    }
    public String calcDistance()
    {
        return rt.getDistance();
    }
    public List<String> calcInstructions()
    {
        return rt.getRoute();
    }
}
