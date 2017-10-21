package GameTheory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

	final int row;
	final int col;
	public static Random rand = new Random();
	private static Object[][] grid;
	private int[] countStateList = new int[8];

	public Grid(int row, int col) {
		this.row = row;
		this.col = col;
		grid = new Object[row][col];
		addPlayers(row, col);
	}

	public static Object[][] addPlayers(int row, int col) {
		int k = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				k = rand.nextInt(8);
				Player p = new Player(i, j, 0, k);
				grid[i][j] = p;
			}
		}
		return grid;
	}

	public Object[][] getPlayers() {
		return grid;
	}

	public int[] count() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) grid[i][j];
				countStateList[p.getState()]++;
			}
		}
		return countStateList;
	}

	public void Clear() {
		for (int i = 0; i < countStateList.length; i++) {
			countStateList[i] = 0;
		}
	}

    //public void addToDataset(int[] stateList) {
		//XYLineChart.S_0.add((double) stateList[0]);
		//XYLineChart.S_1.add((double) stateList[1]);
		//XYLineChart.S_2.add((double) stateList[2]);
		//XYLineChart.S_3.add((double) stateList[3]);
		//XYLineChart.S_4.add((double) stateList[4]);
		//XYLineChart.S_5.add((double) stateList[5]);
		//XYLineChart.S_6.add((double) stateList[6]);
		//XYLineChart.S_7.add((double) stateList[7]);
    //}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public int getIndAbove(int i) {
		return correctPos(i - 1, row);
	}

	public int getIndRightOf(int i) {
		return correctPos(i + 1, col);
	}

	public int getIndBelow(int i) {
		return correctPos(i + 1, row);
	}

	public int getIndLeftOf(int i) {
		return correctPos(i - 1, col);
	}

	private int correctPos(int i, int lim) {
		return (i + lim) % lim;
	}

	public void updateLattice(Object[][] lattice) {
		grid = lattice;
	}
}
