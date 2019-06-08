import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ToClient extends Thread {
	PrintWriter garbageBin;
	boolean isConnected=true;
	public void run() {
		Scanner fromGarbage=new Scanner(System.in);
		DataInputStream dis=new DataInputStream(System.in);
		while(isConnected) {
			try{
				if (dis.available()>0) {
				
				//Byte b=dis.readByte();
				garbageBin.println(fromGarbage.nextLine());
				garbageBin.flush();
				
				
				
				}
			
			}	
			catch(Exception e) {
				System.out.println("error"+e);
				fromGarbage.close();
				garbageBin.close();
				return;
			}
		}
		//fromGarbage.close();
		//garbageBin.close();
		return;
			//garbageBin.println(fromGarbage.nextLine());
			//garbageBin.flush();
		
	} 
	
	public void setFlag(boolean x) {
		isConnected=x;
	}
	public void setPW(PrintWriter pw) {
		garbageBin=pw;
	}
}
