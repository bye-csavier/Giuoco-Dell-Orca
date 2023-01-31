package logic;

public class Giocatore {
	private String nome;
	private int punteggio;
	//sprite
	private ImprevistiOld modificatore;
	private int turno;
	private Dado dado;
	private int []domandeFatte;
	private int casellaGiocatore;
	
	public Giocatore(String nome, int casellaIniziale) {
		this.nome = nome;
		this.punteggio = 0;
		this.modificatore = ImprevistiOld.NORMALE;
		this.turno = 0;
		this.domandeFatte = null;
		this.casellaGiocatore = casellaIniziale;
		
	}
	
	public Giocatore(String nome, int punteggio, ImprevistiOld modificatore, int turno, int[] domandeFatte, int casellaGiocatore) {
		super();
		this.nome = nome;
		this.punteggio = punteggio;
		this.modificatore = modificatore;
		this.turno = turno;
		this.domandeFatte = domandeFatte;
		this.casellaGiocatore = casellaGiocatore;
	}

	public int getPunteggio() {
		return punteggio;
	}
	
	public Dado getDado()
	{
		return this.dado;
	}
	
	public void setPunteggio(int punteggio) {
		this.punteggio = punteggio;
	}

	public void incrementaPunteggio(int incremento) {
		this.punteggio += incremento;
	}

	public ImprevistiOld getModificatore() {
		return modificatore;
	}

	public void setModificatore(ImprevistiOld modificatore) {
		this.modificatore = modificatore;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCasellaGiocatore() {
		return casellaGiocatore;
	}

	public void setCasellaGiocatore(int casellaGiocatore) {
		this.casellaGiocatore = casellaGiocatore;
	}
	
	public boolean controllaDomanda(int indice) {
		for(int i : domandeFatte) {
			if(i == indice) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
}
