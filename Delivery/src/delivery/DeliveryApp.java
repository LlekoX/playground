package delivery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class DeliveryApp {

	public static void main(String[] args) {
		/*Box gene1 = new Gene('A', 70, 260);
		Gene gene2 = new Gene('B', 60, 245);
		Gene gene3 = new Gene('C', 50, 200);
		Gene gene4 = new Gene('D', 40, 100);
		Gene gene5 = new Gene('E', 30, 80);
		Gene gene6 = new Gene('F', 20, 65);
		Gene gene7 = new Gene('G', 10, 60);
		Gene gene8 = new Gene('H', 10, 60);
		Gene gene9 = new Gene('I', 1, 10);
		
		List<Gene> genePool = new ArrayList<>();
		genePool.add(gene1);
		genePool.add(gene2);
		genePool.add(gene3);
		genePool.add(gene4);
		genePool.add(gene5);
		genePool.add(gene6);
		genePool.add(gene7);
		genePool.add(gene8);
		genePool.add(gene9);
   
		GeneGenerator factory = new GeneGenerator(genePool);
		
		Population pop = new Population(5,10, factory,10, 0.2);

		while(!pop.evaluatePopulation()) {
			System.out.println();
			pop.PoolSelection();
		}*/
		int capacity = 0;
		int quota = 0;
		int numOfBoxes = 0;
		int count = 1;
		Box box;
		GeneGenerator factory;
		Chromosome chr;
		Population population;
		ArrayList<Box> arr = new ArrayList<>();
		Box[] boxes = {};
		BufferedReader r;
		Scanner sc;
		
		try {
			//r = new BufferedReader(new FileReader("input.txt"));
			sc = new Scanner(new File("input.txt"));
			String line;
			System.out.println("============================");
			System.out.println("First Sample: ");
			System.out.println("============================");
			while(sc.hasNextLine()) {
				line = sc.next();
				if(line.equals("***")) {
					capacity = sc.nextInt();
					quota = sc.nextInt();
					numOfBoxes = sc.nextInt();
					boxes = new Box[numOfBoxes];
					for(int i = 0; i < numOfBoxes;i++) {
						String name = sc.next();
						int weight = Integer.parseInt(sc.next());
						int value = Integer.parseInt(sc.next());
						boxes[i] = new Box(name,weight,value);
						arr = new ArrayList<>();
						Collections.addAll(arr, boxes);
					}	
				}
				factory = new GeneGenerator(arr);
				population = new Population(capacity,quota, factory,1000, 0.1);
				
				
				while(!population.evaluatePopulation()) {
					System.out.println();
					population.Selection();
				}
				System.out.println();
				System.out.println("============================");
				System.out.println("Next Sample: ");
				System.out.println("============================");
				System.out.println();
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
