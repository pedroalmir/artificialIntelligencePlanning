/**
 * 
 */
package com.pedroalmir.secondweek.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * 
 * A representation for the 8-puzzle board + some useful methods for generating successor states.
 * 
 * @author Pedro Almir
 * 
 */
public class Board implements Comparable<Board>{
	/**
	 * The internal board representation
	 */
	protected int[] board;
	/** Constants for specifying move directions */
	public static final int UP = -3;
	public static final int LEFT = -1;
	public static final int DOWN = 3;
	public static final int RIGHT = 1;
	/** a complete list of all legal moves */
	protected static final int[] legalMoves = new int[] { UP, LEFT, DOWN, RIGHT };
	/** helps us keep track of how a solution was generated */
	protected Board parent;
	/** keeps track of the estimated distance to the goal state - f(n) */
	protected int costEstimated;
	
	public static void main(String[] args) {
		/*Board randomBoard = Board.randomBoard();
		System.out.println(randomBoard.toString());
		for(Board board : randomBoard.getSuccessors()){
			System.out.println(board.toString());
		}*/
		Board board = new Board(new int[]{7, 2, 4, 5, 0, 6, 8, 3, 1});
		System.out.println(board.getManhattanDistance());
		System.out.println(board.getNumberOfMisplacedTiles());
	}

	/**
	 * Creates an instance of the 8-puzzle board from a 1D array of ints;<br>
	 * The Blank tile is represented by 0;
	 * 
	 * @param board
	 *            an array of 9 ints
	 * @param parent
	 *            the board that generated this one as a successor
	 */
	public Board(int[] board, Board parent) {
		this.board = board;
		this.parent = parent;
	}

	/**
	 * Creates an instance of the 8-puzzle board from a 1D array of ints;<br>
	 * Blank tile is represented by 0;
	 * 
	 * @param board
	 *            an array of 9 ints
	 */
	public Board(int[] board) {
		this(board, null);
	}

	/**
	 * Returns the tile at a given location
	 * 
	 * @param x
	 *            the X coordinate [0-2]
	 * @param y
	 *            the Y coordinate [0-2]
	 * @return the tile at the specified location (0 for blank)
	 */
	public int getTileAt(int x, int y) {
		return board[3 * y + x];
	}

	/**
	 * Generate a specific successor of the current board by moving the
	 * blank in the specified direction
	 * 
	 * @param moveDirection
	 *            the direction for blank to move
	 * @return a new Board object or null if the specified move is not legal
	 */
	public Board getSuccessor(int moveDirection) {
		int newPos = getBlankLocation() + moveDirection;
		// check if the move is legal
		if (newPos < 0 || newPos >= board.length) {
			return null;
		}
		if (getBlankLocationX() == 0 && moveDirection == LEFT || getBlankLocationX() == 2 && moveDirection == RIGHT) {
			return null;
		}
		int[] newBoard = new int[board.length];
		System.arraycopy(board, 0, newBoard, 0, board.length);
		// swap the blank tile and the tile that we are moving
		newBoard[getBlankLocation()] = board[newPos];
		newBoard[newPos] = 0;
		// create a new board object and return it
		return new Board(newBoard, this);
	}

	/**
	 * Generates all possible successors of this board configuration
	 * 
	 * @return a Vector of Board objects
	 */
	public Vector<Board> getSuccessors() {
		Vector<Board> res = new Vector<Board>();
		// attempt to generate successors for all possible moves
		for (int i = 0; i < legalMoves.length; i++) {
			Board successor = getSuccessor(legalMoves[i]);
			// check if the move was legal
			if (successor != null) {
				res.addElement(successor);
			}
		}
		return res;
	}

	/**
	 * returns the location of the blank tile (represented by number 0) within
	 * the internal data structure
	 * 
	 * @return the location of the blank tile in the internal data structure
	 */
	protected int getBlankLocation() {
		if (blankLocationCache < 0) {
			int i = -1;
			for (i = 0; i < board.length && board[i] != 0; i++) {
			}
			blankLocationCache = i;
		}
		return blankLocationCache;
	}

	/** cache of the blank location */
	private int blankLocationCache = -1;

	/**
	 * @return the X coordinate of the location of the blank tile
	 */
	public int getBlankLocationX() {
		return getBlankLocation() % 3;
	}

	/**
	 * @return the Y coordinate of the location of the blank tile
	 */
	public int getBlankLocationY() {
		return (int) Math.floor(getBlankLocation() / 3);
	}

	/**
	 * Returns the lenght of the path (i.e. the number of transitions)
	 * from the start state to this one
	 * 
	 * @return the lenght of the path from the start state
	 */
	public int getPathLength() {
		if (pathCache == null) {
			getPathFromStartNode();
		}
		/*
		 * We substract 1 because we are interested in the number of _moves_
		 * that were necessary to get from the start state to this one
		 */
		return pathCache.size() - 1;
	}

