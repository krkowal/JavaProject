package AnimalTesting;

import GuiAndWindow.Engine;
import GuiAndWindow.GuiSwing;

import java.awt.*;

public class World {
    public static void main(String[] args) {
        GeneratedMap map = new GeneratedMap(10, 10,80,30,2,10,5);
        map.placeAnimal(new Animal(map, new Vector2d(2, 2),30,2));
//        Engine engine = new Engine(map);
//        engine.start();
//        engine.start();
//        map.removeDeadAnimals();
        map.moveAnimals();
//        map.animalsEatOnFields();
//        map.animalsCopulateOnFields();
//        map.addGrass();
//        map.decreaseAllAnimalsEnergy();
//        new GuiSwing();
//        GuiAndWindow.Engine engine = new Engine(800,600,true,15,15,500,500,15);
//        GeneratedMap map = new GeneratedMap(20,15,500,312,32,200,3);
//        map.placeAnimal(new Animal(map,new Vector2d(2,2),2,2));
//        engine.render();
//        new Engine(800,600,false,10,2,100,10,0);
        System.out.println(1);
    }
}
