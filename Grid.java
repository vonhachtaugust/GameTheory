package homeproblem2;

import java.util.Random;

public class Grid {

	final int row;
	final int col;
	public static Random rand = new Random();
	private static Object[][] grid;

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
				// System.out.println(p);
			}
		}
		return grid;
	}

	public Object[][] getPlayers() {
		/*
		 * for (int i = 0; i < row; i++) { for (int j = 0; j < col; j++) {
		 * System.out.print(grid[i][j] + " "); } System.out.println(); }
		 */
		return grid;
	}

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
}
