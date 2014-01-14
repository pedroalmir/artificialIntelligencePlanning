/**
 * 
 */
package com.pedroalmir.model.graph;

import java.util.HashMap;
import java.util.List;

/**
 * @author Pedro Almir
 *
 */
public class Graph {
	
	private String name;
	private HashMap<Node, List<Edge>> graph;
	private boolean bidirectional;
	
	/**
	 * @param name
	 * @param graph
	 */
	public Graph(String name, HashMap<Node, List<Edge>> graph) {
		super();
		this.name = name;
		this.graph = graph;
		this.bidirectional = true;
	}
	/**
	 * @param name
	 * @param bidirectional
	 */
	public Graph(String name, boolean bidirectional) {
		super();
		this.name = name;
		this.bidirectional = bidirectional;
		this.graph = new HashMap<Node, List<Edge>>();
	}
	/**
	 * @param name
	 */
	public Graph(String name) {
		super();
		this.name = name;
		this.bidirectional = true;
		this.graph = new HashMap<Node, List<Edge>>();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the graph
	 */
	public HashMap<Node, List<Edge>> getGraph() {
		return graph;
	}
	/**
	 * @param graph the graph to set
	 */
	public void setGraph(HashMap<Node, List<Edge>> graph) {
		this.graph = graph;
	}
	/**
	 * @return the bidirectional
	 */
	public boolean isBidirectional() {
		return bidirectional;
	}
	/**
	 * @param bidirectional the bidirectional to set
	 */
	public void setBidirectional(boolean bidirectional) {
		this.bidirectional = bidirectional;
	}
}
