package AnimalTesting;

import java.util.ArrayList;
import java.util.Random;

public class NewbornGenes {
    private ArrayList <Integer> genes = new ArrayList<>();
    private Random rand = new Random();
    public NewbornGenes(Genes parent1, Genes parent2) {
        super();
        inheritedGenes(parent1.getGenes(),parent2.getGenes());
    }


    private void inheritedGenes(ArrayList<Integer> genes1, ArrayList<Integer> genes2){
        int dominant = chooseDominantGenes();
        switch(dominant){
            case 0: generateGenes(genes1,genes2);
            case 1: generateGenes(genes2,genes1);
        }

    }
    private int chooseDominantGenes(){
        return rand.nextInt(2);
    }
    private void generateGenes(ArrayList<Integer> genes1, ArrayList<Integer> genes2){
        int a,b;
        a = rand.nextInt(32);
        b = rand.nextInt(32);
        while(a==b){
            a= rand.nextInt(32);
        }

        for(int i = 0;i<Math.min(a,b);i++){
            this.genes.add(genes1.get(i));
        }
        for(int i = Math.min(a,b);i<Math.max(a,b);i++){
            this.genes.add(genes2.get(i));
        }
        for(int i = Math.max(a,b);i<32;i++){
            this.genes.add(genes2.get(i));
        }
        checkGenotype();
    }


    private void checkGenotype(){
        boolean contains = false;
        while(!contains){
            for(int i =0; i<7;i++){
                addNonexistentGene(i);
            }
            contains=true;
            for(int i=0;i<7;i++){
                if(!this.genes.contains(i)) {
                    contains = false;
                    break;
                }
            }

        }
        
    }

    private void addNonexistentGene(int i) {
        int a;
        if(!this.genes.contains(i)){
            a = rand.nextInt(32);
            this.genes.set(a, i);
        }
    }


}
