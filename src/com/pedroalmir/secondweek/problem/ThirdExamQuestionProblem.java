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
public class ThirdExamQuestionProblem implements EightPuzzleProblem {

	@Override
	public Board getInitialNode() {
		return new Board(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 });
	}

	@Override
	public boolean goalTest(Board board) {
		if(board.getPathLength() == 27){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public LinkedList<Board> expand(Board actualBoard) {
		return new LinkedList<Board>(actualBoard.getSuccessors());
	}

}
