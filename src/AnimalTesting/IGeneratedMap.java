package AnimalTesting;

public interface IGeneratedMap {
    void placeAnimal(Animal animal);
    void placeGrass(Grass grass);
    boolean canMoveTo(Vector2d position);
    boolean compareAnimalPosition(Animal animal, Vector2d position);
    boolean compareGrassPosition(Grass grass, Vector2d position);
}
