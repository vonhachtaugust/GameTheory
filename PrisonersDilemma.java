package homeproblem2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
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

import homeproblem2.XYLineChart;

public class PrisonersDilemma extends JPanel implements ActionListener {

	final int width = 640;
	final int height = 640;
	final int N = 7;
	public int timeStep = 0;

	public static final int row = 32;
	public static final int col = 32;
	public static final double mutrate = 1 / (row ^ 2);

	public Grid grid = new Grid(row, col);
	public CompetitionStep step1 = new CompetitionStep(row, col, N);
	public ReproductionStep step2 = new ReproductionStep(mutrate, row, col, N);
	public Object[][] lattice;

	public Random rand = new Random();

	public static void main(String[] args) {
		new PrisonersDilemma().program();
	}

	void program() {
		lattice = grid.getPlayers();
		initEvent();
		initGraphics();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		timeStep++;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				Player p = (Player) lattice[i][j];
				g.setColor(getStateColor(p.getState()));
				g.fillRect(getPlayerPosX(p, width / col), getPlayerPosY(p, height / row), height / row, height / row);
			}
		}
		step1.updatescores(step1.getScores(grid), grid);
		step2.getReproduction(grid);

		int[] check = grid.count();
		grid.addToDataset(check, timeStep);
		grid.Clear();

		if (timeStep > 103) {
			t.stop();
			new XYLineChart(row * col);
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
	Timer t = new Timer(100, this);

	private void initEvent() {
		t.setInitialDelay(500);
	}

}
