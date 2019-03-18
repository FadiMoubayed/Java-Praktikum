package NAPI.Model;
import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.RoutingApi;
import com.graphhopper.directions.api.client.model.RouteResponse;
import com.graphhopper.directions.api.client.model.RouteResponsePath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.TimeUnit;


/**
 * This class interacts with the RoutingApi from the GraphhopperApi
 */
class Routing {

    private RoutingApi routing;
    private RouteResponsePath path;
    private String key = "d7bb71f8-0024-4338-b602-f052a9ad1c54";

    private String distance;
    private long time;

    public Routing(List<String> points, String vehicle)
    {
        // To-do: test if the input is correct
        calcPath(points, vehicle);
        calcTime();
        calcDist();
    }

    private void calcPath(List<String> points, String vehicle) {
        routing = new RoutingApi();

        try {
            RouteResponse rsp = routing.routeGet(points, false, key,
                    "en", true, vehicle, true, true, Arrays.<String>asList(), false,
                    "fastest", null, null, null, null, null,
                    null, null, null, null, null, null, null);

            path = rsp.getPaths().get(0);
        } catch (ApiException ex)
        {
            System.out.println(ex.getResponseBody());
            throw new RuntimeException(ex);
        }
    }


    // A method to convert time from miliseconds to minuets
    private void calcTime(){
        long estimatedTimeMilliSeconds = (path.getTime());
        long estimatedTimeMinutes = TimeUnit.MILLISECONDS.toMinutes(estimatedTimeMilliSeconds);
        time = estimatedTimeMinutes;
    }

    // A method to convert distance from meters to KM
    private void calcDist(){
        double routeDistance = path.getDistance()/1000;
        // Formatting the distance to display 2 digits
        String routeDistanceString = String.format("%.2f", routeDistance);
        distance = routeDistanceString;
    }

    /**
     * this method gives you a list of instructions on how to travel between given coordinates with a vehicle of your choice
     * @return list of instructions (as Strings)
     */
    public List<String> getRoute( ) {


        //Getting the output from the Geocoding API (of class java.util.ArrayList)
        //List<String> points = new GeoCoding().convertAddressToCoordinates("Muenster ifgi", "Muenster hbf");

        List<String> instructions = new ArrayList<String>();

            for(int i=0; i<path.getInstructions().size(); i++) {
                if (path.getInstructions().get(i).getText().startsWith("Continue")) {
                    instructions.add(path.getInstructions().get(i).getText() + " for " + path.getInstructions().get(i).getDistance() + " meters");
                } else if (path.getInstructions().get(i).getText().equals("Arrive at destination")) {
                    instructions.add(path.getInstructions().get(i).getText());
                } else if (path.getInstructions().get(i).getText().startsWith("Keep")) {
                    instructions.add(path.getInstructions().get(i).getText() + " for " + path.getInstructions().get(i).getDistance() + " meters");
                } else {
                    instructions.add(" In " + path.getInstructions().get(i).getDistance() + " meters " + path.getInstructions().get(i).getText());
                    //instructions.add(path.getInstructions().get(i).getText());
                }
            }

        return instructions;
    }

    public String getDistance() {
        return distance;
    }

    public long getTime() {
        return time;
    }
}


