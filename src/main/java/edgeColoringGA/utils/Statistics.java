package edgeColoringGA.utils;

import edgeColoringGA.EdgeColoring;
import edgeColoringGA.GALib.ChromStrings;

public class Statistics {
	private Parameters params;
	private Graph graph;
	private EdgeColoring edgeColoring;
	private long duration;
	
	public Statistics (Parameters params, Graph graph, EdgeColoring edgeColoring,long duration){
		this.params = params;
		this.graph = graph;
		this.edgeColoring = edgeColoring;
		this.duration = duration;
	}
	
	/**
	 * Prints parameters and results on the console
	 */
	public void printStats(){
		System.out.println("-----------PARAMETRY----------");
		System.out.println("Ilość krawędzi grafu: " + graph.getEdgesNumber());
		System.out.println("Ilość wierzchołków grafu: " + graph.getVerticesNumber());
		System.out.println("Stopień grafu: " + graph.getGraphDegree());
		int deg = graph.getGraphDegree();
		System.out.println("Prziedział rozwiązania optymalnego: " + deg + "-" + (deg*3/2));
		System.out.println("Populacja: " + params.getPopulation());
		System.out.println("Ilość generacji: " + params.getMaxGenerations());
		System.out.println("Szansa krzyżowania: " + params.getCrossoverProbability());
		System.out.println("Szansa mutacji: " + params.getMutationProbablity());
		System.out.println("Szansa losowego wyboru: " + params.getRandomSelectionChance() + "%");
		System.out.println("-------------WYNIKI-----------");
		ChromStrings chrom = (ChromStrings) edgeColoring.getFittestChromosome();		
		System.out.println("Ilość błędów w kolorowaniu: " + edgeColoring.numberOfAdjacentSameColoredEdges(chrom));
		System.out.println("Ilość użytych kolorów: " + edgeColoring.numberOfColorUsed(chrom));
		System.out.println("Zakończono na generacji: " + edgeColoring.getFinalGeneration());
		System.out.println("Fitness: " + edgeColoring.getFittestChromosomesFitness());		
		System.out.println("Czas trwania obliczeń: " + duration + " s");
	}
	
	
}
