package NAPI.Controller;

import NAPI.Model.Model;
import NAPI.Model.Routing;

import java.util.List;

public class CliController {
    Model rh;
    Routing rt;
    public CliController(List<String> addresses, String vehicle)
    {
        rh = new Model();
        rt = rh.calculateRoute(addresses, vehicle);
    }
    public String calcTime()
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
