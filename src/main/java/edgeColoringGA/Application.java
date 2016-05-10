package edgeColoringGA;

import java.io.File;
import edgeColoringGA.GALib.GAException;
import edgeColoringGA.utils.Graph;
import edgeColoringGA.utils.Parameters;
import edgeColoringGA.utils.Statistics;

public class Application {
	public static void main (String[] args){
		try {
			String graphFileLocation = "src/main/resources/graphs/2016_04_06-18_51_05.txt";
			Graph graph = new Graph(new File(graphFileLocation));
			
			Parameters params = new Parameters();
			params.setPopulation(500);
			params.setMaxGenerations(2000);
			params.setCrossoverProbability(0.6);			
			params.setMutationProbablity(0.3);			
			params.setRandomSelectionChance(5);
			
			long startTime = System.currentTimeMillis();
			EdgeColoring edgeColoring = new EdgeColoring(graph, params);
			Thread thread = new Thread(edgeColoring);
			thread.start();
			try {
				thread.join();
				long endTime = System.currentTimeMillis();
				long duration = (endTime-startTime)/1000;
				Statistics stats = new Statistics(params, graph, edgeColoring,duration);
				stats.printStats();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		} catch (GAException e) {
			System.out.println(e.getMessage());
		}
	}
}
