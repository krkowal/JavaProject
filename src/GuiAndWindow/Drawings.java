package GuiAndWindow;


import javax.swing.JFrame;
import java.awt.*;


public class Drawings extends Canvas {
    public static void main(String[]args){
        JFrame frame = new JFrame("drawing");
        Canvas canvas = new Drawings();
        canvas.setSize(400,400);
        frame.add(canvas);

        frame.pack();
        frame.setVisible(true);

    }
    public void paint(Graphics g,int i,int j){
        g.fillOval(100,100,10,10);
    }
}
