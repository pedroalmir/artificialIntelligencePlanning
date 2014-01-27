/**
 * 
 */
package com.pedroalmir.firstweek.model.problem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import com.pedroalmir.firstweek.model.graph.Edge;
import com.pedroalmir.firstweek.model.graph.Graph;
import com.pedroalmir.firstweek.model.graph.Node;
import com.pedroalmir.firstweek.model.problem.base.Problem;
import com.pedroalmir.firstweek.model.problem.base.Strategy;

/**
 * @author Pedro Almir
 *
 */
public class SimpleProblem implements Problem{
	
	private Graph graph;
	
	/**
	 * Default Constructor
	 */
	public SimpleProblem() {
		this.graph = createGraph();
	}

	@Override
	public Graph createGraph() {
		Node A = new Node("A");
		Node B = new Node("B");
		Node C = new Node("C");
		Node D = new Node("D");
		Node E = new Node("E");
		
		Edge edge1 = new Edge(A, B);
		Edge edge2 = new Edge(A, C);
		Edge edge3 = new Edge(C, D);
		Edge edge4 = new Edge(C, E);
		
		HashMap<Node, List<Edge>> graph = new HashMap<Node, List<Edge>>();
		LinkedList<Edge> linkedListA = new LinkedList<Edge>();
		linkedListA.add(edge1);
		linkedListA.add(edge2);
		graph.put(A, linkedListA);
		
		LinkedList<Edge> linkedListB = new LinkedList<Edge>();
		linkedListB.add(edge1);
		graph.put(B, linkedListB);
		
		LinkedList<Edge> linkedListC = new LinkedList<Edge>();
		linkedListC.add(edge2);
		linkedListC.add(edge3);
		linkedListC.add(edge4);
		graph.put(C, linkedListC);
		
		LinkedList<Edge> linkedListD = new LinkedList<Edge>();
		linkedListD.add(edge3);
		graph.put(D, linkedListD);
		
		LinkedList<Edge> linkedListE = new LinkedList<Edge>();
		linkedListE.add(edge4);
		graph.put(E, linkedListE);
		
		return new Graph("Simple Problem", graph);
	}

	@Override
	public Node getInitialNode() {
		return new Node("A");
	}

	@Override
	public LinkedHashSet<Node> expand(Node node) {
		LinkedHashSet<Node> nodes = new LinkedHashSet<Node>();
		for(Edge edge : this.getGraph().getGraph().get(node)){
			if(node.equals(edge.getBegin())){
				nodes.add(edge.getEnd());
			}else{
				nodes.add(edge.getBegin());
			}
		}
		return nodes;
	}

	@Override
	public boolean goalTest(Node node) {
		return node.equals(new Node("E"));
	}

	@Override
	public Node selectFrom(LinkedHashSet<Node> fringe, Strategy strategy) {
		if(strategy.equals(Strategy.FIFO)){
			Iterator<Node> iterator = fringe.iterator();
			while (iterator.hasNext()) {
				Node node = (Node) iterator.next();
				fringe.remove(node);
				return node;
			}
		}else if(strategy.equals(Strategy.LIFO)){
			Iterator<Node> iterator = fringe.iterator();
			while (iterator.hasNext()) {
				Node node = (Node) iterator.next();
				if(!iterator.hasNext()){
					fringe.remove(node);
					return node;
				}
			}
		}
		return null;
	}

	/**
	 * @return the graph
	 */
	public Graph getGraph() {
		return graph;
	}

}
