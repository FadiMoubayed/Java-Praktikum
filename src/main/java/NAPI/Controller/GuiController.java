package NAPI.Controller;

import NAPI.Model.RequestHandler;
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
    private RequestHandler model;
    private GUIView view;

    private String vehicle;

    public GuiController(JTextField startTextField, JTextField destTextField, JButton startCheckButton, JButton destCheckButton,JButton calculateButton, RequestHandler model, GUIView view) {
        super();
        this.startTextField = startTextField;
        this.destTextField = destTextField;
        this.startCheckButton = startCheckButton;
        this.destCheckButton = destCheckButton;
        this.calculateButton = calculateButton;
        this.model = model;
        this.view = view;

        vehicle = "car";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startCheckButton)
        {
            String startAddress = startTextField.getText() + "";
            String destAddress = "";
            try {
                view.updateLabels(model.calculateLocation(startAddress), destAddress);
            } catch (IllegalArgumentException ex)
            {
                view.errorMessage("error while checking start address: \n" + ex.getMessage());
                startTextField.setText("");
            }
        }
        else if(e.getSource() == this.destCheckButton)
        {
            String startAddress = "";
            String destAddress = destTextField.getText() + "";
            try {
                view.updateLabels(startAddress, model.calculateLocation(destAddress));
            } catch (IllegalArgumentException ex)
            {
                view.errorMessage("error while checking destination address: \n" + ex.getMessage());
                destTextField.setText("");
            }
        }
        else if(e.getSource() == this.calculateButton)
        {
            List<String> addresses = new ArrayList<String>();
            addresses.add(startTextField.getText() + "");
            addresses.add(destTextField.getText() + "");
            Routing rt;
            try {
                rt  = model.calculateRoute(addresses, vehicle);
                view.updateOutput(rt.getTime(),rt.getDistance(),rt.getRoute());
            } catch (IllegalArgumentException ex)
            {
                view.errorMessage("error while calculating: \n" + ex.getMessage());
            }

        }
        else
        {
            vehicle = e.getActionCommand();
        }
    }

}