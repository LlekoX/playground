package DangerousCrossing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		int p1Time, p2Time, p3Time, p4Time, p5Time;
		String path_minDFS, path_minBFS, path_minAStar;
		
//		String id = "A";
//		int count_ = 65;
//		String left = "";
//		for (int i = 0; i < 100; i++) {
//			
//			if ((id.charAt(0)) > 90) {
//				left = Character.toString((char)count_);
//				id = Character.toString((char)((id.charAt(0)% 90)+64));
//				System.out.println(left+id);
//				count_++;
//			}
//			else {
//				if (count_ > 65)
//					System.out.println(left + id);
//				else
//					System.out.println(id);
//			}
//			id = Character.toString((char)((int)(id.charAt(0)+1)));
//		}
		
		try {
			Scanner sc = new Scanner(new File("inputs.txt"));
			System.out.println("Would you like to solve the problem using: \n(1) Depth-First  Search \n(2) Breadth-First  Search \n(3) A* Algorithm");
			@SuppressWarnings("resource")
			int user_input = new Scanner(System.in).nextInt();
			
			int count = 1;
			while(sc.hasNext()) {
				String lineRead = sc.nextLine();
				String[] line = lineRead.split(" ");
				int minTime = Integer.parseInt(line[line.length-1]);
				int numPeople = line.length-1;
				ArrayList<Integer> arr;
				Tree tree;
				
				switch (numPeople) {
				case 3:
					p1Time = Integer.parseInt(line[0]);
					p2Time = Integer.parseInt(line[1]);
					p3Time = Integer.parseInt(line[2]);
					arr = new ArrayList<>();
					arr.add(p1Time);
					arr.add(p2Time);
					arr.add(p3Time);
					System.out.println("\n<< Three People >>");
					System.out.println("Minumum time: " + minTime);
					System.out.println("Person 1 speed: " + p1Time);
					System.out.println("Person 2 speed: " + p2Time);
					System.out.println("Person 3 speed: " + p2Time);
					
					tree = new Tree(arr, 3,minTime);
					tree.setupTree();
					
//					if (user_input == 1)
//						path_minDFS = tree.depthFirstSearch();
//					else if (user_input == 2)
//						path_minBFS = tree.breadthFirstSearch();
//					else if (user_input == 3)
//						path_minAStar = tree.AStarAlgorithm();
					break;
					
				case 4:
					p1Time = Integer.parseInt(line[0]);
					p2Time = Integer.parseInt(line[1]);
					p3Time = Integer.parseInt(line[2]);
					p4Time = Integer.parseInt(line[3]);
					arr = new ArrayList<>();
					arr.add(p1Time);
					arr.add(p2Time);
					arr.add(p3Time);
					arr.add(p4Time);
					
					System.out.println("\n<< Four People >>");
					System.out.println("Minumum time: " + minTime);
					System.out.println("Person 1 speed: " + p1Time);
					System.out.println("Person 2 speed: " + p2Time);
					System.out.println("Person 3 speed: " + p3Time);
					System.out.println("Person 4 speed: " + p4Time);
					
					tree = new Tree(arr, 4, minTime);
					tree.setupTree();
					
//					if (user_input == 1)
//						path_minDFS = tree.depthFirstSearch();
//					else if (user_input == 2)
//						path_minBFS = tree.breadthFirstSearch();
//					else if (user_input == 3)
//						path_minAStar = tree.AStarAlgorithm();
					break;
					
				case 5:
					p1Time = Integer.parseInt(line[0]);
					p2Time = Integer.parseInt(line[1]);
					p3Time = Integer.parseInt(line[2]);
					p4Time = Integer.parseInt(line[3]);
					p5Time = Integer.parseInt(line[4]);
					arr = new ArrayList<>();
					arr.add(p1Time);
					arr.add(p2Time);
					arr.add(p3Time);
					arr.add(p4Time);
					arr.add(p5Time);
					
					System.out.println("\n<< Five People >>");
					System.out.println("Minumum time: " + minTime);
					System.out.println("Person 1 speed: " + p1Time);
					System.out.println("Person 2 speed: " + p2Time);
					System.out.println("Person 3 speed: " + p3Time);
					System.out.println("Person 4 speed: " + p4Time);
					System.out.println("Person 5 speed: " + p5Time);
					
					tree = new Tree(arr, 5, minTime);
					tree.setupTree();
					
//					if (user_input == 1)
//						path_minDFS = tree.depthFirstSearch();
//					else if (user_input == 2)
//						path_minBFS = tree.breadthFirstSearch();
//					else if (user_input == 3)
//						path_minAStar = tree.AStarAlgorithm();

					break;
					
				default:
					System.out.println("Unable to process the number of people.");
				}
			}
			sc.close();
		} 
		catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
