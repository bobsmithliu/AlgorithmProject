

import java.util.*;

/**
 * This class is use to create Pool object.
 * @author Zichang Liu
 * @version JDK13
*/
public class Pool {
    /**
     *  Setting default pool name.
     */
    public static final String DEFAULT_POOL_NAME = "Unnamed";
    /**
     * Setting default pool temp.
     */
    public static final double DEFAULT_POOL_TEMP_CELSIUS = 40.0;
    /**
     * Setting minimum pool temp.
     */
    public static final double MINIMUM_POOL_TEMP_CELSIUS = 0.0;
    /**
     * Setting max pool temp.
     */
    public static final double MAXIMUM_POOL_TEMP_CELSIUS = 100.0;
    /**
     * Setting neutral ph value.
     */
    public static final double NEUTRAL_PH = 7.0;
    /**
     * Setting max ph value.
     */
    public static final double MAX_PH = 14.0;
    /**
     * Setting min ph value.
     */
    public static final double MIN_PH = 0.0;
    /**
     * Setting default nutrient coefficent.
     */
    public static final double DEFAULT_NUTRIENT_COEFFICIENT = 0.50;
    /**
     * Setting minimum nutrient coefficient.
     */
    public static final double MINIMUM_NUTRIENT_COEFFICIENT = 0.0;
    /**
     *  Setting maximum nutriet coefficient.
     */
    public static final double MAXIMUM_NUTRIENT_COEFFICIENT = 1.0;
    /**
     *  Setting minimum volume for pool.
     */
    public static final double MINIMUM_POOL_VOLUME = 0.0;

    private static int numberOfPools = 0;
    private static int poolCounter = 0;

    private String name;
    private double volumeLitres;
    private double temperatureCelsius;
    private double pH;
    private double nutrientCoeffcient;
    private int identificationNumber;
    private ArrayList<Guppy> guppiesInPool;
    private Random randomNumberGenerator;

    /**
     * Create a Default Pool object.
     */
    public Pool() {
        volumeLitres = 0.0;
        name = DEFAULT_POOL_NAME;
        temperatureCelsius = DEFAULT_POOL_TEMP_CELSIUS;
        pH = NEUTRAL_PH;
        nutrientCoeffcient = DEFAULT_NUTRIENT_COEFFICIENT;
        guppiesInPool = new ArrayList<>();
        randomNumberGenerator = new Random();
    }

    /**
     * Create a modified Pool object.
     * @param newName name for Pool
     * @param newVolumeLitres volume for pool
     * @param newTemperature temperature for pool
     * @param newPH ph for pool
     * @param newNutrientCoe nutrientCoefficient for pool
     * @throws IllegalArgumentException if no name throws IAE.
     */
    public Pool(final String newName, double newVolumeLitres, double newTemperature,
                double newPH, double newNutrientCoe) {
        if (newName.strip().equals("") || newName.equals(null)) {
            throw new IllegalArgumentException("NEIN");
        }
        if (newVolumeLitres < MINIMUM_POOL_VOLUME) {
            this.volumeLitres = MINIMUM_POOL_VOLUME;
        }
        if (newTemperature < MINIMUM_POOL_TEMP_CELSIUS
                || newTemperature > MAXIMUM_POOL_TEMP_CELSIUS) {
            this.temperatureCelsius = DEFAULT_POOL_TEMP_CELSIUS;
        }
        if (newPH > MAX_PH || newPH < MIN_PH) {
            this.pH = NEUTRAL_PH;
        }
        if (newNutrientCoe < 0.0 || newNutrientCoe > 1.0) {
            this.nutrientCoeffcient = DEFAULT_NUTRIENT_COEFFICIENT;
        }
        this.name = newName;
        this.volumeLitres = newVolumeLitres;
        this.temperatureCelsius = newTemperature;
        this.pH = newPH;
        this.nutrientCoeffcient = newNutrientCoe;
        this.guppiesInPool = new  ArrayList<>();
        this.randomNumberGenerator = new Random();
        this.identificationNumber = poolCounter + 1;
    }

    /**
     * Return the name of the pool.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the volume of the pool.
     * @return volumeLitres
     */
    public double getVolumeLitres() {
        return volumeLitres;
    }

    /**
     * Return the temp for the pool.
     * @return temperatureCelsius
     */
    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    /**
     * Return the ph of the pool.
     * @return pH
     */
    public double getpH() {
        return pH;
    }

    /**
     * Return the nutrient coefficient of the pool.
     * @return nutrientCoeffcient
     */
    public double getNutrientCoeffcient() {
        return nutrientCoeffcient;
    }

