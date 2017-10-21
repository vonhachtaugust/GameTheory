package GameTheory;

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

import java.lang.Thread;

//import GameTheory.XYLineChart;

public class PrisonersDilemma extends JPanel implements ActionListener {

	final int width = 640;
	final int height = 640;
	final int N = 7;
	final double T = 1.5;
	final double P = 0.5;
	final int timesteps = 200;
	public int timeStep = 0;

	public static final int row = 32;
	public static final int col = 32;
	public static final double mutrate = 1 / (row ^ 2);

	public Grid grid = new Grid(row, col);
	public CompetitionStep step1 = new CompetitionStep(row, col, N, T, P);
	public ReproductionStep step2 = new ReproductionStep(mutrate, row, col, N);
	public Object[][] lattice;

	public Random rand = new Random();

  JButton a = new JButton("Start");
  JButton b = new JButton("Stop");
  Timer t = new Timer(10, this);


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

		//int[] check = grid.count();
		//grid.addToDataset(check);

		if (timeStep > timesteps) {
			t.stop();
			//new XYLineChart(row * col);
		}
		grid.Clear();
    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        System.err.println("Caught InterruptedException: " + e.getMessage());
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
		JFrame window = new JFrame("PrisonersDilemma");
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		window.setLayout(new BorderLayout());
		window.add(this, BorderLayout.CENTER);
		JPanel p = new JPanel();
		a.addActionListener(this);
		p.add(a);
		b.addActionListener(this);
		p.add(b);
		window.add(p, BorderLayout.SOUTH);

    window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
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

	private void initEvent() {
		t.setInitialDelay(500);
	}
}
