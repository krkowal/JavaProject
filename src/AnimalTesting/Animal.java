package AnimalTesting;

import java.util.Objects;

public class Animal  implements IAnimalConfig {
    private Vector2d position;
    private MapDirection animalDirection= MapDirection.NORTH;
    private int energy,energyDecreaseByDay;
    private GeneratedMap map;
    private Genes genes;
    public int daysAlive=0;
    public int childrenCount=0;


    public int getEnergy() {
        return energy;
    }

    public Animal(GeneratedMap map, Vector2d position,int energy,int energyDecreaseByDay) {
        this.map = map;
        this.position = position;
        this.energy=energy;
        this.energyDecreaseByDay=energyDecreaseByDay;
        genes = new Genes();
        this.genes.generateFirstGenes();
    }

    public Animal(GeneratedMap map, Vector2d position,int energy,int energyDecreaseByDay, Genes genes1, Genes genes2){
        this.map=map;
        this.position=position;
        this.energy=energy;
        this.energyDecreaseByDay=energyDecreaseByDay;
        genes = new Genes(genes1,genes2);
    }

    @Override
    public Genes getGenes() {
        return genes;
    }
    public Vector2d getPosition() {
        return position;
    }

    public void move(){
        setTurn();
        if(map.canMoveTo(addDirection())){
            this.position = addDirection();
        }


    }
    private void setTurn(){
        for(int i=this.getGenes().getTurn();i>0;i--){
            this.animalDirection=animalDirection.next();
        }

    }
    public void incrementDaysAlive(){
        this.daysAlive++;
    }
    private Vector2d addDirection() {
        return position.addVectors(Objects.requireNonNull(animalDirection.toUnitVector()));
    }
    public void decreaseEnergy(){
        this.energy=this.energy-energyDecreaseByDay;
    }
    public void birthEnergyDecrease(){
        this.energy= AnimalUtil.getAnimalEnergyAfterCopulation(this.energy);
    }
    public void eat(int divided) {
        this.energy+=10/divided;
    }



}
