/**
 * 
 */
package com.pedroalmir.secondweek.problem.base;

import java.util.LinkedList;

import com.pedroalmir.secondweek.puzzle.Board;

/**
 * @author Pedro Almir
 *
 */
public interface EightPuzzleProblem {

	/**
	 * @return initial node
	 */
	Board getInitialNode();
	/**
	 * @param board
	 * @return <code>true</code> if this node is the goal
	 */
	boolean goalTest(Board board);
	/**
	 * @param actualBoard
	 * @return list of successor nodes
	 */
	LinkedList<Board> expand(Board actualBoard);
}
