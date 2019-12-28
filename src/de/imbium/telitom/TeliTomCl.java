package de.imbium.telitom;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TeliTomCl extends JFrame {

	final static String strTitle = "TeliTom Client v0.1";
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;

	public TeliTomCl() {

	}

	public static void addComponentsToPane(Container pane) {
		if (RIGHT_TO_LEFT) {
			pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		}
		
		pane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) {
			//natural height, maximum width
			c.fill = GridBagConstraints.HORIZONTAL;
		}

		JLabel Label1 = new JLabel("Label 1:");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(Label1, c);

		JTextField tf1 = new JTextField("TextField 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridwidth = 2;   // columns wide
		c.gridy = 0;
		pane.add(tf1, c);

		JLabel Label2 = new JLabel("Label 2:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(Label2, c);
		
		JTextField tf2 = new JTextField("TextField 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridwidth = 2;   // columns wide
		c.gridy = 1;
		pane.add(tf2, c);

		JButton StartBtn = new JButton("Start");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 2;       //aligned with button 2
		c.gridwidth = 1;   //2 columns wide
		c.gridy = 3;       //third row
		pane.add(StartBtn, c);
		
		JButton StopBtn = new JButton("Stop");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 1;   // columns wide
		c.gridy = 3;       //third row
		pane.add(StopBtn, c);
		
		JTextArea ta1 = new JTextArea("TextArea");
		c.fill = GridBagConstraints.BOTH;		
		c.ipady = 40;      //make this component tall		
		c.weightx = 1.0;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weighty = 1.0;   //request any extra vertical space	
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       		
		c.gridy = 2;       //third row
		pane.add(ta1, c);
		
	}


	private static void createAndShowGUI() {
		//Create and set up the window.
		JFrame frame = new JFrame(strTitle);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Set up the content pane.
		addComponentsToPane(frame.getContentPane());

		//Display the window.
		frame.pack();        
		frame.setLocationRelativeTo(null);
		frame.setSize(600,300);		
		frame.setVisible(true);
	}

	public static void main(String[] args) {		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

}
