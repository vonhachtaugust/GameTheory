package homeproblem2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class PrisonersDilemma extends JFrame {

	final int width = 400; // Size of paint area
	final int height = 400;
	final int N = 7;
	public static final int row = 4;
	public static final int col = 4;
	public static final double mutrate = 0.01;

	public Grid grid = new Grid(row, col);
	public CompetitionStep step1 = new CompetitionStep(row, col, N);
	public ReproductionStep step2 = new ReproductionStep(mutrate);

	public Random rand = new Random();

	public static void main(String[] args) {
		new PrisonersDilemma().program();
	}

	void program() {

		// Initialise
		Object[][] lattice = grid.getPlayers();

		for (int z = 0; z < 2; z++) {
			// Competitions step
			System.out.println();
			System.out.println("Competition: ");
			step1.updatescores(step1.getScores(grid), grid);

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					Player p = (Player) lattice[i][j];
					System.out.print(p.getState() + "[" + p.getScore() + "]\t");
				}
				System.out.println();
			}

			System.out.println();
			System.out.println("Reproduction: ");

			// Reproduction step
			step2.getReproduction(grid);

			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					Player p = (Player) lattice[i][j];
					System.out.print(p.getState() + "\t");
				}
				System.out.println();
			}
		}

		// initGraphics();

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		repaint();

	}

	void initGraphics() {

		setPreferredSize(new Dimension(width, height));
		JFrame window = new JFrame(
				"Title"/*
						 * "d = " + board.threshold + " " + "beta = " + beta +
						 * " " + "gamma = " + gamma
						 */);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.add(this);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		/*
		 * window.add(stoppButton); stoppButton.setLocation(width, height);
		 * stoppButton.addActionListener(this);
		 */
	}

	public void actionPerformed(ActionEvent e) {
		;
	}

}
