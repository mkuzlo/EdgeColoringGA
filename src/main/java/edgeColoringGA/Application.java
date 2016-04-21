package edgeColoringGA;

import java.io.File;

import edgeColoringGA.GALib.GAException;
import edgeColoringGA.utils.Graph;

public class Application {
	public static void main (String[] args){
		try {
			//ścieżka z plikiem grafu
			File file = new File("src/main/resources/graphs/2016_04_06-18_51_05.txt");
			//Tworzymy obiekt klasy graf ze ścieżki powyżej
			Graph graph = new Graph(file);
			//wywolujemy algorytm genetyczny
			EdgeColoring edgeColoring = new EdgeColoring(graph);
			Thread thread = new Thread(edgeColoring);
			thread.start();
			
		} catch (GAException e) {
			System.out.println(e.getMessage());
		}
	}
}
