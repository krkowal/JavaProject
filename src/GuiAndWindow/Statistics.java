package GuiAndWindow;

import WorldSettings.GeneratedMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Statistics extends JPanel implements ActionListener,KeyListener {
    private GeneratedMap map;
    private Window window;
    private JFrame frame;
    private Timer timer;
    private RenderStatistics renderStatistics;
    public Statistics(GeneratedMap map, Window window,boolean fileOut){
        this.map=map;
        this.window=window;
        frame = new  JFrame("Statystyki");
        timer = new Timer(2,this);
        frame.setPreferredSize(new Dimension(400, 210));
        frame.setMaximumSize(new Dimension(400, 210));
        frame.setMinimumSize(new Dimension(400, 210));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(false);
        renderStatistics = new RenderStatistics(map,window,fileOut);
        frame.add(renderStatistics);
        frame.addKeyListener(this);
        timer.start();

    }

    public void showStats(){
        frame.setVisible(true);
    }
    public void hideStats(){
        frame.setVisible(false);
    }


    @Override
    public void keyTyped(KeyEvent e) {
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
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void setTimerToSleep(){
        timer.stop();
    }
    public void startTimer(){
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==timer){
            renderStatistics.repaint();
        }
    }
}
