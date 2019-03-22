package NAPI;

import NAPI.View.CliView;
import NAPI.View.GUIView;


/**
 * This is the runner class for our NavigationApi.
 * You can choose between a Graphical User Interface and a Console Line Interface.
 * @author Stefan, Fadi, Thomas, Paula
 * @version 1.0
 */
public class Runner {
    public static void main(String[] args)
    {
        if(args[0].equals("gui")) {
            GUIView gv = new GUIView();
        }
        else if(args[0].equals("cli")) {
            CliView cv = new CliView();
        }
        else {
            System.out.println("You can choose between a gui and a cli");
        }
        //TODO java -jar jarname "cli"

    }


    /**
     * This method gives the user a route for the start and destination of his travel with a vehicle of his choice.
     * The interaction between the user and the Software happens through the CLI.
     */

}
