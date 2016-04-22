package edgeColoringGA.utils;

/**
 * Stores key parameters for genetic algorithm and graph.
 * @author Mateusz Kuzło
 */
public class Parameters {
	private int population;
	private double crossoverProbability;
	private int randomSelectionChance;
	private int maxGenerations;
	private double mutationProbablity;
	
	
	
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public double getCrossoverProbability() {
		return crossoverProbability;
	}
	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}
	public int getRandomSelectionChance() {
		return randomSelectionChance;
	}
	public void setRandomSelectionChance(int randomSelectionChance) {
		this.randomSelectionChance = randomSelectionChance;
	}
	public int getMaxGenerations() {
		return maxGenerations;
	}
	public void setMaxGenerations(int maxGenerations) {
		this.maxGenerations = maxGenerations;
	}
	public double getMutationProbablity() {
		return mutationProbablity;
	}
	public void setMutationProbablity(double mutationProbablity) {
		this.mutationProbablity = mutationProbablity;
	}
	
	

}
