package delivery;

import java.util.*;

public class GeneGenerator {
private List<Box> genes;
	
	public GeneGenerator(List<Box> genePool) {
		this.genes = genePool;
	}
	
	public void addGene(Box gene) {
		if(!genes.contains(gene)) {
			this.genes.add(gene);
		}
	}

	public List<Box> getBoxes() {
		
		Random rand = new Random();
		int index1 = rand.nextInt(this.genes.size());
		int index2 = rand.nextInt(this.genes.size());
		while(index1 == index2) {
			index2 = rand.nextInt(this.genes.size());
		}
		
		List<Box> returns = new ArrayList<>(this.genes.subList(Math.min(index1, index2), Math.max(index1, index2)));
		
		return returns;
	}
	
	public Box getBox() {
		Random rand = new Random();
		int index = rand.nextInt(this.genes.size());
		return this.genes.get(index);
	}

	public int size() {
		return this.genes.size();
	}
}
