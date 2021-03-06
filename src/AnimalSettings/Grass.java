package AnimalSettings;

import Interfaces.IGrassConfig;
import WorldSettings.GeneratedMap;
import WorldSettings.Vector2d;

public class Grass implements IGrassConfig {
    public Vector2d position;
    private GeneratedMap map;
    public Grass(GeneratedMap map, Vector2d position){
        this.position=position;
        this.map = map;
    }

    public Vector2d getPosition() {
        return position;
    }
}
