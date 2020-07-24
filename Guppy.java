


import java.util.ArrayList;
import java.util.Random;
/**
 * This class is use to create Guppy object.
 * @version JDK 13
 * @author Zichang Liu
 */
public class Guppy {
//SYMBOLIC CONSTANTS HERE
    /**
     * Setting age for young guppy.
     */
    public static final int YOUNG_FISH_AGE_IN_WEEKS = 10;
    /**
     * Setting age for mature guppy.
     */
    public static final int MATURE_FISH_AGE_IN_WEEKS = 30;
    /**
     * Setting MAX age for guppy.
     */
    public static final int MAXIMUM_AGE_IN_WEEKS = 50;
    /**
     * Setting default genus name.
     */
    public static final String DEFAULT_GENUS = "Poecillia";
    /**
     * Setting default species name.
     */
    public static final String DEFAULT_SPECIES = "reticulata";
    /**
     * Setting minimum water volume for guppy.
     */
    public static final double MINIMUM_WATER_VOLUME_ML = 250.0;
    /**
     * Setting default health coefficient for guppy.
     */
    public static final double DEFAULT_HEALTH_COEFFICIENT = 0.5;
    /**
     * Setting MINIMUM_HEALTH_COEFFICIENT for guppy.
     */
    public static final double MINIMUM_HEALTH_COEFFICIENT = 0.0;
    /**
     * Setting value for MAXIMUM_HEALTH_COEFFICIENT for guppy.
     */
    public static final double MAXIMUM_HEALTH_COEFFICIENT = 1.0;

    private static int numberOfGuppiesBorn;

//INSTANCE VARIABLES

    private String genus;
    private String species;
    private int ageInWeeks;
    private int generationNumber;
    private int idendificaNumber;
    private double healthCoefficient;
    private boolean isFemale;
    private boolean isAlive;

//Incremented by 1 each time a guppy is constructed

//assign this number to idendificaNumber once new guppy is constructed



//TWO CONSTRUCTORS
//
//FIRST CONSTRUCTOR(DEFAULT), NO PARAMETER.


//*
//     * Create the Guppy default constructor


    public Guppy() {
        numberOfGuppiesBorn++;
        ageInWeeks = 0;
        generationNumber = 0;
        genus = DEFAULT_GENUS;
        species = DEFAULT_SPECIES;
        isFemale = true;
        isAlive = true;
        healthCoefficient = DEFAULT_HEALTH_COEFFICIENT;
        this.idendificaNumber = numberOfGuppiesBorn;
    }
//SECOND CONSTRUCTOR(MODIFY), SIX PARAMETERS


/**
     * Modifying the Guppy Object.
     * @param newGenus String represent genus
     * @param newSpecies String represent species
     * @param newAgeinWeeks int represent age
     * @param newGenerationNumber int represent Generation number
     * @param newHealthCoefficient double represent health coefficient
     * @param newIsFemale boolean represent female status
*/

    public Guppy(String newGenus, String newSpecies, int newAgeinWeeks, int newGenerationNumber,
                 double newHealthCoefficient, boolean newIsFemale) {
        numberOfGuppiesBorn += 1;
//Titlecasing newGenus

        if (newGenus == null) {
            throw new IllegalArgumentException("Null detetced ");
        } else if (newGenus.isBlank()) {
            throw new IllegalArgumentException("No Genus Specified");
        } else {
            genus = newGenus.trim().substring(0, 1).toUpperCase()
                    + newGenus.trim().substring(1).toLowerCase();
        }

//Lowercasing newSpecies

        if (newSpecies == null) {
            throw new IllegalArgumentException("Null detected in species");
        } else if (newSpecies.isBlank()) {
            throw new IllegalArgumentException("blank species");
        } else {
            species = newSpecies.trim().toLowerCase();
        }

        
//Checking newAgeInWeeks

        if (newAgeinWeeks < 0) {
            throw new IllegalArgumentException("No Neg age.");
        } else {
            ageInWeeks = newAgeinWeeks;
        }
//assigning isFemale

        isFemale = newIsFemale;
//Assigning generationNumber

        if (newGenerationNumber < 0) {
            generationNumber = 1;
        } else {
            generationNumber = newGenerationNumber;
        }
//Assigning newHealthCoefficient

        if (newHealthCoefficient > MAXIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException("Over Max Health Coefficient");
        } else if (newHealthCoefficient < MINIMUM_HEALTH_COEFFICIENT) {
            throw new IllegalArgumentException("Below MIN health coefficient");
        } else {
            healthCoefficient = newHealthCoefficient;
        }
        isAlive = true;
        if (newAgeinWeeks > MAXIMUM_AGE_IN_WEEKS) {
            throw new IllegalArgumentException("Over Max age.");
        }
    }
    public ArrayList<Guppy> spawn() {
        // check if Guppy is female
        //if female, can have babie
        //Female must be 8 or older to spawn
        Random funtime = new Random();
        final int spawnHighBound = 100;
        final int spawnAgeBound = 8;
        final double spawnDecider = 0.5;
        int spawnNumberOfGuppy = funtime.nextInt(spawnHighBound);
        ArrayList<Guppy> babyGuppies = new ArrayList<>();
        if (isFemale && getAgeInWeeks() >= spawnAgeBound) {
            for (int i = 0; i < spawnNumberOfGuppy; i++) {
                double shouldWeHaveSomeBabies = funtime.nextDouble();
                if (shouldWeHaveSomeBabies <= spawnDecider) {
                    Guppy babyGuppy = new Guppy();
                    babyGuppies.add(babyGuppy);
                    int babyAmount = funtime.nextInt(spawnHighBound);
                }
            }
        }
        return babyGuppies;
    }
/**
     * define incrementAge().
     * Increment of Guppy

*/

