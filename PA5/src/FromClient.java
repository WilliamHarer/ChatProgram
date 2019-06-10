import java.net.Socket;
import java.util.Scanner;
//CSE223 WilliamHarer/DavidKriss for Professor N.Macias
//Receive output, reads the socket output without decrypting used by the server
//for a full description read ClientDecryptor, as they are nearly identical.
public class FromClient extends Thread {
	Scanner nastyStuff;
	Socket s;
	public void run() {
		while (nastyStuff.hasNextLine()) {
		System.out.println(nastyStuff.nextLine());
	
		}
		try{
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
