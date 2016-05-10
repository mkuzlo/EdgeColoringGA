package edgeColoringGA;

import java.io.File;

import edgeColoringGA.GALib.ChromStrings;
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
			params.setPopulation(400);
			params.setMaxGenerations(800);
			params.setCrossoverProbability(0.6);			
			params.setMutationProbablity(0.2);			
			params.setRandomSelectionChance(0.05);
			
			//long startTime = System.currentTimeMillis();
			int symulacja = 0;
			int wyniki[] = new int[210];
			
			int numberOfSimulations = 10;
			
			//sprawdzane jest 21 wartosci parametrow
			for(double test=0;test<=1.0001;test+=0.05){
				
				//testowany parametr:
				params.setCrossoverProbability(test); 
				//params.setMutationProbablity(test);			
				//params.setRandomSelectionChance(test);
				
				for(int i=0;i<numberOfSimulations;i++){
					EdgeColoring edgeColoring = new EdgeColoring(graph, params);
					Thread thread = new Thread(edgeColoring);
					thread.start();
					try {
						thread.join();
					} catch (InterruptedException e) {				
						e.printStackTrace();
					}
					wyniki[symulacja] = edgeColoring.numberOfColorUsed((ChromStrings) edgeColoring.getFittestChromosome());	
					System.out.println("Symulacja " + (symulacja+1) + "/210  parametr: " + test);
					symulacja++;
				}
			}	
			
			System.out.println("Zakończono wszystkie testy");
			
			symulacja=0;
			
			for(int i=0;i<210;i++){
				for(int j=0;j<numberOfSimulations;j++){
					System.out.print(wyniki[symulacja] + "    ");
					symulacja++;
				}
				System.out.println("");
			}
			
			
			//long endTime = System.currentTimeMillis();
			//long duration = (endTime-startTime)/1000;
			//Statistics stats = new Statistics(params, graph, edgeColoring,duration);
			//stats.printStats();
					
		}catch(Exception e){
			
		}
	}
}
