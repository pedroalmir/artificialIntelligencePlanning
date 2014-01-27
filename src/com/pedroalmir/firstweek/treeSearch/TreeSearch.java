/**
 * 
 */
package com.pedroalmir.firstweek.treeSearch;

import java.util.LinkedHashSet;

import com.pedroalmir.firstweek.model.graph.Node;
import com.pedroalmir.firstweek.model.problem.MissionariesCannibalsProblem;
import com.pedroalmir.firstweek.model.problem.SimpleProblem;
import com.pedroalmir.firstweek.model.problem.base.Problem;
import com.pedroalmir.firstweek.model.problem.base.Strategy;

/**
 * General Tree Search Algorithm
 * 
 * @Coursera
 * @author Pedro Almir
 */
public class TreeSearch {
	/**
	 * @param problem
	 * @param strategy
	 * @return Path to Goal or null value
	 */
	public static LinkedHashSet<Node> execute(Problem problem, Strategy strategy){
		LinkedHashSet<Node> fringe = new LinkedHashSet<Node>();
		LinkedHashSet<Node> path = new LinkedHashSet<Node>();
		
		fringe.add(problem.getInitialNode());
		path.add(problem.getInitialNode());
		
		while(!fringe.isEmpty()){
			Node actualNode = problem.selectFrom(fringe, strategy);
			path.add(actualNode);
			if(problem.goalTest(actualNode)){
				return path;
			}else{
				fringe.addAll(problem.expand(actualNode));
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		MissionariesCannibalsProblem problem = new MissionariesCannibalsProblem();
		LinkedHashSet<Node> result = TreeSearch.execute(problem, Strategy.FIFO);
		System.out.println("Tree Search using FIFO: ");
		for(Node node : result){
			System.out.println("\taction> " + node.getName());
		}
		
		result = TreeSearch.execute(problem, Strategy.LIFO);
		System.out.println("Tree Search using LIFO: ");
		for(Node node : result){
			System.out.println("\taction> " + node.getName());
		}
	}
	
	public static void mainn(String[] args) {
		SimpleProblem simpleProblem = new SimpleProblem();
		LinkedHashSet<Node> result = TreeSearch.execute(simpleProblem, Strategy.FIFO);
		System.out.print("FIFO: ");
		for(Node node : result){
			System.out.print(node.getName() + ", ");
		}
		
		result = TreeSearch.execute(simpleProblem, Strategy.LIFO);
		System.out.print("\nLIFO: ");
		for(Node node : result){
			System.out.print(node.getName() + ", ");
		}
	}
}
