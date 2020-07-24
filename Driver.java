
import java.util.Scanner;
/**
 * @author Zichang Liu
 * @version JDK13
 */
public class Driver {
    public static void main(String[] args) {
        Ecosystem funTime = new Ecosystem();
        funTime.setupSimulation();
        Scanner start = new Scanner(System.in);
        System.out.println("Enter how many weeks you want to see the guppy simulation");
        int lengthOfSimulation = start.nextInt();
        funTime.simulate(lengthOfSimulation);
    }
}
