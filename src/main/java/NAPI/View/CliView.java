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
        System.out.print("Please enter your starting adress: ");
        adresses.add(sn.nextLine());

        System.out.print("Please enter your destination adress: ");
        adresses.add(sn.nextLine());

        System.out.println("Please enter the type of your vehicle: ");
        System.out.println("car , truck , scooter, foot , hike , bike ");
        String vehicle = sn.nextLine();

        CliController cc = new CliController(adresses, vehicle);
        System.out.println("\n" + "Estimated time is: " + cc.calcTime() + " Minuets");
        System.out.println("The total distance is: " + cc.calcDistance() + " KM" + "\n");

        List<String> instructions = cc.calcInstructions();

        for(int i = 0; i<instructions.size();i++) {
            System.out.println(instructions.get(i));
            // The class of instructions.get(i) is class java.lang.String
        }
    }
}
