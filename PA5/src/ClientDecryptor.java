

import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
//CSE223 WilliamHarer/DavidKriss for Professor N.Macias
//ClientDecryptor; reads the socket output and decrypts, used by the client. If you do not give a correct password
public class ClientDecryptor extends Thread {
	Scanner nastyStuff; //create our scanner
	Socket clientSocket;
	private boolean wrongPassword=false; //assume we have the correct password
	private static Cipher decryptor; //set our cipher that will be used to decrypt
	private SecretKey password; //set our password variable
	public void run() {
		try {
			decryptor=Cipher.getInstance("DES"); //create a DES algorithm Cipher
			decryptor.init(Cipher.DECRYPT_MODE, password); //initialize it in decrypt mode, and pass it out password
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeyException e) {
			wrongPassword=true; //if we give an incorrect password or get an exception we then set this flag to true
		}
		if(!wrongPassword) { //if we don't have an invalid key exception decryption using a wrong password still spits out garbage
			while (nastyStuff.hasNextLine()) {//regular scanner stuff, read from socket and print out using system.out.println
				System.out.println(decrypt(nastyStuff.nextLine()));
	
			}
		}
		else {//same as above but no decryption only garbled nonsense
			while(nastyStuff.hasNextLine()) {
				System.out.println(nastyStuff.nextLine());
			}
		}
		try{
			clientSocket.close();//close our socket on client side
		}
		catch(Exception e) {
			System.out.println("error closing socket"+e);
		}
		return;
	}
	public static String decrypt(String str) {
		byte[] dec=Base64.getDecoder().decode(str.getBytes()); //turn the string into bites, and use Base64 to decode it
		byte[] toPrint;// the bytestream we will turn into the output string
		try {
			toPrint = decryptor.doFinal(dec);//set the toPrint bytestream to the decoded message by running it through our Decryptor Cipher
			return new String(toPrint, "UTF8"); //return the decrytped string using the byteStream toPrint which is in base64 and the Unicode classifier UTF8
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			return str;
		}
		 catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public void setPassword(SecretKey clientPassword) {
		password=clientPassword;
	}
	public void setSocket(Socket x) {
		clientSocket=x;
	}
	public void setScanner(Scanner sc) {
		nastyStuff=sc;
	}
}

