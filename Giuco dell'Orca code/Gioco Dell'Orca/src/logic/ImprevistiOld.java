package logic;

import extra.Support;

public enum ImprevistiOld {
	NORMALE,
	PUNTIDOPPI,
	PUNTIMEZZI,
	DUEAVANZI,
	DUEINDIETRO;
	
	public static ImprevistiOld random()
	{
		int rand = Support.randInt(0, 100);
		int range = 30;
		
		if(rand <= (50-(range/2)) && rand >= (50+(range/2)))
		{
			return ImprevistiOld.NORMALE;
		}
		else
		{
			return randomImp();
		}
		
	}
	
	public static ImprevistiOld randomImp()
	{
		int rand = Support.randInt(1, 4);
		
		switch(rand)
		{
			
			case 1:{
				return ImprevistiOld.PUNTIDOPPI;
			}
			
			case 2:{
				return ImprevistiOld.PUNTIMEZZI;
			}
			
			case 3:{
				return ImprevistiOld.DUEAVANZI;
			}
			
			case 4:{
				return ImprevistiOld.DUEINDIETRO;
			}
		}
		
		return null;
	}
}
