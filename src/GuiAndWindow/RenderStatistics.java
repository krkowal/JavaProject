package GuiAndWindow;
import AnimalTesting.Animal;
import AnimalTesting.Field;
import AnimalTesting.GeneratedMap;

import javax.swing.*;
import java.awt.*;

public class RenderStatistics extends JPanel {
    GeneratedMap map;
    Window window;
    public RenderStatistics (GeneratedMap map, Window window){
        this.map=map;
        this.window=window;
    }
    public int countAnimals(){
        int count=0;
        for(Field field:map.getFields().values()){
            count +=field.getAnimals().size();
        }
        return count;
    }
    public int countGrasses(){
        int count=0;
        for(Field field:map.getFields().values()){
            if(field.grass) count++;
        }
        return count;
    }
    public double averageEnergy(){
        int energyAmount =0;
        for(Field field:map.getFields().values()){
            for(Animal animal:field.animals){
                energyAmount+=animal.getEnergy();
            }
        }
        return Math.round((float)energyAmount/countAnimals()*100.0)/100.0;
    }
    public double averageDaysAlive(){
        int deadAnimalsAmount=0;
        int deadAnimalsDaysAliveCount=0;
        for(Field field:map.getFields().values()){
            deadAnimalsAmount+=field.deadAnimalsCount;
            deadAnimalsDaysAliveCount+=field.daysAliveAmount;
        }
        return Math.round((float)deadAnimalsDaysAliveCount/deadAnimalsAmount*100.0)/100.0;
    }
    public double averageChildrenCount(){
        int count=0;
        for(Field field:map.getFields().values()){
            count+=field.childrenCount();
        }
        return Math.round((float)count/countAnimals()*100.0)/100.0;
    }

    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawString("Animals' count = "+countAnimals(),20,30);
        g.drawString("Grass' count = "+countGrasses(),20,50);
        g.drawString("Average energy level = "+averageEnergy(),20,70);
        g.drawString("Average lifespan of dead animals = "+averageDaysAlive(),20,90);
        g.drawString("Average children count = "+averageChildrenCount(),20,110);


    }
}
