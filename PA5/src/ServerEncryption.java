	import java.io.DataInputStream;
	import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
//CSE223 WilliamHarer/DavidKriss for Professor N.Macias
//ServerEncryptionEncrypts the server and sends off output to the client
public class ServerEncryption extends Thread {
		PrintWriter garbageBin; //initialize all variables
		boolean isConnected=true; //checks connection
		private static Cipher encryptor; //sets our encryption Cipher
		SecretKey password;
		public void run() {
		try {
			encryptor=Cipher.getInstance("DES"); //first off run our encryptor with DES algorithm
			encryptor.init(Cipher.ENCRYPT_MODE, password);//initialize our Cipher
		}
		catch(Exception e) {
			
		}
			Scanner fromGarbage=new Scanner(System.in); //checks input
			DataInputStream dis=new DataInputStream(System.in);//checks data stream
			while(isConnected) { //while we are connected
				try{
					if (dis.available()>0) { //if there is a data stream available
						garbageBin.println(encrypt(fromGarbage.nextLine())); //encrypt
						garbageBin.flush();//send to socket
					}
				
				}	
				catch(Exception e) {
					System.out.println("error"+e);//on exception close the socket
					fromGarbage.close();
					garbageBin.close();
					return;
				}
			}
			return;					
		} 
		public void setPassword(SecretKey serverPass) {
			password=serverPass;  
		}
		public void setFlag(boolean x) {
			isConnected=x;
		}
		public void setPW(PrintWriter pw) {
			garbageBin=pw;
		}
		public static String encrypt(String str) {
			byte[] forBase64; //create Base64 byte stream
			byte[] enc;
			try {
				forBase64=str.getBytes("UTF8"); //create the string using get bites in unicode UTF8
				enc = encryptor.doFinal(forBase64);//encrypt into bytes
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			String encryptedString= Base64.getEncoder().encodeToString(enc);//use out base64 methods to convert our base64bytestream into an encrypted string
			return encryptedString;
		}
}
