package part2_2;

import main.Edge;
import main.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Represents an approximation algorithm for TSP.
 * @author Bryony Church
 */
public class Approximation {

    private List<Node> map = new ArrayList<Node>();
    private Node startNode;
    private List<Node> orderedNodes = new ArrayList<Node>();
    private double routeDistance = 0;

    /**
     * Constructs and approximation algorithm.
     * @param map   The nodes on the route.
     */
    public Approximation(List<Node> map){
        this.map = map;
        this.startNode = findStart();
        findRoute(startNode);
        System.out.println("Order of the towns on the route:");
        for (int i = 0; i < orderedNodes.size(); i++){
            System.out.print(orderedNodes.get(i).getIndex() + " -> ");
            if (i != 0 && i % 20 == 0){
                System.out.println("");
            }
        }
        System.out.println("\nNumber of towns on the map: " + map.size());
        System.out.println("Number of towns visited: " + orderedNodes.size());
        System.out.println("Distance travelled: " + routeDistance);
    }

    public List<Node> getOrderedNodes() {
        return orderedNodes;
    }

    public double getRouteDistance() {
        return routeDistance;
    }

    /**
     * Finds the node with the lowest average distance to the other nodes.
     * @return  The node with the lowest distance.
     */
    public Node findStart(){
        double lowestAverage = Double.MAX_VALUE;
        Node start = null;
        for (Node n : map){
            double distance = 0;
            for (Edge e : n.getEdges()){
                distance += e.getDistance();
            }
            if (distance / (map.size()-1) < lowestAverage){
                lowestAverage = distance / (map.size()-1);
                start = n;
            }
        }
        return start;
    }

    /**
     * Cosntructs the route by selecting the shortest edge each iteration until the start node is found.
     * @param current   The current node in the route.
     */
    public void findRoute(Node current){
        if (orderedNodes.size() > 0 && current.equals(startNode)){
            orderedNodes.add(startNode);
            return;
        }
        current.setVisited(true);
        orderedNodes.add(current);
        Queue<Edge> edges = current.getEdges();
        if (edges.isEmpty()){
            System.out.println("No route found");
            return;
        }
        while (!edges.isEmpty() && edges.peek().getFirstNode().isVisited() && edges.peek().getSecondNode().isVisited()) {
            edges.poll();
        }
        Edge shortest = edges.poll();
        Node otherNode;
        if (shortest != null) {
            if (shortest.getFirstNode().equals(current)) {
                otherNode = shortest.getSecondNode();
            } else {
                otherNode = shortest.getFirstNode();
            }
            if (!otherNode.isVisited()) {
                routeDistance += shortest.getDistance();
                findRoute(otherNode);
            }
        }
    }

}
