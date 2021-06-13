package GuiAndWindow;

import AnimalTesting.Animal;
import AnimalTesting.GeneratedMap;
import AnimalTesting.Genes;
import AnimalTesting.Vector2d;

import javax.swing.*;
import java.awt.*;

public class ClickOnAnimalRender extends JPanel {
    private GeneratedMap map;
    private Window window;
    private Animal animal;
    public ClickOnAnimalRender(GeneratedMap map, Window window, Animal animal) {
        this.map = map;
        this.window = window;
        this.animal=animal;
    }
    public String animalStatus(){
        if(animal.getEnergy()>0){
            return "alive";
        }
        else return "dead";
    }
    public int daysAlive(){
        return animal.daysAlive;
    }
    public int childrenCount(){
        return animal.childrenCount;
    }
    public Vector2d animalPosition(){
        return animal.getPosition();
    }
    public Genes animalGenes (){
        return animal.getGenes();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawString("Animal's current status: "+animalStatus(),20,30);
        g.drawString("Current animal's position: "+animalPosition(),20,50);
        g.drawString("Animal's genes: "+animalGenes().printOut(),20,70);
        g.drawString("Animal's current energy: "+animal.getEnergy(),20,90);
        g.drawString("Current animal's children count: "+childrenCount(),20,110);
        g.drawString("Days alive: "+daysAlive(),20,130);
    }
}