    public void incrementAge() {
        int ageCurrent = getAgeInWeeks();
        ageCurrent += 1.0;
        if (ageCurrent > MAXIMUM_AGE_IN_WEEKS) {
            isAlive = false;
        } else {
            setAgeInWeeks(ageCurrent);
        }
    }
//getter and setter


/**
     * set ageInWeeks.
     * @param ageInWeeks int represent new age in weeks
*/

    public void setAgeInWeeks(int ageInWeeks) {
        if (ageInWeeks >= 0 && ageInWeeks <= MAXIMUM_AGE_IN_WEEKS) {
            this.ageInWeeks = ageInWeeks;
        }
    }

/**
     * set the new healthCoefficient.
     * @param healthCoefficient int represent new health coefficient
*/

    public void setHealthCoefficient(double healthCoefficient) {
        if (healthCoefficient >= MINIMUM_HEALTH_COEFFICIENT
                && healthCoefficient <= MAXIMUM_HEALTH_COEFFICIENT) {
            this.healthCoefficient = healthCoefficient;
        }
    }

/**
     * set alive status.
     * @param alive new alive boolean
 */

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

/**
     * get genus.
     * @return genus
 */

    public String getGenus() {
        return this.genus;
    }

/**
     * return species.
     * @return species
 */

    public String getSpecies() {
        return species;
    }

/**
     * return ageInWeeks.
     * @return ageInweeks
 */

    public int getAgeInWeeks() {
        return ageInWeeks;
    }

/**
     * return Generation number.
     * @return generation number
 */

    public int getGenerationNumber() {
        return generationNumber;
    }

/**
     * return idendificaNumber.
     * @return idendificaNumber
 */

    public int getidendificaNumber() {
        return idendificaNumber;
    }

/**
     * get healthCoefficient.
     * @return healthCoefficient
 */

    public double getHealthCoefficient() {
        return healthCoefficient;
    }

/**
     * get isFemale.
     * @return isFemale
 */


    public boolean isFemale() {
        return isFemale;
    }

/**
     * get isAlive.
     * @return isAlive
 */

    public boolean isAlive() {
        return isAlive;
    }

/**
     * get Number of guppy created.
     * @return numberOfGuppiesBorn
 */

    public static int getNumberOfGuppiesBorn() {
        return numberOfGuppiesBorn;
    }
/**
define getVolumeNeed
     * get how much volume is needed.
     * @return int represent volume needed
 */

    public double getVolumeNeed() {
        int guppyAge = getAgeInWeeks();
//Less than 10
        final double matureGuppyWaterConverter = 1.5;
        if (guppyAge < YOUNG_FISH_AGE_IN_WEEKS) {
            return  MINIMUM_WATER_VOLUME_ML;
        } else if (guppyAge >= YOUNG_FISH_AGE_IN_WEEKS && guppyAge <= MATURE_FISH_AGE_IN_WEEKS) {
            return MINIMUM_WATER_VOLUME_ML * guppyAge / YOUNG_FISH_AGE_IN_WEEKS;
        } else if (guppyAge > MATURE_FISH_AGE_IN_WEEKS && guppyAge <= MAXIMUM_AGE_IN_WEEKS) {
            return MINIMUM_WATER_VOLUME_ML * matureGuppyWaterConverter;
        } else {
            return 0.0;
        }
    }
/**
     * define changeHealthCoefficient.
     * Change health coefficient
     * @param delta int represent change
 */

    public void changeHealthCoefficient(double delta) {
        double currentHealthCoefficent = getHealthCoefficient();
        currentHealthCoefficent += delta;
        if (currentHealthCoefficent <= MINIMUM_HEALTH_COEFFICIENT) {
            setHealthCoefficient(0.0);
            setAlive(false);
        } else if (currentHealthCoefficent >= MAXIMUM_HEALTH_COEFFICIENT) {
            setHealthCoefficient(MAXIMUM_HEALTH_COEFFICIENT);
        } else {
            setHealthCoefficient(currentHealthCoefficent);
        }
    }
//define own equals()

    @Override

    /*
      Check if obj created from Guppy.
     */

    public boolean equals(Object obj) {
//null check

        if (obj == null) {
            return false;
        } else if (!(obj instanceof Guppy)) {
            return false;
        } else if (this.getAgeInWeeks() != ((Guppy) obj).getAgeInWeeks()
                && this.getGenerationNumber() != ((Guppy) obj).getGenerationNumber()
                && !(this.getGenus().equals(((Guppy) obj).getGenus()))
                && this.getHealthCoefficient() != ((Guppy) obj).getHealthCoefficient()
                && !(this.getSpecies().equals(((Guppy) obj).getSpecies()))
                && this.getVolumeNeed() != ((Guppy) obj).getVolumeNeed()
                && this.isFemale() != ((Guppy) obj).isFemale()
                && this.isAlive() != ((Guppy) obj).isAlive()
        ) {
            return false;
        } else {
            return this == obj;
        }
    }
    /**
     * define own toString.
     * String representation of Guppy
     * @return Guppy in a String
    */

    @Override
    public String toString() {
        return "Guppy{"
                + "genus='" + genus + '\''
                + ", species='" + species + '\''
                + ", ageInWeeks=" + ageInWeeks
                + ", generationNumber=" + generationNumber
                + ", idendificaNumber=" + idendificaNumber
                + ", healthCoefficient=" + healthCoefficient
                + ", isFemale=" + isFemale
                + ", isAlive=" + isAlive
                + '}';
    }
}
