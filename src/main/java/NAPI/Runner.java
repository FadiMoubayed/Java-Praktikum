package NAPI;

import NAPI.Model.RequestHandler;
import NAPI.Model.Routing;
import NAPI.View.GUIView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the runner class for our NavigationApi
 */
public class Runner {
    public static void main(String[] args)
    {
        GUI_Try1 gt = new GUI_Try1();
        //GUIView gv = new GUIView();
        //new Runner().routeInstructionsCli();
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

        System.out.println("Please enter the type of your vehicle: ");
        System.out.println("car , truck , scooter, foot , hike , bike ");
        String vehicle = sn.nextLine();

        Routing rt = rh.calculateRoute(adresses, vehicle);
        System.out.println("\n" + "Estimated time is: " + rt.getTime() + " Minuets");
        System.out.print("The total distance is: " + rt.getDistance() + " KM" + "\n");

        List<String> instructions = rt.getRoute();

        for(int i = 0; i<instructions.size();i++) {
            System.out.println(instructions.get(i));
            // The class of instructions.get(i) is class java.lang.String
        }

        /*
        String testString = "test";
        if(testString == "test"){
            System.out.println("Testing string is successful");
        }
        else {
            System.out.println("Test string was not successul");
        }

        System.out.println(instructions.get(0));

        if(instructions.get(0).equals("Continue")){
            System.out.println("Was able to find continue!");
        }
        else{
            System.out.println("was not able to ind continue :(");
        }
        */


    }
}
