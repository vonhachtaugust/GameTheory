package homeproblem2;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYLineChart extends JFrame {
	
	// Constructor
		public XYLineChart() {
			super(" ");
			
			setSize(640, 480);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);

			JPanel chartPanel = createChartPanel();
			add(chartPanel, BorderLayout.CENTER);
			
			setVisible(true);
			
		}

		private JPanel createChartPanel() {

			String chartTitle = "SIR model";
			String xAxisLabel = "Time";
			String yAxisLabel = "Population density";

			XYDataset dataset = createDataset();
			JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, xAxisLabel, yAxisLabel, dataset);
			
			XYPlot plot = chart.getXYPlot();
			XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true,false);
			 
			// sets paint color for each series
			renderer.setSeriesPaint(0, Color.RED);
			renderer.setSeriesPaint(1, Color.BLUE);
			renderer.setSeriesPaint(2, Color.GREEN);
			 
			// sets thickness for series (using strokes)
			renderer.setSeriesStroke(0, new BasicStroke(2.0f));
			renderer.setSeriesStroke(1, new BasicStroke(2.0f));
			renderer.setSeriesStroke(2, new BasicStroke(2.0f));
			
			// Setting background color for the plot:
			plot.setBackgroundPaint(Color.DARK_GRAY);
			
			// Setting visibility and paint color for the grid lines:
			plot.setRangeGridlinesVisible(true);
			plot.setRangeGridlinePaint(Color.BLACK);
			 
			plot.setDomainGridlinesVisible(true);
			plot.setDomainGridlinePaint(Color.BLACK);
			 
			plot.setRenderer(renderer);

			return new ChartPanel(chart);
		}

		private XYDataset createDataset() {
			
			XYSeriesCollection dataset = new XYSeriesCollection();
		    
			XYSeries series1 = new XYSeries("Number of infected");
		    XYSeries series2 = new XYSeries("Number of susceptible");
		    XYSeries series3 = new XYSeries("Number of recovered");
			
			
			dataset.addSeries(series1);
		    dataset.addSeries(series2);
		    dataset.addSeries(series3);
		   			
		    return dataset;
		}


}
