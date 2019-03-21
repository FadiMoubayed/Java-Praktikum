package NAPI.Controller;

import NAPI.Model.GeoCoding;
import NAPI.Model.Model;
import NAPI.Model.Routing;

import java.util.ArrayList;
import java.util.List;

public class CliController {
    Model model;
    Routing rt;
    List<GeoCoding> gc;
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
