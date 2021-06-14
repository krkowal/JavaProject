package AnimalSettings;

public class AnimalUtil {
    public static int getAnimalEnergyAfterCopulation(int energy){
      return (int) Math.floor(energy * 3 /4);
    }
}
