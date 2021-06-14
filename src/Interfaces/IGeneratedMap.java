package Interfaces;

import AnimalSettings.Animal;
import AnimalSettings.Grass;
import WorldSettings.Vector2d;

public interface IGeneratedMap {
    void placeAnimal(Animal animal);
    void placeGrass(Grass grass);
    boolean canMoveTo(Vector2d position);
}