    /**
     * Return the id number of the pool.
     * @return identificationNumber
     */
    public int getidentificationNumber() {
        return identificationNumber;
    }

    /**
     * Setting the volume for the pool.
     * @param volumeLitres new volume of the pool.
     */
    public void setVolumeLitres(double volumeLitres) {
        if (volumeLitres > 0.0) {
            this.volumeLitres = volumeLitres;
        }
    }

    /**
     * setting the new temp for the pool.
     * @param temperatureCelsius new temp of the pool
     */
    public void setTemperatureCelsius(double temperatureCelsius) {
        if (temperatureCelsius > MINIMUM_POOL_TEMP_CELSIUS
                && temperatureCelsius < MAXIMUM_POOL_TEMP_CELSIUS) {
            this.temperatureCelsius = temperatureCelsius;
        }
    }

    /**
     * setting the new ph for the pool.
     * @param pH new ph of the pool
     */
    public void setpH(double pH) {
        if (pH > MIN_PH && pH < MAX_PH) {
            this.pH = pH;
        }
    }

    /**
     * setting the new nutrient coefficient of the pool.
     * @param nutrientCoeffcient new nutrient coefficient of the pool.
     */
    public void setNutrientCoeffcient(double nutrientCoeffcient) {
        if (nutrientCoeffcient > 0.0 && nutrientCoeffcient < 1.0) {
            this.nutrientCoeffcient = nutrientCoeffcient;
        }
    }

    /**
     * Changing the pool`s nutrient coefficient.
     * @param delta the value add or minus to the nutrient coefficient
     */
    public void changeNutrientCoeffcient(double delta) {
        double changingNuCo = getNutrientCoeffcient();
        changingNuCo += delta;
        if (changingNuCo < MINIMUM_NUTRIENT_COEFFICIENT) {
            setNutrientCoeffcient(MINIMUM_NUTRIENT_COEFFICIENT);
        } else if (changingNuCo > MAXIMUM_NUTRIENT_COEFFICIENT) {
            setNutrientCoeffcient(MAXIMUM_NUTRIENT_COEFFICIENT);
        } else {
            setNutrientCoeffcient(changingNuCo);
        }
    }

    /**
     * Changing the temp of the pool.
     * @param delta the value add or minus to the temp
     */
    public void changeTempretature(double delta) {
        double changingTemp = getTemperatureCelsius();
        changingTemp += delta;
        if (changingTemp < MINIMUM_POOL_TEMP_CELSIUS) {
            setTemperatureCelsius(MINIMUM_POOL_TEMP_CELSIUS);
        } else if (changingTemp > MAXIMUM_POOL_TEMP_CELSIUS) {
            setTemperatureCelsius(MAXIMUM_POOL_TEMP_CELSIUS);
        } else {
            setTemperatureCelsius(changingTemp);
        }
    }

    /**
     *Return the number of guppy in a pool.
     * @return poolCounter
     */
    public static int getNumberCreated() {
        return poolCounter;
    }

    /**
     *Add guppy if a guppy object is provided.
     * @param guppy Guppy to be added
     * @return true/false
     */
    public boolean addGuppy(Guppy guppy) {
        if (guppy.equals(null)) {
            return false;
        } else {
            guppiesInPool.add(guppy);
            return true;
        }
    }

    /**
     *Return the population of the Pool.
     * @return guppiesInPool.size()
     */
    public int getPopulation() {
        return guppiesInPool.size();
    }

    /**
     * apply new nutrient coefficient and kill guppy base on that.
     * @return casualty
     */
    public int applyNuCo() {
        int casualty = 0;
        Random killingTime = new Random();
        double mayIkillthatGuppy = 0.0;
        Iterator<Guppy> timeTokill = guppiesInPool.iterator();
        while (timeTokill.hasNext()) {
            Guppy guppy = timeTokill.next();
            mayIkillthatGuppy = killingTime.nextDouble();
            if (mayIkillthatGuppy > nutrientCoeffcient) {
                guppy.setAlive(false);
                ++casualty;
            }
        }
        return casualty;
    }

    /**
     *Remove the guppy which is dead.
     * @return howManybodyBagsdoweNeed
     */
    public int removeDeadGuppies() {
        int howManybodyBagsdoweNeed = 0;
        Guppy looksDead;
        Iterator<Guppy> knockknockYoudead = guppiesInPool.iterator();
        while (knockknockYoudead.hasNext()) {
            looksDead = knockknockYoudead.next();
            if (!looksDead.isAlive()) {
                knockknockYoudead.remove();
                ++howManybodyBagsdoweNeed;
            }
        }
        return howManybodyBagsdoweNeed;
    }

