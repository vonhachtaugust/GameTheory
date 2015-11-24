package homeproblem2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PhaseDiagram extends JPanel {

	final int width = 320;
	final int height = 320;
	final int N = 7;
	final double T = 0;
	final double P = 0;
	final int interiorpoints = 10;
	public int timeStep = 0;

	public static final int row = 32;
	public static final int col = 32;
	public static final double mutrate = 1 / (row ^ 2);

	public Object[][] lattice;
	public int[][] phasepoints = new int[interiorpoints][interiorpoints];
	// public ReproductionStep step2 = new ReproductionStep(mutrate, row, col,
	// N);

	public static void main(String[] args) {
		new PhaseDiagram().program();
	}

	void program() {
		/*
		 * int[][] points = getPhasePoints(); for (int i = 0; i <
		 * interiorpoints; i++) { for (int j = 0; j < interiorpoints; j++) {
		 * System.out.print(points[i][j] + "\t"); } System.out.println(); }
		 */

		initGraphics();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		int[][] points = getPhasePoints();

		for (int i = 0; i < interiorpoints; i++) {
			for (int j = 0; j < interiorpoints; j++) {
				g.setColor(getStateColor(points[i][j]));
				g.fillRect(i * row, j * col, row, col);
			}
		}

	}

	public Color getStateColor(int s) {
		int rgb = 255 - 30 * (s + 1);
		Color c;
		if (s == 7) {
			c = new Color(0, 0, 255);
		} else {
			c = new Color(rgb, rgb, rgb);
		}
		return c;
	}

	private int getPlayerPosX(Player p, int cellWidth) {
		return (p.getCol()) * cellWidth;
	}

	private int getPlayerPosY(Player p, int cellHeight) {
		return (p.getRow()) * cellHeight;
	}

	void initGraphics() {

		setPreferredSize(new Dimension(width, height));
		JFrame window = new JFrame("Title");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

	public int[][] getPhasePoints() {

		double stepLengthT = 0.2;
		double stepLengthP = 0.1;
		double Tmax = 2 / stepLengthT;
		double Pmax = 1 / stepLengthP;
		int timeSteps = 1000;

		for (int i = 0; i < Tmax; i++) {
			for (int j = 0; j < Pmax; j++) {
				Grid grid = new Grid(row, col);
				CompetitionStep step1 = new CompetitionStep(row, col, N, i * stepLengthT, j * stepLengthP);
				ReproductionStep step2 = new ReproductionStep(mutrate, row, col, N);
				lattice = grid.getPlayers();
				for (int t = 0; t < timeSteps; t++) {
					step1.updatescores(step1.getScores(grid), grid);
					step2.getReproduction(grid);
				}
				int[] set = grid.count();
				int maxNum = 0;
				int tcc = -1;
				for (int l = 0; l < set.length; l++) {
					if (set[l] > maxNum) {
						maxNum = set[l];
						tcc = l;
					}
				}
				phasepoints[i][j] = tcc;
				grid.Clear();
			}
		}
		return phasepoints;
	}
}
