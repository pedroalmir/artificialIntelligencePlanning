/**
 * 
 */
package com.pedroalmir.model.problem;

import java.util.LinkedHashSet;

import com.pedroalmir.model.graph.Graph;
import com.pedroalmir.model.graph.Node;

/**
 * @author Pedro Almir
 *
 */
public interface Problem {
	/**
	 * @return graph that represents the problem
	 */
	Graph createGraph();
	/**
	 * @return initial node
	 */
	Node getInitialNode();
	/**
	 * @return set of nodes achieved from actual node
	 */
	LinkedHashSet<Node> expand(Node node);
	/**
	 * Verify if this node is the goal of problem
	 * @param node
	 * @return <code>true</code> if this node is the goal of problem
	 */
	boolean goalTest(Node node);
	/**
	 * @param fringe
	 * @return
	 */
	Node selectFrom(LinkedHashSet<Node> fringe, Strategy strategy);
}

