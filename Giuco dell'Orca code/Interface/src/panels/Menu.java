package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu {
	
	// VAR ================================
	
			// Panel Vars ------------------------ 
		
		MainPanel panel;
		
			//Game Objects ------------------------
		
		// CONSTRUCTORS ================================
		
		public Menu(MainPanel panelGivn)
		{
			this.panel =  panelGivn;
		}
		
		// FUNCTIONS ================================
		
			// Funzioni di loop GUI ------------------------
		
		public void update()
		{
			
		}
		
		public void draw(Graphics2D g2)
		{
			g2.setFont(panel.minecraft);
			g2.setFont(panel.minecraft.deriveFont(Font.BOLD, (panel.getVW() * 8) ));
			g2.setColor(Color.white);
			String titleStr = "Gioco Dell'Orca";
			
			g2.drawString(titleStr, (panel.getVW()*29) , (panel.getVH()*20) );
		}
			
	
}
