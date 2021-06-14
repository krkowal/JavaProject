package GuiAndWindow;

import WorldSettings.GeneratedMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Objects;

public class GuiSwing implements ActionListener, ItemListener {
    boolean doubleWorld=false;
    boolean fileOut = false;
    private final JFrame frame;
    private JComboBox resolution;
    private int guiWidth=800,guiHeight=600;
    private JButton button,button2;
    private JLabel startingGrassCountLabel,grassIncreaseByDayLabel,energyDecreaseByDayLabel,animalsEnergyLabel,startingAnimalsCountLabel;
    private JLabel startingGrassCountError,grassIncreaseByDayError,energyDecreaseByDayError,animalsEnergyError,startingAnimalsCountError;
    private JTextField startingGrassCount,grassIncreaseByDay,energyDecreaseByDay,animalsEnergy,startingAnimalsCount;
    private JCheckBox doubleWorldCheckBox, fileWriteOut;

    String [] resolutions = {"800x600","1024x768","1280x720","1366x768","1600x900","1920x1080"};

    public GuiSwing(){
        frame = new JFrame ();
        button = new JButton("start");
        button2 = new JButton("close");
        button.addActionListener(this);
        button.setActionCommand("Start");
        button2.addActionListener(this);
        button2.setActionCommand("Close");
        JLabel widthText = new JLabel("enter the resolution");
        resolution = new JComboBox(resolutions);
        resolution.setSelectedIndex(0);
        resolution.addActionListener(this);

        startingAnimalsCount=new JTextField();
        startingAnimalsCount.addActionListener(this);
        startingGrassCount=new JTextField();
        startingGrassCount.addActionListener(this);
        grassIncreaseByDay=new JTextField();
        grassIncreaseByDay.addActionListener(this);
        energyDecreaseByDay = new JTextField();
        energyDecreaseByDay.addActionListener(this);
        animalsEnergy=new JTextField();
        animalsEnergy.addActionListener(this);


        JLabel label = new JLabel("Click start to start the game");
        JLabel animalsHeader = new JLabel("Animals");
        Font font = new Font("Courier", Font.BOLD, 15);
        animalsHeader.setFont(font);
        JLabel worldSettingsHeader = new JLabel("WorldSettings");
        worldSettingsHeader.setFont(font);
        startingGrassCountLabel = new JLabel("How many starting grasses");
        grassIncreaseByDayLabel = new JLabel("How many new grasses per day");
        energyDecreaseByDayLabel = new JLabel("How much energy decrease per day");
        animalsEnergyLabel = new JLabel("How much starting animal energy");
        startingAnimalsCountLabel = new JLabel("How many starting Animals ");

        startingGrassCountError= new JLabel("Wrong grass count");
        grassIncreaseByDayError = new JLabel("Wrong grass increase count");
        energyDecreaseByDayError = new JLabel("Wrong energy descrease count");
        animalsEnergyError = new JLabel("Wrong animals' energy count");
        startingAnimalsCountError = new JLabel("wrong starting animals' count");

        doubleWorldCheckBox = new JCheckBox("Double worlds");
        doubleWorldCheckBox.addItemListener(this);
        fileWriteOut = new JCheckBox("Write stats out to file");
        fileWriteOut.addItemListener(this);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,2,10,5));
        panel.add(animalsHeader);
        panel.add(new JLabel());
        panel.add(animalsEnergyLabel);
        panel.add(animalsEnergy);
        panel.add(new JLabel());
        panel.add(animalsEnergyError);
        panel.add(energyDecreaseByDayLabel);
        panel.add(energyDecreaseByDay);
        panel.add(new JLabel());
        panel.add(energyDecreaseByDayError);
        panel.add(worldSettingsHeader);
        panel.add(new JLabel());
        panel.add(startingAnimalsCountLabel);
        panel.add(startingAnimalsCount);
        panel.add(new JLabel());
        panel.add(startingAnimalsCountError);
        panel.add(startingGrassCountLabel);
        panel.add(startingGrassCount);
        panel.add(new JLabel());
        panel.add(startingGrassCountError);
        panel.add(grassIncreaseByDayLabel);
        panel.add(grassIncreaseByDay);
        panel.add(new JLabel());
        panel.add(grassIncreaseByDayError);
        panel.add(button);
        panel.add(button2);
        panel.add(widthText);
        panel.add(resolution);
        panel.add(doubleWorldCheckBox);
        panel.add(fileWriteOut);
        animalsEnergyError.setVisible(false);
        energyDecreaseByDayError.setVisible(false);
        startingAnimalsCountError.setVisible(false);
        startingGrassCountError.setVisible(false);
        grassIncreaseByDayError.setVisible(false);
        frame.add(panel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Pierwsze GUI");
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == resolution) {
            String gameResolution = (String) resolution.getSelectedItem();
            switch (Objects.requireNonNull(gameResolution)) {
                case "800x600" -> {
                    guiWidth = 800;
                    guiHeight = 600;
                }
                case "1024x768" -> {
                    guiWidth = 1024;
                    guiHeight = 768;
                }
                case "1280x720" -> {
                    guiWidth = 1280;
                    guiHeight = 720;
                }
                case "1366x768" -> {
                    guiWidth = 1366;
                    guiHeight = 768;
                }
                case "1600x900" -> {
                    guiWidth = 1600;
                    guiHeight = 900;
                }
                case "1920x1080" -> {
                    guiWidth = 1920;
                    guiHeight = 1080;
                }
                default -> System.out.println("something went wrong");
            }
        }
        if (e.getSource() == button) {
            animalsEnergyError.setVisible(false);
            energyDecreaseByDayError.setVisible(false);
            startingAnimalsCountError.setVisible(false);
            startingGrassCountError.setVisible(false);
            grassIncreaseByDayError.setVisible(false);
            try {
                int guiAnimalEnergy = Integer.parseInt(animalsEnergy.getText());
                if(guiAnimalEnergy<1){
                    animalsEnergyError.setVisible(true);
                }
                int guiEnergyDecreaseByDay = Integer.parseInt(energyDecreaseByDay.getText());
                if(guiEnergyDecreaseByDay<0){
                    energyDecreaseByDayError.setVisible(true);
                }
                int guiStartingAnimalsCount = Integer.parseInt(startingAnimalsCount.getText());
                if(guiStartingAnimalsCount<2){
                    startingAnimalsCountError.setVisible(true);
                }
                int guiStartingGrassCount = Integer.parseInt(startingGrassCount.getText());
                if(guiStartingGrassCount<0){
                    startingGrassCountError.setVisible(true);
                }
                int guiGrassIncreaseByDay = Integer.parseInt(grassIncreaseByDay.getText());
                if(guiGrassIncreaseByDay<0){
                    grassIncreaseByDayError.setVisible(true);
                }
                if (guiStartingGrassCount >= 0 && guiStartingAnimalsCount >= 2 && guiAnimalEnergy > 0 && guiEnergyDecreaseByDay >= 0 && guiGrassIncreaseByDay >= 0) {

                    new Window(guiWidth+15,guiHeight+38,"gra",new GeneratedMap(guiWidth/20,guiHeight/20,guiStartingAnimalsCount,guiAnimalEnergy,guiEnergyDecreaseByDay,guiStartingGrassCount,guiGrassIncreaseByDay),fileOut);
                    if(doubleWorldCheckBox.isSelected())
                        new Window(guiWidth+15,guiHeight+38,"gra",new GeneratedMap(guiWidth/20,guiHeight/20,guiStartingAnimalsCount,guiAnimalEnergy,guiEnergyDecreaseByDay,guiStartingGrassCount,guiGrassIncreaseByDay),false);
                    frame.dispose();
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            if (e.getSource() == button2) {
                frame.dispose();
            }

        }
    }




    @Override
    public void itemStateChanged(ItemEvent e) {

        if(e.getSource()==doubleWorldCheckBox)doubleWorld= e.getStateChange() == ItemEvent.SELECTED;
        if(e.getSource()==fileWriteOut) fileOut=e.getStateChange()==ItemEvent.SELECTED;
    }
}
