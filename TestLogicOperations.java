package homeproblem2;

import java.awt.List;

import javax.swing.*;

import homeproblem2.Player.*;
import task1.Actor;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class TestLogicOperations {

	// payoff parameters
	public static int N = 1;
	public static double T = 2;
	public static double R = 1;
	public static double P = 0.5;
	public static double S = 0;
	public static final int row = 4;
	public static final int col = 4;
	public static Object[][] lattice;
	public static Double[][] scores;
	final static Random rand = new Random();

	public static void main(String[] args) {

		updatescores(scores(grid()), grid());
	}

	// Method generates matrix containing objects, in this case Players.
	public static Object[][] grid() {
		int k = 0;
		lattice = new Object[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				k = rand.nextInt(8);
				lattice[i][j] = new Player(i, j, 0, k);
			}
		}
		return lattice;
	}

	// Method that given the Object matrix evaluates the scores.
	public static Double[][] scores(Object[][] grid) {
		scores = new Double[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {

				Player p = (Player) lattice[i][j];

				int up = i - 1;
				int down = i + 1;
				int right = j + 1;
				int left = j - 1;

				if (up < 0) {
					up = row - 1;
				}
				if (down > row - 1) {
					down = 0;
				}
				if (right > col - 1) {
					right = 0;
				}
				if (left < 0) {
					left = col - 1;
				}

				scores[i][j] = (game(p, (Player) lattice[up][j], N) + game(p, (Player) lattice[down][j], N)
						+ game(p, (Player) lattice[i][right], N) + game(p, (Player) lattice[i][left], N));
				System.out.print("(" + scores[i][j] + ")" + " ");
				System.out.println(p.getScore() + " ");
			}
			System.out.println();
		}
		return scores;
	}

	// Method update the score according to scores matrix given object matrix.
	public static void updatescores(Double[][] scores, Object[][] grid) {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) grid[i][j];
				p.setScore(scores[i][j]);
				System.out.println(p.getScore() + " ");
			}
			System.out.println();
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
