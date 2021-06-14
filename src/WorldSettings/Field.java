package WorldSettings;

import AnimalSettings.Animal;
import WorldSettings.GeneratedMap;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Field {
    private GeneratedMap map;
    public LinkedList<Animal> animals = new LinkedList<>();
    private LinkedList<Animal> newAnimals = new LinkedList<>();
    public LinkedList<Animal> deadAnimals = new LinkedList<>();
    public int x,y;
    private boolean isJungle;
    public int deadAnimalsCount=0;
    public int daysAliveAmount=0;


    public int childrenCount(){
        int count=0;
        for(Animal animal:animals){
            count+=animal.childrenCount;
        }
        return count;
    }

    public boolean grass;
    public Field(GeneratedMap map,int x,int y,boolean isJungle){
        this.map=map;
        this.x=x;
        this.y=y;
        this.isJungle=isJungle;
    }

    public LinkedList<Animal> getAnimals() {
        return animals;
    }
    public LinkedList<Animal> getNewAnimals() {
        return newAnimals;
    }

    @Override
    public String toString() {
        return "Field{" +
                "animals=" + animals +
                ", newAnimals=" + newAnimals +
                '}';
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
            newAnimals.add(new Animal(map,animals.get(a).getPosition(),animals.get(a).getEnergy()/4+animals.get(b).getEnergy()/4,map.getEnergyDecreaseByDay(),animals.get(a).getGenes(),animals.get(b).getGenes()));
            animals.get(a).childrenCount++;
            animals.get(b).childrenCount++;
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
        if(!this.grass){
            g.setColor(Color.RED);
            g.fillRect(x*20,y*20,20,20);
        }
        if(this.isJungle){
            g.setColor(Color.YELLOW);
            g.fillRect(x*20,y*20,20,20);
        }
        if(!animals.isEmpty()){
            g.setColor(Color.BLUE);
            g.fillRect(20*x+5,20*y+5,10,10);
        }


    }
}
