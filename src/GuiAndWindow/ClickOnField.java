package GuiAndWindow;

import AnimalTesting.Animal;
import AnimalTesting.GeneratedMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ClickOnField implements ActionListener, KeyListener {
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
        frame.addKeyListener(this);
        timer.start();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            render.repaint();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if(c=='h')
            frame.setVisible(false);
        if(c=='r') {
            window.timer.start();
            timer.start();
        }
        if(c=='s') {
            window.timer.stop();
            timer.stop();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
