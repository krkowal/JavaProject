package AnimalTesting;

import java.util.ArrayList;
import java.util.Random;

public class Genes {
    private ArrayList<Integer> genes = new ArrayList<>();
    private Random rand = new Random();
    public Genes (){}
    public Genes(Genes genes1, Genes genes2){
        inheritedGenes(genes1.getGenes(),genes2.getGenes());
    }
    public void generateFirstGenes(){
        for(int i= 0; i<32; i++){
            this.genes.add(rand.nextInt(9));
        }
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

        copyGenes(genes1, 0, Math.min(a,b));
        copyGenes(genes2,Math.min(a,b),Math.max(a,b));
        copyGenes(genes2,Math.max(a,b),32);
        checkGenotype();
    }

    private void copyGenes(ArrayList<Integer> genes1, int a, int b) {
        for (int i = a; i < b; i++) {
            this.genes.add(genes1.get(i));
        }
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
    public ArrayList<Integer> getGenes() {
        return genes;
    }

    public int getTurn() {
        int randomIndex = rand.nextInt(32);
        return genes.get(randomIndex);
    }

    public void printOut(){
        for(int i =0;i<32;i++){
            System.out.println("gene "+(i+1)+ " = "+this.genes.get(i));
        }
    }

}
