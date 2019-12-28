package de.imbium.telitom;

public class TeliTomClient extends Thread{
	
	private String nickName = "";
	private String ipAddr = "";
	private int Port = 0;
	private boolean RunFlag = true;
	
	
	public TeliTomClient(String nick, String ipNum , String portstr) {
		nickName = nick;
		ipAddr = ipNum;
		Port = intFromStr(portstr);
	}	
	
	public void run() {
		int cnt = 0;
		while(RunFlag == true 
				&& cnt++ < 10) {			
			
			System.out.println("cnt "+ cnt);
			
			try {
			sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(nickName + " stopped");
	}
	
	public void setNickName (String name) {
		nickName = name;
	}
	
	public void setIP(String ip) {
		ipAddr = ip;
	}
	
	public void setPort(String p) {
		Port = intFromStr(p);
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
}
