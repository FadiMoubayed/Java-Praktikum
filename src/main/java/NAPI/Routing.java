package NAPI;
import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.RoutingApi;
import com.graphhopper.directions.api.client.model.RouteResponse;
import com.graphhopper.directions.api.client.model.RouteResponsePath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Routing {

    public static void main(String[] args) {

        List<String> x = new GeoCoding().convertAddressToCoordinates("Muenster ifgi", "Muenster hbf");

        new Routing().getRoute(x, "foot");
    }

    private List<String> getRoute(List<String> points, String vehicle ) {

        RoutingApi routing = new RoutingApi();
        String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54";
        //Getting the output from the Geocoding API (of class java.util.ArrayList)
        //List<String> points = new GeoCoding().convertAddressToCoordinates("Muenster ifgi", "Muenster hbf");

        List<String> instructions = new ArrayList<String>();

        try {

            RouteResponse rsp = routing.routeGet(points, false, key,
                    "en", true, vehicle, true, true, Arrays.<String>asList(), false,
                    "fastest", null, null, null, null, null,
                    null, null, null, null, null, null, null);

            RouteResponsePath path = rsp.getPaths().get(0);

            for(int i=0; i<path.getInstructions().size(); i++)
            {
                instructions.add(path.getInstructions().get(i).getText());
            }

        } catch (ApiException ex) {
            System.out.println(ex.getResponseBody());
            throw new RuntimeException(ex);
        }
        return instructions;
    }

}


