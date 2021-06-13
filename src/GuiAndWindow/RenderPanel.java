package GuiAndWindow;

import AnimalTesting.Field;
import AnimalTesting.GeneratedMap;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    private GeneratedMap map;
    public RenderPanel(GeneratedMap map){
        this.map=map;
    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(149,171,99));
        g.fillRect(0,0,map.getWidth()*20,map.getHeight()*20);

        g.setColor(new Color(88, 143, 39));
        g.fillRect(map.getWidth()/4,map.getHeight()/4, map.getWidth()/2,map.getHeight()/2 );

        for(Field field: map.getFields().values()){
            if(field.grass){
                g.setColor(new Color(51, 105, 30));
                g.fillRect(field.x*20+2,field.y*20+2,16,16);
            }
        }
        for(Field field:map.getFields().values()){
            if(!field.getAnimals().isEmpty()){
                g.setColor(new Color(244,67,54));
                g.fillRect(field.x*20+5,field.y*20+5,10,10);
            }
        }
    }

}
