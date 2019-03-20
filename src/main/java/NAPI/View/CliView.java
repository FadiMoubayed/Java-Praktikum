package NAPI.View;

import NAPI.Controller.CliController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class views routing information and accepts input
 * via the command line interface (CLI). Input variables
 * are:
 *
 * - starting address: enter the starting address here
 * - destination address: enter the destination address
 * - vehicle: enter your vehicle here
 *
 * When asking for an origin or a destination address it
 * is impossible to enter an empty string. There are
 * several vehicles supported by the Graphhopper API.
 * This class allows only six of them (car, truck,
 * scooter, foot, hike, bike) but they cover nearly all
 * use cases.
 *
 * The output provides the total estimated time, the
 * total distance in kilometers and the routing
 * instructions.
 */

public class CliView{

    public CliView()
    {
        this.draw();
    }

    public void draw() {
        List<String> adresses = new ArrayList<String>();
        Scanner sn = new Scanner(System.in);
        String startingAddress = "";
        while (startingAddress.isEmpty()) {
            System.out.print("Please enter your starting address: ");
            startingAddress = sn.nextLine();
        }
        adresses.add(startingAddress);


        String destinationAddress = "";
        while (destinationAddress.isEmpty()) {
            System.out.print("Please enter your destination address: ");
            destinationAddress = sn.nextLine();
        }
        adresses.add(destinationAddress);

        String vehicle = "";
        // check whether vehicle input matches with the supported vehicles
        while (!vehicle.equals("car") && !vehicle.equals("truck") && !vehicle.equals("scooter") && !vehicle.equals("foot") && !vehicle.equals("hike") && !vehicle.equals("bike")) {
            System.out.println("Please enter the type of your vehicle: ");
            System.out.println("car, truck, scooter, foot, hike, bike ");
            vehicle = sn.nextLine();
        }


        CliController cc = new CliController(adresses, vehicle);
        System.out.println("\n" + "Estimated time is: " + cc.calcTime() + " minutes");
        System.out.println("The total distance is: " + cc.calcDistance() + " kilometers" + "\n");

        List<String> instructions = cc.calcInstructions();

        for(int i = 0; i<instructions.size();i++) {
            System.out.println(instructions.get(i));
            // The class of instructions.get(i) is class java.lang.String
        }
    }
}
