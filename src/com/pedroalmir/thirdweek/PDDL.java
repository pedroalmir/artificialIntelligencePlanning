package com.pedroalmir.thirdweek;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import pddl4j.Domain;
import pddl4j.ErrorManager;
import pddl4j.ErrorManager.Message;
import pddl4j.PDDLObject;
import pddl4j.Parser;
import pddl4j.Problem;
import pddl4j.exp.action.ActionDef;

/**
 * @author Pedro Almir
 *
 */
public class PDDL {
	/** */
	private static final File domainFile = new File("C:/Users/Pedro Almir/git/artificialIntelligencePlanning/pddl/random-domain.txt");
	private static final File problemFile = new File("C:/Users/Pedro Almir/git/artificialIntelligencePlanning/pddl/random-pbl1.txt");
	
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Creates an instance of the java pddl parser
        Parser parser = new Parser();
        Domain domain = parser.parse(domainFile);
        Problem problem = parser.parse(problemFile);
        PDDLObject obj = parser.link(domain, problem);
        
        Iterator<ActionDef> actionsIterator = obj.actionsIterator();
        while (actionsIterator.hasNext()) {
			ActionDef actionDef = (ActionDef) actionsIterator.next();
			System.out.println(actionDef.toString());
		}
        
        // Gets the error manager of the pddl parser
        ErrorManager mgr = parser.getErrorManager();
        // If the parser produces errors we print it and stop
        if (mgr.contains(Message.ERROR)) {
            mgr.print(Message.ALL);
        } // else we print the warnings
        else {
            mgr.print(Message.WARNING);
            System.out.println("\nParsing domain \"" + domain.getDomainName() + "\" done successfully ...");
            System.out.println("Parsing problem \"" + problem.getProblemName() + "\" done successfully ...\n");
        }
	}
}