    /**
     *Return the volume the guppies need.
     * @return totalVolumeNeeded / volumeConverter
     */
    public double getGuppyVolumeReqInLitres() {
        double totalVolumeNeeded = 0.0;
        final int volumeConverter = 1000;
        for (Guppy guppy : guppiesInPool) {
            if (guppy.isAlive()) {
                totalVolumeNeeded += guppy.getVolumeNeed();
            }
        }
        return  totalVolumeNeeded / volumeConverter;
    }

    /**
     *Return the average age of guppy in a week.
     * @return totalAge / guppiesInPool.size()
     */
    public double getAverageAgeInWeeks() {
        double totalAge = 0.0;
        for (Guppy guppy : guppiesInPool) {
            totalAge += guppy.getAgeInWeeks();
        }
        return totalAge / guppiesInPool.size();
    }

    /**
     *Return the average health coefficient in the pool.
     * @return totalHealthCo / guppiesInPool.size()
     */
    public double getAverageHealthCoe() {
        double totalHealthCo = 0.0;
        for (Guppy guppy : guppiesInPool) {
            totalHealthCo += guppy.getHealthCoefficient();
        }
        return totalHealthCo / guppiesInPool.size();
    }

    /**
     *Return the percentage of female guppies in the pool.
     * @return femalePercent
     */
    public double getFemalePercent() {
        double totalFemale = 0.0;
        final int percentConvert = 100;
        for (Guppy guppy : guppiesInPool) {
            if (guppy.isFemale()) {
                totalFemale += 1.0;
            }
        }
        double femalePercent = totalFemale / guppiesInPool.size();
        femalePercent *= percentConvert;
        return femalePercent;
    }

    /**
     *return median age of guppy in a pool.
     * @return guppyMedian.getAgeInWeeks()
     */
    public double getMedianAge() {
        Guppy guppyMedian = guppiesInPool.get(guppiesInPool.size() / 2);
        return guppyMedian.getAgeInWeeks();
    }

    /**
     *Spawn guppies to the pool.
     * @return totalSpawn
     */
    public int spawn() {
        int totalSpawn = 0;
        Guppy probablyAMom;
        Iterator<Guppy> babyTime = guppiesInPool.iterator();
        ArrayList<Guppy> newFry = new ArrayList<>();
        while (babyTime.hasNext()) {
            probablyAMom = babyTime.next();
            if (probablyAMom.isFemale()) {
                newFry.addAll(probablyAMom.spawn());
            }
        }
        totalSpawn += newFry.size();
        guppiesInPool.addAll(newFry);
        return totalSpawn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pool)) return false;
        Pool pool = (Pool) o;
        return Double.compare(pool.getVolumeLitres(), getVolumeLitres()) == 0 &&
                Double.compare(pool.getTemperatureCelsius(), getTemperatureCelsius()) == 0 &&
                Double.compare(pool.getpH(), getpH()) == 0 &&
                Double.compare(pool.getNutrientCoeffcient(), getNutrientCoeffcient()) == 0 &&
                identificationNumber == pool.identificationNumber &&
                Objects.equals(getName(), pool.getName()) &&
                Objects.equals(guppiesInPool, pool.guppiesInPool) &&
                Objects.equals(randomNumberGenerator, pool.randomNumberGenerator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getVolumeLitres(), getTemperatureCelsius(), getpH(), getNutrientCoeffcient(), identificationNumber, guppiesInPool, randomNumberGenerator);
    }

    /**
     * Return how many guppy has died due to oldness.
     * @return yoHeisDead
     */
    public int incrementAges() {
        int yoHeisDead = 0;
        for (Guppy guppy : guppiesInPool) {
            if (guppy.getAgeInWeeks() == Guppy.MAXIMUM_AGE_IN_WEEKS) {
                guppy.setAlive(false);
                ++yoHeisDead;
            } else {
                guppy.incrementAge();
            }
        }
        return yoHeisDead;
    }

    /**
     *Return how many has died due to crowding.
     * @return crowdedCasaulty
     */
    public int adjustForCrowding() {
        int crowdedCasaulty = 0;
       while (getGuppyVolumeReqInLitres() > getVolumeLitres()) {
           guppiesInPool.sort(Comparator.comparingDouble(Guppy::getHealthCoefficient));
           guppiesInPool.get(crowdedCasaulty).setAlive(false);
           crowdedCasaulty++;
       }
       return crowdedCasaulty;
    }

}
