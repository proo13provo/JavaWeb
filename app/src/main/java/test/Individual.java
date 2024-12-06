package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.Random;

public class Individual {
	private static final int GRID_SIZE = 9;
	private static final int POPULATION_SIZE = 1000;
	private static final int CROSSOVER_SIZE = 500;
	private static final int MUTATION_SIZE = 300;
	private ArrayList<Gene> individual;
	private int[][] sudoku;

	public Individual(int[][] arr) {
		super();
		this.individual = new ArrayList<>(9);
		this.sudoku = arr;
		create9Gene();

	}

	private void create9Gene() {
		for (int i = 0; i < GRID_SIZE; i++) {
			ArrayList<Integer> arr = new ArrayList<>();
			for (int j = 0; j < GRID_SIZE; j++) {
				int value = sudoku[i][j];
				arr.add(value);
			}
			individual.add(new Gene(arr));
		}

	}

	@Override
	public String toString() {
		return "Individual " + "\n" + individual + "";
	}

	public ArrayList<Gene> getGenes() {
		return individual;
	}

	public void setGenes(ArrayList<Gene> individual) {
		this.individual = individual;
	}

//	public boolean checkIndividual(ArrayList<Individual> ts, Individual a) {
//		for (Individual individual : ts) {
//			boolean isSame = true;
//			for (int i = 0; i < 9; i++) {
//				for (int j = 0; j < 9; j++) {
//					if (individual.getGenes().get(i).getGene().get(j) == a.getGenes().get(i).getGene().get(j)) {
//						isSame = false;
//						break;
//					}
//				}
//				if (!isSame) {
//					break;
//				}
//			}
//			if (isSame) {
//				return true;
//
//			}
//		}
//		return false;
//
//	}

	public ArrayList<Individual> population() {// Tạo ra một 100 cá thể
		ArrayList<Individual> ts = new ArrayList<>();
		while (ts.size() != POPULATION_SIZE) {
			Individual a;
			do {
				a = new Individual(sudoku);
			} while (ts.contains(a));
			ts.add(a);
		}
		return ts;

	}

	public ArrayList<Individual> crossover(ArrayList<Individual> ts) {// Lai cá thể cha mẹ để tạo thành 1000 cá thể
																		// con
		Random r = new Random();
		ArrayList<Individual> ts1 = new ArrayList<>();
		ArrayList<Integer> ts2 = new ArrayList<>();
		ArrayList<Integer> ts3 = new ArrayList<>();
		for (int i = 0; i < CROSSOVER_SIZE; i++) {// Tạo ra một danh sách là các sô nguyên nhằm giúp việc lấy các cặp cá
													// thể cha
			// mẹ ngẫu nhiên để lai với nhau
			int a;
			do {
				a = r.nextInt(POPULATION_SIZE);
			} while (ts2.contains(a));
			ts2.add(a);
		}
		for (int i = 0; i < CROSSOVER_SIZE; i++) {// Tạo ra một danh sách là các sô nguyên nhằm giúp việc lấy các cặp cá
													// thể cha
			// mẹ ngẫu nhiên để lai với nhau và khác với danh sách trước đó
			int a;
			do {
				a = r.nextInt(POPULATION_SIZE);
			} while (ts2.contains(a) && ts3.contains(a));
			ts3.add(a);
		}

		for (int i = 0; i < CROSSOVER_SIZE; i++) {
			Individual id = new Individual(sudoku);// Tạo ra cá thể con thứ nhất
			Individual id1 = new Individual(sudoku);// Tạo ra cá thể con thứ hai
			for (int j = 0; j <= 4; j++) {
				if (j != 4) {// Lấy 4 phần tử là 4 gen đầu tên
					id.getGenes().set(j, ts.get(ts2.get(i)).getGenes().get(j));
					id1.getGenes().set(j, ts.get(ts3.get(i)).getGenes().get(j));

				} // Lấy 5 phần tử là 5 gen cuối cùng
				id.getGenes().set(j + 4, ts.get(ts3.get(i)).getGenes().get(j + 4));
				id1.getGenes().set(j + 4, ts.get(ts2.get(i)).getGenes().get(j + 4));

			}
			ts1.add(id);
			ts1.add(id1);
		}
		return ts1;
	}

