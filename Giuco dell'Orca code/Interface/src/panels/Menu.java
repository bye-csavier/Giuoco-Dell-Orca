package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import extra.Support;
import panels.MainPanel.PanelStateFlag;
import uiElements.*;

@SuppressWarnings("unused")

public class Menu {
	
	// VAR ================================
	
			// Panel Vars ------------------------ 
		
		MainPanel panel;
		
			//UI Elements ------------------------
		
		ButtonA butPlayGame, butGameSettings, butQuit;
		
		// CONSTRUCTORS ================================
		
		public Menu(MainPanel panelGivn)
		{
			this.panel =  panelGivn;
			
			butPlayGame = new ButtonA("Gioca", 0, 0, panel.standard, panel.whiteA, "vw", 5, panel, panel.mainColorA, 0.1, PanelStateFlag.names);
			butGameSettings = new ButtonA("Impostazioni", 0, 0, panel.standard, panel.whiteA, "vw",5, panel, panel.mainColorA, 0.1, PanelStateFlag.menuGameSettings);
			butQuit =  new ButtonA("Esci", 0, 0, panel.standard, panel.whiteA, "vw", 5, panel, panel.mainColorA, 0.1, PanelStateFlag.quit);
			
			butPlayGame.setAfterClickedLink(PanelStateFlag.gamePlay);
		}
		
		// FUNCTIONS ================================
		
		public void disableListeners() 
		{
			butPlayGame.disableListeners();
			butGameSettings.disableListeners();
			butQuit.disableListeners();
		}
		
		public void enableListeners() 
		{
			butGameSettings.enableListeners();
			butPlayGame.enableListeners();
			butQuit.enableListeners();
		}
			// Funzioni di loop GUI ------------------------
		
		public void update()
		{
			
		}
			
		public void draw(Graphics2D g2)
		{
			// TITLE
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(8)) ));
			g2.setColor(Color.white);
			
			String text = "Gioco Dell'Orca";
			int printX = (panel.getWidth() /2) - getTextCenterX(text, g2);
			int printY = panel.getVH(17) - getTextCenterY(text, g2);
			
			g2.drawString(text, printX, printY);
			
			// OPTIONS
				
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(5)) ));
			int spacing = panel.getVW(5);
			printY += panel.getVH(15);
			
				//Start game
			
			printX = panel.getWidth() /2;
			printY += spacing;
			
			butPlayGame.setDrawPos(printX, printY);
			butPlayGame.draw(g2);	
			
				//Game settings
			
			if(panel.gameStarted == false)
			{
				printY += spacing;
				
				butGameSettings.setDrawPos(printX, printY);
				butGameSettings.draw(g2);
			}
		
				//Quit
			
			printY += spacing;
			
			butQuit.setDrawPos(printX, printY);
			butQuit.draw(g2);
			
		}
		
		public int getTextCenterX(String text, Graphics2D g2)
		{
			
			int centerX = (int) g2.getFontMetrics().getStringBounds(text,g2).getCenterX();
			
			return centerX;
		}
		
		public int getTextCenterY(String text, Graphics2D g2)
		{
			int centerY = (int) g2.getFontMetrics().getStringBounds(text,g2).getCenterY();
			
			return centerY;
		}
			
	
}
