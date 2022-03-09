package DangerousCrossing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {
	private Node root;
	private int numPeople;
	Node leftmost; //should be in the node class
	int goal;
	
	class Node {
		private Node prev;
		LinkedList<Node> siblings;
		ArrayList<Integer> left_of_state, right_of_state;
		String id;
		int heuristic;
		int cost;
		boolean isTravelingRight;
				
		Node() {
			
		}
		
		Node(ArrayList<Integer> left_of_state, ArrayList<Integer> right_of_state, Node prev, int cost) {
			this.prev = prev;
			this.left_of_state = left_of_state;
			this.right_of_state = right_of_state;
			if (this.left_of_state != null)
				heuristic = left_of_state.size();
			this.cost =  cost;
			this.isTravelingRight = false;
		}
	}
	
	Tree(ArrayList<Integer> left_of_state, int numPeople, int goal) {
		this.numPeople = numPeople;
		this.goal = goal;
		root = new Node(left_of_state, null, null, 0);
	}
//	public void toggleTravelDir(boolean dir) {
//		isTravelingRight = isTravelingRight == true ? false : true;
//	}
	
	public void setupTree() {
		Node current = null;
		Queue<Node> nodesToExpand = new LinkedList<>(); 
		nodesToExpand.offer(root);
		
		int height = 0, div = 0; //used to uniquely identify each leftmost node of a subtree
		int count_ = 65;	// used when generating an id value for a node
		String left = "";	// // used when generating an id value for a node
		current = new Node();
		int count = root.left_of_state.size();
		// iterate from index 0 and concatenates with index + 1 create a new node then store in a linked list
		while (!nodesToExpand.isEmpty()) {
			if (current.prev != null) {
				count = nodesToExpand.peek().left_of_state.size();
				numPeople = count;
			}
			//|| i < current.prev.left_of_state.size()
			//height++;
			for (int i = 0; i < count-1; i++) {
				// person with the torch is traveling to the left of the bridge
				if (i == 0 && current.prev != null && current.prev.isTravelingRight == false) { 	// create siblings
					height++;
					current = new Node(null, null, current.prev, 0);
					current.prev = nodesToExpand.poll();
					current.left_of_state = new ArrayList<>();
					current.right_of_state = new ArrayList<>();
					
					current.cost = current.prev.right_of_state.get(i) + current.prev.cost;
					current.left_of_state.add(current.prev.right_of_state.get(i));
					current.heuristic = current.left_of_state.size();
					current.id = Character.toString((char)(++count_));
					current.isTravelingRight = false;
					
					// adds people on the left side of the bridge of the prev node to the left side of the bridge of the current node
					for (Integer person : current.prev.left_of_state) {
						current.left_of_state.add(person);
					}
					
					for (int k = 0; k < numPeople; k++) {
						if (k != i)
							current.right_of_state.add(current.prev.right_of_state.get(k));
					}
					continue;
				}
				else if (i != 0 && current.prev != null && current.prev.isTravelingRight == false) {
					current = new Node(null, null, current.prev, 0);
					current.prev = nodesToExpand.poll();
					current.left_of_state = new ArrayList<>();
					current.right_of_state = new ArrayList<>();
					
					
					current.left_of_state.add(current.prev.right_of_state.get(i));
					current.cost = current.prev.right_of_state.get(i);
					current.heuristic = current.left_of_state.size() + current.prev.cost;
					current.id = Character.toString((char)(++count_));
					current.isTravelingRight = false;
					
					// adds people on the left side of the bridge of the prev node to the left side of the bridge of the current node
					for (Integer person : current.prev.left_of_state) {
						current.left_of_state.add(person);
					}
					
					for (int k = 0; k < current.prev.left_of_state.size() && k < numPeople ; k++) {
						if (k != i)
							current.right_of_state.add(current.prev.left_of_state.get(k));
					}
					continue;
				}
				
				// || j < current.prev.right_of_state.size()
				for (int j = i + 1; j < count; j++)
				{
					// assign an id value
//					int count__ = 65;
//					String new_id = "";
//					String left_conc = "";
//					if ((current.id.charAt(0)) > 90) {
//						left_conc = Character.toString((char)count__);
//						current.id = Character.toString((char)((current.id.charAt(0)% 90)+64));
//						System.out.println(left_conc+current.id);
//						count_++;
//					}
//					else {
//						if (count_ > 65)
//							new_id = left_conc + current.id;
//						else
//							new_id = current.id;
//					}
//					current.id = Character.toString((char)((int)(current.id.charAt(0)+1)));
					
					int current_cost = 0;
					
					// create the leftmost node
					if (i == 0 && j == 1) {
//						if (current == null)
							if (current.prev == null) {
								current = new Node(null, null, current.prev, 0);
								current.id = "A";
							}
							else {
								current = new Node(null, null, current.prev, 0);
								current.id = Character.toString((char)(++count_));
							}
						current.prev = nodesToExpand.poll();
						leftmost = current;
						height++;
						
						if (current.prev.left_of_state.toString().length() > 2)
							leftmost.siblings = new LinkedList<>(); // create siblings list only if permutations are possible
						
						current.left_of_state = new ArrayList<>();
						current.right_of_state = new ArrayList<>();
						
						// person with the torch is moving to the right of the bridge
						
						if (i < current.prev.left_of_state.size())
							current.left_of_state.add(current.prev.left_of_state.get(i));
						if (j < current.prev.left_of_state.size())
							current.left_of_state.add(current.prev.left_of_state.get(j));
						current.heuristic = current.left_of_state.size();
						if (i < current.prev.left_of_state.size())
							current.cost = current.prev.cost + current.prev.left_of_state.get(i);
						current.isTravelingRight = true;
						
						for (int k = 0; k < numPeople && k <= current.prev.left_of_state.size(); k++) {
							if (k != i && k != j)
								current.right_of_state.add(current.prev.left_of_state.get(k));
						}
						
						
						/*
						 * modify this to handle a person crossing the bridge over to the left side 
						 */
//						else {
//							current.left_of_state.add(current.prev.right_of_state.get(i));
//							current.heuristic = current.right_of_state.size();
//							
//							for (int k = 0; k < numPeople; k++) {
//								if (k != i)
//									current.right_of_state.add(current.prev.right_of_state.get(k));
//							}
//						}
					}
					// create siblings of the leftmost node
					else {
						//Character.toString((char)((current.id.charAt(0)% 90)+64))
						current = new Node(null, null, current.prev, 0); // need to assign unique identifiers for each node
						if (i == current.prev.left_of_state.size() - 1 && j ==current.prev.left_of_state.size())
							current.prev = nodesToExpand.poll();
						
						current.left_of_state = new ArrayList<>();
						current.right_of_state = new ArrayList<>();
						
						if (i < current.prev.left_of_state.size()) {
							current.left_of_state.add(current.prev.left_of_state.get(i));
						}
						if (j < current.prev.left_of_state.size()) {
							current.left_of_state.add(current.prev.left_of_state.get(j));
						}
						current.heuristic = current.left_of_state.size();
						current.id = Character.toString((char)(++count_));
						current.isTravelingRight = true;
					
					
						for (int k = 0; k < numPeople; k++) {
							if (k != i && k != j)
								current.right_of_state.add(current.prev.left_of_state.get(k));
						}
						for (int k = 0; k < current.right_of_state.size(); k++) {
							if (current.right_of_state.get(k) > current.cost) 
								current.cost = current.right_of_state.get(k) + current.prev.cost;
						}
						
						/*
						 * modify this to handle a person crossing the bridge over to the left side 
						 */
//						else {
//							current.left_of_state.add(current.prev.right_of_state.get(i));
//							current.cost = current.right_of_state.get(j);
//							current.heuristic = current.left_of_state.size();
//							current.id = Character.toString((char)(++count_));
//						
//							for (int k = 0; k < numPeople; k++) {
//								if (k != i)
//									current.right_of_state.add(current.prev.right_of_state.get(k));
//							}
//						}
						if (i < current.prev.left_of_state.size()) {
							current.cost = current.prev.cost + current.prev.left_of_state.get(i);
						}
						leftmost.siblings.add(current);
						
					}
						
					// adds current node to the queue if it has children so it can be further expanded
					if (!current.left_of_state.isEmpty()) {
						nodesToExpand.offer(current);
						//count = current.left_of_state.size();
					}
				}
			}
		}
	}
	
	
	public static String depthFirstSearch(ArrayList<Integer> start) {
		Stack<Node> open = new Stack<>(); 
		Queue<Node> closed = new LinkedList<>(); 
		boolean isComplete = false;
		Node currentState = null;
		Tree.Node goalState = null;
		String path = null;
		
		while (!isComplete && !open.isEmpty()) {
			currentState = open.push(currentState);
			
			if (currentState.equals(goalState)) 
				isComplete = true;
			else {
				// generate children of X
				// push children of x to open
				closed.offer(currentState);
			}
		}
		
		return path;
	}
	
	public static  String breadthFirstSearch() {
		Queue<Node> open = new LinkedList<>(); 
		Queue<Node> closed = new LinkedList<>(); 
		boolean isComplete = false;
		Node currentState = null;
		Tree.Node goalState = null;
		
		while (!isComplete && !open.isEmpty()) {
			currentState = open.poll();
			
			if (currentState.equals(goalState)) 
				isComplete = true;
			else {
				// generate children of X
				// push children of x to open
				closed.offer(currentState);
			}
		}
		return null;
	}
	
	public static  String AStarAlgorithm() {
		
		return null;
	}
	
}
