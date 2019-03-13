package NAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the runner class for our NavigationApi
 */
public class Runner {
    public static void main(String[] args)
    {
        new Runner().routeInstructionsCli();
    }

    /**
     * This method gives the user a route for the start and destination of his travel with a vehicle of his choosing.
     * The interaction between the user and the Software happens through the CLI.
     */
    public void routeInstructionsCli()
    {
        RequestHandler rh = new RequestHandler();
        List<String> adresses = new ArrayList<String>();
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter your starting adress: ");
        adresses.add(sn.nextLine());

        System.out.print("Please enter your destination adress: ");
        adresses.add(sn.nextLine());

        System.out.print("Please enter the type of your vehicle: ");
        String vehicle = sn.nextLine();

        List<String> instructions = rh.calculateRoute(adresses, vehicle);
        for(int i = 0; i<instructions.size();i++)
            System.out.println(instructions.get(i));
    }
}
