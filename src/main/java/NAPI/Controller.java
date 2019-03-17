package NAPI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Controller implements ActionListener {

    private JTextField startTextField;
    private JButton startCheckButton;
    private RequestHandler model;
    private GUIView view;

    public Controller(JTextField startTextField, JButton startCheckButton, RequestHandler model, GUIView view) {
        super();
        this.startTextField = startTextField;
        this.startCheckButton = startCheckButton;
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.startCheckButton)
        {
            String inputAddress = startTextField.getText();
            System.out.print(model.calculateLocation(inputAddress));
            //view.updateCombos(model.calculateLocation(inputAddress));
        }
    }

}