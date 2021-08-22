package part2_3;

import main.Node;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Reprsents and LocalSearch Simulated Annealing Algorithm for the TSP.
 * @author Bryony Church
 */
public class LocalSearch {

    private static final double T0 = 1000000;
    private double distance;
    private int count = 1;
    private Queue<Integer> tabu = new ArrayDeque<Integer>();

    /**
     * Constructs a LocalSearch Algorithm.
     * @param initial   The initial solution.
     * @param distance  The distance of the original solution.
     */
    public LocalSearch(List<Node> initial, double distance){
        this.distance = distance;
        try {
            completeSearch(initial);
        } catch (IOException e) { System.out.println("error creating file"); }
    }

    /**
     * Iteratively searches for a more optimal solution.
     * @param solution      The initial solution.
     * @throws IOException
     */
    public void completeSearch(List<Node> solution) throws IOException {
        double cooling;
        double temperature = T0;
        FileWriter output = new FileWriter("output.txt");
        Random rand = new Random();
        output.write("Iteration,Distance,\n");
        while (temperature > 0.1) {
            if (count % 1000 == 0 || count == 1) {
                output.write(count+","+distance+",\n");
            }
            count++;
            cooling = 1/(1.0*count);
            temperature = T0 * cooling;

            int pos1 = 1 + rand.nextInt(solution.size()-1);
            while(tabu.contains(pos1)){
                pos1 = 1 + rand.nextInt(solution.size()-1);
            }
            int pos2 = pos1;
            while (pos2 == pos1 || tabu.contains(pos2)) {
                pos2 = 1 + rand.nextInt(solution.size()-1);
            }
            Collections.swap(solution, pos1, pos2);
            double newDistance = calculateNewDistance(solution);
            //If a better solution or probability is high enough, move to the new solution.
            if (newDistance < distance || calculateProbability((newDistance - distance), temperature) > rand.nextFloat()) {
                distance = newDistance;
                tabu.add(pos1);
                tabu.add(pos2);
                if (tabu.size() > 49){
                    tabu.poll();
                    tabu.poll();
                }
            }
            //Otherwise revert back.
            else {
                Collections.swap(solution, pos1, pos2);
            }
        }
        System.out.println("The best distance found is: " + distance);
        System.out.println("Number of iterations: " + count);
        output.close();
    }

    /**
     * Calculates the distance of the route.
     * @param nodes The locations in the route.
     * @return      The distance.
     */
    public double calculateNewDistance(List<Node> nodes) {
        double newDistance = 0;
        for (int i = 0; i < nodes.size()-1; i++) {
            newDistance += nodes.get(i).getDistance(nodes.get(i + 1));
        }
        return newDistance;
    }

    /**
     * Calculates the probability of moving to the new solution if it is worse than the current.
     * @param difference    Difference between new and current solutions.
     * @param temperature   Weight function for calculating probability.
     * @return
     */
    public double calculateProbability(double difference, double temperature){
        return Math.exp((-1*difference)/(temperature));
    }
}
