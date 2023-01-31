package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import extra.Support;

public class Domandiere {
	
	public Domanda []poolDomande;
	public int numeroDomande;
	public Scanner scan;
	private String tempStr = "";
	
	public Domandiere() throws FileNotFoundException {
//		super();
		this.scan = new Scanner(new File("./res/other/domande.txt"));
		this.poolDomande = new Domanda[0];
		this.numeroDomande = 0;
		this.riempiDomandiere();
	}
	
	public void riempiDomandiere()
	{
		for(int i=0; scan.hasNext() == true; i++)
		{
			//System.out.println(" i = " + i + " | length : " + poolDomande.length);
			
			this.addSpace(1);
			
			poolDomande[i] = new Domanda();
			
			do
			{
				tempStr = scan.nextLine();
				poolDomande[i].setDomanda(tempStr);
				//System.out.println(tempStr + " .");
			
			}while( tempStr.equals("===") || tempStr.equals(" ") || tempStr.length() == 0);
			
			for(int k = 0; k<4; k++ ) {
				poolDomande[i].aggiungiRisposta(scan.nextLine(), k);
				//System.out.println(poolDomande[i].getRisposte()[k] + " ..");
			}
			
			poolDomande[i].setIndiceCorretta(scan.nextInt());
			//System.out.println(poolDomande[i].getIndiceCorretta() + "...");
			
		}
		
		this.numeroDomande = poolDomande.length;
	}
	
	private void addSpace(int space)
	{
		Domanda[] temp = new Domanda[poolDomande.length+space];
		
		for(int i=0; i<poolDomande.length; i++)
		{
			temp[i] = poolDomande[i];
		}
		
		this.poolDomande = temp;
	}
	
	public void aggiungiDomandaFile() {
		poolDomande[this.numeroDomande] = new Domanda();
		
		//poolDomande[this.numeroDomande].setDomanda(scan.nextLine());
		System.out.println(scan.nextLine());
		
		for(int i = 0; i<4; i++ ) {
			//poolDomande[this.numeroDomande].aggiungiRisposta(scan.nextLine(), i);
			System.out.println(scan.nextLine());
		}
		
		//scan.skip("ANSWER: ");
		poolDomande[this.numeroDomande].setIndiceCorretta(scan.nextInt());
		System.out.println(poolDomande[this.numeroDomande].getIndiceCorretta());
		
		do
		{
			tempStr = scan.nextLine();
		}
		while(tempStr == "");
		
		this.numeroDomande++;
	}
	
	public Domanda getDomanda(int indice) {
		return poolDomande[indice];
	}
	
	public Domanda random()
	{
		return this.getDomanda(Support.randInt(0, poolDomande.length-1));
	}
	
	
}
