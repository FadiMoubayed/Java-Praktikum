package NAPI.Controller;

import NAPI.Model.Model;
import NAPI.Model.Routing;
import NAPI.View.GUIView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class GuiController implements ActionListener {

    private JTextField startTextField;
    private JTextField destTextField;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private JLabel startCheckLabel;
    private JLabel destCheckLabel;
    private JButton calculateButton;
    private JTextArea outputTextArea;
    private Model model;

    private String vehicle;

    /**
     * This is the constructor.
     * It imports some components from the GuiView, creates a new instance of the model
     * and sets the default vehicle to "car"
     * @param startTextField
     * @param destTextField
     * @param startCheckButton
     * @param destCheckButton
     * @param calculateButton
     * @param outputTextArea
     * @param startCheckLabel
     * @param destCheckLabel
     */
    public GuiController(JTextField startTextField, JTextField destTextField, JButton startCheckButton, JButton destCheckButton,JButton calculateButton, JTextArea outputTextArea, JLabel startCheckLabel, JLabel destCheckLabel) {
        super();
        this.startTextField = startTextField;
        this.destTextField = destTextField;
        this.startCheckButton = startCheckButton;
        this.destCheckButton = destCheckButton;
        this.calculateButton = calculateButton;
        this.outputTextArea = outputTextArea;
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
                String destAddress = "";
                try {
                    String output = model.calculateLocation(startAddress);
                    this.updateLabels(output, destAddress);
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
                String startAddress = "";
                String destAddress = destTextField.getText() + "";
                try {
                    this.updateLabels(startAddress, model.calculateLocation(destAddress));
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
                List<String> addresses = new ArrayList<String>();
                addresses.add(startTextField.getText() + "");
                addresses.add(destTextField.getText() + "");
                Routing rt;
                try {
                    rt = model.calculateRoute(addresses, vehicle);
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
    public void updateLabels(String startAddress, String destAddress)
    {
        if(startAddress != "") {
            startCheckLabel.setText(startAddress);
        }
        if(destAddress != "") {
            destCheckLabel.setText(destAddress);
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
        outputTextArea.append("Estimated time is: " + time + " minutes" + "\n");
        outputTextArea.append("The total distance is: " + distance + " kilometers" + "\n" + "\n");
        for(int i = 0; i<instructions.size();i++) {
            outputTextArea.append(instructions.get(i) + "\n");
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