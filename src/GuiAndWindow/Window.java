package GuiAndWindow;

import AnimalSettings.Animal;
import WorldSettings.GeneratedMap;
import WorldSettings.Vector2d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends Thread implements ActionListener,KeyListener, MouseListener{
    public Timer timer;
    public RenderPanel renderPanel;
    private GeneratedMap map;
    private Statistics stats;
    private ClickOnField click;
    private Thread t1;
    private int days;
    public Window(int width, int height,  String title, GeneratedMap map,boolean fileOut) {
        JFrame frame = new  JFrame(title);
        stats = new Statistics(map,this,fileOut);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        timer = new Timer(10,this);
        t1 = new Thread(this);

        frame.addKeyListener(this);
        frame.addMouseListener(this);
        this.map=map;

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        renderPanel = new RenderPanel(map);
        frame.add(renderPanel);
        t1.start();
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
            days++;
        }
    }

    public int getDays() {
        return days;
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y =e.getY();
        System.out.println(new Vector2d((x-10)/20,(y-25)/20));
        if(!map.getFields().get(new Vector2d((x-10)/20,(y-25)/20)).getAnimals().isEmpty()){
            Animal selectedAnimal = map.getFields().get(new Vector2d((x-10)/20,(y-25)/20)).getAnimals().get(0);
            click = new ClickOnField(this.map,this,selectedAnimal);

        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void run(){
        timer.start();
    }
}
