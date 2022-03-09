package s_grammar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Grammar extends RunAssignment {
	private char[] non_terminal;
	private char[] terminal;
	private char start;
	private HashMap<Character, ArrayList<String>> prod_rules;
	private boolean isVarValid = true, isStartValid = true;
	private Stack<Character> visited;
	public String stOut = "", output = "";
	Queue<String> out;

	public Grammar(String V, String T, String S, String[] P) {
		this.terminal = T.toCharArray();
		this.non_terminal = V.toCharArray();
		if (S.length() > 1)
			isStartValid = false;
		else
			this.start = S.toCharArray()[0];
		prod_rules = new HashMap<>();
		
		for (String s : P) {
			if (s.charAt(1) != '-' && s.charAt(1) != '>') {
				isVarValid = false;
				break;
			}
			if (contains(non_terminal, s.charAt(0))) {
				if (prod_rules.containsKey(s.charAt(0))) 
					prod_rules.get(s.charAt(0)).add(s.substring(3));
				else {
					prod_rules.put(s.charAt(0), new ArrayList<String>());
					prod_rules.get(s.charAt(0)).add(s.substring(3));
				}
			}
		}
	}
	
	public boolean parseGrammar(String s) {
		out = new LinkedList<>();
		char current = start;
		System.out.println(current+"->"+prod_rules.get(current).get(0));
		visited = new Stack<>();
		int count = 0;
		
		if (prod_rules.get(current).size() == 1)
			addToVisited(prod_rules.get(current).get(0));
		else {
			
		}
		
		while (count < s.length() && !visited.empty()) {
			current = visited.pop();
			if (contains(terminal, current)) {
				output += current+"";
				//System.out.println(output);
			}
			if (visited.empty() && count < s.length()-1) return false;
			if (contains(terminal, current)) {
				if (current == s.charAt(count)) {
					if (count == s.length()-1 && visited.empty())
						break;
					count++;
					continue;
				}
				else {
					return false;
				}
			}
			if (current == '|') 
				continue;
			
			String substr = "";
			
			if (prod_rules.get(current).size() == 1) {
				String s1 = prod_rules.get(current).get(0);
				substr = getBestSubString(s1, s.charAt(count));
				if (substr.equals("")) 
					return false;
			}
				
			else {
				for (String s2 : prod_rules.get(current)) {
					substr = getBestSubString(s2, s.charAt(count));
					if (!substr.equals("")) {
						break;
					}
				}
				if (substr.equals(""))
					return false;
			}
			addToVisited(substr);
			Stack<Character> stack_copy = (Stack<Character>)visited.clone();
			stOut = "";
			while (!stack_copy.empty()) {
				stOut += stack_copy.pop();
			}
			out.offer(output + "" +stOut);
			System.out.println();
		}
		return true;
	}
	
	
	public Queue<String> getOutput() {
		return out;
	}
	private String getBestSubString(String s, char c) {
		ArrayList<Integer> indexes = new ArrayList<>();
		String str = "";
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '|') {
				indexes.add(i);
			}
		}
		
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				if (indexes == null)
					return s.substring(i);
				else if (count == indexes.size()) {
					return s.substring(i);
				}
				else 
					return s.substring(i, indexes.get(count));
			}
			else {
				i = indexes.get(count);
				count++;
			}
		}
		return str; 		
	}
	
	private void addToVisited(String s) {
		for (int i = s.length()-1; i >= 0; i--) {
			visited.push(s.charAt(i));
		}
	}

	private boolean contains(char[] array, char c) {
		for (char ch : array) {
			if (ch == c) return true;
		}
		return false;
	}
	
	private boolean areTerminalSymbolsOK() {
		for (char c : terminal) {
			if ((int)c >= 65 && (int)c <= 90) {
				return false;
			}
		}
		return true;
	}
	
	private boolean areNonTerminalSymbolsOK() {
		for (char c : non_terminal) {
			if ((int)c < 65 || (int)c > 90) {
				return false;
			}
		}		
		return true;
	}
	
	public boolean isSGrammar() {
		int counter = 0;
		ArrayList<Character> indexOneArray = new ArrayList<>();
		if (!isVarValid || !isStartValid || !areTerminalSymbolsOK() || !areNonTerminalSymbolsOK()) return false;
		for (Map.Entry<Character, ArrayList<String>> entry : prod_rules.entrySet()) {
			if (entry.getValue().size() == 1) {
				for (int i = 0; i < entry.getValue().get(0).length(); i++) {
					if (entry.getValue().get(0).charAt(i) == '|') {
						counter = 0;
						continue;
					}
					if (counter == 0 && !contains(terminal, entry.getValue().get(0).charAt(i))) 
						return false;
					else {
						char c = entry.getValue().get(0).charAt(i);
						boolean b = contains(terminal, c);
						if (counter != 0 && b) 
							return false;
					}
					counter++;
				}
			}
			else {
				for (String s : entry.getValue()) {
					for (int i = 0; i < s.length(); i++) {
						if (entry.getValue().get(0).charAt(i) == '|') {
							counter = 0;
							continue;
						}
						if (counter == 0 && !contains(terminal, s.charAt(i))) 
							return false;
						else {
							if (counter != 0 && contains(terminal, s.charAt(i))) 
								return false;
						}
						if (counter == 0 && !indexOneArray.contains(s.charAt(i))) {
							indexOneArray.add(s.charAt(i));
						}
						else if (counter == 0 && indexOneArray.contains(s.charAt(i)))
							return false;
						counter++;
					}
					counter = 0;
				}
			}
			counter = 0;
			indexOneArray.clear();
		}
		return true;
	}
	
	public static void main(String[] args) {
		String[] P = {"S->aAB", "A->aAB|b", "B->b", "A->c"};
		Grammar G1 = new Grammar("SAB", "abc", "S", P);
		System.out.println(G1.parseGrammar("aabbb"));
	}
}
