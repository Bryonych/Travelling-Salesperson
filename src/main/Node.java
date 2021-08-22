package main;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Represents a location on the route.
 * @author Bryony Church
 */
public class Node {

    private double x;
    private double y;
    private int index;
    private Queue<Edge> edges = new PriorityQueue<Edge>();
    private boolean visited = false;

    /**
     * Constructs a node in the graph.
     * @param index Index of the node.
     * @param x     x co-ordinate.
     * @param y     y co-ordinate.
     */
    public Node(int index, double x, double y) {
        this.x = x;
        this.y = y;
        this.index = index;
    }

    /**
     * Gets the distance from this node to the other.
     * @param other The other node.
     * @return      The distance.
     */
    public double getDistance(Node other){
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();

        return Math.sqrt(dx*dx + dy*dy);
    }

    /**
     * Adds an edge to the list.
     * @param e The edge to be added.
     */
    public void addEdge(Edge e){
        edges.add(e);
    }

    public Queue<Edge> getEdges(){
        return edges;
    }

    public void setVisited(boolean visted) {
        this.visited = visted;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y &&
                index == node.index;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, index);
    }
}
