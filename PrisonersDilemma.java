package homeproblem2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class PrisonersDilemma extends JPanel implements ActionListener {

	final int width = 400; // Size of paint area
	final int height = 400;
	final int N = 7;
	public static final int row = 32;
	public static final int col = 32;
	public static final double mutrate = 0.01;

	public Grid grid = new Grid(row, col);
	public CompetitionStep step1 = new CompetitionStep(row, col, N);
	public ReproductionStep step2 = new ReproductionStep(mutrate, row, col, N);

	public Random rand = new Random();

	public static void main(String[] args) {
		new PrisonersDilemma().program();
	}

	void program() {

		// Initialise
		Object[][] lattice = grid.getPlayers();

		/*
		 * for (int z = 0; z < 100; z++) { // Competitions step
		 * System.out.println(); System.out.println("Competition: ");
		 * step1.updatescores(step1.getScores(grid), grid);
		 * 
		 * for (int i = 0; i < row; i++) { for (int j = 0; j < col; j++) {
		 * Player p = (Player) lattice[i][j]; System.out.print(p.getState() +
		 * "[" + p.getScore() + "]\t"); } System.out.println(); }
		 * 
		 * System.out.println(); System.out.println("Reproduction: ");
		 * 
		 * // Reproduction step step2.getReproduction(grid);
		 * 
		 * for (int i = 0; i < row; i++) { for (int j = 0; j < col; j++) {
		 * Player p = (Player) lattice[i][j]; System.out.print(p.getState() +
		 * "\t"); } System.out.println(); } }
		 */

		initEvent();
		initGraphics();

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		// repaint();

	}

	void initGraphics() {

		setPreferredSize(new Dimension(width, height));
		JFrame window = new JFrame(
				"Title"/*
						 * "d = " + board.threshold + " " + "beta = " + beta +
						 * " " + "gamma = " + gamma
						 */);
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);

		JPanel p = new JPanel();

		a.addActionListener(this);
		p.add(a);

		b.addActionListener(this);
		p.add(b);

		window.add(p, BorderLayout.SOUTH);

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == t) {
			System.out.println("Timer");
		}

		if (e.getSource() == a) {
			System.out.println("Go");
			t.start();
		}

		if (e.getSource() == b) {
			System.out.println("Stop");
			t.stop();
		}

		repaint();
	}

	JButton a = new JButton("Start");
	JButton b = new JButton("Stop");
	Timer t = new Timer(500, this);

	private void initEvent() {
		t.setInitialDelay(500);
	}

}
