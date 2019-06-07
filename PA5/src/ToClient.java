import java.io.DataInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ToClient extends Thread {
	PrintWriter garbageBin;
	public void run() {
		Scanner fromGarbage=new Scanner(System.in);
		DataInputStream dis=new DataInputStream(System.in);
		while(true) {
			try{
				if (dis.available()>0) {
				
				//Byte b=dis.readByte();
				garbageBin.println(fromGarbage.nextLine());
				garbageBin.flush();
				//System.out.print(b);
				
				}
			}	
			catch(Exception e) {
				System.out.println("error"+e);
				return;
			}
			//garbageBin.println(fromGarbage.nextLine());
			//garbageBin.flush();
		
		} 
	}
	public void setPW(PrintWriter pw) {
		garbageBin=pw;
	}
}
