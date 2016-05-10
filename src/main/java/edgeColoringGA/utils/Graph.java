package edgeColoringGA.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Class representation of graph loaded from file.
 * @author Mateusz Kuzło
 *
 */
public class Graph {
	private int edgesNumber;
	private int verticesNumber;
	private int graphRepresentation[][];
	private String possibleColors[];
	private static Graph instance;
	
	/**
	 * Creates object representation of a graph from a given file.
	 * @param file
	 */
	public Graph(File file){
		try {
			Scanner scan = new Scanner(file);
			edgesNumber = scan.nextInt();
			graphRepresentation = new int[3][];
			graphRepresentation[0] = new int[edgesNumber];
			graphRepresentation[1] = new int[edgesNumber];
			graphRepresentation[2] = new int[edgesNumber];
			
			for(int i=0;i<edgesNumber;i++){
				for(int j=0;j<3;j++){
					graphRepresentation[j][i] = scan.nextInt();
				}
			}
			scan.close();
			generatePossibleColors();
			computeNumberOfVertices();
			instance = this;
			
		} catch (FileNotFoundException e) {
			System.out.println("Graph not found at: " + file.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns instance of this class.
	 * @return Graph
	 */
	public static Graph getInstace(){
		return instance;
	}
	
	/**
	 * Fills array with integers representing possible colors that can
	 * be used in genetic algorithm.
	 */
	private void generatePossibleColors(){
		possibleColors = new String[edgesNumber];
		for(int i=1;i<=edgesNumber;i++){
			possibleColors[i-1] = " " + i;
		}
	}
	
	/**
	 * Computes number of vertices used to build this graph.
	 */
	private void computeNumberOfVertices(){
		List<String> list = new ArrayList<String>();		
		for (int i = 0; i < edgesNumber; i++)
        {
            if(!list.contains("" + graphRepresentation[1][i])){
            	list.add("" + graphRepresentation[1][i]);
            }
            if(!list.contains("" + graphRepresentation[2][i])){
            	list.add("" + graphRepresentation[2][i]);
            }		
        }
		verticesNumber = list.size();
	}
	
	
	
	/**
	 * Returns number of vertices is this graph.
	 * @return int
	 */
	public int getVerticesNumber() {
		return verticesNumber;
	}

	/**
	 * Returns number of edges in this graph.
	 * @return
	 */
	public int getEdgesNumber() {
		return edgesNumber;
	}
	
	/**
	 * Returns two dimensional array representing this graph
	 * [0][n] - edge
	 * [1][n] - source vertex
	 * [2][n] - destination vertex
	 * @return int[3][numberOfEdges]
	 */
	public int[][] getGraphRepresentation() {
		return graphRepresentation;
	}

	/**
	 * Returns array of possible colors that can be used in genetic algorithm.
	 * @return
	 */
	public String[] getPossibleColors() {
		return possibleColors;
	}
	
	/**
	 * Returns degree of this graph
	 * @return
	 */
	public int getGraphDegree(){
		int highestValue = 0;
		int temp;
		for(int v=1;v<=verticesNumber;v++){
			temp = 0;
			for(int i=0;i<edgesNumber;i++){
				if(graphRepresentation[1][i] == v) temp++;
				if(graphRepresentation[2][i] == v) temp++;
			}
			if(temp>highestValue) highestValue = temp;			
		}		
		return highestValue;		
	}

}