	/**
	 * Returns a vector that shows all the board states from the
	 * starting node to this one
	 * 
	 * @return the path from the start node (including the start node)
	 */
	@SuppressWarnings({ "unchecked" })
	public Vector<Board> getPathFromStartNode() {
		if (pathCache == null) {
			if (parent == null) {
				pathCache = new Vector<Board>();
			} else {
				pathCache = parent.getPathFromStartNode();
			}
			pathCache.add(this);
		}
		/*
		 * We are returning a clone of the cache because vectors are
		 * passed by reference and we do not want other classes to be
		 * messing around with our data structures
		 */
		return (Vector<Board>) pathCache.clone();
	}

	/** cache for the path from the start node */
	protected Vector<Board> pathCache;

	/**
	 * Generates a random board instance
	 * 
	 * @return a random board instance
	 */
	public static Board randomBoard() {
		ArrayList<Integer> tiles = new ArrayList<Integer>();
		for (int i = 0; i <= 8; i++) {
			tiles.add(new Integer(i));
		}

		int[] newBoard = new int[9];
		for (int i = 0; i <= 8; i++) {
			newBoard[i] = ((Integer) tiles.remove((int) ((double) tiles.size() * Math.random()))).intValue();
		}
		return new Board(newBoard);
	}

	public String toString() {
		if (stringRepCache == null) {
			//stringRepCache = "  |0 1 2\n--+-----\n";
			stringRepCache = "";
			for (int y = 0; y < 3; y++) {
				//stringRepCache += y + " |";
				stringRepCache += "|";
				for (int x = 0; x < 3; x++) {
					if(x != 2){
						stringRepCache += getTileAt(x, y) + " ";
					}else{
						stringRepCache += getTileAt(x, y);
					}
				}
				stringRepCache += "|\n";
			}
		}
		return stringRepCache;
	}

	/** String rep cache -- useful because the hashCode is computed from it */
	private String stringRepCache;

	/**
     * Get the manhattan distance.
     * Where d<sub>1</sub> is the metric induced by the 1-norm.
     * Visually speaking, the manhattan distance is the distance following
     * the rectangular streets of manhattan = delta x + delta y.
     */
    public int getManhattanDistance() {
    	int manhattanDistance = 0;
    	for(int x = 0; x < 3; x++){
    		for(int y = 0; y < 3; y++){
    			int tile = this.getTileAt(x, y);
    			int[] dest = getCorrectPosition(tile);
    			if(tile != 0){
    				manhattanDistance += Math.abs(dest[0] - x) + Math.abs(dest[1] - y);
    			}
    		}
    	}
        return manhattanDistance;
    }
    
    /**
     * @return number of misplaced tiles
     */
    public int getNumberOfMisplacedTiles(){
    	int misplacedTiles = 0;
    	for(int x = 0; x < 3; x++){
    		for(int y = 0; y < 3; y++){
    			int tile = this.getTileAt(x, y);
    			int[] dest = getCorrectPosition(tile);
    			if((tile != 0) && (dest[0] != x || dest[1] != y)){
    				misplacedTiles++;
    			}
    		}
    	}
        return misplacedTiles;
    }

	/**
	 * @param tile
	 * @return correct position of tile
	 */
	private int[] getCorrectPosition(int tile) {
		if(tile == 0){
			return new int[]{0, 0};
		}else if(tile == 1){
			return new int[]{0, 1};
		} else if(tile == 2){
			return new int[]{0, 2};
		}else if(tile == 3){
			return new int[]{1, 0};
		}else if(tile == 4){
			return new int[]{1, 1};
		}else if(tile == 5){
			return new int[]{1, 2};
		}else if(tile == 6){
			return new int[]{2, 0};
		}else if(tile == 7){
			return new int[]{2, 1};
		}else if(tile == 8){
			return new int[]{2, 2};
		}
		return null;
	}

	@Override
	public int compareTo(Board b) {
		/* function h(n) = distance to the nearest goal node - heuristic function */
		int h1 = this.getManhattanDistance();
		/* function g(n) = the cost to reach the node n - cost function */
		int g1 = this.getPathFromStartNode().size();
		Integer f1 = new Integer(h1+g1); 
		/* function h(n) = distance to the nearest goal node - heuristic function */
		int h2 = b.getManhattanDistance();
		/* function g(n) = the cost to reach the node n - cost function */
		int g2 = b.getPathFromStartNode().size();
		Integer f2 = new Integer(h2+g2);
		return f1.compareTo(f2);
	}

	/**
	 * @return the board
	 */
	public int[] getBoard() {
		return board;
	}

	/**
	 * @param board the board to set
	 */
	public void setBoard(int[] board) {
		this.board = board;
	}

	/**
	 * @return the parent
	 */
	public Board getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Board parent) {
		this.parent = parent;
	}

	/**
	 * @return the costEstimated
	 */
	public int getCostEstimated() {
		return costEstimated;
	}

	/**
	 * @param costEstimated the costEstimated to set
	 */
	public void setCostEstimated(int costEstimated) {
		this.costEstimated = costEstimated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(board);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board other = (Board) obj;
		if (!Arrays.equals(board, other.board))
			return false;
		return true;
	}
}
