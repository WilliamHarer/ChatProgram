import java.net.Socket;
import java.util.Scanner;

public class FromClient extends Thread {
	Scanner nastyStuff;
	Socket s;
	public void run() {
		while (nastyStuff.hasNextLine()) {
		System.out.println(nastyStuff.nextLine());
	
		}
		try{
			System.out.println("Closing:");
			s.close();
		}
		catch(Exception e) {
			System.out.println("error closing socket"+e);
		}
		return;
	}
	public void setSocket(Socket x) {
		s=x;
	}
	public void setScanner(Scanner sc) {
		nastyStuff=sc;
	}
}
