package edgeColoringGA;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import edgeColoringGA.GALib.ChromStrings;
import edgeColoringGA.GALib.Crossover;
import edgeColoringGA.GALib.GAException;
import edgeColoringGA.GALib.GAStringsSeq;
import edgeColoringGA.utils.Graph;

public class EdgeColoring extends GAStringsSeq {
	public EdgeColoring(Graph graph) throws GAException
    {
		
		super(  graph.getEdgesNumber(), //size of chromosome
                600, //population has N chromosomes
                0.7, //crossover probability
                5, //random selection chance % (regardless of fitness)
                10000, //max generations
                0, //num prelim runs (to build good breeding stock for final/full run)
                20, //max generations per prelim run
                0.3, //chromosome mutation prob.
                0, //number of decimal places in chrom
                graph.getPossibleColors(), //gene space (possible gene values)
                Crossover.ctTwoPoint, //crossover type
                true); //compute statisitics?
		
    }
	

	@Override
	protected double getFitness(int iChromIndex) {
		ChromStrings chromosome = (ChromStrings)this.getChromosome(iChromIndex);
	    double fitness = 0;
	    int usedColors = numberOfColorUsed(chromosome);	    
	    int repetitions = numberOfAdjacentSameColoredEdges(chromosome);
	    if(repetitions>0) fitness = 1.0/repetitions;
	    else fitness = 1 + (1.0/usedColors);
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
		Graph graph = Graph.getInstace();
		int[][] graphRepresentation = graph.getGraphRepresentation();
		int edges = chromosomeDim;
		int vertices = graph.getVerticesNumber();
		int colors = numberOfColorUsed(chromosome);
		
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