	public ArrayList<Individual> mutation(ArrayList<Individual> ts) {// Đột biến cá thể con
		Random r = new Random();
		ArrayList<Individual> ts1 = new ArrayList<>();
		ArrayList<Integer> ts2 = new ArrayList<>();
		for (int i = 0; i < MUTATION_SIZE; i++) {// Đột biến cá thể con ngẫu nhiên
			int a;
			do {
				a = r.nextInt(POPULATION_SIZE);
			} while (ts2.contains(a));
			ts2.add(a);
		}

		for (int i = 0; i < MUTATION_SIZE; i++) {
			Individual a = new Individual(sudoku);
			Individual a2 = new Individual(sudoku);
			a = ts.get(ts2.get(i));
			// mutationcheck(a);
			do {
				a2 = mutationcheck(a);
			} while (fitnessfunction(a2) > fitnessfunction(a));
			ts1.add(a2);
		}

		return ts1;
	}

	public Individual mutationcheck(Individual a) {// Đổi chỗ cho hai số trong một gen ngẫu nhiên
		Random r = new Random();
		int b, c;
		do {
			b = r.nextInt(9);
			c = r.nextInt(9);

		} while (b == c || a.getGenes().get(b).getT().contains(b) || a.getGenes().get(b).getT().contains(c));
		int d = a.getGenes().get(b).getGene().get(b);
		a.getGenes().get(b).getGene().set(b, a.getGenes().get(b).getGene().get(c));
		a.getGenes().get(b).getGene().set(c, d);
		return a;

	}

	public int fitnessfunction(Individual a) {// Đếm sô xung đột
		return checkCol(a) + checkSquare(a);

	}

	public int checkCol(Individual a) {
		int count = 0;
		for (int i = 0; i < 9; i++) {
			ArrayList<Integer> seen = new ArrayList<>();
			for (int j = 0; j < 9; j++) {
				int num = a.getGenes().get(j).getGene().get(i);
				if (num != 0) {
					if (seen.contains(num)) {
						count++;
					}
					seen.add(num);
				}
			}
			seen.clear();

		}
		return count;

	}

	public int checkSquare(Individual a) {
		int count = 0;

		for (int i = 0; i < 9; i += 3) {
			for (int j = 0; j < 9; j += 3) {
				ArrayList<Integer> seen = new ArrayList<>();
				for (int k = i; k < i + 3; k++) {
					for (int l = j; l < j + 3; l++) {
						int num = a.getGenes().get(k).getGene().get(l);
						if (num != 0) {
							if (seen.contains(num)) {
								count++;
							}
							seen.add(num);

						}

					}

				}
				seen.clear();
			}

		}

		return count;

	}

	public Individual sudokuSolve() {
		ArrayList<Individual> ts = population();
		ArrayList<Individual> ts2 = crossover(ts);
		ArrayList<Individual> ts3 = mutation(ts2);
		ArrayList<Individual> ts4 = new ArrayList<>();
		ts4.addAll(ts);
		ts4.addAll(ts2);
		ts4.addAll(ts3);

		boolean foundZeroScoreIndividual = false; // Biến để kiểm tra đã tìm thấy individual có điểm số 0 chưa
		int i = 0;
		while (i < 1000 && !foundZeroScoreIndividual) {
			// Sắp xếp danh sách ts theo điểm số
			Collections.sort(ts4, Comparator.comparingInt(this::fitnessfunction));
			// Nếu cá thể có điểm số 0 tồn tại, trả về cá thể đó
			if (ts4.get(0).fitnessfunction(ts3.get(0)) == 0) {
				foundZeroScoreIndividual = true; // Đã tìm thấy, đặt cờ true
				return ts4.get(0);
			}
			// Lấy top 1000 cá thể tốt nhất
			ArrayList<Individual> top100 = new ArrayList<>(ts4.subList(0, Math.min(ts.size(), POPULATION_SIZE)));

			// Thực hiện crossover và mutation trên top100
			ts = top100;
			ts2 = crossover(ts);
			ts3 = mutation(ts2);
			ts4.clear();
			ts4.addAll(ts);
			ts4.addAll(ts2);
			ts4.addAll(ts3);

			i++; // Tăng biến đếm

		}
		// Nếu không tìm thấy giải pháp với điểm số 0, trả về cá thể có điểm số thấp
		// nhất
		Collections.sort(ts4, Comparator.comparingInt(this::fitnessfunction));
		System.out.println("Số xung đột là :" + fitnessfunction(ts4.get(0)));
		return ts4.get(0);
	}

