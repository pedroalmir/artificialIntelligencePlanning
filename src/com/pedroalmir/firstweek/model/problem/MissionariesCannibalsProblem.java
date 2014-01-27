package com.pedroalmir.firstweek.model.problem;

import java.util.Iterator;
import java.util.LinkedHashSet;

import com.pedroalmir.firstweek.model.graph.Graph;
import com.pedroalmir.firstweek.model.graph.Node;
import com.pedroalmir.firstweek.model.problem.base.Problem;
import com.pedroalmir.firstweek.model.problem.base.Strategy;

public class MissionariesCannibalsProblem implements Problem{
	
	private LinkedHashSet<Node> tabuList;
	
	/**
	 * Default constructor
	 */
	public MissionariesCannibalsProblem() {
		this.tabuList = new LinkedHashSet<Node>();
	}

	@Override
	public Graph createGraph() {
		return null;
	}

	@Override
	public Node getInitialNode() {
		Node initialNode = new Node("Três missionários e três canibais na margem esquerda do rio");
		initialNode.getInformations().put("missionariesNumberOnLeft", new Integer(3));
		initialNode.getInformations().put("cannibalsNumberOnLeft", new Integer(3));
		
		initialNode.getInformations().put("boat", "left");
		return initialNode;
	}

	@Override
	public LinkedHashSet<Node> expand(Node node) {
		/* Add node to tabu list*/
		this.tabuList.add(node);
		LinkedHashSet<Node> result = new LinkedHashSet<Node>();
		/* Missionários na margem esquerda e direita */
		Integer missionariesOnLeft = (Integer) node.getInformations().get("missionariesNumberOnLeft");
		Integer missionariesOnRight = new Integer(3) - missionariesOnLeft;
		/* Canibais na margem esquerda e direita */
		Integer cannibalsOnLeft = (Integer) node.getInformations().get("cannibalsNumberOnLeft");
		Integer cannibalsOnRight = new Integer(3) - cannibalsOnLeft;
		/* Posição do barco */
		String boat = (String) node.getInformations().get("boat");
		/* Variáveis auxiliares. Apenas para manter a organização do código. */
		Integer min = new Integer(1);
		Integer max = new Integer(2);
		
		if(boat.equals("left")){
			/* 1. O número de missionários na margem esquerda menos o valor mínimo transportado pelo barco
			 * deve ser maior ou igual ao número de canibais que ficarão do lado esquerdo.
			 * 
			 * 2. O número de missionários na margem direira mais o valor mínimo transportador pelo barco
			 * deve ser maior ou igual ao número de canibais que estão do lado direito.
			 * 
			 * Ação: Levar apenas um missionário para a margem direita. */
			if((!(cannibalsOnLeft > missionariesOnLeft-min) || missionariesOnLeft-min == 0) && (!(cannibalsOnRight > missionariesOnRight) || (missionariesOnRight == 0)) && (missionariesOnLeft-min >= 0)){
				Node newNode = new Node("Ação: Levar " + min + " missionário para a margem direita.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft-min);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft);
				newNode.getInformations().put("boat", "right");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* 1. O número de canibais na margem esquerda menos o valor mínimo transportado pelo barco
			 * deve ser menor ou igual ao número de missionários que ficarão do lado esquerdo.
			 * 
			 * 2. O número de canibais na margem direira mais o valor mínimo transportador pelo barco
			 * deve ser menor ou igual ao número de missionários que estão do lado direito.
			 * 
			 * Ação: Levar apenas um canibal para a margem direita. */
			if(((!(cannibalsOnLeft-min > missionariesOnLeft) || missionariesOnLeft == 0) && (!(cannibalsOnRight+min > missionariesOnRight) || (missionariesOnRight == 0)) && (cannibalsOnLeft-min >= 0))){
				Node newNode = new Node("Ação: Levar " + min + " canibal para a margem direita do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft-min);
				newNode.getInformations().put("boat", "right");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar dois um missionários para a margem direita. */
			if((!(cannibalsOnLeft > missionariesOnLeft-max) || missionariesOnLeft-max == 0) && !(cannibalsOnRight > missionariesOnRight+max) && (missionariesOnLeft-max >= 0)){
				Node newNode = new Node("Ação: Levar " + max + " missionários para a margem direita do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft-max);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft);
				newNode.getInformations().put("boat", "right");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar dois um canibais para a margem direita. */
			if(((!(cannibalsOnLeft-max > missionariesOnLeft) || missionariesOnLeft == 0) && (!(cannibalsOnRight+max > missionariesOnRight) || (missionariesOnRight == 0)) && (cannibalsOnLeft-max >= 0))){
				Node newNode = new Node("Ação: Levar " + max + " canibais para a margem direita do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft-max);
				newNode.getInformations().put("boat", "right");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar um canibal e um missionário para a margem direita. */
			if(!(cannibalsOnLeft-min > missionariesOnLeft-min) && !(cannibalsOnRight+min > missionariesOnRight+min) && (cannibalsOnLeft-min >= 0)){
				Node newNode = new Node("Ação: Levar 1 missionário e 1 canibal para a margem direita do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft-min);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft-min);
				newNode.getInformations().put("boat", "right");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
		}else{
			/* Ação: Levar apenas um missionário para a margem esquerda. */
			if((!(cannibalsOnRight > missionariesOnRight-min) || missionariesOnRight-min == 0) && !(cannibalsOnLeft >= missionariesOnLeft+min) && (missionariesOnRight-min >= 0)){
				Node newNode = new Node("Ação: Levar " + min + " missionário para a margem esquerda do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft+min);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft);
				newNode.getInformations().put("boat", "left");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar apenas um canibal para a margem esquerda. */
			if(((!(cannibalsOnRight-min > missionariesOnRight) || missionariesOnRight == 0) && (!(cannibalsOnLeft+min > missionariesOnLeft) || missionariesOnLeft == 0) && (cannibalsOnRight-min >= 0))
					|| (missionariesOnLeft == 0)){
				Node newNode = new Node("Ação: Levar " + min + " canibal para a margem esquerda do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft+min);
				newNode.getInformations().put("boat", "left");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar dois um missionários para a margem esquerda. */
			if((!(cannibalsOnRight > missionariesOnRight-max) || missionariesOnRight-max == 0)  && !(cannibalsOnLeft > missionariesOnLeft+max) && (missionariesOnRight-max >= 0)){
				Node newNode = new Node("Ação: Levar " + max + " missionários para a margem esquerda do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft+max);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft);
				newNode.getInformations().put("boat", "left");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar dois um canibais para a margem esquerda. */
			if(((!(cannibalsOnRight-max > missionariesOnRight) || missionariesOnRight == 0) && (!(cannibalsOnLeft+max > missionariesOnLeft) || missionariesOnLeft == 0) && (cannibalsOnRight-max >= 0))
					|| (missionariesOnLeft == 0)){
				Node newNode = new Node("Ação: Levar " + max + " canibais para a margem esquerda do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft+max);
				newNode.getInformations().put("boat", "left");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
			/* Ação: Levar um canibal e um missionário para a margem esquerda. */
			if(!(cannibalsOnRight-min > missionariesOnRight-min) && !(cannibalsOnLeft+min > missionariesOnLeft+min) && (cannibalsOnRight-min >= 0) && (missionariesOnRight-min >= 0)){
				Node newNode = new Node("Ação: Levar 1 missionário e 1 canibal para a margem esquerda do rio.");
				newNode.getInformations().put("missionariesNumberOnLeft", missionariesOnLeft+min);
				newNode.getInformations().put("cannibalsNumberOnLeft", cannibalsOnLeft+min);
				newNode.getInformations().put("boat", "left");
				/* Verify if newNode isn't present in tabulist */
				if(!this.tabuList.contains(newNode)){
					result.add(newNode);
				}
			}
		}
		return result;
	}

	@Override
	public boolean goalTest(Node node) {
		if(node.getInformations().get("missionariesNumberOnLeft").equals(new Integer(0))
				&& node.getInformations().get("cannibalsNumberOnLeft").equals(new Integer(0))
				&& node.getInformations().get("boat").equals("right")){
			return true;
		}
		return false;
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

}
