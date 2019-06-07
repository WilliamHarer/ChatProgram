import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	public static void main(String args[]) {
		ToClient tc=new ToClient();
		FromClient fc=new FromClient();
			
		ServerSocket garbageChat;
		Socket s;
		PrintWriter toGarbage;
		Scanner fromSelf,fromGarbage;
		fromSelf=new Scanner(System.in);
		try{
			garbageChat= new ServerSocket(1221);
			s=garbageChat.accept();
			fromGarbage=new Scanner(s.getInputStream());
			toGarbage=new PrintWriter(s.getOutputStream());
		}
		catch(Exception e) {
			System.out.println("Your trash socket had issues"+e);
			return;
		}
		tc.setPW(toGarbage);
		fc.setScanner(fromGarbage);
		tc.start();
		fc.start();
	}
}
