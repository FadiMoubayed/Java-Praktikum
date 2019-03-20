package NAPI.View;

import NAPI.Controller.GuiController;

import javax.swing.*;
import java.awt.*;

import java.util.List;

public class GUIView{
    private JTextField startTextField;
    private JTextField destTextField;
    private JButton startCheckButton;
    private JButton destCheckButton;
    private JPanel GUISwing;
    private JLabel ueberschrift;
    private JLabel startLabel;
    private JLabel destLabel;
    private JLabel startCheckLabel;
    private JLabel destCheckLabel;
    private JTextArea outputTextArea;
    private JButton calculateButton;
    private JLabel vehicleLabel;
    private JRadioButton carRadio;
    private JRadioButton footRadio;
    private JRadioButton bikeRadio;
    private ButtonGroup vehicleRadio;
    private JLabel outputLabel;
    private JFrame frame;


public GUIView()
{
    this.draw();
}

    /**
     * This method creates the Gui with all its components using GridBagLayout
     */
    public void draw() {

        // Create views swing UI components
        startLabel = new JLabel("start address: ");
        startTextField = new JTextField(26);
        startCheckButton = new JButton("check");
        startCheckLabel = new JLabel();


        destLabel = new JLabel("dest address: ");
        destTextField = new JTextField();
        destCheckButton = new JButton("check");
        destCheckLabel = new JLabel();

        vehicleLabel = new JLabel("vehicle type: ");
        carRadio = new JRadioButton("car", true);
        footRadio = new JRadioButton("foot");
        bikeRadio = new JRadioButton("bike");
        vehicleRadio = new ButtonGroup();
        vehicleRadio.add(carRadio);
        vehicleRadio.add(bikeRadio);
        vehicleRadio.add(footRadio);

        calculateButton = new JButton("calculate");
        outputLabel = new JLabel("output:");
        outputTextArea = new JTextArea();
        JScrollPane outputPane = new JScrollPane(outputTextArea);

        // Create guiController
        GuiController guiController = new GuiController(startTextField, destTextField, startCheckButton, destCheckButton, calculateButton, outputTextArea, startCheckLabel, destCheckLabel);
        startCheckButton.addActionListener(guiController);
        destCheckButton.addActionListener(guiController);
        calculateButton.addActionListener(guiController);
        carRadio.setActionCommand("car");
        carRadio.addActionListener(guiController);
        footRadio.setActionCommand("foot");
        footRadio.addActionListener(guiController);
        bikeRadio.setActionCommand("bike");
        bikeRadio.addActionListener(guiController);

        // Set the view layout
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel ctrlPane = new JPanel();
        ctrlPane.setLayout(new GridBagLayout());
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        ctrlPane.add(startLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 0;
        ctrlPane.add(startTextField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridx = 4;
        gbc.gridy = 0;
        ctrlPane.add(startCheckButton, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 100;
        gbc.gridx = 5;
        gbc.gridy = 0;
        ctrlPane.add(startCheckLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 0;
        gbc.gridx = 0;
        gbc.gridy = 1;
        ctrlPane.add(destLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 3;
        gbc.gridx = 1;
        gbc.gridy = 1;
        ctrlPane.add(destTextField,gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.ipady = 0;
        gbc.gridx = 4;
        gbc.gridy = 1;
        ctrlPane.add(destCheckButton, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 5;
        gbc.gridy = 1;
        ctrlPane.add(destCheckLabel, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 2;
        ctrlPane.add(vehicleLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 1;
        gbc.gridy = 2;
        ctrlPane.add(carRadio, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 2;
        gbc.gridy = 2;
        ctrlPane.add(footRadio, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 3;
        gbc.gridy = 2;
        ctrlPane.add(bikeRadio, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 15;
        gbc.gridwidth = 4;
        gbc.gridx = 1;
        gbc.gridy = 3;
        ctrlPane.add(calculateButton, gbc);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.PAGE_START;
        gbc.ipady = 0;
        gbc.gridwidth = 0;
        gbc.gridx = 0;
        gbc.gridy = 4;
        ctrlPane.add(outputLabel, gbc);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 5;
        gbc.ipady = 300;
        gbc.gridx = 1;
        gbc.gridy = 4;
        ctrlPane.add(outputPane, gbc);

        ImageIcon img = new ImageIcon("C:\\Users\\paula\\Java-Praktikum\\earth.png");
        JFrame frame = new JFrame("NAPI");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(ctrlPane);
        frame.pack();
        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
