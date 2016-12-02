package project.p3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.zip.GZIPInputStream;

/**
 * The Graph class holds the data for the entire graph read in from the
 * adjacency list files. THIS CLASS IS WHERE YOU WILL DO MOST OF YOUR WORK FOR
 * THIS PROJECT.
 *
 * @author Mark Hancock
 * @author <your name> - you are going to author quite a bit in this class!
 *
 */
public class Graph {
	/**
	 * This HashMap provides access to the GraphNode objects for each node. The
	 * keys are the node IDs (from the text files), and the values are
	 * GraphNodes that hold the adjacency lists (see the GraphNode class).
	 */
	private HashMap<Integer, GraphNode> nodes = new HashMap<>(5000000);

	/**
	 * Returns the number of nodes in the graph.
	 *
	 * @return the number of nodes in the graph
	 */
	public int numVertices() {
		return nodes.size();
	}

	/**
	 * Returns the number of edges in the graph.
	 *
	 * NOTE: THIS METHOD IS NOT IMPLEMENTED, AND ALSO DOESN'T NEED TO BE TO GET
	 * FULL MARKS ON THE PROJECT. Some solutions may benefit from it, if you
	 * wish to implement it.
	 *
	 * @return the number of edges in the graph
	 */
	public int numEdges() {
		throw new UnsupportedOperationException();
	}

	/**
	 * This method allows you to print a Graph object using
	 * System.out.println().
	 *
	 * NOTE: I don't recommend trying this for the full graph of Kevin Bacon
	 * data (your program will probably run out of memory).
	 */
	@Override
	public String toString() {
		StringWriter result = new StringWriter();
		PrintWriter writer = new PrintWriter(result);
		writer.println("V = " + nodes.keySet());
		for (GraphNode node : nodes.values()) {
			writer.printf("%d: %s\n", node.getData(), node.getAdjacencyList());
		}
		return result.toString();
	}

	/**
	 * This method should print out a report about components and use
	 * depth-first search to provide data about the graph. This method has been
	 * implemented for you, so you should not have to modify this code.
	 *
	 * Use this information for answers to Question 1
	 *
	 * HOWEVER, the depthFirstSearch algorithm below has NOT been implemented
	 * yet, so you will need to complete this in order to make this method work
	 * properly.
	 */
	public void printComponentReport() {
		// Report on components
		System.out.println("Component Report:");
		ArrayList<Integer> componentSizes = depthFirstSearch();
		System.out.printf("There were %d connected components\n", componentSizes.size());

		Statistics stats = new Statistics(componentSizes);

		System.out.println("Statistics:");
		System.out.println("Min: " + stats.getMin());
		System.out.println("Max: " + stats.getMax());
		System.out.printf("Mean: %.2f\n", stats.getMean());
		System.out.println("Q1: " + stats.getQ1());
		System.out.println("Q2: " + stats.getQ2());
		System.out.println("Q3: " + stats.getQ3());

		System.out.printf("Size of Component,Number (count) of components of this size\n");
		for (int size : stats.getSortedUniqueKeys()) {
			int count = stats.getCountOf(size);
			System.out.printf("%d,%d\n", size, count);
		}
	}

	/**
	 * This method should print out a report about shortest paths from a source
	 * node and use breadth-first search to provide this data about the graph.
	 *
	 * Use this information for answers to Question 2 a-d
	 *
	 * ONLY PARTS OF THIS METHOD HAVE BEEN IMPLEMENTED FOR YOU, so you will need
	 * to modify this code to make it work.
	 *
	 * ALSO, the breadthFirstSearch algorithm below has NOT been implemented
	 * yet, so you will need to complete this in order to make this method work
	 * properly.
	 *
	 * @param source
	 *            the source node to start the breadth-first search and write
	 *            the report about
	 */
	public void printPathReport(int source) {
		// Report on shortest path from source
		System.out.println("Path Report:");
		Map<Integer, BFSData> bfsMap = breadthFirstSearch(source);

		// TODO: create a list of integers (e.g., ArrayList<Integer>) to be able
		// to create a new Statistics object (my solution used two different
		// Statistics objects, but there are ways of doing it with just one)
		ArrayList<Integer> Hello = new ArrayList<Integer>();
		Statistics stats1 = new Statistics(Hello);

		// TODO: print the data you need for the table in Question 2a

		// TODO: report on the fraction of nodes reachable from the source for
		// Question 2b

		// TODO: report the max, mean, and quartiles for Question 2c

		// TODO: Question 2d should be answerable from parts a-c, but you can
		// output info for that too, if you like

		// NOTE: I recommend looking at the printComponentReport method for help
		// in getting these statistics and iterating through the distances to
		// provide counts.
		
		
	}
	

