import java.util.Scanner;

/**
 * Routing.Java
 * 
 * $Id: Routing.java,v 1.2 2014/10/21 19:34:29 kbb8134 Exp $
 * 
 * $Log: Routing.java,v $
 * Revision 1.2  2014/10/21 19:34:29  kbb8134
 * Routing.java created
 *
 * Revision 1.1  2014/10/21 16:12:16  kbb8134
 * Completed implementation for Graph and GraphNode classes
 *
 * 
 */

/**
 * Routing is the main function for Graph and GraphNode
 * 
 * @author kbb8134 - Kristopher Brown
 *
 */
public class Routing {
	
	/**
	 * Asks the user for an input file, creates a graphs from the file
	 * Asks the user for two names and reports the path between those
	 * two nodess
	 * 
	 * @param args - commandline arguments, unused
	 */
	public static void main(String[] args){
		String start = "";
		String finish = "";
		System.out.println("Please enter input filepath: ");
		Scanner darkly = new Scanner(System.in);
		String in = darkly.next();
		Graph ILoveWheeler = new Graph(in);
		System.out.println("Enter a start node: ");
		start = darkly.next();
		System.out.println("Enter a finish node: ");
		finish = darkly.next();
		darkly.close();
		if((ILoveWheeler.canReachDFS(start, finish)) == true){
			System.out.println("It is possible to get from " + start +
					" to " + finish);
		}
		else{
			System.out.println("It isn't possible to get from " + start +
					" to " + finish);
		}
		ILoveWheeler.printPathDFS(start, finish);
	}//main
}//Routing
