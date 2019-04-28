package application.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagLayoutUtils {

	public static GridBagConstraints constraint(int x, int y, int padx, int pady, int top, int left, int bottom, int right) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.ipadx = padx;
		c.ipady = pady;	
		c.insets = new Insets(top, left, bottom, right);
		return c;
	}
	public static GridBagConstraints constraint(int x, int y, int padx, int pady, int inset) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.ipadx = padx;
		c.ipady = pady;	
		c.insets = new Insets(inset, inset, inset, inset);
		return c;
	}
}
