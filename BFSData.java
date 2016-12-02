package project.p3;

/**
 * The BFSData class is a helper class that can store the distance and parent
 * information that's required for the breadth-first search algorithm.
 *
 * The INFINITY and NIL constants are useful for testing if this node has been
 * set yet or not.
 *
 * @author Mark Hancock
 *
 */
public class BFSData {
    /**
     * Constant to represent the value of infinity for distance.
     */
    public static final int INFINITY = -1;

    /**
     * Constant to represent the value of nil for parent (i.e., no parent).
     */
    public static final int NIL = -1;

    /**
     * The distance to this node in the BFS tree.
     */
    public int distance;

    /**
     * The parent node of this node in the BFS tree.
     */
    public int parent;

    /**
     * Constructs a new BFSData object with infinity as the distance and nil as
     * the parent value.
     */
    public BFSData() {
	this(INFINITY, NIL);
    }

    /**
     * Constructs a new BFSData object with the specified distance and parent.
     *
     * @param distance
     *            the distance for this node in the BFS tree
     * @param parent
     *            the parent of this node in the BFS tree
     */
    public BFSData(int distance, int parent) {
	this.distance = distance;
	this.parent = parent;
    }
}
