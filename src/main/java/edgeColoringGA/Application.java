package edgeColoringGA;

import java.io.File;

import edgeColoringGA.GALib.GAException;
import edgeColoringGA.utils.Graph;
import edgeColoringGA.utils.Parameters;

/**
 * Main class for running genetic algorithm.
 * @author Quzi
 *
 */
public class Application {
	public static void main (String[] args){
		try {
			String graphFileLocation = "src/main/resources/graphs/2016_04_06-18_51_05.txt";
			Graph graph = new Graph(new File(graphFileLocation));
			
			Parameters params = new Parameters();
			params.setPopulation(500);
			params.setMaxGenerations(1000);
			params.setCrossoverProbability(0.7);			
			params.setMutationProbablity(0.2);			
			params.setRandomSelectionChance(10);
			
			EdgeColoring edgeColoring = new EdgeColoring(graph, params);
			Thread thread = new Thread(edgeColoring);
			thread.start();
			
		} catch (GAException e) {
			System.out.println(e.getMessage());
		}
	}
}
