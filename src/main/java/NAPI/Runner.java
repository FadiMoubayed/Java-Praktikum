package NAPI;

import NAPI.View.CliView;
import NAPI.View.GUIView;


/**
 * This is the runner class for our NavigationApi.
 * You can choose between a Graphical User Interface
 * and a Command Line Interface.
 * @author Stefan, Fadi, Thomas, Paula
 * @version 0.99
 */
public class Runner {
    public static void main(String[] args)
    {
        if(args.length >= 1) {
            if (args[0].equals("gui")) {
                GUIView gv = new GUIView();
            } else if (args[0].equals("cli")) {
                CliView cv = new CliView();
            }
        }
        else {
            System.out.println("You can choose between a GUI and a CLI.");
        }
    }
}
