package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    private List<Node> map = new ArrayList<Node>();
    private int dimension;

    public FileReader(InputStream file){
        readFile(file);
        createEdges();
    }

    public void readFile(InputStream file){
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String text = sc.next();
            if (text.equals("DIMENSION:")) {
                dimension = sc.nextInt();
            }
            else if (text.equals("DIMENSION")){
                sc.next();
                dimension = sc.nextInt();
            }
            else if (text.equals("NODE_COORD_SECTION")) {
                for (int i = 0; i < dimension; i++) {
                    Node current = new Node(sc.nextInt(), sc.nextDouble(), sc.nextDouble());
                    map.add(current);
                }
            }
        }
        sc.close();
    }

    public void createEdges(){
        for (int i = 0; i < map.size(); i++){
            for (int j = i+1; j < map.size(); j++){
                if (map.get(i).getDistance(map.get(j)) == 0){
                    continue;
                }
                else {
                    Edge newEdge = new Edge(map.get(i), map.get(j), map.get(i).getDistance(map.get(j)));
                    map.get(i).addEdge(newEdge);
                    map.get(j).addEdge(newEdge);
                }
            }
        }
    }

    public List<Node> getMap() {
        return map;
    }
}
