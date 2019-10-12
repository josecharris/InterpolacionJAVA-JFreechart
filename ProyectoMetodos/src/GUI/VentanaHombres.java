package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import logic.ManejoRegistros;

public class VentanaHombres extends JFrame{
	private ManejoRegistros manejo;
	private PanelButtons panelButtons;
	private Table tabla;
	
	public VentanaHombres() {
		manejo = new ManejoRegistros();
		panelButtons = new PanelButtons();
		tabla = new Table(new Object []{"Año", "Cantidad"});
		setTitle("Ventana Hombres");
		setSize(700, 450);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		fullTable();
		add(panelButtons, BorderLayout.SOUTH);
		add(tabla.getTable(), BorderLayout.CENTER);
		setResizable(false);
	}
	
	public void fullTable() {
		for(int i=0;i<manejo.getMen().size();i++) {
			Object [] record = {manejo.getMen().get(i).getAnio(),manejo.getMen().get(i).getCantidad()};
			tabla.insertRow(record);
		}
	}
}

class PanelButtons extends JPanel implements ActionListener{
	private JButton generarPolinomio;
	private JButton generarGrafica;
	private JButton calcularAño;
	private JLabel imagen;
	private ManejoRegistros manejo;
	
	public PanelButtons() {
		manejo = new ManejoRegistros();
		generarPolinomio = new JButton("Generar polinomio");
		generarPolinomio.setIcon(new ImageIcon("resources/icons/ecuacion.png"));
		generarPolinomio.addActionListener(this);
		generarGrafica = new JButton("Generar grafica");
		generarGrafica.setIcon(new ImageIcon("resources/icons/grafica.png"));
		generarGrafica.addActionListener(this);
		calcularAño = new JButton("Calcular año");
		calcularAño.setIcon(new ImageIcon("resources/icons/anio.png"));
		calcularAño.addActionListener(this);
		add(generarPolinomio);
		add(generarGrafica);
		add(calcularAño);
		imagen = new JLabel();
		imagen.setIcon(new ImageIcon("resources/image/polinomio.png"));
	}
	
	public void generateGraph() {
		JFrame frame = new JFrame();
		frame.setSize(600, 700);
		XYSeries chart = new XYSeries("Grafica");
		for(int i=0;i<manejo.getMen().size();i++) {
			double x = manejo.getMen().get(i).getAnio();
			double y = funcion(x);
			chart.add(x,y);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(chart);
		JFreeChart freechart = ChartFactory.createXYLineChart("Grafica de la ecuación", "X", "Y", dataset,PlotOrientation.VERTICAL,
				true, false, false);
		ChartPanel panel = new ChartPanel(freechart);
		frame.add(panel);
		frame.setVisible(true);		
	}
	
	public double funcion(double x) {
		double resultado = (5.217694565373883 * Math.pow(10, 20)) - (1.840613745652545*Math.pow(10, 18) * x)
		  +((2.782659870636119*Math.pow(10, 15))*(Math.pow(x, 2))) - ((2.33708944492832 * Math.pow(10, 12))*(Math.pow(x, 3)))
				+((1.177692888927953*Math.pow(10, 9))*(Math.pow(x, 4))) - ((356065.82359829586)*(Math.pow(x, 5)))
				+((59.806212793771046)*(Math.pow(x, 6))) - ((0.004305026366041355)*(Math.pow(x, 7)));
		return resultado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == generarPolinomio) {
			JFrame frame = new JFrame();
			frame.setTitle("Polinomio");
			frame.setSize(581,150);
			frame.setLocationRelativeTo(null);
			frame.add(imagen);
			frame.setVisible(true);
		}
		if(e.getSource() == generarGrafica) {
			generateGraph();
		}
		if(e.getSource() == calcularAño) {
			try {
				double anio = Double.parseDouble(JOptionPane.showInputDialog(null,"Digite el año : "));
				if(anio>=1950 && anio<=2010) {
					JOptionPane.showMessageDialog(null, "La cantidad aproximada de hombres registrados en el año "+
							anio+" fue : "+funcion(anio));
				}else {
					JOptionPane.showMessageDialog(null, "Limite erroneo");
				}
			}catch(Exception e2) {
				JOptionPane.showMessageDialog(null, "Presione aceptar.");
			}	
		}
	}
}