package NAPI;

import NAPI.View.CliView;
import NAPI.View.GUIView;


/**
 * This is the runner class for our NavigationApi
 */
public class Runner {
    public static void main(String[] args)
    {
        if(args.length <= 2) { // changed for testing purposes only
        // if (args[1] == "gui") {
            GUIView gv = new GUIView();
        }
        else if(args[1] == "cli") {
            CliView cv = new CliView();
        }
        else
        {
            System.out.println("You can choose between a gui and a cli");
        }
        //TODO java -jar jarname "gui"

    }


    /**
     * This method gives the user a route for the start and destination of his travel with a vehicle of his choice.
     * The interaction between the user and the Software happens through the CLI.
     */

}
