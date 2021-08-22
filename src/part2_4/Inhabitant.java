package part2_4;

import main.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents and inhabitant in the population.
 * @author Bryony Church
 */
public class Inhabitant implements Comparable<Inhabitant> {

    private List<Node> nodes = new ArrayList<Node>();
    private double distance;
    private double relativeFitness;

    /**
     * Constructs and inhabitant object.
     * @param nodes     The order of the nodes in the route.
     * @param distance  The distance of the route.
     */
    public Inhabitant(List<Node> nodes, double distance){
        this.nodes = nodes;
        this.distance = distance;
    }

    /**
     * Swaps two of the nodes and recalculates the distance, for mutation.
     */
    public void mutate(){
        Random rand = new Random();
        int pos1 = 1 + rand.nextInt(nodes.size()-1);
        int pos2 = pos1;
        while (pos2 == pos1){
            pos2 = 1 + rand.nextInt(nodes.size()-1);
        }
        Collections.swap(nodes, pos1, pos2);
        distance = calculateDistance();
    }

    /**
     * Calculates the distance of the route.
     * @return  The distance.
     */
    public double calculateDistance(){
        double distance = 0;
        for (int i = 0; i < nodes.size()-1; i++){
            distance += nodes.get(i).getDistance(nodes.get(i+1));
        }
        return distance;
    }

    public void setRelativeFitness(double fitness){
        this.relativeFitness = fitness;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Inhabitant other){
        if (this.distance < other.getDistance()) return -1;
        else if (this.distance > other.getDistance()) return 1;
        else return 0;
    }
}
