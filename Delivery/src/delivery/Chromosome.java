package delivery;

import java.util.*;



public class Chromosome {
private List<Box> genes;
	
	private final int maxCapacity;
	
	private final int minQuota;
	
	private final double fitness;
	
	public Chromosome(int capacity, int qouta, List<Box> InitailizedGenes) {
		this.maxCapacity = capacity;
		this.minQuota = qouta;
		if(InitailizedGenes == null || InitailizedGenes.isEmpty())
			System.exit(0);
		this.genes = InitailizedGenes;
		
		this.fitness = this.evaluateFitness();
	}

	private double evaluateFitness() {
		if((this.getWeight() <= this.maxCapacity) && ( this.getValue() >= this.minQuota))
			return 1;
		
		if((this.getWeight() <= this.maxCapacity)){
			return 0.75;
		}
		
		if(this.getValue() >= this.minQuota)
			return 0.5;
		return 0.1;
	}

	public List<Box> getBoxes() {
		return genes;
	}

	public void setBoxes(List<Box> genes) {
		this.genes = genes;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public int getMinQuota() {
		return minQuota;
	}

	public double getFitness() {
		return fitness;
	}

	@Override
	public String toString() {
		return genes.toString();
	}
	

	public int getWeight() {
		int sum = 0;
		for(Box gene: this.genes)
			sum += gene.getWeight();
		return sum;
	}
	
	
	public int getValue() {
		int sum = 0;
		for(Box gene: this.genes)
			sum += gene.getValue();
		return sum;
	}
	
	public int ChromeLength() {
		return this.genes.size();
	}
	
	
	public int compareTo(Chromosome o) {
		if(this.getFitness() > o.getFitness())
			return -1;
		else if (this.getFitness() == o.getFitness())
			return 0;
		return 1;
	}
}
