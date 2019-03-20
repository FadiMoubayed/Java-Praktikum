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
    private JComboBox startComboBox;
    private JComboBox destComboBox;
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
     * @param startComboBox
     * @param destComboBox
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
                    List<String> output = new ArrayList<String>();
                    output.add(model.calculateLocation(startAddress));
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
                    List<String> output = new ArrayList<String>();
                    output.add(model.calculateLocation(destAddress));
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
                List<String> addresses = new ArrayList<String>();
                System.out.println(startComboBox.getSelectedItem());
                if(startComboBox.getSelectedItem() == "null")
                    addresses.add(startComboBox.getSelectedItem() + "");
                else
                {
                    addresses.add(startCheckLabel.getText() + "");
                    System.out.println(startCheckLabel.getText());
                }
                    if(destComboBox.getSelectedItem() == "null")
                    addresses.add(destComboBox.getSelectedItem() + "");
                else
                    addresses.add(destCheckLabel.getText() + "");
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