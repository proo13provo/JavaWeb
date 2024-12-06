package test;

import java.util.ArrayList;

import java.util.Random;


public class Gene {
	private ArrayList<Integer> gene;
	private ArrayList<Integer> t = new ArrayList<>();

	public Gene() {
		super();
		this.gene = new ArrayList<>(9);
	}

	public Gene(ArrayList<Integer> gene1) {
		this.gene = gene1;
		Random r = new Random();
		for (int i = 0; i < 9; i++) {
			if (gene1.get(i) == 0) {
				int randomNumber;
				do {
					randomNumber = r.nextInt(9) + 1;
				} while (gene.contains(randomNumber));
				gene.set(i, randomNumber);
			} else {
				gene.set(i, gene1.get(i));
				t.add(i);

			}
		}
	}

	@Override
	public String toString() {
		return "" + gene + "\n"+"";
	}

	public ArrayList<Integer> getT() {
		return t;
	}

	public void setT(ArrayList<Integer> t) {
		this.t = t;
	}

	public ArrayList<Integer> getGene() {
		return gene;
	}

	public void setGene(ArrayList<Integer> gene) {
		this.gene = gene;
	}

}
