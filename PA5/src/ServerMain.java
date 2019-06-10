import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class ServerMain {
	//CSE223 WilliamHarer/DavidKriss for Professor N.Macias
	//ServerMain, Creates sockets as needed to  interact with clients
	public static void main(String args[]) {
		ServerSocket garbageChat; //initialize our variables
		Socket s;
		SecretKey password;
		try {
			password = KeyGenerator.getInstance("DES").generateKey(); //generate a DES algorithm key
			System.out.println(Base64.getEncoder().encodeToString(password.getEncoded()));//spit out the key to display on the server
			garbageChat= new ServerSocket(1221);//sets the ServerSocket to 1221
			s=garbageChat.accept();//and sets the socket to whatever the ServerSocket accepts
		}
		catch(Exception e) {
			System.out.println("server creation error"+e);
			return;
		}
		ServerEncryption tc=new ServerEncryption(); //create a new ServerEncryption class
		FromClient fc=new FromClient(); //creates a class to recieve input from client
		
			
		PrintWriter toGarbage; //sets printwriters
		Scanner fromSelf,fromGarbage;
		fromSelf=new Scanner(System.in);
		try{
			fromGarbage=new Scanner(s.getInputStream()); //set them to socket input stream/outputstream
			toGarbage=new PrintWriter(s.getOutputStream());
		}
		catch(Exception e) {
			System.out.println("Your trash socket had issues"+e);
			fromSelf.close(); 
			return;
		}
		tc.setPW(toGarbage); //set printwriter,scanner,socket,password and run
		tc.setPassword(password);
		fc.setScanner(fromGarbage);
		fc.setSocket(s);
		tc.start();
		fc.start();
		while(true) { //after the first initialization keep searching for more
			
			if(s.isClosed()) { //if our socket is closed, reset our flags, and then reset all of our PW,scanners,passwords, and sockets after recreating out threads.
				tc.setFlag(false);
				tc.setPassword(password);
				tc=new ServerEncryption();
				fc=new FromClient();
				fromSelf=new Scanner(System.in);
				try {
					s=garbageChat.accept();
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
				tc.setPassword(password);
				tc.start();
				fc.start();
			}
		}
	}
}