	/**
	 * This method should print out a report about shortest paths from a source
	 * node and use breadth-first search to provide this data about the graph.
	 *
	 * Use this information for answers to Question 2 a-d
	 *
	 * ONLY PARTS OF THIS METHOD HAVE BEEN IMPLEMENTED FOR YOU, so you will need
	 * to modify this code to make it work.
	 *
	 * ALSO, the breadthFirstSearch algorithm below has NOT been implemented
	 * yet, so you will need to complete this in order to make this method work
	 * properly.
	 *
	 * @param source
	 *            the source node to start the breadth-first search and write
	 *            the report about
	 */
	public void printSimplePathReport(int source) {
		// Report on shortest path from source for non-Kevin Bacon actors
		Map<Integer, BFSData> bfsMap = breadthFirstSearch(source);

		// TODO: create a list of integers (similar to what you did in
		// printPathReport(source)) to be able to create a new Statistics object
		// (this time you should really only need one Statistics object).

		// TODO: report on a single line the source node, the fraction of actors
		// reached from that source node, and the average distance to the source
		// node. This method will be called many times, so needs to be simpler
		// than the full report for Kevin Bacon.
	}

	/**
	 * This method conducts a depth-first search of this graph. The depth-first
	 * search has no source node, so iterates through the entire list of nodes
	 * until all have been visited. The method will return an array list
	 * containing the size of each connected component. For example, a graph
	 * with 4 connected components with 4, 10, 300, and 2 nodes respectively
	 * would return the array list: [4, 10, 300, 2].
	 * 
	 * @return an ArrayList containing the size of each connected component.
	 */
	private ArrayList<Integer> depthFirstSearch() {
		// for each vertex u in G
		ArrayList<Integer> liste = new ArrayList<Integer>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		for (int i : nodes.keySet()) {
			// if visited (u) = false
			visited.put(i, false);

		}
		for (int j : nodes.keySet()) {
			if (visited.get(j) == false) {
				liste.add(dfsVisit(j, visited));
			}
		}
		return liste;
		// visited(u) = false
		// next
		// for each vertex u in G
		// if not visited(u)
		// DFS-Visit( G, u, visited )
		// end if
		// next

		// TODO: implement the depth-first search algorithm. You should use this
		// iterative version, rather than the recursive one from lecture or in
		// the textbook (recursion is too slow here!).

		// You should use the dfsVisit helper method below to make this work.

		// IMPORTANT NOTE: you'll need to modify the depth-first search
		// algorithm slightly to be able to provide the list of integers that
		// contains the size of each connected component.

	}

	/**
	 * This method is a helper method for the depthFirstSearch method. This
	 * method takes in a starting vertex, u, and a map with nodes (integers) as
	 * keys and whether nodes have been visited yet (booleans) as values. The
	 * method returns the size of the connected component that u belongs to, and
	 * updates the map so that all nodes in that component are visited.
	 * 
	 * @param u
	 *            the starting vertex in this connected component
	 * @param visited
	 *            a map from nodes (integers) to whether they have been visited
	 *            yet (booleans)
	 * @return the size of u's connected component
	 */
	private int dfsVisit(int u, Map<Integer, Boolean> visited) {

		// let verticesToVisit be an empty stack
		int counter = 1;
		Stack<Integer> verticesToVisit = new Stack<Integer>();

		verticesToVisit.push(u);
		visited.put(u, true);

		while (verticesToVisit.size() != 0) {
			u = verticesToVisit.pop();
			// for each vertex w adjacent to u in G
			for (int w : nodes.get(u).getAdjacencyList()) {
				// if not visited(w)
				if (visited.get(w) == false) {
					verticesToVisit.push(u);
					verticesToVisit.push(w);
					visited.put(w, true);
					counter++;
					break;
				}
				
			}
		}
return counter;
		// TODO: implement this helper method

		// IMPORTANT NOTE: you'll need to modify the DFS-Visit algorithm
		// slightly to be able to return a count of a component's size

		//throw new UnsupportedOperationException();
	}

