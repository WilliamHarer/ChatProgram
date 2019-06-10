import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
//CSE223 WilliamHarer/DavidKriss for Professor N.Macias
//ClientMain: initializes our clients thread and connects to our encrypted server
public class ClientMain {
	public static void main(String args[]) { 
		byte[] inputPassword=Base64.getDecoder().decode(args[0]); //using our command line argument input the password given by the server in base64 bytes
		SecretKey password = new SecretKeySpec(inputPassword, 0, inputPassword.length, "DES"); //this is a SecretKey constructor that accepts the arguments, byte[] or our password, an offset(0), a length, and the encryption algorithm
		ToClient tc=new ToClient(); //create a new to client
		ClientDecryptor fc=new ClientDecryptor(); //create a new clientDecryptor
		InetAddress localH; //I think this is where the band got the name from
		Socket s; //init our PrintWriter
		PrintWriter toGarbage;
		Scanner fromSelf,fromGarbage;
		fromSelf=new Scanner(System.in);
		
		try{
			localH=InetAddress.getLocalHost(); //set our socket info.
			s=new Socket(localH,1221);
			fromGarbage=new Scanner(s.getInputStream()); //set  our input/outputstream scanners/printwriters
			toGarbage=new PrintWriter(s.getOutputStream());
		}
		catch(Exception e) {
			System.out.println("You just don't get it"+e); //haha get it? get it??
			return;
		}
		fc.setSocket(s); //pass our socket printwriter scanner and password
		tc.setPW(toGarbage);
		fc.setScanner(fromGarbage);
		fc.setPassword(password);
		tc.start();
		fc.start();
		
	}
}
