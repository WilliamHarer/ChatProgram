import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {
	public static void main(String args[]) {
		ToClient tc=new ToClient();
		FromClient fc=new FromClient();
		InetAddress localH;
		Socket s;
		PrintWriter toGarbage;
		Scanner fromSelf,fromGarbage;
		fromSelf=new Scanner(System.in);
		
		try{
			localH=InetAddress.getLocalHost();
			s=new Socket(localH,1221);
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
		/*if(!fromSelf.hasNextLine()) {
			try{ s.close();
			
			}
			catch(Exception e) {
				System.out.println("YARRRRRR"+e);
			}
		}*/
	}
}
