package GUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import logic.ManejoRegistros;

public class VentanaPrincipal extends JFrame {
	private Table tabla;
	private ManejoRegistros manejo;
	private MyMenu menu;
	private JLabel titulo;
	
	public VentanaPrincipal() {
		titulo = new JLabel("Estadisticas de población");
		tabla = new Table(new Object []{"Año", "Total","Hombres","Mujeres"});
		manejo = new ManejoRegistros();
		menu = new MyMenu();
		setSize(800,400);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		titulo.setFont(new Font("Times New Roman", Font.ITALIC, 48));
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setBorder(LineBorder.createGrayLineBorder());
		fullTable();
		add(titulo, BorderLayout.NORTH);
		add(tabla.getTable(), BorderLayout.CENTER);
		setJMenuBar(menu);
		setResizable(false);
		setVisible(true);
	}
	public void fullTable() {
		for(int i=0;i<manejo.getMen().size();i++) {
			double total = manejo.getMen().get(i).getCantidad()+manejo.getWomen().get(i).getCantidad();
			Object [] record = {manejo.getMen().get(i).getAnio(), total,
					manejo.getMen().get(i).getCantidad(), manejo.getWomen().get(i).getCantidad()};
			tabla.insertRow(record);
		}	
	}
}

class MyMenu extends JMenuBar implements ActionListener{
	private JMenu interpolacion;
	private JMenuItem man;
	private JMenuItem woman;
	private JMenuItem total;
	private JMenu acercade;
	private JMenuItem acercade_p;
	private JMenuItem contexto;
	private VentanaHombres ventanaHombres;
	private VentanaMujeres ventanaMujeres;
	private VentanaTotal ventanaTotal;
	
	public MyMenu() {
		ventanaTotal = new VentanaTotal();
		ventanaHombres = new VentanaHombres();
		ventanaMujeres = new VentanaMujeres();
		acercade = new JMenu("Informacion");
		acercade.setIcon(new ImageIcon("resources/icons/informacion.png"));
		acercade_p = new JMenuItem("Acerca de:");
		acercade_p.setIcon(new ImageIcon("resources/icons/acercade.png"));
		acercade_p.addActionListener(this);
		contexto = new JMenuItem("Contexto");
		contexto.setIcon(new ImageIcon("resources/icons/contexto.png"));
		contexto.addActionListener(this);
		acercade.add(acercade_p);
		acercade.add(contexto);
		
		interpolacion = new JMenu("Interpolacion");
		interpolacion.setIcon(new ImageIcon("resources/icons/grafica.png"));
		total = new JMenuItem("Total");
		total.setIcon(new ImageIcon("resources/icons/comprobado.png"));
		total.addActionListener(this);
		man = new JMenuItem("Hombres");
		man.setIcon(new ImageIcon("resources/icons/man.png"));
		woman = new JMenuItem("Mujeres");
		woman.setIcon(new ImageIcon("resources/icons/mujer.png"));
		man.setActionCommand("man");
		man.addActionListener(this);
		woman.setActionCommand("woman");
		woman.addActionListener(this);
		interpolacion.add(man);	
		interpolacion.add(woman);
		interpolacion.add(total);
		add(interpolacion);
		add(acercade);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getActionCommand() == "man") {
			ventanaHombres.setVisible(true);
		}
		if(arg0.getActionCommand() == "woman") {
			ventanaMujeres.setVisible(true);
		}
		
		if(arg0.getSource() == total) {
			ventanaTotal.setVisible(true);
		}
		
		if(arg0.getSource() == acercade_p) {
			JOptionPane.showMessageDialog(null, "Desarrollado por:\n    José Ángel Charris de la Rosa\n"
					+ "    Alejandro Castañeda.\nProyecto de Métodos Numéricos");
		}
		if(arg0.getSource() == contexto) {
			String mensaje = "En México el INEGI(*) lleva a cabo censos de población desde el año 1950, al principio se llevaban a cabo cada 10 años\n"
					+ "ya que no se contaba con la tecnología actual y por lo tanto era un proceso muy largo y costoso, pero a partir del año \n"
					+ "1990 se comenzó a realizar cada 5 años.\n";
			String mensaje2 = "\nUn problema que surge naturalmente es el saber que población hubo en algún año en el que no se realizo un \ncenso, aquí es donde intervienen los métodos numéricos.\n\n" + 
					"Para encontrar una aproximación de población en algún año se recurre a la interpolación polinomial, que consiste \nen "
					+ "hallar un polinomio que pase por todos los datos que tenemos y nos pueda dar una aproximación al año que\n necesitemos, siempre y cuando el año buscado este dentro del intervalo, en este caso desde 1950 a 2010.";
			JOptionPane.showMessageDialog(null, mensaje+mensaje2);
		}
	}
}


