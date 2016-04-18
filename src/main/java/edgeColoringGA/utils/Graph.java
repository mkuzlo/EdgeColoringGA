package edgeColoringGA.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Klasa reprezentująca graf, jak konstruktor podajemy scieżke pliku z danymi grafu
 * @author Quzi
 *
 */
public class Graph {
	private int edgesNumber;
	private int verticesNumber;
	private int graphRepresentation[][];
	private String possibleColors[];
	
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
			
		} catch (FileNotFoundException e) {
			System.out.println("Graph not found at: " + file.toString());
			e.printStackTrace();
		}
	}
	
	private void generatePossibleColors(){
		possibleColors = new String[edgesNumber];
		for(int i=1;i<=edgesNumber;i++){
			possibleColors[i-1] = " " + i;
		}
	}
	
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
	
	public int getVerticesNumber() {
		return verticesNumber;
	}

	public int getEdgesNumber() {
		return edgesNumber;
	}

	public int[][] getGraphRepresentation() {
		return graphRepresentation;
	}

	public String[] getPossibleColors() {
		return possibleColors;
	}

}
