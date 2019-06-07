import java.util.Scanner;

public class FromClient extends Thread {
	Scanner nastyStuff;
	public void run() {
		while (nastyStuff.hasNextLine()) {
		System.out.println(nastyStuff.nextLine());
	
		}
	}
	public void setScanner(Scanner sc) {
		nastyStuff=sc;
	}
}
