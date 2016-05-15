package edgeColoringGA.utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Class for generating random graph and saving it in file.
 * File structure:
 * <Number of edges>
 * <Edge number> <Source vertex> <Target vertex>
 * <Edge number> <Source vertex> <Target vertex>
 * ...
 * Edges and vertices are represented as numbers.
 * @author Mateusz Kuzło
 *
 */
public class GraphGenerator {
	/*
	 * Number of edges in a graph.
	 */
	private static final int edgesNumber = 4000;
	
	/*
	 * How many vertices are created based on number of edges, bottom limit and upper limit 
	 * For example for verticesBottomLimit = 0.3 and verticesUpperLimit = 0.6 and edgesNumber = 100,
	 * the number of vertices will be between 30 and 60;
	 */
	private static final double verticesBottomLimit = 0.6; 
	private static final double verticesUpperLimit = 0.8;  

	
	public static void main(String[] args) throws IOException {		
		File file = new File("src/main/resources/graphs/" + generateFileName());
		file.createNewFile();
		if(file.exists())
			System.out.println("Created file: " + file.toString());
		
		int array[][] = new int[3][];
		array[0] = new int[edgesNumber];
		array[1] = new int[edgesNumber];
		array[2] = new int[edgesNumber];
		
		generateEdges(array);
		
		PrintWriter writer = new PrintWriter(file);
		writer.println(edgesNumber);
		
		for(int i=0;i<edgesNumber;i++){
			writer.print(array[0][i]);
			writer.print("\t\t");
			writer.print(array[1][i]);
			writer.print("\t\t");
			writer.print(array[2][i]);
			writer.print("\n");			
		}	
		writer.close();	
		System.out.println("Graph successfully created");
	}
	
	/**
	 * Fills array with edges and vertices.	 
	 * @param array [3][edgesNumber]
	 */
	private static void generateEdges(int array[][]){
		int vertices = (int) ((Math.random() * (((verticesUpperLimit-verticesBottomLimit)*edgesNumber)+1)) + verticesBottomLimit*edgesNumber);

		for(int i=0;i<edgesNumber;i++){
			array[0][i] = i+1;
		}
		int i=0;
		int temp1=-1,temp2=-1;
		
		for(;i<vertices-1;i++){
			array[1][i] = i + 1;
			array[2][i] = i + 2;			
		}
		for(;i<edgesNumber;i++){
			temp1 = (int) (Math.random()*vertices) +1;
			temp2 = (int) (Math.random()*vertices) +1;
			while(temp1==temp2)
				temp2 = (int) (Math.random()*vertices) + 1;
			
			array[1][i] = temp1;			
			array[2][i] = temp2;
		}
		System.out.println("Number od edges: " + edgesNumber);
		System.out.println("Number of vertices: " + vertices);
	}
	
	
	/**
	 * Creates name for a file using current date.
	 * @return String
	 */
	private static String generateFileName(){
		LocalDateTime date = LocalDateTime.now();
		String fileName = date.getYear() + "_";
		
		if(date.getMonthValue()<=9)
			fileName += "0" + date.getMonthValue() + "_";
		else
			fileName += date.getMonthValue() + "_";
		
		if(date.getDayOfMonth()<=9)
			fileName += "0" + date.getDayOfMonth() + "-";
		else
			fileName += date.getDayOfMonth() + "-";
		
		if(date.getHour()<=9)
			fileName += "0" + date.getHour() + "_";
		else
			fileName += date.getHour() + "_";
		
		if(date.getMinute()<=9)
			fileName += "0" + date.getMinute() + "_";
		else
			fileName += date.getMinute() + "_";
		
		if(date.getSecond()<=9)
			fileName += "0" + date.getSecond() + ".txt";
		else
			fileName += date.getSecond() + ".txt";
		
		return fileName;
	}
}




