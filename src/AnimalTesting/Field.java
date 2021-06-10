package AnimalTesting;

import javax.imageio.plugins.tiff.ExifGPSTagSet;
import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Field {
    private GeneratedMap map;
    private LinkedList<Animal> animals = new LinkedList<>();
    private LinkedList<Animal> newAnimals = new LinkedList<>();
    private int x,y;

    public LinkedList<Animal> getNewAnimals() {
        return newAnimals;
    }

    private boolean grass;
    public Field(GeneratedMap map,int x,int y){
        this.map=map;
        this.x=x;
        this.y=y;
    }

    public LinkedList<Animal> getAnimals() {
        return animals;
    }

    private boolean canCopulate(){
        return animals.size()>1;
    }

    public void setGrass(boolean grass) {
        this.grass = grass;
    }
    public void eatGrass(){
        if(this.grass){
            int maxEnergy = chooseStrongestAnimal();
            int multiple = howManyWithTheHighestEnergy(maxEnergy);
            eat(maxEnergy,multiple);
            this.grass=false;
        }
    }

    private int howManyWithTheHighestEnergy(int maxEnergy) {
        int multiple = 0;
        for(Animal animal: animals){
            if(animal.getEnergy()== maxEnergy){
                multiple++;
            }
        }
        return multiple;
    }
    private void eat(int maxEnergy, int multiple){
        for(Animal animal: animals){
            if(animal.getEnergy()==maxEnergy){
                animal.eat(multiple);
            }
        }
    }
    private int chooseStrongestAnimal() {
        int max = -1;
        for(Animal animal : animals){
            if(animal.getEnergy()>max){
                max= animal.getEnergy();

            }
        }
        return max;

    }
    public void copulate() {
        if (canCopulate()) {
            int a = 0;
            int b = 1;
            if (animals.get(a).getEnergy() < animals.get(b).getEnergy()) {
                a = 1;
                b = 0;
            }
            for (int i = 2; i < animals.size(); i++) {
                if (animals.get(i).getEnergy() > animals.get(b).getEnergy()) {
                    b = i;
                    if (animals.get(i).getEnergy() > animals.get(a).getEnergy()) {
                        int tmp = a;
                        a = b;
                        b = tmp;
                    }
                }
            }
            int multiple =howManyWithTheHighestEnergy(animals.get(a).getEnergy());
            if(animals.get(a).getEnergy()==animals.get(b).getEnergy()&&multiple>2){

                int aCount = chooseStrongestAnimalsToCopulate(multiple);
                int bCount = chooseStrongestAnimalsToCopulate(multiple);
                while(aCount==bCount){
                    bCount=chooseStrongestAnimalsToCopulate(multiple);
                }
                for(Animal animal:animals){
                    int i=0;
                    if(animal.getEnergy()==animals.get(a).getEnergy()){ //LOSOWANIE ZWIERZĄT DO KOPULACJI
                        if(aCount==1){
                            a=i;
                        }
                        if(bCount==1){
                            b=i;
                        }
                    }
                    aCount--;
                    bCount--;
                    i++;
                }
            }
            animals.get(a).birthEnergyDecrease();
            animals.get(b).birthEnergyDecrease();
            newAnimals.add(new Animal(map,animals.get(a).getPosition(),map.getAnimalEnergy(),map.getEnergyDecreaseByDay(),animals.get(a).getGenes(),animals.get(b).getGenes()));
        }
    }
    private int chooseStrongestAnimalsToCopulate(int bound){
        Random rand = new Random();
        return rand.nextInt(bound)+1;
    }

    public void render(Graphics g){
        if(this.grass){
            g.setColor(Color.GREEN);
            g.fillRect(x*20,y*20,20,20);
        }
        if(animals.isEmpty()){
            g.setColor(Color.BLUE);
            g.fillRect(20*x+5,20*y+5,10,10);
        }
        if(!this.grass){
            g.setColor(Color.RED);
            g.fillRect(x*20,y*20,20,20);
        }
    }
}
