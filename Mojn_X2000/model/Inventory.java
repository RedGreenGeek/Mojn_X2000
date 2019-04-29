package application.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class Inventory extends AbstractTableModel {

	private static final long serialVersionUID = -8100080945080186023L;
	private Map<String, Integer> products;
	
	public Inventory() {
		products = new HashMap<String, Integer>();
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
	

	
	
	// methods below to extend table model
	



