package delivery;

import java.util.*;


public class Population {
	private int populationSize;
	private List<Chromosome> individuals;
	int fittest;
	private int quota;
	private int capacity;
	private GeneGenerator geneFactory;
	private int currentGen;
	private double mutationRate;

	public Population(int capacity, int qouta, GeneGenerator geneFactory, int populationSize, double mutationRate) {
		this.capacity = capacity;
		this.quota = qouta;
		this.geneFactory = geneFactory;
		this.populationSize = populationSize;
		this.currentGen = 0;
		this.mutationRate = mutationRate;
		individuals = new ArrayList<>();
		initializeP(this.populationSize);
	}

	public void initializeP(int size) {
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			List<Box> genes = new ArrayList<>();
			int loop = rand.nextInt(geneFactory.size() - 1)+1;
			for(int j = 0; j < loop; j++) {
				Box gene = geneFactory.getBox();
				while(genes.contains(gene)) {
					gene = geneFactory.getBox();
				}
				genes.add(gene);
			}
			individuals.add(new Chromosome(capacity, quota, genes));
		}
	}
	
	public boolean evaluatePopulation() {
		System.out.print("Population Evaluation: " + this.currentGen);
		boolean foundSuccess = false;
		Chromosome fittestG = null;
		for(Chromosome individual : this.individuals) {
			if(fittestG == null)
				fittestG = individual;
			else
				fittestG = (individual.getFitness() > fittestG.getFitness()) ? individual : fittestG;
			if(individual.getFitness() >= 1) {
				foundSuccess = true;
				System.out.println("\t\tFound: "+foundSuccess);
				System.out.println("Solution : "+individual.toString());
				return foundSuccess;
			}
		}
		System.out.print("\t\tFound: "+foundSuccess);
		System.out.print("\tFittest: " +fittestG);
		this.currentGen++;
		return foundSuccess;
	}

	public void Selection() {
		List<Chromosome> children = new ArrayList<>();
		while (children.size() < individuals.size()) {
			Chromosome partner1 = this.selectPartner();
			Chromosome partner2 = this.selectPartner();
			while (partner2.equals(partner1)) {
				partner2 = this.selectPartner();
			}

			Chromosome child = crossover(partner1, partner2);
			mutation(child);
			children.add(child);
		}

		individuals.clear();
		individuals = children;
	}

	private void mutation(Chromosome child) {
		if(Math.random() < this.mutationRate) {
			int index = (int)Math.random()*child.ChromeLength();
			Box mutated = this.geneFactory.getBox();
			while(child.getBoxes().get(index).equals(mutated))
			{
				mutated = this.geneFactory.getBox();
			}
			
			child.getBoxes().set(index, mutated);
			Set<Box> set = new HashSet<>(child.getBoxes());
			child.setBoxes(new ArrayList<>(set));
		}
	}

	private Chromosome crossover(Chromosome partner1, Chromosome partner2) {
		Chromosome child;
		Random rand = new Random();
		int crossPoint = rand.nextInt(partner1.ChromeLength());
		while (crossPoint >= partner2.ChromeLength())
			crossPoint = rand.nextInt(partner1.ChromeLength());
		List<Box> newGenes = new ArrayList<>();

		newGenes.addAll(partner1.getBoxes().subList(0, crossPoint));
		for(Box gene : partner2.getBoxes().subList(crossPoint, partner2.ChromeLength())) {
			if(!newGenes.contains(gene))
				newGenes.add(gene);
		}
		child = new Chromosome(this.capacity, this.quota, newGenes);
		return child;
	}

	private Chromosome selectPartner() {
		Random ron = new Random();
		int k = this.populationSize > 5 ? 5 : this.populationSize;
		List<Chromosome> tournament = new ArrayList<>();
		TreeSet<Chromosome> tree = new TreeSet<>();
		
		while(tournament.size() != k) {
			Chromosome select = this.individuals.get(ron.nextInt(this.populationSize));
			if(!tournament.contains(select))
				tournament.add(select);
		}
		Collections.shuffle(tournament);
		
		Chromosome fittest = tournament.get(0);
		for(Chromosome c:tournament) {
			if(c.getFitness() < fittest.getFitness()) {
				fittest = c;
			}
		}
		
		return fittest;

	}
	
	
	
}
