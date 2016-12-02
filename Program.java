package project.p3;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * This is the main program that you will run.
 *
 * @author <your name> - you don't have to do much to this file, but you'll
 *         probably change it enough that it becomes yours.
 *
 */
public class Program {
    /**
     * This is the main method of your program.
     *
     * @param args
     *            Command-line arguments
     * @throws IOException
     *             If reading from your file had an error.
     */
    public static void main(String[] args) throws IOException {
	// TODO: create several sample adjacency lists in text files yourself to
	// test your code before running it on the large Kevin Bacon dataset

	// METHOD #1: you can read compressed graphs from files ending in .gz
	Graph graph = Graph.createGraphFromGzip("adj.txt.gz");

	// METHOD #2: you can read uncompressed graphs that you create yourself,
	// as long as they conform to the format in the project description.
	// small-adj.txt is an example of one of these (which you can open in
	// Notepad).
	//
	// Graph graph = Graph.readGraphFromTxt("small-adj.txt");

	// METHOD #3: you can also create custom graphs within the code as
	// follows, but it is probably easier to just use your own test file.
	//
	// Graph graph = new Graph();
	//
	// graph.addNode(1);
	// graph.addNode(2);
	// graph.addNode(3);
	// graph.addNode(4);
	//
	// graph.addEdge(1, 2);
	// graph.addEdge(1, 3);
	// graph.addEdge(1, 4);
	// graph.addEdge(2, 1);
	// graph.addEdge(3, 1);
	// graph.addEdge(3, 4);
	// graph.addEdge(4, 1);
	// graph.addEdge(4, 3);

	graph.printComponentReport();
	System.out.println();
	graph.printPathReport(357650); // Kevin Bacon
	// graph.printPathReport(2);

	// These two lines of code are beyond the scope of what you've learned
	// in class, but you should be able to code them in a different way (you
	// just don't have to, because I did it for you!)
	//
	// What the first part does is create a list of integers from 0 to n,
	// where n is the number of vertices in the graph.
	//
	// What the second line does is randomize the order of that list.
	List<Integer> allSources = IntStream.range(0, graph.numVertices())
		.boxed()
		.collect(Collectors.toList());
	Collections.shuffle(allSources);

	// TODO: you can change the 1000 to other numbers (and may need to,
	// until you optimize your code).
	//
	// This for loop just takes the first N of that randomized list.
	for (int source : allSources.subList(0, 1000)) {
	    graph.printSimplePathReport(source);
	}
    }
}
