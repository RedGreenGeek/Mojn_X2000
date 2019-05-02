package application.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// This class is the text out put area all functions has to communicate with the user.
@SuppressWarnings("serial")
public class TextPanelView extends JPanel{
	JTextArea textArea;
	TextPanelView(){
		setLayout(new BorderLayout());
		textArea = new JTextArea();	
		textArea.setEditable(false);
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}
