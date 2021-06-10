package AnimalTesting;


public class Engine {
    GeneratedMap map;
    public Engine(GeneratedMap map){
        this.map= map;
    }
    private boolean running = true;

//    while(running)
    public void start(){
        for(int i=0;i<100;i++){
            this.map.removeDeadAnimals();
            this.map.moveAnimals();
            this.map.animalsEatOnFields();
            this.map.animalsCopulateOnFields();
            this.map.addGrass();
            this.map.decreaseAllAnimalsEnergy();
        }
    }


    public void run(){
        running = true;
    }
    public void stop(){
        running = false;
    }
}
