package homeproblem2;

import java.util.ArrayList;
import java.util.Random;

public class ReproductionStep {

	public Grid newGrid;
	private double mutrate;
	private int N;
	private int row;
	private int col;

	private ArrayList<Player> neighbours = new ArrayList<Player>();
	private Integer[][] updateLattice;
	private Random rand = new Random();

	public ReproductionStep(double mutrate, int row, int col, int N) {
		this.mutrate = mutrate;
		this.row = row;
		this.col = col;
		this.N = N;
		updateLattice = new Integer[row][col];

	}

	public Grid getReproduction(Grid grid) {
		return changeStates(updateLattice(grid), grid);
	}

	public Grid changeStates(Integer[][] lattice, Grid grid) {

		Object[][] toupdate = grid.getPlayers();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) toupdate[i][j];
				p.setState(lattice[i][j]);
			}
		}
		return grid;
	}

	public Integer[][] updateLattice(Grid origGrid) {
		Object[][] origLattice = origGrid.getPlayers();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				// Current player
				Player p = (Player) origLattice[i][j];
				double myScore = p.getScore();
				int newStrat;

				// Best strategy so far
				int bestStrat = p.getState();
				double bestScore = p.getScore();

				neighbours.clear();

				// Neighbour above
				int up = origGrid.getIndAbove(i);
				Player upPlayer = (Player) origLattice[up][j];
				neighbours.add(upPlayer);

				// Neighbour below
				int down = origGrid.getIndBelow(i);
				Player downPlayer = (Player) origLattice[down][j];
				neighbours.add(downPlayer);

				// Neighbour to the right
				int right = origGrid.getIndRightOf(j);
				Player rightPlayer = (Player) origLattice[i][right];
				neighbours.add(rightPlayer);

				// Neighbour to the left
				int left = origGrid.getIndLeftOf(j);
				Player leftPlayer = (Player) origLattice[i][left];
				neighbours.add(leftPlayer);

				// Check for highest score and update bestStrat
				for (int n = 0; n < neighbours.size(); n++) {
					Player thisPlayer = neighbours.get(n);
					if (thisPlayer.getScore() > bestScore) {
						bestStrat = thisPlayer.getState();
						bestScore = thisPlayer.getScore();
					} else if (thisPlayer.getScore() == bestScore) { // break_ties_randomly
						Double r = Math.random();
						if (r > 0.5) {
							bestStrat = thisPlayer.getState();
							bestScore = thisPlayer.getScore();
						}
					}
				}

				// Mutation
				if (Math.random() < mutrate) {
					bestStrat = rand.nextInt(N + 1);
				}
				updateLattice[i][j] = bestStrat;
			}
		}
		return updateLattice;
	}
}