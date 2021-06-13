package GuiAndWindow;

import AnimalTesting.GeneratedMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window implements ActionListener,KeyListener {
    private static final long serialVersionUID = 8578644362229340505L;
    public Timer timer;
    public RenderPanel renderPanel;
    private GeneratedMap map;
    private Statistics stats;

    public Window(int width, int height, boolean hasJng, String title, GeneratedMap map) {
        JFrame frame = new  JFrame(title);
        stats = new Statistics(map,this);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        timer = new Timer(10,this);
        frame.addKeyListener(this);
        this.map=map;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
//        frame.add(engine);
        renderPanel = new RenderPanel(map);
        frame.add(renderPanel);
        startSimulation();


//        engine.start();


    }
    public void startSimulation(){
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer) {
            renderPanel.repaint();
            map.removeDeadAnimals();
            map.moveAnimals();
            map.animalsEatOnFields();
            map.animalsCopulateOnFields();
            map.addGrass();
            map.decreaseAllAnimalsEnergy();

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c =e.getKeyChar();
        System.out.println("nacisnieto przycisk "+c);
        if(c=='s'){
            timer.stop();
            stats.setTimerToSleep();
            System.out.println("uzyto stopu");
        }
        if(c=='r'){
            timer.start();
            stats.startTimer();
        }
        if(c=='g'){
            stats.showStats();
        }
        if(c=='h'){
            stats.hideStats();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
