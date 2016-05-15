package edgeColoringGA;

import java.io.File;
import edgeColoringGA.utils.Graph;
import edgeColoringGA.utils.Parameters;
import edgeColoringGA.utils.Statistics;

public class Application {
	public static void main(String[] args) {
		try {
			String graphFileLocation = "src/main/resources/graphs/2016_05_12-14_57_22.txt";
			Graph graph = new Graph(new File(graphFileLocation));

			Parameters params = new Parameters();
			params.setPopulation(300);
			params.setMaxGenerations(5000);
			params.setCrossoverProbability(0.6);
			params.setMutationProbablity(0.45);
			params.setRandomSelectionChance(0.03);
			params.setNumberOfPossibleGeneValues(graph.getGraphDegree());

			long startTime = System.currentTimeMillis();
			EdgeColoring edgeColoring = new EdgeColoring(graph, params);
			Thread thread = new Thread(edgeColoring);
			thread.start();			
			
			try {
				thread.join();
				long endTime = System.currentTimeMillis();
				long duration = (endTime-startTime)/1000;
				Statistics stats = new Statistics(params, graph, edgeColoring, duration);				
				stats.printStats();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
