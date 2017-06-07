import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Graph.Java
 * 
 * $Id: Graph.java,v 1.5 2014/10/21 20:15:14 kbb8134 Exp $
 * 
 * $Log: Graph.java,v $
 * Revision 1.5  2014/10/21 20:15:14  kbb8134
 * Completed
 *
 * Revision 1.4  2014/10/21 20:07:32  kbb8134
 * Main works, but print path is one node short than it should be.
 *
 * Revision 1.3  2014/10/21 19:34:29  kbb8134
 * Routing.java created
 *
 * Revision 1.2  2014/10/21 16:12:16  kbb8134
 * Completed implementation for Graph and GraphNode classes
 *
 * Revision 1.1  2014/10/21 15:28:33  kbb8134
 * Added implementation for a couple of methods in Graph.java
 *
 * 
 */

/**
 * Graph is a representation of a graph, using GraphNode as nodes
 * 
 * @author kbb8134 - Kristopher Brown
 *
 */
public class Graph {
	private ArrayList<GraphNode> nodes = new ArrayList<GraphNode>();
	
	/**
	 * Instantiates a graph from the input specified by the filepath string
	 * 
	 * @param s - Filename
	 */
	public Graph(String s){
		FileInputStream f = null;
		String line = "";
		try {
			f = new FileInputStream(s);
		} catch (FileNotFoundException err) {
			System.err.println("Invalid filepath.");
			System.exit(0);
		}
		Scanner darkly = new Scanner(f);
		while(darkly.hasNextLine()){
			GraphNode n1 = null, n2 = null;
			String[] split = null;
			line = darkly.nextLine();
			split = line.split("\\s+");
			if((isInGraph(split[0])) == false){
				n1 = new GraphNode(split[0]);
				nodes.add(n1);
			}
			else{
				n1 = get(split[0]);
			}
			if((isInGraph(split[1])) == false){
				n2 = new GraphNode(split[1]);
				nodes.add(n2);
			}
			else{
				n2 = get(split[1]);
			}
			n1.addNeighbor(n2);
			n2.addNeighbor(n1);
		}	
	}
	/**
	 * toString creates a string representation of the graph
	 * 
	 * @return - A string representation of the graph.
	 */
	public String toString(){
		String str = "";
		for(GraphNode nbr : nodes){
			str = str + nbr.toString() + "\n";
		}
		str = "Graph Contents:\n" + str;
		return str;
	}
	
	/**
	 * isInGraph checks to see if a node by a certain exists in this graph
	 * 
	 * @param nodeName - A name for a possible node
	 * @return - True if the node exists in the graph
	 */
	public boolean isInGraph(String nodeName){
		boolean ret = false;
		String str = nodeName;
		int i = 0;
		while(i < nodes.size()){
			if (nodeName.equals(nodes.get(i).getName())){
				ret = true;
				break;
			}
			i++;
		}
		return ret;
	}
	
	/**
	 * canReachDFS checks if two nodes can reach each other
	 * 
	 * @param start
	 * @param finish
	 * @return
	 */
	public boolean canReachDFS(String start, String finish){
		boolean ret = false;
		ArrayList<GraphNode> visited = new ArrayList<GraphNode>();
		visited.add(get(start));
		//System.out.println(visited.get(0).toString());
		visitDFS(visited.get(0), visited);
		if (visited.contains(get(finish)) == true){
			ret = true;
		}
		return ret;
	}
	
	/**
	 * printPathDFS prints the path between two nodes
	 * 
	 * @param start
	 * @param finish
	 */
	public void printPathDFS(String start, String finish){
		ArrayList<GraphNode> visited = new ArrayList<GraphNode>();
		visited.add(get(start));
		String str = "";
		ArrayList<GraphNode> path = buildPathDFS(get(start), visited, get(finish));
		if(path.isEmpty() == true){
			System.out.print(start + "and" + finish + "are not connected"
					+ "by any path.\n");
		}
		else{
			for(GraphNode nodes : path){
				 str = str + nodes.getName() + ", ";
			}
			System.out.println(str.substring(0, str.length()-2));
		}
	}
	
	/**
	 * get returns the node with the given name
	 * 
	 * @param name - Name of the node
	 * @return - The named node
	 */
	public GraphNode get(String name){
		GraphNode n = null;
		String str = name;
		int i = 0;
		while(i < nodes.size()){
			if (str.equals(nodes.get(i).getName())){
				n = nodes.get(i);
				break;
			}
			i++;
		}
		return n;
	}
	
	/**
	 * Add adds a GraphNode to the list of nodes
	 * 
	 * @param n - the graphnode to be added
	 */
	
	/**
	 * visitDFS is a helper function for canReachDFS
	 * 
	 * @param node - A node to start with
	 * @param visited - A list of nodes that can be reached from the node
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<GraphNode> visitDFS(GraphNode node, ArrayList<GraphNode> visited){
		ArrayList<GraphNode> l = node.getNeighbors();
		for( GraphNode nbr : l){
			if (visited.contains(nbr) == false){
				visited.add(nbr);
				visitDFS(nbr, visited);
			}
		}
		return visited;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<GraphNode> buildPathDFS(GraphNode node, 
			ArrayList<GraphNode> visited, GraphNode finish){
		ArrayList<GraphNode> path = new ArrayList<GraphNode>();
		if(node == finish){
			path.add(node);
			return path;
		}
		ArrayList<GraphNode> l = node.getNeighbors();
		for(GraphNode nbr : l){
			if(visited.contains(nbr) == false){
				visited.add(nbr);
				if((path = buildPathDFS(nbr, visited, finish)) != null){
					path.add(0, node);
					return path;
				}
			}
		}
		return null;
	}
}//Graph