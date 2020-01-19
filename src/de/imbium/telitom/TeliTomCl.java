package de.imbium.telitom;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TeliTomCl extends JFrame {
	
	public final static int STARTED = 0;
	public final static int CONNECTED = 1;
	

	final static String strTitle = "TeliTom Client v0.1";
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = false;
	
	private static JTextField tf1,tf2,tf3;
	private static JTextArea ta1;
	

	public TeliTomCl() {

	}
	
	
	/*
	 * connects to the Server 
	 */
	private static void startCommunication() {
		// gather User Input
		String ip_addr = tf3.getText();
		String portstr = tf2.getText();
		String name = tf1.getText();		
		
		// tries to connect to the Server and do the ping pong
		TeliTomClient tcl = new TeliTomClient(name,ip_addr,portstr);
		tcl.start();
		
	}
	
	
	public void addTextToTA(String str) {
		ta1.append(str);
	}
	
	
	
	/*
	 *  GUI Initialization
	 */
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

		// Row 0
		int row_cnt = 0;
		JLabel Label1 = new JLabel("Nickname:");
		if (shouldWeightX) {
			c.weightx = 0.5;
		}
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = row_cnt;
		pane.add(Label1, c);

		tf1 = new JTextField("Nick1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridwidth = 2;   // columns wide
		c.gridy = row_cnt;
		pane.add(tf1, c);

		// Row 1
		row_cnt++; 
		JLabel Label2 = new JLabel("Port:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = row_cnt;
		pane.add(Label2, c);
		
		tf2 = new JTextField("7001");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridwidth = 2;   // columns wide
		c.gridy = row_cnt;
		pane.add(tf2, c);
		
		// Row 2
		row_cnt++;
		JLabel Label3 = new JLabel("IP Number v4:");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = row_cnt;
		pane.add(Label3, c);
		
		tf3 = new JTextField("127.0.0.1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridwidth = 2;   // columns wide
		c.gridy = row_cnt;
		pane.add(tf3, c);
		
		// Row 4
		row_cnt++;
		JTextArea ta1 = new JTextArea("TextArea");
		c.fill = GridBagConstraints.BOTH;		
		c.ipady = 40;      //make this component tall		
		c.weightx = 1.0;
		c.gridwidth = 3;
		c.gridheight = 1;
		c.weighty = 1.0;   //request any extra vertical space	
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 0;       		
		c.gridy = row_cnt;       //third row
		pane.add(ta1, c);
				
		// Last Row
		row_cnt++;
		JButton StartBtn = new JButton("Start");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 2;       //aligned with button 2
		c.gridwidth = 1;   //2 columns wide
		c.gridy = row_cnt;       //third row
		StartBtn.addActionListener(new ActionListener() {							
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("Start Pressed " + evt.toString());	
				startCommunication(); 
			}	
		});
		pane.add(StartBtn, c);
		
		JButton StopBtn = new JButton("Stop");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 0;       //reset to default
		c.weighty = 1.0;   //request any extra vertical space
		c.anchor = GridBagConstraints.PAGE_END; //bottom of space
		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridx = 1;       //aligned with button 2
		c.gridwidth = 1;   // columns wide
		c.gridy = 4;       //third row
		pane.add(StopBtn, c);
		
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
