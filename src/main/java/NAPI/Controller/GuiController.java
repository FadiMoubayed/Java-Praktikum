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
    private JButton calculateButton;
    private Model model;
    private GUIView view;

    private String vehicle;

    public GuiController(JTextField startTextField, JTextField destTextField, JButton startCheckButton, JButton destCheckButton,JButton calculateButton, GUIView view) {
        super();
        this.startTextField = startTextField;
        this.destTextField = destTextField;
        this.startCheckButton = startCheckButton;
        this.destCheckButton = destCheckButton;
        this.calculateButton = calculateButton;
        this.model = new Model();
        this.view = view;

        vehicle = "car";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startCheckButton)
        {
            if(startTextField.getText().isEmpty())
            {
                view.errorMessage("error while checking start address: \n" + "please put in a starting address");
            }
            else {
                String startAddress = startTextField.getText() + "";
                String destAddress = "";
                try {
                    view.updateLabels(model.calculateLocation(startAddress), destAddress);
                    view.errorMessage("");
                } catch (IllegalArgumentException ex) {
                    view.errorMessage("error while checking start address: \n" + ex.getMessage());
                } catch (Exception ex)
                {
                    view.errorMessage("error while checking start address: \n" + "Please try a different address \n \n" + "type of error: \n" + ex.toString());
                }
            }
        }
        else if(e.getSource() == this.destCheckButton)
        {
            if(destTextField.getText().isEmpty())
            {
                view.errorMessage("error while checking destination address: \n" + "please put in a destination address\n ");
            }
            else {
                String startAddress = "";
                String destAddress = destTextField.getText() + "";
                try {
                    view.updateLabels(startAddress, model.calculateLocation(destAddress));
                    view.errorMessage("");
                } catch (IllegalArgumentException ex) {
                    view.errorMessage("error while checking destination address: \n" + ex.getMessage());
                } catch (Exception ex)
                {
                    view.errorMessage("error while checking destination address: \n" + "Please try a different address \n \n" + "type of error: \n" + ex.toString());
                }
            }
        }
        else if(e.getSource() == this.calculateButton)
        {
            if(startTextField.getText().isEmpty())
            {
                view.errorMessage("error while calculating: \n" + "please put in a starting address");
            }
            else if(destTextField.getText().isEmpty())
            {
                view.errorMessage("error while calculating: \n" + "please put in a destination address");
            }
            else {
                List<String> addresses = new ArrayList<String>();
                addresses.add(startTextField.getText() + "");
                addresses.add(destTextField.getText() + "");
                Routing rt;
                try {
                    rt = model.calculateRoute(addresses, vehicle);
                    view.updateOutput(rt.getTime(), rt.getDistance(), rt.getRoute());
                } catch (IllegalArgumentException ex) {
                    view.errorMessage("error while calculating: \n" + ex.getMessage());
                } catch (Exception ex)
                {
                    view.errorMessage("error while calculating: \n" + "Please try different addresses \n \n" + "type of error: \n" + ex.toString());
                }
            }
        }
        else
        {
            vehicle = e.getActionCommand();
        }
    }

}