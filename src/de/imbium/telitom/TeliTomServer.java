package de.imbium.telitom;

import java.io.*;
import java.net.*;
import java.util.*;

public class TeliTomServer extends Thread {
	
	final int PORT = 7001;	
	
	private ArrayList<String> UserList = new ArrayList<String>(); 
	
	public TeliTomServer() {
		
	}
	
	public void run() {
		String line = "";
		String sendStr = "";
		
		try {
			
			ServerSocket x = new ServerSocket(PORT);
			System.out.println("listening...");
			Socket x2 = x.accept();
			System.out.println("accepted: "+x2.toString());
			
			BufferedReader in = new BufferedReader(new InputStreamReader(x2.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(x2.getOutputStream()),true);
			
			int cnt = 0;
			while((line = in.readLine()) != null && cnt < 30) {
				
				System.out.println("se recv "+line);
				
				StringTokenizer st = new StringTokenizer(line,":",false);
				
				sendStr = "NOT ENTERED SWITCH ON SERVER";
				String str1 = st.nextToken();
				System.out.println("serv str1 " + str1);
				
				switch(str1) {
				
				case "NICK":	
					addUserToList(st.nextToken());					
					sendStr = "ACCESSGRANTED";					
					break;
				case "REQUSERLIST":				
					sendStr = getUserListAsString();
					break;
					
				case "OK":
					sleep(500);
					break;
				
				
				case "HALLO":
					sendStr = "GOODDAY";
					break;				
				}
								
				
				out.println(sendStr);
				sleep(100);	
				cnt++;
			}
						
				
				
			
			in.close();
			out.close();
			x2.close();
			
			System.out.println("Server Ende");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
	
	private String getUserListAsString() {
		System.out.println("size user list server: "+ UserList.size());
		String retVal = "USERLIST:";
		for(int i = 0;i<UserList.size();i++) {
			String userStr = (String)UserList.get(i);
			retVal = retVal + userStr + ",";  
		}
		System.out.println(retVal);
		return retVal;
	}
	
	
	private void addUserToList(String userstr) {
		StringTokenizer st = new StringTokenizer (userstr,",",false);
		String uname = st.nextToken();
		
		UserList.add(uname);
	}
	
	private boolean accessGranted(String uname,String passwd) {
		return true;
	}

	
	
	public static void main(String[] args) {
		TeliTomServer x = new TeliTomServer();
		x.start();
		
	}

}
