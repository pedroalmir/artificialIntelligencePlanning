/**
 * 
 */
package com.pedroalmir.secondweek.puzzle;

import java.util.Comparator;

/**
 * @author Pedro Almir
 *
 */
public class BoardComparator implements Comparator<Board>{

	@Override
	public int compare(Board b1, Board b2) {
		/* function h(n) = distance to the nearest goal node - heuristic function */
		int h1 = b1.getManhattanDistance();
		/* function g(n) = the cost to reach the node n - cost function */
		int g1 = b1.getPathLength();
		Integer f1 = new Integer(h1+g1); 
		/* function h(n) = distance to the nearest goal node - heuristic function */
		int h2 = b2.getManhattanDistance();
		/* function g(n) = the cost to reach the node n - cost function */
		int g2 = b2.getPathLength();
		Integer f2 = new Integer(h2+g2);
		return f1.compareTo(f2);
	}

}
