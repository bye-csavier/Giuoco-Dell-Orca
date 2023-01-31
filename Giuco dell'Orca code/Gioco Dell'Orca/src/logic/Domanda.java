package logic;

import java.util.Arrays;

public class Domanda {
	private String domanda;
	private String []risposte;
	private int indiceCorretta;
	private Difficolta difficolta;
	
	public enum Difficolta	{
		FACILE,
		MEDIO,
		DIFFICILE
	}
	
	public Domanda() {
		this.risposte = new String[4];
	}

	public Domanda(String domanda, String[] risposte, int indiceCorretta/*, Difficolta difficolta*/) {
		this.domanda = domanda;
		this.risposte = risposte;
		this.indiceCorretta = indiceCorretta;
//		this.difficolta = difficolta;
	}

	public String getDomanda() {
		return domanda;
	}

	public void setDomanda(String domanda) {
		this.domanda = domanda;
	}

	public String[] getRisposte() {
		return risposte;
	}

	public void setRisposte(String[] risposte) {
		this.risposte = risposte;
	}
	
	public void aggiungiRisposta(String risposta, int indice) {
		this.risposte[indice] = risposta;
	}

	public int getIndiceCorretta() {
		return indiceCorretta;
	}

	public void setIndiceCorretta(int indiceCorretta) {
		this.indiceCorretta = indiceCorretta;
	}

	public Difficolta getDifficolta() {
		return difficolta;
	}

	public void setDifficolta(Difficolta difficolta) {
		this.difficolta = difficolta;
	}

	public String toString() {
		return  domanda + Arrays.toString(risposte);
	}
	
	
	
}
