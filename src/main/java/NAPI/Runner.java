package NAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args)
    {
        new Runner().routeInstructions();
    }

    public void routeInstructions()
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
