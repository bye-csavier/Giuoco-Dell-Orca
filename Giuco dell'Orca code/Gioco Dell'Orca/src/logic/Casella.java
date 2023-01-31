package logic;

public class Casella {
	
	private Casella precedente;
	private Casella successiva;
	private ImprevistiOld tipo;
	private int numCasella;
//	private int x;
//	private int y;
//	private int size;
	private String []giocatori;
	
	
	public Casella(Casella precedente, Casella successiva, ImprevistiOld tipo, int numCasella) {
		this.precedente = precedente;
		this.successiva = successiva;
		this.tipo = tipo;
		this.numCasella = numCasella;
//		this.x = x;
//		this.y = y;
//		this.size = size;
		this.giocatori = new String[2];
	}
	
	public Casella(Casella successiva, int numGiocatori, int numCasella) {
		this.precedente = null;
		this.successiva = successiva;
		this.tipo = ImprevistiOld.NORMALE;
		this.numCasella = numCasella;
//		this.x = x;
//		this.y = y;
//		this.size = size;
		this.giocatori = new String[numGiocatori];
	}

	public Casella getPrecedente() {
		return precedente;
	}
	public void setPrecedente(Casella precedente) {
		this.precedente = precedente;
	}
	public Casella getSuccessiva() {
		return successiva;
	}
	public void setSuccessiva(Casella successiva) {
		this.successiva = successiva;
		successiva.setPrecedente(this);
	}
	public ImprevistiOld getTipo() {
		return tipo;
	}
	public void setTipo(ImprevistiOld tipo) {
		this.tipo = tipo;
	}
	public int getNumCasella() {
		return numCasella;
	}
	public void setNumCasella(int numCasella) {
		this.numCasella = numCasella;
	}
	
//	public int getX() {
//		return x;
//	}
//	public void setX(int x) {
//		this.x = x;
//	}
//	public int getY() {
//		return y;
//	}
//	public void setY(int y) {
//		this.y = y;
//	}
//	public int getSize() {
//		return size;
//	}
//	public void setSize(int size) {
//		this.size = size;
//	}
	
	public String[] getGiocatori() {
		return giocatori;
	}
	
	public void setGiocatori(String[] giocatori) {
		this.giocatori = giocatori;
	}
	
	public void addGiocatori(String giocatore) {
		int i=0;
		while(giocatori[i]==null)	{
			i++;
		}
		giocatori[i] = giocatore;
	}
	
	public void togliGiocatori(String giocatore) {
		int i=0;
		while(giocatori[i].equals(giocatore))	{
			i++;
		}
		giocatori[i] = null;
		
		if(giocatori[0] == null) {
			giocatori[0] = giocatori[1];
			giocatori[1] = null;
		}
		
	}

	

	
	
	
	
	
	
	
}
