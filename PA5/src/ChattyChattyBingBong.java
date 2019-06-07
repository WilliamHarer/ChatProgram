import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChattyChattyBingBong {
	public static void main(String args[]) {
		ServerSocket garbageChat;
		Socket s;
		PrintWriter toGarbage;
		Scanner fromSelf,fromGarbage;
		fromSelf=new Scanner(System.in);
		try {
			garbageChat= new ServerSocket(1221);
			s=garbageChat.accept();
			fromGarbage=new Scanner(s.getInputStream());
			toGarbage=new PrintWriter(s.getOutputStream());
			while (fromSelf.hasNextLine()) {
				String temp=(fromSelf.nextLine()+"? uWu");
				toGarbage.println(temp);
				toGarbage.flush();
			 
				//System.out.println("Message received: <"+temp + ">");
				temp=fromGarbage.nextLine();
				System.out.println("Received message: <" + temp + ">");
			} 
		} 
		catch(Exception e) {
			System.out.println("trying to open server"+e);
		}
	}
}
