package WorldSettings;

import AnimalSettings.Animal;
import AnimalSettings.Grass;
import Interfaces.IGeneratedMap;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class GeneratedMap implements IGeneratedMap {
    private int width, height;
    private ConcurrentHashMap<Vector2d, Field> fields = new ConcurrentHashMap<>();
    private ArrayList<Grass> grasses = new ArrayList<>();
    private Random rand = new Random();
    private int howManyFirstAnimals,animalEnergy,energyDecreaseByDay,grassCount,grassIncreaseByDay;
    private int grassInJungle;
    private int grassOutsideJungle;
    private Field tempField;

    public GeneratedMap(int width, int height,int howManyFirstAnimals,int animalEnergy, int energyDecreaseByDay, int grassCount,int grassIncreaseByDay) {
        this.width = width;
        this.height = height;
        this.howManyFirstAnimals=howManyFirstAnimals;
        this.animalEnergy=animalEnergy;
        this.energyDecreaseByDay=energyDecreaseByDay;
        this.grassCount=grassCount;
        this.grassIncreaseByDay=grassIncreaseByDay;
        this.grassOutsideJungle= (int) (grassIncreaseByDay/1.5);
        this.grassInJungle=grassIncreaseByDay-grassOutsideJungle;

        generateFields();
        placeRandomAnimals();
        placeFirstGrasses();

    }
    public void addGrass(){
        for(int i =0;i<grassOutsideJungle;i++) {
            placeGrass(new Grass(this,new Vector2d(rand.nextInt(this.width),rand.nextInt(this.height))));
        }
        for(int i=0;i<grassInJungle;i++){
            placeGrass(new Grass(this,new Vector2d((rand.nextInt(this.width/4*3-this.width/4+1)+this.width/4),rand.nextInt(this.height/4*3-this.height/4+1)+this.height/4)));
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
        fields.values().forEach(field ->
                field.getAnimals().forEach(animal -> {
                    animal.move();
                    field.getNewAnimals().add(animal);
                }));
        clearAnimalArray();
        fields.values().forEach(field ->
                field.getNewAnimals().forEach(animal ->fields.get(animal.getPosition())
                        .getAnimals().add(animal)
                ));
        clearNewAnimalArray();
//        for(Field field: fields.values()){
//            for(Animal animal : field.getAnimals()){
//                animal.move();
//                field.getNewAnimals().add(animal);
//            }
//        }
//        clearAnimalArray();
//        for(Field field: fields.values()){
//            for(Animal animal:field.getNewAnimals()){
//                fields.get(animal.getPosition()).getAnimals().add(animal);
//            }
//        }
//        clearNewAnimalArray();
    }

    public void decreaseAllAnimalsEnergy(){
        fields.values().forEach(field ->
                field.getAnimals().forEach(animal -> {
                    animal.decreaseEnergy();
                    animal.incrementDaysAlive();
                }));
//        for(Field field: fields.values()){
//            for(Animal animal: field.getAnimals()){
//                animal.decreaseEnergy();
//                animal.incrementDaysAlive();
//            }
//        }
    }

    private void clearNewAnimalArray() {
        fields.values().forEach(field -> field.getNewAnimals().clear());
//        for(Field field: fields.values()){
//            field.getNewAnimals().clear();
//        }
    }

    private void clearAnimalArray() {
        fields.values().forEach(field -> field.getAnimals().clear());
//        for(Field field: fields.values()){
//            field.getAnimals().clear();
//        }
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
        fields.values().forEach(field ->{
            field.getAnimals().forEach(animal ->{
                if(animal.getEnergy() <= 0){
                    field.daysAliveAmount+=animal.daysAlive;
                    field.deadAnimals.add(animal);
                }
            });
            field.deadAnimalsCount += field.deadAnimals.size();
            field.deadAnimals.forEach(animal -> field.getAnimals().remove(animal));
            field.deadAnimals.clear();
        });
//        for(Field field:fields.values()){
//            for(Animal animal :field.getAnimals()){
//                if(animal.getEnergy()<=0){
//                    field.daysAliveAmount+=animal.daysAlive;
//                    field.deadAnimals.add(animal);}
//            }
//            field.deadAnimalsCount+=field.deadAnimals.size();
//            for(Animal animal:field.deadAnimals){
//                field.getAnimals().remove(animal);
//            }
//            field.deadAnimals.clear();
//        }

    }
    public void animalsEatOnFields(){
        fields.values().forEach(field -> {
            if(!field.getAnimals().isEmpty()) field.eatGrass();
        });
//        for(Field field: fields.values()){
//            if(!field.getAnimals().isEmpty())
//                field.eatGrass();
//        }
    }
    public void animalsCopulateOnFields(){
        fields.values().forEach(Field::copulate);
//        for(Field field:fields.values()){
//            field.copulate();
//        }
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


    public boolean compareAnimalPosition(Animal animal, Vector2d position) {
        return animal.getPosition().x == position.x && animal.getPosition().y == position.y;
    }

    public boolean compareGrassPosition(Grass grass, Vector2d position) {
        return grass.getPosition().x == position.x && grass.getPosition().y == position.y;
    }

}
