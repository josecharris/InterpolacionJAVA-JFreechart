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

public class VentanaMujeres extends JFrame{
	private ManejoRegistros manejo;
	private Panel2 panelButtons;
	private Table tabla;
	
	public VentanaMujeres() {
		manejo = new ManejoRegistros();
		panelButtons = new Panel2();
		tabla = new Table(new Object []{"Año", "Cantidad"});
		setTitle("Ventana Mujeres");
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
			Object [] record = {manejo.getWomen().get(i).getAnio(),manejo.getWomen().get(i).getCantidad()};
			tabla.insertRow(record);
		}
	}
}

class Panel2 extends JPanel implements ActionListener{
	private JButton generarPolinomio;
	private JButton generarGrafica;
	private JButton calcularAño;
	private JLabel imagen;
	private ManejoRegistros manejo;
	
	public Panel2() {
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
		imagen.setIcon(new ImageIcon("resources/image/polinomio2.png"));
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
		double resultado = (1.437898470474812 * Math.pow(10, 20)) - (5.067569189902419*Math.pow(10, 17) * x)
		  +((7.653889139205605*Math.pow(10, 14))*(Math.pow(x, 2))) - ((6.422142531113195 * Math.pow(10, 11))*(Math.pow(x, 3)))
				+((3.233082274767506*Math.pow(10, 8))*(Math.pow(x, 4))) - ((97654.57952214906)*(Math.pow(x, 5)))
				+((16.38640765990861)*(Math.pow(x, 6))) - ((0.0011783814454064455)*(Math.pow(x, 7)));
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