	/**
	 * This method conducts a breadth-first search of this graph from a source
	 * node. The method returns a map from each node (integer) as keys to
	 * breadth-first search information about that node (BFSData) as values. The
	 * BFSData object contains information about (a) the distance to that node
	 * from the source node and (b) that node's parent in the BFS tree resulting
	 * from this breadth-first search.
	 * 
	 * @param source
	 *            the source node to start the breadth-first search
	 * @return a map from nodes (integers) to breadth-first search data for each
	 *         node (BFSData)
	 */
	private Map<Integer, BFSData> breadthFirstSearch(int source) {
		HashMap<Integer, BFSData> graphic = new HashMap<Integer, BFSData>();
		// Source: https://en.wikipedia.org/wiki/Breadth-first_search
		//
		// for each node n in Graph:
		// n.distance = INFINITY
		// n.parent = NIL
		for (int n : nodes.keySet()) {
	

			graphic.put(n, new BFSData(BFSData.INFINITY, BFSData.NIL));
		}
			// create empty queue Q
			Queue<Integer> Q = new LinkedList<Integer>();
			graphic.get(source).distance = 0;
			Q.add(source);
			//
			// root.distance = 0
			// Q.enqueue(root)
			//
			// while Q is not empty:
			int current =0;
			while (!Q.isEmpty()) {
				 current = Q.poll();
				for (int w : nodes.get(current).getAdjacencyList()) {
					if (graphic.get(w).distance == -1) {
						graphic.get(w).distance = graphic.get(current).distance + 1;
						graphic.get(w).parent = current;
						Q.add(w);
					}
				

			}
		}
		// current = Q.dequeue()
		// for each node n that is adjacent to current:
		// if n.distance == INFINITY:
		// n.distance = current.distance + 1
		// n.parent = current
		// Q.enqueue(n)

		// TODO: implement the breadth-first search algorithm. I've included a
		// second version from Wikipedia above, but the one from lecture is
		// similar (and also works).

		// IMPORTANT NOTE: you'll need to modify the breadth-first search
		// algorithm slightly to be able to provide a map data structure that
		// contains the breadth-first search information (distance and parent)
		// for each node.

		// ANOTHER IMPORTANT NOTE: You should make use of the BFSData object
		// that I've already created for you to be able to store information
		// about distances and parent nodes.

		return graphic;
	}

	/**
	 * This static method allows you to read in an adjacency list from an
	 * uncompressed file (e.g., small-adj.txt), whose format is specified in the
	 * project description.
	 *
	 * @param filename
	 *            The name of the uncompressed adjacency list text file
	 * @return A {@link Graph} object containing each node and its adjacency
	 *         list
	 * @throws IOException
	 *             If there is an error reading the file
	 */
	public static Graph createGraphFromTxt(String filename) throws IOException {
		Graph graph = new Graph();

		Scanner scanner = new Scanner(new File(filename));
		graph.readFromScanner(scanner, -1);
		scanner.close();
		return graph;
	}

	/**
	 * This static method allows you to read in an adjacency list from a
	 * compressed file (e.g., adj.txt.gz), whose format is specified in the
	 * project description.
	 *
	 * @param filename
	 *            The name of the compressed adjacency list text file
	 * @return A {@link Graph} object containing each node and its adjacency
	 *         list
	 * @throws IOException
	 *             If there is an error reading the file
	 */
	public static Graph createGraphFromGzip(String filename) throws IOException {
		Graph graph = new Graph();

		FileInputStream fileStream = new FileInputStream(filename);
		GZIPInputStream zipStream = new GZIPInputStream(fileStream);
		Scanner scanner = new Scanner(zipStream);

		graph.readFromScanner(scanner, 50000);

		scanner.close();

		return graph;
	}

	/**
	 * This private method is used by both createGraphFromTxt and
	 * createGraphFromGzip to read in the data from a Scanner object. You should
	 * be able to understand what's going on in this code, I've provided it here
	 * so your project will take less time to complete.
	 *
	 * @param scanner
	 *            the Scanner object from which to read adjacency lists
	 * @param statusAfterCount
	 *            Report to the console after statusAfterCount number of lines
	 *            have been read from the file (-1 to not report anything)
	 */
	private void readFromScanner(Scanner scanner, int statusAfterCount) {
		if (scanner.hasNextLine()) {
			if (scanner.hasNextInt()) {
				scanner.nextInt(); // ignore number of records
			}
			scanner.nextLine();
		}

		int i = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Scanner lineScanner = new Scanner(line);

			GraphNode node = null;
			if (lineScanner.hasNextInt()) {
				int value = lineScanner.nextInt();
				node = new GraphNode(value);
				nodes.put(value, node);
			}

			while (lineScanner.hasNextInt()) {
				node.edgeTo(lineScanner.nextInt());
			}
			lineScanner.close();

			i++;
			if (statusAfterCount > 0 && i % statusAfterCount == 0) {
				System.out.printf("Processed %,d records so far...\n", i);
			}
		}
	}

}
