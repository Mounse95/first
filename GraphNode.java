package project.p3;

import java.util.ArrayList;
import java.util.List;

/**
 * The GraphNode class stores a node's data and its adjacency list. I have
 * provided the code for reading in nodes from an adjacency list file, which
 * will create a Graph with a list of GraphNodes for you.
 *
 * These methods, however, are STILL VERY IMPORTANT, as this is how you access
 * the adjacency lists and data for each node. Specifically, you will need to
 * make use of the getData() and getAdjacencyList() methods to complete this
 * project.
 *
 * @author Mark Hancock
 *
 */
public class GraphNode {
    private int data;
    private ArrayList<Integer> adjacencyList = new ArrayList<>();

    /**
     * Constructs a new node with an empty adjacency list.
     *
     * @param data
     *            the data value of this node
     */
    public GraphNode(int data) {
	this.data = data;
    }

    /**
     * Returns the data held in this node.
     *
     * @return the data held in this node
     */
    public int getData() {
	return data;
    }

    /**
     * Returns the adjacency list.
     *
     * @return the adjacency list
     */
    public List<Integer> getAdjacencyList() {
	return adjacencyList;
    }

    /**
     * Adds an edge to this node's adjacency list to the specified node
     *
     * @param data
     *            the node to add to the adjacency list
     */
    public void edgeTo(int data) {
	adjacencyList.add(data);
    }

}
