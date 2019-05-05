package application.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// This class sets up the text output area where the user can see the response messages
public class TextPanelView extends JPanel{
	private static final long serialVersionUID = 13467823463542368L;
	JTextArea textArea;
	TextPanelView(){
		setLayout(new BorderLayout());
		textArea = new JTextArea();	
		textArea.setEditable(false);
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
}
