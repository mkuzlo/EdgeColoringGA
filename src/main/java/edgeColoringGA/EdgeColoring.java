package edgeColoringGA;




import java.util.ArrayList;
import java.util.List;

import edgeColoringGA.GALib.ChromStrings;
import edgeColoringGA.GALib.Crossover;
import edgeColoringGA.GALib.GAException;
import edgeColoringGA.GALib.GAStringsSeq;
import edgeColoringGA.utils.Graph;

public class EdgeColoring extends GAStringsSeq {
	private Graph graph;
	
	public EdgeColoring(Graph graph) throws GAException
    {
		super(  graph.getEdgesNumber(), //size of chromosome
                100, //population has N chromosomes
                0.6, //crossover probability
                10, //random selection chance % (regardless of fitness)
                100, //max generations
                0, //num prelim runs (to build good breeding stock for final/full run)
                20, //max generations per prelim run
                0.4, //chromosome mutation prob.
                0, //number of decimal places in chrom
                graph.getPossibleColors(), //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                true); //compute statisitics?
		this.graph = graph;
    }

	@Override
	protected double getFitness(int iChromIndex) {
		ChromStrings chromosome = (ChromStrings)this.getChromosome(iChromIndex);
	    double fitness = 0;
	    int usedColors = numberOfColorUsed(chromosome);	    
	    
	    //tutaj należy dopisać reszte kodu funkcji celu
	    //mianowicie nalezy policzyc czy graf jest pokolorwany poprawnie
	    
	    fitness = 1.0/usedColors;
		return fitness;
	}
	
	//zwraca ile kolorow uzyto do pokolorowania grafu w danej generacji
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
	
	//zwraca ile krawedzi przyleglych do wierzcholkow ma ten sam kolor
	public int numberOfAdjacentSameColoredEdges(ChromStrings chromosome){
		int count = 0;
		int[][] graphRepresentation = graph.getGraphRepresentation();
		//niedokńczona funkcja ktora bedzie wykorzysywana w funkcji celu (fitness)
		
		return count;
	}

}
