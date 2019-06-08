import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
	public static void main(String args[]) {
		ServerSocket garbageChat;
		Socket s;
		try {
			garbageChat= new ServerSocket(1221);
			s=garbageChat.accept();
		}
		catch(Exception e) {
			System.out.println("server creation error"+e);
			return;
		}
		
		ToClient tc=new ToClient();
		FromClient fc=new FromClient();
		
			
		PrintWriter toGarbage;
		Scanner fromSelf,fromGarbage;
		fromSelf=new Scanner(System.in);
		try{
			fromGarbage=new Scanner(s.getInputStream());
			toGarbage=new PrintWriter(s.getOutputStream());
		}
		catch(Exception e) {
			System.out.println("Your trash socket had issues"+e);
			fromSelf.close();
			return;
		}
		tc.setPW(toGarbage);
		fc.setScanner(fromGarbage);
		fc.setSocket(s);
		tc.start();
		fc.start();
		
	}
}

