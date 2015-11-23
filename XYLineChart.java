package homeproblem2;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import homeproblem2.PrisonersDilemma;

public class XYLineChart extends JFrame {

	final int nrOfPlayers;
	PrisonersDilemma die = new PrisonersDilemma();

	final static List<Double> S_0 = new ArrayList<>();
	final static List<Double> S_1 = new ArrayList<>();
	final static List<Double> S_2 = new ArrayList<>();
	final static List<Double> S_3 = new ArrayList<>();
	final static List<Double> S_4 = new ArrayList<>();
	final static List<Double> S_5 = new ArrayList<>();
	final static List<Double> S_6 = new ArrayList<>();
	final static List<Double> S_7 = new ArrayList<>();

	// Constructor
	public XYLineChart(int nrOfPlayers) {
		super(" ");
		this.nrOfPlayers = nrOfPlayers;

		setSize(640, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel chartPanel = createChartPanel();
		add(chartPanel, BorderLayout.CENTER);

		setVisible(true);

	}

	private JPanel createChartPanel() {

		String chartTitle = "Prisoners Dilemma";
		String xAxisLabel = "Time";
		String yAxisLabel = "Population density";

		XYDataset dataset = createDataset();
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);

		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);

		NumberAxis range = (NumberAxis) plot.getRangeAxis();
		range.setNumberFormatOverride(NumberFormat.getNumberInstance());

		// sets paint color for each series
		renderer.setSeriesPaint(0, die.getStateColor(0));
		renderer.setSeriesPaint(1, die.getStateColor(1));
		renderer.setSeriesPaint(2, die.getStateColor(2));
		renderer.setSeriesPaint(3, die.getStateColor(3));
		renderer.setSeriesPaint(4, die.getStateColor(4));
		renderer.setSeriesPaint(5, die.getStateColor(5));
		renderer.setSeriesPaint(6, die.getStateColor(6));
		renderer.setSeriesPaint(7, die.getStateColor(7));

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		renderer.setSeriesStroke(3, new BasicStroke(2.0f));
		renderer.setSeriesStroke(4, new BasicStroke(2.0f));
		renderer.setSeriesStroke(5, new BasicStroke(2.0f));
		renderer.setSeriesStroke(6, new BasicStroke(2.0f));
		renderer.setSeriesStroke(7, new BasicStroke(2.0f));

		// Setting background color for the plot:
		plot.setBackgroundPaint(Color.WHITE);

		// Setting visibility and paint color for the grid lines:
		// plot.setRangeGridlinesVisible(true);
		// plot.setRangeGridlinePaint(Color.BLACK);

		// plot.setDomainGridlinesVisible(true);
		// plot.setDomainGridlinePaint(Color.BLACK);

		plot.setRenderer(renderer);

		return new ChartPanel(chart);
	}

	private XYDataset createDataset() {

		XYSeriesCollection dataset = new XYSeriesCollection();

		XYSeries series0 = new XYSeries("S_0");
		XYSeries series1 = new XYSeries("S_1");
		XYSeries series2 = new XYSeries("S_2");
		XYSeries series3 = new XYSeries("S_3");
		XYSeries series4 = new XYSeries("S_4");
		XYSeries series5 = new XYSeries("S_5");
		XYSeries series6 = new XYSeries("S_6");
		XYSeries series7 = new XYSeries("S_7");

		dataset.addSeries(getSeries(series0, S_0));
		dataset.addSeries(getSeries(series1, S_1));
		dataset.addSeries(getSeries(series2, S_2));
		dataset.addSeries(getSeries(series3, S_3));
		dataset.addSeries(getSeries(series4, S_4));
		dataset.addSeries(getSeries(series5, S_5));
		dataset.addSeries(getSeries(series6, S_6));
		dataset.addSeries(getSeries(series7, S_7));

		return dataset;
	}

	public XYSeries getSeries(XYSeries series, List<Double> S) {
		int t = 0;
		for (Double i : S) {
			series.add(t, i / nrOfPlayers);
			t++;
		}
		return series;
	}

}
