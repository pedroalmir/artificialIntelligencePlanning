/**
 * 
 */
package com.pedroalmir.secondweek.astar;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.PriorityQueue;

import com.pedroalmir.secondweek.problem.ThirdExamQuestionProblem;
import com.pedroalmir.secondweek.problem.base.EightPuzzleProblem;
import com.pedroalmir.secondweek.puzzle.Board;
import com.pedroalmir.secondweek.puzzle.BoardComparator;

/**
 * A Star Graph Search
 * 
 * @Coursera
 * @author Pedro Almir
 *
 */
public class GraphSearch {
	/**
	 * A Star Graph Search
	 * @param problem
	 * @return eight puzzle problem
	 */
	public static LinkedHashSet<Board> aStarGraphSearch(EightPuzzleProblem problem){
		PriorityQueue<Board> queue = new PriorityQueue<Board>(1, new BoardComparator());
		queue.add(problem.getInitialNode());
		LinkedHashSet<Board> visitedNodes = new LinkedHashSet<Board>(queue);
		while(!queue.isEmpty()){
			Board actualBoard = queue.remove();
			/*System.out.println(actualBoard.toString());*/
			if(problem.goalTest(actualBoard)){
				return new LinkedHashSet<Board>(actualBoard.getPathFromStartNode());
			}else {
				List<Board> boards = problem.expand(actualBoard);
				for(Board b : boards){
					/* Used to find path from started node */
					b.setParent(actualBoard);
					if(!visitedNodes.contains(b)){
						queue.add(b);
						visitedNodes.add(b);
					}
				}
			}
			
		}
		return null;
	}
	
	public static LinkedHashSet<Board> aStarGraphSearchSecondVersion(EightPuzzleProblem problem){
		PriorityQueue<Board> queue = new PriorityQueue<Board>(1, new BoardComparator());
		queue.add(problem.getInitialNode());
		LinkedHashMap<Board, Integer> visitedNodes = new LinkedHashMap<Board, Integer>();
		visitedNodes.put(problem.getInitialNode(), 0);
		while(!queue.isEmpty()){
			Board actualBoard = queue.remove();
			/*System.out.println(actualBoard.toString());*/
			if(problem.goalTest(actualBoard)){
				return new LinkedHashSet<Board>(actualBoard.getPathFromStartNode());
			}else {
				List<Board> boards = problem.expand(actualBoard);
				for(Board b : boards){
					/* Used to find path from started node */
					b.setParent(actualBoard);
					if(visitedNodes.get(b) == null){
						queue.add(b);
						visitedNodes.put(b, b.getPathLength());
					}else{
						/* Problem of shortest path for two equals board */
						Integer visitedBoardPathLength = visitedNodes.get(b);
						if(b.getPathLength() < visitedBoardPathLength){
							queue.add(b);
							visitedNodes.put(b, b.getPathLength());
						}
					}
				}
			}
			
		}
		return null;
	}
	
	public static LinkedHashSet<Board> aStarGraphSearchThirdVersion(EightPuzzleProblem problem){
		PriorityQueue<Board> queue = new PriorityQueue<Board>(1, new BoardComparator());
		queue.add(problem.getInitialNode());
		LinkedHashMap<Board, Integer> visitedNodes = new LinkedHashMap<Board, Integer>();
		visitedNodes.put(problem.getInitialNode(), 0);
		LinkedHashSet<Board> results = new LinkedHashSet<Board>();
		while(!queue.isEmpty()){
			Board actualBoard = queue.remove();
			/*System.out.println(actualBoard.toString());*/
			if(problem.goalTest(actualBoard)){
				System.out.println(actualBoard.getPathLength());
				results.add(actualBoard);
			}else {
				List<Board> boards = problem.expand(actualBoard);
				for(Board b : boards){
					/* Used to find path from started node */
					b.setParent(actualBoard);
					if(visitedNodes.get(b) == null){
						queue.add(b);
						visitedNodes.put(b, b.getPathLength());
					}else{
						/* Problem of shortest path for two equals board */
						Integer visitedBoardPathLength = visitedNodes.get(b);
						if(b.getPathLength() < visitedBoardPathLength){
							queue.add(b);
							visitedNodes.put(b, b.getPathLength());
						}
					}
				}
			}
			
		}
		return results;
	}
	
	public static void main(String[] args) {
		/*System.out.println("###################################################");
		System.out.println("First Sample");
		System.out.println("###################################################");
		BasicEightPuzzleProblem problem = new BasicEightPuzzleProblem();
		LinkedHashSet<Board> result = GraphSearch.aStarGraphSearchSecondVersion(problem);
		if(result != null && !result.isEmpty()){
			System.out.println(result.iterator().next().toString());
			System.out.println("Number of boards: " + result.size());
			System.out.println("Number of actions: " + (result.size()-1));
		}else{
			System.out.println("Result not found");
		}
		System.out.println("###################################################");
		System.out.println("First Question of the exam");
		System.out.println("###################################################");
		FirstExamQuestionProblem firstExamProblem = new FirstExamQuestionProblem();
		result = GraphSearch.aStarGraphSearchSecondVersion(firstExamProblem);
		if(result != null && !result.isEmpty()){
			System.out.println(result.iterator().next().toString());
			System.out.println("Number of boards: " + result.size());
			System.out.println("Number of actions: " + (result.size()-1));
		}else{
			System.out.println("Result not found");
		}
		System.out.println("###################################################");
		System.out.println("Second Question of the exam");
		System.out.println("###################################################");
		SecondExamQuestionProblem secondExamProblem = new SecondExamQuestionProblem();
		result = GraphSearch.aStarGraphSearchSecondVersion(secondExamProblem);
		if(result != null && !result.isEmpty()){
			System.out.println("Number of boards: " + result.size());
			System.out.println("Number of actions: " + (result.size()-1));
		}else{
			System.out.println("Result not found");
		}*/
		System.out.println("###################################################");
		System.out.println("Third Question of the exam");
		System.out.println("###################################################");
		ThirdExamQuestionProblem thirdExamProblem = new ThirdExamQuestionProblem();
		LinkedHashSet<Board> result = GraphSearch.aStarGraphSearchThirdVersion(thirdExamProblem);
		if(result != null && !result.isEmpty()){
			System.out.println(result.iterator().next().toString());
			System.out.println("Number of boards: " + result.size());
		}else{
			System.out.println("Result not found");
		}
		System.out.println("###################################################");
	}
}
