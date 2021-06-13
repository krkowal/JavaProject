package GuiAndWindow;
import AnimalTesting.Animal;
import AnimalTesting.Field;
import AnimalTesting.GeneratedMap;
import AnimalTesting.Genes;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RenderStatistics extends JPanel {
    GeneratedMap map;
    Window window;
    private boolean fileOut;
    private Map<Genes,Integer> genesCount =  new HashMap<>();
    private File w1;
    private FileWriter fw ;
    private BufferedWriter bw;
    public RenderStatistics (GeneratedMap map, Window window,boolean fileOut){
        this.map=map;
        this.window=window;
        this.fileOut=fileOut;
        if(fileOut) createNewFile();
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
    public Genes getDominantGenes(){
        for(Field field:map.getFields().values()){
            for(Animal animal : field.animals){
                genesCount.putIfAbsent(animal.getGenes(),1);
                genesCount.computeIfPresent(animal.getGenes(),(key,value)->value++);
            }
        }
        int index=0;
        Genes g = null;
        for(Genes genes: genesCount.keySet()){
            if(genesCount.get(genes)>index){
                g=genes;
            }
        }
        return g;
    }


    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        g.drawString("Day: "+window.getDays(),20,30);
        g.drawString("Animals' count = "+countAnimals(),20,50);
        g.drawString("Grass' count = "+countGrasses(),20,70);
        g.drawString("Average energy level = "+averageEnergy(),20,90);
        g.drawString("Average lifespan of dead animals = "+averageDaysAlive(),20,110);
        g.drawString("Average children count = "+averageChildrenCount(),20,130);
        g.drawString("Dominant genotype "+getDominantGenes().printOut(),20,150);
        printOut();
    }

    private void createNewFile()  {
        try{
             w1 = new File("Stats.txt");
             if(w1.createNewFile()){
                 System.out.println("stworzony plik tekstowy");
             }
             else System.out.println("nie udalo sie stworzyc pliku");
        } catch( IOException e){
            e.printStackTrace();
        }
    }
    private void printOut(){
        if(fileOut){
            if(w1.exists()){
                try {
                    fw = new FileWriter(w1);
                    bw = new BufferedWriter(fw);
                    bw.write(window.getDays()+","+countAnimals()+","+countGrasses()+","+averageEnergy()+","+averageDaysAlive()+","+averageChildrenCount()+","+getDominantGenes().printOut());
                    bw.newLine();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
