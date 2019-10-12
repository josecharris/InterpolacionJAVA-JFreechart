package GUI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

class Table {
	private JTable table;
	private DefaultTableModel dtm;
	public Table(Object [] titulos) {
		dtm = new DefaultTableModel();
		//Object [] titulos = {"Año", "Total","Hombres","Mujeres"};
		dtm.setColumnIdentifiers(titulos);
		table = new JTable(dtm);
	}
	
	public void insertRow(Object[] rowData) {
		dtm.addRow(rowData);
	}
	public JScrollPane getTable() {
		return new JScrollPane(table);
	}
}