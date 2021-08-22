package main;

/**
 * Represents an edge in the graph.
 * @author Bryony Church
 */
public class Edge implements Comparable<Edge> {

    private Node firstNode;
    private Node secondNode;
    private double distance;

    /**
     * Contructs an edge in the graph.
     * @param one       Node at one end.
     * @param two       Node at the other end.
     * @param distance  Length of the edge.
     */
    public Edge(Node one, Node two, double distance) {
        this.firstNode = one;
        this.secondNode = two;
        this.distance = distance;
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getSecondNode() {
        return secondNode;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(Edge other){
        if (this.distance < other.getDistance()) return -1;
        else if (this.distance > other.getDistance()) return 1;
        else return 0;
    }
}
