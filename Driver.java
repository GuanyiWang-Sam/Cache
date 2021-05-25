package cache;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		WriteBack WB = new WriteBack();
		WB.initialCache();
		WB.initialMainMemory();
	    Boolean determine = true;
	    
		while(determine == true) {
			System.out.println("(R)ead, (W)rite, or (D)isplay Cache?(press E to exit)");
			String choice = scanner.next();
			if(choice.equals("R")) {
				System.out.println("What address would you like read?");
				String addressR = scanner.next();			
				WB.read(Integer.parseInt(addressR, 16));
			}else if(choice.equals("W")) {
				System.out.println("What address would you like read?");
				String addressW = scanner.next();
				System.out.println("What data would you like to write at that address?");
				String value = scanner.next();
				WB.write(Integer.parseInt(addressW, 16),Integer.parseInt(value, 16));
			}else if(choice.equals("D")) {
				WB.Display();
			}else if(choice.equals("E")) {
				determine = false;
			}
		}
		scanner.close();
   }
}
