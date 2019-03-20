package NAPI.View;

import NAPI.Controller.CliController;
import NAPI.Model.RequestHandler;
import NAPI.Model.Routing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CliView{

    public CliView()
    {
        this.draw();
    }

    public void draw() {
        List<String> adresses = new ArrayList<String>();
        Scanner sn = new Scanner(System.in);
        System.out.print("Please enter your starting address: ");
        String startingAddress = "";
        while (startingAddress.isEmpty()) {
            startingAddress = sn.nextLine();
        }
        adresses.add(startingAddress);

        System.out.print("Please enter your destination address: ");
        String destinationAddress = "";
        while (destinationAddress.isEmpty()) {
            destinationAddress = sn.nextLine();
        }
        adresses.add(destinationAddress);

        String vehicle = "";
        while (true) {
            System.out.println("Please enter the type of your vehicle: ");
            System.out.println("car, truck, scooter, foot, hike, bike ");
            vehicle = sn.nextLine();
            if (vehicle.equals("car") || vehicle.equals("truck") || vehicle.equals("scooter") || vehicle.equals("foot") || vehicle.equals("hike") || vehicle.equals("bike")) {
                break;
            }
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
