package GuiAndWindow;

import AnimalTesting.Animal;
import AnimalTesting.GeneratedMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickOnField implements ActionListener {
    private GeneratedMap map;
    private Window window;
    private JFrame frame;
    private Timer timer;
    private ClickOnAnimalRender render;

    public ClickOnField(GeneratedMap map, Window window, Animal animal) {
        this.map = map;
        this.window = window;
        frame = new JFrame("Dane zwierzęcia");
        timer =  new Timer(2,this);
        render = new ClickOnAnimalRender(map,window,animal);
        frame.setPreferredSize(new Dimension(380, 190));
        frame.setMaximumSize(new Dimension(380, 190));
        frame.setMinimumSize(new Dimension(380, 190));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.add(render);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            render.repaint();

        }
    }
}
