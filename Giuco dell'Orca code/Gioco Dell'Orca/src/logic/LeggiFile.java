package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LeggiFile {
	public static void main(String[] args) throws IOException	{
		
		File file = new File("percorso del file");
		Scanner scan = new Scanner(file);
		
		String coseDalFile = "";
		while(scan.hasNextLine()) {
			System.out.println(scan.nextLine());
			
			coseDalFile = coseDalFile.concat(scan.nextLine() + "\n");
		}
		
		FileWriter scrittore = new FileWriter("percorso del file");
		scrittore.write(coseDalFile);
		scrittore.close();
		scan.close();
	}
}
