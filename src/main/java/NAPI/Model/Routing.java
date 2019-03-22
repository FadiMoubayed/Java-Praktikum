package NAPI.Model;

import com.graphhopper.directions.api.client.ApiException;
import com.graphhopper.directions.api.client.api.RoutingApi;
import com.graphhopper.directions.api.client.model.RouteResponse;
import com.graphhopper.directions.api.client.model.RouteResponsePath;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * This class interacts with the RoutingApi from the GraphhopperApi.
 */
public class Routing {

    private static final String KEY = "d7bb71f8-0024-4338-b602-f052a9ad1c54";
    private RoutingApi routingApi;
    private RouteResponsePath path;
    private String distance;
    private long time;

    public Routing(List<String> points, String vehicle)
    {
        this.routingApi = new RoutingApi();
        calcPath(points, vehicle);
        calcTime();
        calcDist();
    }

    private void calcPath(List<String> points, String vehicle) {
        RouteResponse response;
        try {
            response = routingApi.routeGet(points, false, KEY,
                    "en", true, vehicle, true, true, Arrays.<String>asList(), false,
                    "fastest", null, null, null, null, null,
                    null, null, null, null, null, null, null);

        } catch (ApiException ex)
        {
            if(ex.getResponseBody().contains("Connection between locations not found"))
                throw new IllegalArgumentException("Connection between locations not found");
            else
                throw new IllegalArgumentException(ex.getResponseBody());
        }
        path = response.getPaths().get(0);
    }


    // A method to convert time from milliseconds to minutes
    private void calcTime(){
        long estimatedTimeMilliSeconds = (path.getTime());
        this.time = TimeUnit.MILLISECONDS.toMinutes(estimatedTimeMilliSeconds);
    }

    // A method to convert distance from meters to KM
    private void calcDist(){
        double routeDistance = path.getDistance()/1000;
        this.distance = String.format("%.2f", routeDistance);
    }

    /**
     * This method gives you a list of instructions
     * on how to travel between given coordinates
     * with a vehicle of your choice.
     *
     * @return list of instructions (as Strings)
     */
    public List<String> getRoute( ) {
        List<String> instructions = new LinkedList<>();
            for(int i = 0; i < path.getInstructions().size(); i++) {
                if (path.getInstructions().get(i).getText().startsWith("Continue")) {
                    instructions.add(path.getInstructions().get(i).getText() + " for " +
                            (int)((double)path.getInstructions().get(i).getDistance()) + " meters");
                } else if (path.getInstructions().get(i).getText().equals("Arrive at destination")) {
                    instructions.add(path.getInstructions().get(i).getText());
                } else if (path.getInstructions().get(i).getText().startsWith("Keep")) {
                    instructions.add(path.getInstructions().get(i).getText() + " for " +
                            (int)((double)path.getInstructions().get(i).getDistance()) + " meters");
                } else {
                    instructions.add(
                            " In " +
                            (int)((double)path.getInstructions().get(i).getDistance()) +
                            " meters " +
                            path.getInstructions().get(i).getText());
                }
            }

        return instructions;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime()
    {
        if(time < 60)
        {
            return this.time + " minutes";
        }
        else
        {
            double timeHours = time / 60.0;
            return String.format("%.1f", timeHours) + " hours";
        }
    }
}
