package edgeColoringGA;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edgeColoringGA.GALib.ChromStrings;
import edgeColoringGA.GALib.Crossover;
import edgeColoringGA.GALib.GAException;
import edgeColoringGA.GALib.GAStringsSeq;
import edgeColoringGA.utils.Graph;
import edgeColoringGA.utils.Parameters;

public class EdgeColoring extends GAStringsSeq {
	public EdgeColoring(Graph graph, Parameters param) throws GAException{
		super(  graph.getEdgesNumber(), //size of chromosome
                param.getPopulation(), //population has N chromosomes
                param.getCrossoverProbability(), //crossover probability
                param.getRandomSelectionChance(), //random selection chance % (regardless of fitness)
                param.getMaxGenerations(), //max generations
                0, //num prelim runs (to build good breeding stock for final/full run)
                0, //max generations per prelim run
                param.getMutationProbablity(), //chromosome mutation prob.
                0, //number of decimal places in chrom
                graph.getPossibleColors(), //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                false); //compute statisitics?		
    }
	

	@Override
	protected double getFitness(int iChromIndex) {
		ChromStrings chromosome = (ChromStrings)this.getChromosome(iChromIndex);
	    int usedColors = numberOfColorUsed(chromosome);	    
	    int repetitions = numberOfAdjacentSameColoredEdges(chromosome);
	    if(repetitions>0) return  1.0/repetitions;
	    else return  1 + (1.0/usedColors);
	}
	
	/**
	 * Returns number of colors used to color graph.
	 * @param chromosome
	 * @return
	 */
	public int numberOfColorUsed(ChromStrings chromosome){	
		List<String> list = new ArrayList<String>();		
		for (int i = 0; i < chromosomeDim; i++)
        {
            if(!list.contains(chromosome.getGene(i))){
            	list.add(chromosome.getGene(i));
            }
        }
		return list.size();
	}
	
	/**
	 * Returns number of times when same colored edges were assigned to vertex in whole chromosome
	 * @param chromosome
	 * @return 
	 */
	public int numberOfAdjacentSameColoredEdges(ChromStrings chromosome){
		int count = 0;
		Graph graph = Graph.getInstace();
		int[][] graphRepresentation = graph.getGraphRepresentation();
		int edges = chromosomeDim;
		int vertices = graph.getVerticesNumber();
		
		for(int i = 0;i<edges;i++){
			graphRepresentation[0][i] = Integer.parseInt(chromosome.getGene(i).trim());
		}
		
		for(int v = 1;v<=vertices;v++){
			int n = 0;
			int[] temp = new int[edges];
			for(int e = 0;e<edges;e++){
				if(graphRepresentation[1][e] == v || graphRepresentation[2][e] == v){
					temp[n] = graphRepresentation[0][e];
					n++;
				}
			}
			count += numberOfRepetitions(temp, n);
		}
		return count;
	}
	
	/**
	 * Returns number of time each color in array has repeated itself.
	 * @param array of colors adjacent to given vertex
	 * @param number of colors in array
	 * @return number of repetitions
	 */
	private int numberOfRepetitions(int tab[], int n){
		int rep = 0;
		int[] tab2 = new int[n];
		for(int i=0;i<n;i++){
			tab2[i] = tab[i];
		}
		Arrays.sort(tab2);
		for(int i = 0;i<n-1;i++){
			if(tab2[i]==tab2[i+1]) rep++;
		}
		return rep;
	}

}
