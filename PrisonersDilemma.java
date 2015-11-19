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
	final int N = 1;
	public static final int row = 2;
	public static final int col = 2;

	public Grid grid = new Grid(row, col);
	public CompetitionStep step = new CompetitionStep(row, col, N);
	public Random rand = new Random();

	public static void main(String[] args) {
		new PrisonersDilemma().program();
	}

	void program() {

		// Initialise
		Object[][] lattice = grid.getPlayers();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) lattice[i][j];
				System.out.print(p.getScore() + " ");
			}
			System.out.println();
		}

		// Competitions step
		step.updatescores(step.getScores(grid), grid);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) lattice[i][j];
				p.setState(rand.nextInt(8));
				System.out.print(p.getScore() + " ");
			}
			System.out.println();
		}

		// Reproduction step
		step.updatescores(step.getScores(grid), grid);

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) lattice[i][j];
				System.out.print(p.getScore() + " ");
			}
			System.out.println();
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
