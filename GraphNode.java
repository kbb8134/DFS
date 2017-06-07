import java.util.ArrayList;
import java.util.List;

/**
 * GraphNode.java
 * 
 * $Id: GraphNode.java,v 1.4 2014/10/21 16:12:16 kbb8134 Exp $
 * 
 * $Log: GraphNode.java,v $
 * Revision 1.4  2014/10/21 16:12:16  kbb8134
 * Completed implementation for Graph and GraphNode classes
 *
 * Revision 1.3  2014/10/21 15:28:33  kbb8134
 * Added implementation for a couple of methods in Graph.java
 *
 * Revision 1.2  2014/10/21 15:05:23  kbb8134
 * toString of GraphNode class implemented
 *
 * Revision 1.1  2014/10/21 14:59:37  kbb8134
 * GraphNode has some implementation so far, but does not have toString yet
 *
 * 
 */

/**
 * GraphNode is a class which represents a node on a graph.
 * 
 * @author kbb8134 Kristopher Brown
 *
 */
public class GraphNode {
	private String name;
	private ArrayList<GraphNode> neighbors;
	
	/**
	 * Constructor initialized with an empty list of neighbors
	 * 
	 * @param newname - The name of the node
	 */
	public GraphNode(String newname){
		neighbors = new ArrayList<GraphNode>();
		String str = newname;
		this.name = str;
	}
	
	/**
	 * A getter for this node's name
	 * 
	 * @return - This node's name
	 */
	public String getName(){
		String str = name;
		return str;
	}
	
	/**
	 * Adds a neighbor to this node's list
	 * 
	 * @param g - A graphnode to be added as a neighbor
	 */
	public void addNeighbor(GraphNode g){
		GraphNode node = g;
		neighbors.add(node);
	}
	
	/**
	 * A getter for the list of neighbors
	 * 
	 * @return - The list of neighbors for this node
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList getNeighbors(){
		ArrayList l = neighbors;
		return l;
	}
	
	/**
	 * Method to generate a string representation of GraphNode
	 * 
	 * @return - A string representing the node
	 */
	public String toString(){
		String str = "";
		str = name + ":  ";
		
		for(GraphNode nbr : neighbors){
			str = str + nbr.getName() + ",  ";
		}
		return (str.substring(0, str.length()-2));
	}
}//GraphNode
