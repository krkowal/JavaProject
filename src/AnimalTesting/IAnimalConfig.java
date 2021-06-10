package AnimalTesting;

public interface IAnimalConfig {
    int energy = 10;
    void move();
    Vector2d getPosition();
    void decreaseEnergy();
    void birthEnergyDecrease();
    void eat(int divided);

    Genes getGenes();
}
