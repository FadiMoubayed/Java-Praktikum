package NAPI.Controller;

import NAPI.Model.GeoCoding;
import NAPI.Model.Model;
import NAPI.Model.Routing;
import NAPI.View.GUIView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is fetching information about calculated
 * duration, distance and routing instruction of the
 * two given addresses and a specified vehicle.
 */
public class GuiController implements ActionListener {

    private JTextField startTextField;
    private JTextField destTextField;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private JComboBox startComboBox;
    private JComboBox destComboBox;
    private JLabel startCheckLabel;
    private JLabel destCheckLabel;
    private JButton calculateButton;
    private JTextArea outputTextArea;
    private GeoCoding startGC;
    private GeoCoding destGC;
    private Model model;

    private String vehicle;

    /**
     * This is the constructor.
     *
     * It imports some components from the GuiView,
     * creates a new instance of the model and sets
     * the default vehicle to "car"
     *
     * @param startTextField The text field for the
     *                       input of the start/origin
     *                       address
     * @param destTextField The text field for the
     *                      input of the destination
     *                      address
     * @param startCheckButton Hitting this button will
     *                         check whether the input
     *                         address exists. A suggested
     *                         address is displayed on the
     *                         screen.
     * @param destCheckButton Hitting this button will check
     *                        whether the input address
     *                        exists. A suggested address is
     *                        displayed on the screen.
     * @param calculateButton Hitting this button will process
     *                        the route calculation between the
     *                        two given addresses.
     * @param outputTextArea In this area the routing information
     *                       will be displayed.
     * @param startComboBox Three suggested addresses will be
     *                      displayed here in a drop down menu.
     *                      The user may select one out of them.
     * @param destComboBox Three suggested addresses will be
     *                     displayed here in a drop down menu.
     *                     The user may select one out of them.
     */
    public GuiController(JTextField startTextField, JTextField destTextField, JButton startCheckButton, JButton destCheckButton,JButton calculateButton, JTextArea outputTextArea, JComboBox startComboBox, JComboBox destComboBox, JLabel startCheckLabel, JLabel destCheckLabel) {
        super();
        this.startTextField = startTextField;
        this.destTextField = destTextField;
        this.startCheckButton = startCheckButton;
        this.destCheckButton = destCheckButton;
        this.calculateButton = calculateButton;
        this.outputTextArea = outputTextArea;
        this.startComboBox = startComboBox;
        this.destComboBox = destComboBox;
        this.startCheckLabel = startCheckLabel;
        this.destCheckLabel = destCheckLabel;
        this.model = new Model();

        vehicle = "car";
    }

    /**
     * This method listens to and processes the user input
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startCheckButton)
        {
            if(startTextField.getText().isEmpty())
            {
                this.errorMessage("error while checking start address: \n" + "please put in a starting address");
            }
            else {
                String startAddress = startTextField.getText() + "";
                List<String> destAddress = new ArrayList<String>();
                try {
                    startGC = model.calculateGC(startAddress, 3);
                    List<String> output = startGC.getAddresses();
                    this.updateComboBox(output, destAddress);
                } catch (IllegalArgumentException ex) {
                    this.errorMessage("error while checking start address: \n" + ex.getMessage());
                } catch (Exception ex)
                {
                    this.errorMessage("error while checking start address: \n" + "Please try a different address \n \n" + "type of error: \n" + ex.toString());
                }
            }
        }
        else if(e.getSource() == this.destCheckButton)
        {
            if(destTextField.getText().isEmpty())
            {
                this.errorMessage("error while checking destination address: \n" + "please put in a destination address\n ");
            }
            else {
                List<String> startAddress = new ArrayList<String>();
                String destAddress = destTextField.getText() + "";
                try {
                    destGC = model.calculateGC(destAddress, 3);
                    List<String> output = destGC.getAddresses();
                    this.updateComboBox(startAddress, output);
                } catch (IllegalArgumentException ex) {
                    this.errorMessage("error while checking destination address: \n" + ex.getMessage());
                } catch (Exception ex)
                {
                    this.errorMessage("error while checking destination address: \n" + "Please try a different address \n \n" + "type of error: \n" + ex.toString());
                }
            }
        }
        else if(e.getSource() == this.calculateButton)
        {
            if(startTextField.getText().isEmpty())
            {
                this.errorMessage("error while calculating: \n" + "please put in a starting address");
            }
            else if(destTextField.getText().isEmpty())
            {
                this.errorMessage("error while calculating: \n" + "please put in a destination address");
            }
            else {
                List<String> coordinates = new ArrayList<String>();
                coordinates.add(startGC.getCoordinateAt(startComboBox.getSelectedIndex()));
                coordinates.add(destGC.getCoordinateAt(destComboBox.getSelectedIndex()));
                Routing rt;
                try {
                    rt = model.calculateRoute(coordinates, vehicle);
                    this.updateOutput(rt.getTime(), rt.getDistance(), rt.getRoute());
                } catch (IllegalArgumentException ex) {
                    this.errorMessage("error while calculating: \n" + ex.getMessage());
                } 
            }
        }
        else
        {
            vehicle = e.getActionCommand();
        }
    }

    /**
     * This method updates the CheckLabels to display the specified address
     * @param startAddress
     * @param destAddress
     */
    public void updateComboBox(List<String> startAddress, List<String> destAddress)
    {
        if(!startAddress.isEmpty()) {
            startComboBox.removeAllItems();
            for(int i = 0; i<startAddress.size(); i++)
                startComboBox.addItem(startAddress.get(i));
        }
        if(!destAddress.isEmpty()) {
            destComboBox.removeAllItems();
            for(int i = 0; i<destAddress.size(); i++)
                destComboBox.addItem(destAddress.get(i));
        }
    }

    /**
     * This method updates the outputTextArea to display the estimated time, the estimated travel-distance
     * and the route instructions.
     * @param time
     * @param distance
     * @param instructions
     */
    public void updateOutput(String time, String distance, List<String> instructions)
    {
        outputTextArea.setText("");
        outputTextArea.setRows(instructions.size() + 3);
        outputTextArea.append("Estimated time is: " + time + "\n");
        outputTextArea.append("The total distance is: " + distance + " kilometers" + "\n" + "\n");
        Iterator it = instructions.iterator();
        while(it.hasNext()) {
            outputTextArea.append(it.next() + "\n");
        }
    }

    /**
     * This method displays error messages in the outputTextArea
     * @param message
     */
    public void errorMessage(String message)
    {
        outputTextArea.setText(message);
    }
}