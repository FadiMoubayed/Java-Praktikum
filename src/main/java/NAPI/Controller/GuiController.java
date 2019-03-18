package NAPI.Controller;

import NAPI.Model.RequestHandler;
import NAPI.View.GUIView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GuiController implements ActionListener {

    private JTextField startTextField;
    private JTextField destTextField;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private RequestHandler model;
    private GUIView view;

    public GuiController(JTextField startTextField, JTextField destTextField, JButton startCheckButton, JButton destCheckButton, RequestHandler model, GUIView view) {
        super();
        this.startTextField = startTextField;
        this.destTextField = destTextField;
        this.startCheckButton = startCheckButton;
        this.destCheckButton = destCheckButton;
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startCheckButton)
        {
            String startAddress = startTextField.getText();
            String destAddress = destTextField.getText() + "";
            view.updateCombos(model.calculateLocation(startAddress), destAddress);
        }
        else if(e.getSource() == this.destCheckButton)
        {
            String startAddress = startTextField.getText() + "";
            String destAddress = destTextField.getText();
            System.out.print(destAddress);
            view.updateCombos(startAddress, model.calculateLocation(destAddress));
        }
    }

}