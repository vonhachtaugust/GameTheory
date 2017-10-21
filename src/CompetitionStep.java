package homeproblem2;

public class CompetitionStep {

	private static int N;
	private static int row;
	private static int col;
	public static Double[][] score;

	private static double T;
	private static double R = 1;
	private static double P;
	private static double S = 0;

	public CompetitionStep(int row, int col, int N, double T, double P) {
		this.row = row;
		this.col = col;
		this.T = T;
		this.P = P;
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

				score[i][j] = (game(p, (Player) lattice[up][j], N) + game(p, (Player) lattice[down][j], N)
						+ game(p, (Player) lattice[i][right], N) + game(p, (Player) lattice[i][left], N));
			}
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
			}
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
