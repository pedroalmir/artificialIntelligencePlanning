/**
 * 
 */
package com.pedroalmir.secondweek.problem;

import java.util.LinkedList;

import com.pedroalmir.secondweek.problem.base.EightPuzzleProblem;
import com.pedroalmir.secondweek.puzzle.Board;

/**
 * @author Pedro Almir
 * 
 */
public class FirstExamQuestionProblem implements EightPuzzleProblem {

	private Board goal = new Board(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });

	@Override
	public Board getInitialNode() {
		return new Board(new int[] { 1, 6, 4, 8, 7, 0, 3, 2, 5 });
	}

	@Override
	public boolean goalTest(Board board) {
		return this.goal.equals(board);
	}

	@Override
	public LinkedList<Board> expand(Board actualBoard) {
		return new LinkedList<Board>(actualBoard.getSuccessors());
	}

}
