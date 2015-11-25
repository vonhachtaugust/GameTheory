package homeproblem2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.math.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class PhaseDiagram extends JPanel {

	private static final int row = 32;
	private static final int col = 32;
	private static final double mutrate = 1 / (row ^ 2);
	private static final int BORDER_GAP = 100;
	private static final int HATCH = 5;
	private static final int width = 400;
	private static final int height = 400;
	private static final int N = 7;
	private static final int interiorpoints = 20;
	private static final int timeSteps = 1000;
	private static final int nrOfAverage = 10;

	private static int timeStep = 0;

	private static List<Double> average = new ArrayList<>();
	private static DecimalFormat df = new DecimalFormat("0.0##");

	private Object[][] lattice;
	private int[][] phasepoints = new int[interiorpoints][interiorpoints];
	private int[][] points = getPhasePoints();

	public static void main(String[] args) {
		new PhaseDiagram().program();
	}

	void program() {
		initGraphics();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		// add ticks y axis
		for (int i = 0; i < interiorpoints + 1; i++) {
			int x0 = (int) BORDER_GAP / 2;
			int y0 = getPosY(i - 1);
			int x1 = (int) BORDER_GAP / 2 - HATCH;
			int y1 = getPosY(i - 1);
			g2.drawLine(x0, y0, x1, y1);

			if (i % 2 == 0) {
				String yLabel = df.format((double) i / interiorpoints) + "";
				FontMetrics metric = g2.getFontMetrics();
				int labelWidth = metric.stringWidth(yLabel);
				g2.drawString(yLabel, x0 - labelWidth - 10, y0 + (metric.getHeight() / 2) - 3);
			}
			if (i == interiorpoints / 2) {
				String unit = "P";
				FontMetrics units = g2.getFontMetrics();
				int labelWidth = units.stringWidth(unit);
				g2.drawString(unit, x0 - labelWidth - 35, y0 + (units.getHeight() / 2) - 3);
			}
		}

		// add ticks x axis
		for (int i = 0; i < interiorpoints + 1; i++) {
			int x0 = getPosX(i);
			int y0 = (int) BORDER_GAP / 2 + height;
			int x1 = getPosX(i);
			int y1 = (int) BORDER_GAP / 2 + HATCH + height;
			g2.drawLine(x0, y0, x1, y1);

			if (i % 2 == 0) {
				String yLabel = df.format((double) i / interiorpoints + (double) 1) + "";
				FontMetrics metric = g2.getFontMetrics();
				int labelWidth = metric.stringWidth(yLabel);
				g2.drawString(yLabel, x0 - (metric.getHeight() / 2), y0 + labelWidth);
			}
			if (i == interiorpoints / 2) {
				String unit = "T";
				FontMetrics units = g2.getFontMetrics();
				int labelWidth = units.stringWidth(unit);
				g2.drawString(unit, x0 - (units.getHeight() / 2), y0 + 40);
			}
		}

		for (int i = 0; i < interiorpoints; i++) {
			for (int j = 0; j < interiorpoints; j++) {
				g.setColor(getStateColor(points[i][j]));
				g.fillRect(getPosX(i), getPosY(j), getRectSizeX(), getRectSizeY());
				g.setColor(Color.BLACK);
				g.drawRect(getPosX(i), getPosY(j), getRectSizeX(), getRectSizeY());
			}
		}

		System.out.println("Done");

	}

	public int getPosX(int i) {
		return i = BORDER_GAP / 2 + i * (width / interiorpoints);
	}

	public int getPosY(int i) {
		return i = height - (i + 1) * (width / interiorpoints) + BORDER_GAP / 2;
	}

	public int getRectSizeX() {
		return width / interiorpoints;
	}

	public int getRectSizeY() {
		return height / interiorpoints;
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

		setPreferredSize(new Dimension(width + BORDER_GAP, height + BORDER_GAP));
		JFrame window = new JFrame("Phase diagram");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

	}

	public int[][] getPhasePoints() {

		double Tmax = 1;
		double Pmax = 1;

		double stepLengthT = Tmax / interiorpoints;
		double stepLengthP = Pmax / interiorpoints;
		double Tstep = 1 / stepLengthT;
		double Pstep = 1 / stepLengthP;

		for (int i = 0; i < Tstep; i++) {
			for (int j = 0; j < Pstep; j++) {
				int[] occur = new int[8];
				for (int n = 0; n < nrOfAverage; n++) {
					Grid grid = new Grid(row, col);
					CompetitionStep step1 = new CompetitionStep(row, col, N, 1 + i * stepLengthT, j * stepLengthP);
					ReproductionStep step2 = new ReproductionStep(mutrate, row, col, N);
					lattice = grid.getPlayers();
					for (int t = 0; t < timeSteps; t++) {
						step1.updatescores(step1.getScores(grid), grid);
						step2.getReproduction(grid);
					}
					int[] set = grid.count();
					for (int l = 0; l < set.length; l++) {
						occur[l] = occur[l] + set[l];
					}
					grid.Clear();
				}
				double maxNum = 0;
				int tcc = -1;
				for (int m = 0; m < occur.length; m++) {
					if (occur[m] > maxNum) {
						maxNum = occur[m];
						tcc = m;
					}
				}
				phasepoints[i][j] = tcc;
			}
		}
		return phasepoints;
	}
}
