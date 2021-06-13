package AnimalTesting;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GeneratedMap implements IGeneratedMap {
    private int width, height;
    private ConcurrentHashMap<Vector2d,Field> fields = new ConcurrentHashMap<>();
    private ArrayList<Grass> grasses = new ArrayList<>();
    private Random rand = new Random();
    private int howManyFirstAnimals,animalEnergy,energyDecreaseByDay,grassCount,grassIncreaseByDay;
    private Field tempField;

    public GeneratedMap(int width, int height,int howManyFirstAnimals,int animalEnergy, int energyDecreaseByDay, int grassCount,int grassIncreaseByDay) {
        this.width = width;
        this.height = height;
        this.howManyFirstAnimals=howManyFirstAnimals;
        this.animalEnergy=animalEnergy;
        this.energyDecreaseByDay=energyDecreaseByDay;
        this.grassCount=grassCount;
        this.grassIncreaseByDay=grassIncreaseByDay;
        generateFields();
        placeRandomAnimals();
        placeFirstGrasses();
    }
    public void addGrass(){
        for(int i =0;i<grassIncreaseByDay;i++) {
            placeGrass(new Grass(this,new Vector2d(rand.nextInt(this.width),rand.nextInt(this.height))));
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void placeFirstGrasses(){
        for(int i=0;i<grassCount;i++){
            placeGrass(new Grass(this,new Vector2d(rand.nextInt(this.width),rand.nextInt(this.height))));
        }
    }
    public void placeRandomAnimals(){
        for(int i=0;i<howManyFirstAnimals;i++){
            placeAnimal(new Animal(this,new Vector2d(rand.nextInt(width),rand.nextInt(height)),animalEnergy,energyDecreaseByDay));
        }
    }

    public int getAnimalEnergy() {
        return animalEnergy;
    }

    public int getEnergyDecreaseByDay() {
        return energyDecreaseByDay;
    }

    public void moveAnimals(){
        for(Field field: fields.values()){
            for(Animal animal : field.getAnimals()){
                animal.move();
                field.getNewAnimals().add(animal);
            }
        }
        clearAnimalArray();
        for(Field field: fields.values()){
            for(Animal animal:field.getNewAnimals()){
                fields.get(animal.getPosition()).getAnimals().add(animal);
            }
        }
        clearNewAnimalArray();
    }


    public void printField() {
        for(Field field : fields.values()){
            System.out.println(field);
        }
    }

    public void decreaseAllAnimalsEnergy(){
        for(Field field: fields.values()){
            for(Animal animal: field.getAnimals()){
                animal.decreaseEnergy();
                animal.incrementDaysAlive();
            }
        }
    }

    private void clearNewAnimalArray() {
        for(Field field: fields.values()){
            field.getNewAnimals().clear();
        }
    }

    private void clearAnimalArray() {
        for(Field field: fields.values()){
            field.getAnimals().clear();
        }
    }

    public void generateFields(){
        for(int i=0;i<=width;i++){
            for(int j = 0; j<=height;j++){
                if(j>15&&j<25&&i>15&&i<30){
                    this.fields.putIfAbsent(new Vector2d(i,j),new Field(this,i,j,true));
            }
                else{
                    this.fields.putIfAbsent(new Vector2d(i,j), new Field(this,i,j,false));
                }
        }
        }

    }
    public void removeDeadAnimals(){
        for(Field field:fields.values()){
            for(Animal animal :field.getAnimals()){
                if(animal.getEnergy()<=0){
                    field.daysAliveAmount+=animal.daysAlive;
                    field.deadAnimals.add(animal);}
            }
            field.deadAnimalsCount+=field.deadAnimals.size();
            for(Animal animal:field.deadAnimals){
                field.getAnimals().remove(animal);
            }
            field.deadAnimals.clear();
        }

    }
    public void animalsEatOnFields(){
        for(Field field: fields.values()){
            if(!field.getAnimals().isEmpty())
                field.eatGrass();
        }
    }
    public void animalsCopulateOnFields(){
        for(Field field:fields.values()){
            field.copulate();
        }
    }


    public void placeAnimal(Animal animal) {
        Animal animalPlaced = new Animal(this, animal.getPosition(),animalEnergy,energyDecreaseByDay);
        fields.get(animal.getPosition()).getAnimals().add(animalPlaced);
    }

    public void placeGrass(Grass grass) {
        fields.get(grass.getPosition()).setGrass(true);
    }

    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.y >= 0 && position.x <= width && position.y <= height;
    }

    public ConcurrentHashMap<Vector2d, Field> getFields() {
        return fields;
    }

    public void paint(Graphics g) {
    }

//    public Object objectAt(Vector2d position) {
//        for (Vector2d animal : fields.keys()) {
//            if (compareAnimalPosition(animal, position)) {
//                return animal;
//            }
//        }
//        for (Grass grass : grasses) {
//            if (compareGrassPosition(grass, position)) {
//                return grass;
//            }
//        }
//        return null;
//    }

//    public Animal animalAt(Vector2d position) {
//        for (Animal animal : fields) {
//            if (compareAnimalPosition(animal, position)) {
//                return animal;
//            }
//        }
//        return null;
//    }
//
//    public Grass grassAt(Vector2d position) {
//        for (Grass grass : grasses) {
//            if (compareGrassPosition(grass, position)) {
//                return grass;
//            }
//        }
//        return null;
//    }

    public boolean compareAnimalPosition(Animal animal, Vector2d position) {
        return animal.getPosition().x == position.x && animal.getPosition().y == position.y;
    }

    public boolean compareGrassPosition(Grass grass, Vector2d position) {
        return grass.getPosition().x == position.x && grass.getPosition().y == position.y;
    }

//    public void giveBirth(Animal animal1, Animal animal2) {
//        Animal animal = new Animal(this, animal1.getPosition(), animal1.getGenes(), animal2.getGenes());
//        fields.get(animal);
//        animal1.birthEnergyDecrease();
//        animal2.birthEnergyDecrease();
//    }

//    public void printEnergies() {
//        for (Animal el : fields) {
//            System.out.println(el.getEnergy());
//        }
//    }
//
//    public void printPositions() {
//        for (Animal el : fields) {
//            System.out.println(el.getPosition());
//        }
//    }

//    public boolean animalOnGrass(Vector2d position) {
//        return grassAt(position) != null;
//    }
//
//    public void removeGrass(Vector2d position) {
//        grasses.remove(grassAt(position));
//    }

}