	public static void main(String[] args) {
		int[][] arr = { { 5, 3, 0, 0, 7, 0, 0, 0, 0 }, { 6, 0, 0, 1, 9, 5, 0, 0, 0 }, { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
				{ 8, 0, 0, 0, 6, 0, 0, 0, 3 }, { 4, 0, 0, 8, 0, 3, 0, 0, 1 }, { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
				{ 0, 6, 0, 0, 0, 0, 2, 8, 0 }, { 0, 0, 0, 4, 1, 9, 0, 0, 5 }, { 0, 0, 0, 0, 8, 0, 0, 7, 9 }, };
		int[][] arr1 = { { 1, 5, 2, 3, 4, 9, 7, 8, 6 }, { 9, 0, 6, 5, 2, 8, 4, 1, 0 }, { 8, 0, 3, 1, 7, 6, 5, 0, 9 },
				{ 6, 8, 4, 0, 3, 2, 1, 7, 5 }, { 7, 1, 5, 6, 0, 4, 9, 3, 2 }, { 3, 2, 9, 7, 1, 0, 8, 6, 4 },
				{ 0, 3, 7, 2, 0, 1, 6, 4, 0 }, { 2, 6, 8, 4, 5, 7, 3, 0, 1 }, { 4, 9, 0, 8, 6, 3, 0, 5, 7 }, };
//int[][] arr1 = { { 1, 0, 2, 3, 4, 0, 7, 0, 6 }, 
//		{ 9, 0, 6, 5, 0, 8, 4, 1, 0 },
//		{ 8, 4, 0, 1, 7, 0, 5, 2, 9 },
//		{ 6, 0, 4, 0, 3, 2, 1, 7, 5 }, 
//		{ 7, 0, 5, 6, 0, 4, 8, 3, 2 }, 
//		{ 3, 2, 0, 7, 1, 0, 8, 0, 4 },
//		{ 0, 3, 7, 5, 0, 1, 0, 4, 0 }, 
//		{ 2, 9, 0, 4, 5, 0, 3, 0, 1 }, 
//		{ 4, 9, 2, 0, 6, 3, 0, 5, 7 }, };
		// Individual ind = new Individual(arr);
		Individual ind2 = new Individual(arr1);
		// testCreate9Gene1(ind.sudokuSolve());
		testCreate9Gene1(ind2.sudokuSolve());
	}

	public static void testCreate9Gene1(Individual a) {
		// Individual individual = new Individual();
		// In thông tin của mỗi gene trong individual
		if (a == null) {
			System.out.println("Không giải được");
		} else {
			System.out.println("Kết quả là :");
			for (int i = 0; i < a.getGenes().size(); i++) {
				System.out.println("Gene " + (i + 1) + ": " + a.getGenes().get(i));
			}
		}

	}

}
//	@Override
//	public boolean equals(Object o) {
//		if (this == o)
//			return true;
//		if (o == null || getClass() != o.getClass())
//			return false;
//
//		Individual that = (Individual) o;
//
//		return Arrays.deepEquals(sudoku, that.sudoku) && Objects.equals(individual, that.individual);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(individual, Arrays.deepHashCode(sudoku));
//	}