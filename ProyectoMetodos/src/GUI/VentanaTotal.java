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

public class VentanaTotal extends JFrame{
	private ManejoRegistros manejo;
	private Panel3 panelButtons;
	private Table tabla;
	
	public VentanaTotal() {
		manejo = new ManejoRegistros();
		panelButtons = new Panel3();
		tabla = new Table(new Object []{"Año", "Cantidad"});
		setTitle("Ventana Total");
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
			Object [] record = {manejo.getWomen().get(i).getAnio(),manejo.getWomen().get(i).getCantidad() +
					manejo.getMen().get(i).getCantidad()};
			tabla.insertRow(record);
		}
	}
}

class Panel3 extends JPanel implements ActionListener{
	private JButton generarPolinomio;
	private JButton generarGrafica;
	private JButton calcularAño;
	private JLabel imagen;
	private ManejoRegistros manejo;
	
	public Panel3() {
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
		imagen.setIcon(new ImageIcon("resources/image/polinomio3.png"));
	}
	
	public void generateGraph() {
		JFrame frame = new JFrame();
		frame.setSize(600, 700);
		XYSeries chart = new XYSeries("Grafica");
		for(int i=0;i<manejo.getWomen().size();i++) {
			double x = manejo.getWomen().get(i).getAnio();
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
		double resultado = (6.655593035848695 * Math.pow(10, 20)) - (2.347370664642787*Math.pow(10, 18) * x)
		  +((3.54804878455668*Math.pow(10, 15))*(Math.pow(x, 2))) - ((2.97930369803964 * Math.pow(10, 12))*(Math.pow(x, 3)))
				+((1.501001116404703*Math.pow(10, 9))*(Math.pow(x, 4))) - ((453720.4031204449)*(Math.pow(x, 5)))
				+((76.19262045367965)*(Math.pow(x, 6))) - ((0.005483407811447812)*(Math.pow(x, 7)));
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