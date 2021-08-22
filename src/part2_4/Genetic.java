package part2_4;

import main.Node;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Represents a genetic algorithm for approximating an optimum travelling salesperson route.
 * @author Bryony Chruch
 * I had help from this tutorial for creating this algrithm:
 * https://towardsdatascience.com/evolution-of-a-salesman-a-complete-genetic-algorithm-tutorial-for-python-6fe5d2b3ca35
 */
public class Genetic {

    private static final double MUTATIONRATE = 0.3;
    private static final int POPULATIONSIZE = 50;
    private static final int NUMPARENTS = 20;
    private List<Inhabitant> population = new ArrayList<Inhabitant>();
    private List<Inhabitant> mates = new ArrayList<Inhabitant>();
    private List<Inhabitant> children = new ArrayList<Inhabitant>();

    /**
     * Constructs a evolving population.
     * @param nodes     The locations to be included on the route.
     * @param distance  The distance of the initial solution.
     */
    public Genetic(List<Node> nodes, double distance){
        Inhabitant first = new Inhabitant(nodes, distance);
        population.add(first);
        createInitialPopulation(nodes);
        try {
            search();
        } catch (IOException e) { System.out.println("Unable to write to file " + e); }
    }

    /**
     * Iterates 10000 times, creating new generations of populations.
     * @throws IOException
     */
    public void search() throws IOException {
        int count = 0;
        FileWriter fw = new FileWriter(new File("gaoutput.txt"));
        fw.write("Iteration,Distance,\n");
        while (count < 10000){
            count++;
            mates = selectMates();
            children = breed();
            mutatePopulation();
            createNewPopulation();
            Collections.sort(population);
            fw.write(count + "," + getDistance(population.get(0).getNodes()) + ",\n");
        }
        System.out.println("Best solution is: " + getDistance(population.get(0).getNodes()));
    }

    /**
     * Mutates an inhabitant in the population.
     */
    public void mutatePopulation(){
        Random rand = new Random();
        for (Inhabitant i : children){
            if (rand.nextFloat() < MUTATIONRATE){
                i.mutate();
            }
        }
    }

    /**
     * Creates a new population for the next generation.
     */
    public void createNewPopulation(){
        //First add the children and parents and then the best of the other inhabitants.
        List<Inhabitant> newPop = new ArrayList<Inhabitant>(children);
        newPop.addAll(mates);
        Collections.sort(population);
        while (newPop.size() < POPULATIONSIZE){
            for (Inhabitant in : population){
                if (!newPop.contains(in)){
                    newPop.add(in);
                }
            }
        }
        population = newPop;
    }

    /**
     * Crossover of two parent to make a child.
     * @return  A list of the children.
     */
    public List<Inhabitant> breed(){
        Random rand = new Random();
        List<Inhabitant> children = new ArrayList<Inhabitant>();
        for (int i = 0; i < NUMPARENTS; i+=2){
            Inhabitant parent1 = mates.get(i);
            Inhabitant parent2 = mates.get(i+1);
            int routeSize = parent1.getNodes().size();
            //Size of the gene to come from parent one.
            int geneSize = (int)Math.round(routeSize*0.3);
            //Location of the gene.
            int start = rand.nextInt(routeSize-geneSize);
            List<Node> childRoute = new ArrayList<Node>();
            for (int j = 0; j < start; j++){
                if (!parent1.getNodes().subList(start, start+geneSize).contains(parent2.getNodes().get(j))) {
                    childRoute.add(parent2.getNodes().get(j));
                }
            }
            for (int k = start; k < start+geneSize; k++) {
                childRoute.add(parent1.getNodes().get(k));
            }
            for (int l = 0; l < parent1.getNodes().size(); l++){
                if (!childRoute.contains(parent1.getNodes().get(l))) {
                    childRoute.add(parent1.getNodes().get(l));
                }
            }
            double distance = getDistance(childRoute);
            Inhabitant child = new Inhabitant(childRoute, distance);
            children.add(child);
        }
        return children;
    }

    /**
     * Selects the parents for mating by a tournament.
     * @return  The parents for mating.
     */
    public List<Inhabitant> selectMates(){
        List<Inhabitant> selection = new ArrayList<Inhabitant>();
        while (selection.size() < NUMPARENTS){
            Inhabitant current = tournament();
            if (!selection.contains(current)){
                selection.add(current);
            }
        }
        Collections.sort(selection);
        return selection;
    }

    /**
     * Picks a parent from a subset of the population.
     * @return  The parent.
     */
    public Inhabitant tournament(){
        Random rand = new Random();
        List<Inhabitant> choice = new ArrayList<Inhabitant>();
        for (int i = 0; i < 15; i++) {
            int index = rand.nextInt(population.size());
            choice.add(population.get(index));
        }
        Collections.sort(choice);
        return choice.get(0);
    }

    /**
     * Creates the initial population of randomised routes.
     * @param nodes The locations in the route.
     */
    public void createInitialPopulation(List<Node> nodes){
        for (int i = 0; i < POPULATIONSIZE; i++){
            List<Node> newInhabitant = new ArrayList<Node>(nodes);
            Collections.shuffle(newInhabitant);
            double distance = getDistance(newInhabitant);
            Inhabitant migrant = new Inhabitant(newInhabitant, distance);
            population.add(migrant);
        }
    }

    /**
     * Calculates the distance of a route.
     * @param nodes The nodes to calculate.
     * @return      The distance.
     */
    public double getDistance(List<Node> nodes){
        double distance = 0;
        for (int i = 0; i < nodes.size()-1; i++){
            distance += nodes.get(i).getDistance(nodes.get(i+1));
        }
        return distance;
    }
}
