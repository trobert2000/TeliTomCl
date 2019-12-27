package de.imbium.telitom;

import javax.swing.JFrame;

public class TeliTomCl extends JFrame {
	
	public final String strTitle = "TeliTom Client v0.1";
 	
	public TeliTomCl() {
		System.out.println(strTitle);
		
		setTitle(strTitle);
		setSize(600,600);
		setVisible(true);
	}
	
	

	public static void main(String[] args) {		
		TeliTomCl tx = new TeliTomCl();
		
	}

}
