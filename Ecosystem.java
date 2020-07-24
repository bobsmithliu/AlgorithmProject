

import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to create Ecosystem object.
 *
 * @author Zichang Liu
 * @version JDK 13
*/

public class Ecosystem {

    /**
     * Creating first Pool object in Ecosystem.
     */
    private Pool Skookumchck = new Pool();
    /**
     * Creating second Pool object in Ecosystem.
     */
    private Pool Squamish = new Pool();
    /**
     * Creating third Pool object in Ecosystem.
     */
    private Pool Semiahoo = new Pool();
    private ArrayList<Pool> pools;
    /**
     * Generation number for Guppies in the Ecosystem.
     */
    private final int generationNumber = 1;
    public Ecosystem() {

    }
    public void addPool(Pool newPool) {
        if (newPool != null) {
            pools.add(newPool);
        }
    }
    public void reset() {
        pools.clear();
    }
    public int getGuppyPopulation() {
        int totalGuppy = 0;
        for (Pool pool : pools) {
            totalGuppy += pool.getPopulation();
        }
        return totalGuppy;
    }
    public int adjustForCrowding() {
        int totalDecrowded = 0;
        for (Pool pool : pools) {
            totalDecrowded += pool.adjustForCrowding();
        }
        return totalDecrowded;
    }
    public void setupSimulation() {
        Random guppyGeneral = new Random();
        final int skookVol = 3000;
        final int squaVol = 15000;
        final int semiVol = 4500;
        final int skookTemp = 42;
        final int squaTemp = 39;
        final int semiTemp = 39;
        final double phForSquaAndSemi = 7.7;
        final double phForSkook = 7.9;
        final double nuCoforSquaAndSemi = 0.85;
        final double nuCoforSkook = 0.9;
        final int guppyNumberforSkook = 300;
        final int guppyNumberforSqua = 100;
        final int guppyNumberforSemi = 200;
        final int ageLowBoundforSkook = 10;
        final int ageHighBoundforSkook = 15;
        final double healthCoLowBoundforSkook = 0.5;
        final double healthCoHighBoundforSkook = 0.8;
        final int genderGuardforSkook = 224;
        final int ageLowBoundforSqua = 10;
        final int ageHighBoundforSqua = 5;
        final double healthCoLowBoundforSqua = 0.8;
        final double healthCoHighBoundforSqua = 1.0;
        final int genderGuardforSqua = 74;
        final int ageLowBoundforSemi = 15;
        final int ageHighBoundforSemi = 34;
        final double healthCoBoundforSemi = 0.9;
        final int genderGuardforSemi = 69;
        Skookumchck = new Pool("Skookumchck", skookVol, skookTemp, phForSkook, nuCoforSkook);
        Squamish = new Pool("Squamish", squaVol, squaTemp, phForSquaAndSemi, nuCoforSquaAndSemi);
        Semiahoo = new Pool("Semiahoo", semiVol, semiTemp, phForSquaAndSemi, nuCoforSquaAndSemi);
        for (int i = 0; i < guppyNumberforSkook; i++) {
            int guppyAge = ageLowBoundforSkook + guppyGeneral.nextInt(ageHighBoundforSkook);
            double guppyHealthCo = Math.random()
                    * (healthCoHighBoundforSkook - healthCoLowBoundforSkook)
                    + healthCoLowBoundforSkook;
            //healthco here, 0.5-0.8
            if (i <= genderGuardforSkook) {
                Guppy guppy = new Guppy("Poecillia", "reticulata",
                        guppyAge, generationNumber, guppyHealthCo, true);
                Skookumchck.addGuppy(guppy);
            } else {
                Guppy guppy = new Guppy("Poecillia", "reticulata",
                        guppyAge, generationNumber, guppyHealthCo, false);
                Skookumchck.addGuppy(guppy);
            }
        }
        for (int b = 0; b < guppyNumberforSqua; b++) {
            int guppyAge = ageLowBoundforSqua + guppyGeneral.nextInt(ageHighBoundforSqua);
            double guppyHealthCo = Math.random()
                    * (healthCoHighBoundforSqua - healthCoLowBoundforSqua)
                    + healthCoLowBoundforSqua;
            //healthco here, 0.8-1.0
            if (b <= genderGuardforSqua) {
                Guppy guppy = new Guppy("Poecillia", "reticulata",
                        guppyAge, generationNumber, guppyHealthCo, true);
                Squamish.addGuppy(guppy);
            } else {
                Guppy guppy = new Guppy("Poecillia", "reticulata",
                        guppyAge, generationNumber, guppyHealthCo, false);
                Squamish.addGuppy(guppy);
            }
        }
        for (int a = 0; a < guppyNumberforSemi; a++) {
            int guppyAge = ageLowBoundforSemi + guppyGeneral.nextInt(ageHighBoundforSemi);
            double guppyHealthCo = Math.random() * healthCoBoundforSemi;
            //healthco here, 0.0-0.9
            if (a <= genderGuardforSemi) {
                Guppy guppy = new Guppy("Poecillia", "reticulata",
                        guppyAge, generationNumber, guppyHealthCo, true);
                Semiahoo.addGuppy(guppy);
            } else {
                Guppy guppy = new Guppy("Poecillia", "reticulata",
                        guppyAge, generationNumber, guppyHealthCo, false);
                Semiahoo.addGuppy(guppy);
            }
        }
    }
    public void simulate(int numberOfWeeks) {
        while (numberOfWeeks != 0) {
            --numberOfWeeks;
            simulateOneWeek();
        }
    }
    public void simulateOneWeek() {
        int casualtyDieOfOld = 0;
        int amountRemoved = 0;
        int starvedToDeath = 0;
        int newFry = 0;
        int crowdedOut = 0;
        System.out.println("Week start!");
        casualtyDieOfOld += Skookumchck.incrementAges();
        casualtyDieOfOld += Squamish.incrementAges();
        casualtyDieOfOld += Semiahoo.incrementAges();
        System.out.println("Amount of died of old " + casualtyDieOfOld);
        amountRemoved += Skookumchck.removeDeadGuppies();
        amountRemoved += Squamish.removeDeadGuppies();
        amountRemoved += Semiahoo.removeDeadGuppies();
        System.out.println("Removed due to old: " + amountRemoved);
        starvedToDeath += Skookumchck.applyNuCo();
        starvedToDeath += Squamish.applyNuCo();
        starvedToDeath += Semiahoo.applyNuCo();
        System.out.println("Starved to death: " + starvedToDeath);
        amountRemoved += Skookumchck.removeDeadGuppies();
        amountRemoved += Squamish.removeDeadGuppies();
        amountRemoved += Semiahoo.removeDeadGuppies();
        System.out.println("Removed due to old and starved: " + amountRemoved);
        newFry += Skookumchck.spawn();
        newFry += Squamish.spawn();
        newFry += Semiahoo.spawn();
        System.out.println("New Fries for ketchup: " + newFry);
        crowdedOut += Skookumchck.adjustForCrowding();
        crowdedOut += Squamish.adjustForCrowding();
        crowdedOut += Semiahoo.adjustForCrowding();
        System.out.println("Die due to crowded: " + crowdedOut);
        amountRemoved += Skookumchck.removeDeadGuppies();
        amountRemoved += Squamish.removeDeadGuppies();
        amountRemoved += Semiahoo.removeDeadGuppies();
        System.out.println("Removed due to old and starved and crowded: " + amountRemoved);
        int totalCasualty = crowdedOut + starvedToDeath + casualtyDieOfOld;
        if (totalCasualty != amountRemoved) {
            System.out.println("Something is wrong bro");
        } else {
            System.out.println("A-ok");
        }
        System.out.println("Week done\n");
        int skookPop = Skookumchck.getPopulation();
        int squaPop = Squamish.getPopulation();
        int semiPop = Semiahoo.getPopulation();
        System.out.println("--Population--\n");
        System.out.println("Skook Pop: " + skookPop);
        System.out.println("Squamish Pop: " + squaPop);
        System.out.println("Semi Pop: " + semiPop + "\n");

    }
}
