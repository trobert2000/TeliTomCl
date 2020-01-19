package de.imbium.telitom;

import java.io.*;
import java.net.*;
import java.util.*;

public class TeliTomClient extends Thread{
	
	private String nickName = "Katy";
	private String passwd = "Perry";
	private String infoIpAddr = "";
	private int infoPort = 0;
	private boolean RunFlag = true;
	private Socket infoSocket;
	private Socket dataSocket;
	private int currentState = 0;
	private int nextState = 0;
	private ArrayList<String> userList = new ArrayList<String>();
		
	
	public TeliTomClient(String nick, String ipNum , String portstr) {
		nickName = nick;
		infoIpAddr = ipNum;
		infoPort = intFromStr(portstr);		
	}	
	
	public void run() {	
		String line = "";
		String sendStr = "";
		try {
			Socket s = new Socket("127.0.0.1",7001);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter out = new PrintWriter(new OutputStreamWriter(s.getOutputStream()),true);
						
			out.println("HALLO");
			sleep(100);	
			
			int cnt = 0;
			while((line = in.readLine()) != null && cnt < 3) {
				System.out.println(cnt+"cl recv "+line);
				
				StringTokenizer st = new StringTokenizer(line,",",false);
				
				switch(st.nextToken()) {
				case "GOODDAY":
					sendStr = "NICK:"+nickName+",PASSWD:"+passwd;
					break;
				case "ACCESSGRANTED":
					sendStr = "REQUSERLIST";
					break;
				case "USERLIST":
					System.out.println(st.nextToken());
					pushUserList(st.nextToken());
					sendStr = "OK";
					break;
					
				}
				
				out.println(sendStr);
				sleep(100);	
				cnt++;
			}
			
			out.println("ENDE");
			sleep(100);	
			
			in.close();
			out.close();			
			s.close();
			
		} catch(InterruptedException e) {
			e.printStackTrace();
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}	
	}
	
	public void openInfoChannel() {
		try {
			infoSocket = new Socket(infoIpAddr,infoPort);
			nextState = 1;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setNickName (String name) {
		nickName = name;
	}
	
	public void setInfoIP(String ip) {
		infoIpAddr = ip;
	}
	
	public void setInfoPort(String p) {
		infoPort = intFromStr(p);
	}
	
	public int getPort(String str) {
		return intFromStr(str);
	}
	
	public void setRunFlag(boolean b) {
		RunFlag = b;
	}
	
	private int intFromStr(String str) {
		System.out.println("str " +str );
		int retVal = -1;		
		try {
			retVal = Integer.parseInt(str);			
		} catch(NumberFormatException ex) {
			
		}
		return retVal;
	}
	
	private void pushUserList(String userStr) {
		StringTokenizer st = new StringTokenizer(userStr,",",false);
		for(int i=0;i< st.countTokens();i++) {
			String ntok = st.nextToken();
			userList.add(ntok);
			System.out.println(ntok);
		}
	}
	
	public static void main(String args[]) {
		TeliTomClient c = new TeliTomClient("Katy","127.0.0.1","7001");
		c.start();
	}
}
