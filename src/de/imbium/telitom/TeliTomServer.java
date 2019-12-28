package de.imbium.telitom;

import java.io.*;
import java.net.*;

public class TeliTomServer extends Thread {
	
	final int PORT = 7001;
	private final Socket[] socks = new Socket[2];
	
	public TeliTomServer() {
		
	}
	
	public void run() {
		try {
			ServerSocket servsock = new ServerSocket(PORT);
			System.out.println("listening on " + PORT);
			
			int cnt = 0;
			while(cnt < 2) {
			
				Socket sock =  servsock.accept();
			
				socks[cnt] = sock;				
				cnt++;
				
				System.out.println("accepted " + sock.toString());
			}
			
			
			
			for(int i=0;i<cnt;i++) {
				socks[i].close();
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TeliTomServer x = new TeliTomServer();
		x.start();
	}

}
