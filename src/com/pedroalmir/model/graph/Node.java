/**
 * 
 */
package com.pedroalmir.model.graph;

import java.util.HashMap;

/**
 * @author Pedro Almir
 *
 */
public class Node {
	
	private String name;
	private HashMap<String, Object> informations;
	
	/**
	 * @param name
	 */
	public Node(String name) {
		super();
		this.name = name;
		this.informations = new HashMap<String, Object>();
	}
	
	/**
	 * @param name
	 * @param informations
	 */
	public Node(String name, HashMap<String, Object> informations) {
		super();
		this.name = name;
		this.informations = informations;
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
	 * @return the informations
	 */
	public HashMap<String, Object> getInformations() {
		return informations;
	}
	/**
	 * @param informations the informations to set
	 */
	public void setInformations(HashMap<String, Object> informations) {
		this.informations = informations;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Node [name=" + name + ", informations=" + informations + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((informations == null) ? 0 : informations.hashCode());
		if(informations == null){
			result = prime * result + ((name == null) ? 0 : name.hashCode());
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		
		Node other = (Node) obj;
		if (informations == null) {
			if (other.informations != null){
				return false;
			}
			
			/* Estou considerando o nome apenas nos casos em que as informações do nó são nulas. */
			if (name == null) {
				if (other.name != null){
					return false;
				}
			} else if (!name.equals(other.name)){
				return false;
			}
		} else if (!informations.equals(other.informations)){
			return false;
		}
		return true;
	}
}
