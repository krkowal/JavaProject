package GuiAndWindow;

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas{
    private static final long serialVersionUID = 8578644362229340505L;

    public Window(int width, int height, boolean hasJng, String title, Engine engine) {
        JFrame frame = new  JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.add(engine);
        frame.setVisible(true);

        engine.start();


    }

}
