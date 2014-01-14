/**
 * 
 */
package com.pedroalmir.model.graph;

/**
 * @author Pedro Almir
 *
 */
public class Edge {
	private String label;
	private Node begin;
	private Node end;
	
	/**
	 * @param label
	 * @param begin
	 * @param end
	 */
	public Edge(String label, Node begin, Node end) {
		super();
		this.label = label;
		this.begin = begin;
		this.end = end;
	}
	/**
	 * @param begin
	 * @param end
	 */
	public Edge(Node begin, Node end) {
		super();
		this.begin = begin;
		this.end = end;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the begin
	 */
	public Node getBegin() {
		return begin;
	}
	/**
	 * @param begin the begin to set
	 */
	public void setBegin(Node begin) {
		this.begin = begin;
	}
	/**
	 * @return the end
	 */
	public Node getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Node end) {
		this.end = end;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((begin == null) ? 0 : begin.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (begin == null) {
			if (other.begin != null)
				return false;
		} else if (!begin.equals(other.begin))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}
}
