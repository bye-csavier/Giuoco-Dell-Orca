package logic;

@SuppressWarnings("unused")

public class Tabellone {
	private Casella primaCasella;
	private Casella ultimaCasella;
	
	public Tabellone(int grandezzaTabellone, int numeroGiocatori) {
		
		Casella casella = new Casella(null, null, ImprevistiOld.NORMALE, grandezzaTabellone); //sarà la casella finale
		ultimaCasella = casella;
		for(int i=grandezzaTabellone-1; i>0; i--)	{
			
			casella = new Casella(null, casella, randImprevisto(), i);
		
		}
		
		primaCasella = new Casella(casella, numeroGiocatori, 1); //casella iniziale
	}

	private ImprevistiOld randImprevisto()
	{
		int random = (int) ((Math.random() * ((100+1)-1)) + 1);
		
		int probabilita = 25 / 2;
		
		if(random >= (50-probabilita) && random <= (50+probabilita))
		{
			random = (int) ((Math.random() * ((4+1)-1)) + 1);
			
			switch(random)
			{
				case 1:{
					return ImprevistiOld.DUEAVANZI;
				}
				
				case 2:{
					return ImprevistiOld.DUEINDIETRO;
				}
				
				case 3:{
					return ImprevistiOld.PUNTIDOPPI;
				}
				
				case 4:{
					return ImprevistiOld.PUNTIMEZZI;
				}
				
				default:{
					return ImprevistiOld.NORMALE;
				}
				
			}
		}
		else
		{
			return ImprevistiOld.NORMALE;
		}
		
	}
	
	
	public Giocatore lotta(Giocatore giocatore1, Giocatore giocatore2)	{
		//fare con dado
		int differenza = giocatore1.getDado().lanciaDado() - giocatore2.getDado().lanciaDado();
		
		if(differenza>0) {	//vince giocatore1
			return giocatore1;
		} else if(differenza<0) {	//vince giocatore2
			return giocatore2;
		} else {	//pareggio
			return null;
		}
		
	}
	
	public Casella getCasella(int casella)	{
		return cercaCasella(casella, primaCasella);
	}
	
	private Casella cercaCasella(int numeroCasella, Casella c)	{
		if(numeroCasella == c.getNumCasella())	{
			return c;
		}	else	{
			return cercaCasella(numeroCasella, c.getSuccessiva());
		}
	}
	
	public void spostaGiocatore(Giocatore giocatore, int indiceCasella)	{
		getCasella(giocatore.getCasellaGiocatore()).togliGiocatori(giocatore.getNome());
		giocatore.setCasellaGiocatore(indiceCasella);
		getCasella(indiceCasella).addGiocatori(giocatore.getNome());
	}
	
	public void avanza(Giocatore giocatore, int nCaselle)	{
		for(int i=0; i<nCaselle; i++)	{
			avanzaUno(giocatore);
		}
	}
	
	public void avanzaUno(Giocatore giocatore)	{
		getCasella(giocatore.getCasellaGiocatore()).togliGiocatori(giocatore.getNome());
		giocatore.setCasellaGiocatore(getCasella(giocatore.getCasellaGiocatore()).getSuccessiva().getNumCasella());
		getCasella(giocatore.getCasellaGiocatore()).addGiocatori(giocatore.getNome());
	}
	
	public void indietroUno(Giocatore giocatore)	{
		getCasella(giocatore.getCasellaGiocatore()).togliGiocatori(giocatore.getNome());
		giocatore.setCasellaGiocatore(getCasella(giocatore.getCasellaGiocatore()).getPrecedente().getNumCasella());
		getCasella(giocatore.getCasellaGiocatore()).addGiocatori(giocatore.getNome());
	}
	
	
	public void scambiaCaselle(int indiceCasella1, int indiceCasella2)	{ // scambia solo il contenuto
		ImprevistiOld scambioTipo;
		String []scambioGiocatori;
		
		scambioTipo = getCasella(indiceCasella1).getTipo();
		getCasella(indiceCasella1).setTipo(getCasella(indiceCasella2).getTipo());
		getCasella(indiceCasella2).setTipo(scambioTipo);
		
		scambioGiocatori = getCasella(indiceCasella1).getGiocatori();
		getCasella(indiceCasella1).setGiocatori(getCasella(indiceCasella2).getGiocatori());
		getCasella(indiceCasella2).setGiocatori(scambioGiocatori);
		
		
		
		
//		casellaScambio.setPrecedente(casella1.getPrecedente());
//		casellaScambio.setSuccessiva(casella1.getSuccessiva());
//		casella1.setPrecedente(casella2.getPrecedente());
//		casella1.setSuccessiva(casella2.getSuccessiva());
//		casella1.getPrecedente().setSuccessiva(casella1);
//		casella1.getSuccessiva().setPrecedente(casella1);
//		casella2.setPrecedente(casellaScambio.getPrecedente());
//		casella2.setSuccessiva(casellaScambio.getSuccessiva());
//		casella2.getPrecedente().setSuccessiva(casella2);
//		casella2.getSuccessiva().setPrecedente(casella2);
	
	}
}
