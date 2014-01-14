/**
 * 
 */
package com.pedroalmir.treeSearch;

import java.util.LinkedHashSet;

import com.pedroalmir.model.graph.Node;
import com.pedroalmir.model.problem.Problem;
import com.pedroalmir.model.problem.Strategy;

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
	public LinkedHashSet<Node> execute(Problem problem, Strategy strategy){
		LinkedHashSet<Node> fringe = new LinkedHashSet<Node>();
		fringe.add(problem.getInitialNode());
		while(!fringe.isEmpty()){
			Node actualNode = problem.selectFrom(fringe, strategy);
			if(problem.goalTest(actualNode)){
				fringe.add(actualNode);
				return fringe;
			}else{
				fringe.addAll(problem.expand(actualNode));
			}
		}
		return null;
	}
}
