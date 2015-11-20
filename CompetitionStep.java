package homeproblem2;

public class CompetitionStep {

	private static int N;
	private static int row;
	private static int col;
	public static Double[][] score;

	public static double T = 1.5;
	public static double R = 1;
	public static double P = 0.5;
	public static double S = 0;

	public CompetitionStep(int row, int col, int N) {
		this.row = row;
		this.col = col;
		this.N = N;
		score = new Double[row][col];
	}

	public Double[][] getScores(Grid grid) {
		return scores(grid);
	}

	// Method that given the Object matrix evaluates the scores.
	public static Double[][] scores(Grid grid) {

		Object[][] lattice = grid.getPlayers();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				Player p = (Player) lattice[i][j];

				int up = grid.getIndAbove(i);
				int down = grid.getIndBelow(i);
				int right = grid.getIndRightOf(j);
				int left = grid.getIndLeftOf(j);

				/*
				 * int up = i - 1; int down = i + 1; int right = j + 1; int left
				 * = j - 1;
				 * 
				 * if (up < 0) { up = row - 1; } if (down > row - 1) { down = 0;
				 * } if (right > col - 1) { right = 0; } if (left < 0) { left =
				 * col - 1; }
				 */

				score[i][j] = (game(p, (Player) lattice[up][j], N) + game(p, (Player) lattice[down][j], N)
						+ game(p, (Player) lattice[i][right], N) + game(p, (Player) lattice[i][left], N));
				// System.out.print("(" + score[i][j] + ")" + " ");
				// System.out.println(p.getScore() + " ");
			}
			// System.out.println();
		}
		return score;
	}

	// Method update the score according to scores matrix given object matrix.
	public static void updatescores(Double[][] scores, Grid grid) {
		Object[][] lattice = grid.getPlayers();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) lattice[i][j];
				p.setScore(scores[i][j]);
				// System.out.println(p.getScore() + " ");
			}
			// System.out.println();
		}
	}

	// perform N rounds of prisoner's dilemma between player 1 and player 2
	public static double game(Player p1, Player p2, int N) {
		double i = p1.getState();
		double j = p2.getState();

		// closed formula for payoff
		double p1Score = Math.min(i, j) * R;
		if (i < j) {
			p1Score += T;
		} else if (i > j) {
			p1Score += S;
		} else if (i == j) {
			p1Score += P;
		}
		p1Score += P * (N - 1 - Math.min(i, j));

		return p1Score;
	}
}
