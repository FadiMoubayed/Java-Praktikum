package NAPI.View;

import NAPI.Controller.CliController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * This class views routing information and accepts input
 * via the command line interface (CLI). Input variables
 * are the starting address (enter the starting address
 * here), the destination address  (enter the destination
 * address here), and the vehicle (enter your vehicle here).
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
 * @author Stephan, Fadi, Thomas, Paula
 */

public class CliView{

    public CliView()
    {
        this.draw();
    }

    public void draw() {
        List<String> adresses = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String startingAddress;
        do {
            System.out.print("Please enter your starting address: ");
            startingAddress = sc.nextLine();
        } while (startingAddress.isEmpty());
        adresses.add(startingAddress);


        String destinationAddress;
        do {
            System.out.print("Please enter your destination address: ");
            destinationAddress = sc.nextLine();
        } while(destinationAddress.isEmpty());
        adresses.add(destinationAddress);

        String vehicle;
        // check whether vehicle input matches with the supported vehicles
        do {
            System.out.println("Please enter the type of your vehicle: ");
            System.out.println("car, truck, scooter, foot, hike, bike ");
            vehicle = sc.nextLine();
        } while (!isInEnum(vehicle, VehicleType.class));


        CliController cc = new CliController(adresses, vehicle);
        System.out.println(System.lineSeparator() + "Estimated time is: " + cc.getTime());
        System.out.println("The total distance is: " + cc.getDistance() + " kilometers" + System.lineSeparator());

        //calcInstructions returns a LinkedList
        List<String> instructions = cc.getInstructions();

        Iterator it = instructions.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    public enum VehicleType
    {
        car, truck, scooter, foot, hike, bike
    }

    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass)
    {
        for (E e : enumClass.getEnumConstants()) {
            if(e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
