package de.imbium.telitom;

import java.net.*;
import java.io.*;


// Test server for mirroring the speech signal

public class TeliTomDataMirrorServer extends Thread {
	
	private Socket s;
	private boolean runFlag = true;
	private int PORT = 0;
	
	public TeliTomDataMirrorServer(int p) {	
	}
	
	public void run() {
		System.out.println("TeliTomDataMirrorServer started...");
		
		try {
			int cnt=0;
			
			ServerSocket serv = new ServerSocket(PORT);
			s = serv.accept();
			
			System.out.println("accepted ");
			
			DataInputStream in = new DataInputStream(s.getInputStream()) ;
			DataOutputStream out = new DataOutputStream(s.getOutputStream()); 
			byte[] buf = new byte[1400];
			while(runFlag && cnt++ < 10 && (in.read(buf) > -1)) {
				System.out.println(cnt+ " steps ");
				sleep(100);
				out.write(buf);
				sleep(100);
			}
			
			in.close();
			out.close();
			s.close();
		} catch(InterruptedException | IOException e1) {
			e1.printStackTrace();
		}
	}

}
