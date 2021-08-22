package main;

import part2_2.Approximation;
import part2_3.LocalSearch;

import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args){

        System.out.println("Approximation algorithm for bier127.tsp - 127 Biergaerten in Augsburg (Juenger/Reinelt)");
        FileReader fr = new FileReader((Main.class.getResourceAsStream("/bier127.tsp.txt")));
        List<Node> nodes = fr.getMap();
        Approximation ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for eil101.tsp - 101-city problem (Christofides/Eilon)");
        fr = new FileReader(Main.class.getResourceAsStream("/eil101.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for eil51.tsp - 51-city problem (Christofides/Eilon)");
        fr = new FileReader(Main.class.getResourceAsStream("/eil51.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for kroA100.tsp - 100-city problem A (Krolak/Felts/Nelson)");
        fr = new FileReader(Main.class.getResourceAsStream("/kroA100.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for nrw1379.tsp - 1379 Orte in Nordrhein-Westfalen (Bachem/Wottawa)");
        fr = new FileReader(Main.class.getResourceAsStream("/nrw1379.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for eil76.tsp - 76-city problem (Christofides/Eilon)");
        fr = new FileReader(Main.class.getResourceAsStream("/eil76.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for pr1002.tsp - 1002-city problem (Padberg/Rinaldi)");
        fr = new FileReader(Main.class.getResourceAsStream("/pr1002.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for pcb1173.tsp - Drilling problem (Juenger/Reinelt)");
        fr = new FileReader(Main.class.getResourceAsStream("/pcb1173.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for u2319.tsp - Drilling problem (Reinelt)");
        fr = new FileReader(Main.class.getResourceAsStream("/u2319.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");
        System.out.println("Approximation algorithm for a280.tsp - Drilling problem (Ludwig)");
        fr = new FileReader(Main.class.getResourceAsStream("/a280.tsp.txt"));
        nodes = fr.getMap();
        ap = new Approximation(nodes);
        System.out.println("____________________________________________________________________________");

        Collections.shuffle(nodes);
        double newDistance = 0;
        for (int i = 0; i < nodes.size()-1; i++) {
            newDistance += nodes.get(i).getDistance(nodes.get(i+1));
        }
        LocalSearch ls = new LocalSearch(nodes, newDistance);

       //**Uncomment the below to run the Genetic Algorithm**

//        Collections.shuffle(nodes);
//        double newDistance = 0;
//        for (int i = 0; i < nodes.size()-1; i++) {
//            newDistance += nodes.get(i).getDistance(nodes.get(i+1));
//        }
//        Genetic g = new Genetic(nodes, newDistance);
    }
}
