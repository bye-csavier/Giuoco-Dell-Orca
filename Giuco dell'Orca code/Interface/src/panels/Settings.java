package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import extra.Support;
import panels.MainPanel.PanelStateFlag;
import uiElements.*;

@SuppressWarnings("unused")

public class Settings {

	// VAR ================================
	
			// Panel Vars ------------------------ 
		
		MainPanel panel;
		
			//UI Elements ------------------------
		
		ButtonA butDone;
		SliderDot playerNum;
		SliderDot squareNum;
		
		int numGiocatori = 2;
		int numCaselle = 4;
			
	// CONSTRUCTORS ================================
	
	public Settings(MainPanel panelGivn)
	{
		this.panel =  panelGivn;
		
		butDone = new ButtonA("DONE", 0, 0, panel.standard, panel.whiteA, "vw", 5, panel, panel.mainColorA, 0.1, PanelStateFlag.menu);
		playerNum = new SliderDot(panelGivn);
		squareNum = new SliderDot(panelGivn);
	}
	
	// FUNCTIONS ================================
	
				// Funzioni di loop GUI ------------------------
			
			public void update()
			{

				double fixedRange = 1.5;
				numGiocatori = Support.map(playerNum.getX(), panel.getVW(10+fixedRange), panel.getVW(88-fixedRange), 2, 4);
				numCaselle = Support.map(squareNum.getX(), panel.getVW(10), panel.getVW(89-fixedRange), numGiocatori*2, 100);
			}
			
			public void draw(Graphics2D g2)
			{
				
				//Slider Giocatori --------------------
				
					//Impostazioni testo
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(6)) ));
				g2.setColor(panel.whiteA);
				String text = "Numero Giocatori: " + numGiocatori;
				int printX = (panel.getWidth() /2) - getTextCenterX(text, g2);
				int printY = panel.getVH(19) - getTextCenterY(text, g2);
				
				playerNum.setY(printY + panel.getVH(2.6)); //!
				
					//Draw
				g2.drawString(text, printX, printY);
				g2.setColor(panel.mainColorA);
				g2.drawRect(panel.getVW(10), printY + panel.getVH(7), panel.getVW(80), panel.getVH(1));
				playerNum.draw(g2);
				
				//Slider Caselle --------------------
				
					//Impostazioni testo
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(6)) ));
				g2.setColor(panel.whiteA);
				text = "Numero Caselle: " + numCaselle;
				printX = (panel.getWidth() /2) - getTextCenterX(text, g2);
				printY += panel.getVH(24) - getTextCenterY(text, g2);
				
				squareNum.setY(printY + panel.getVH(2.6)); //!
				
					//Draw
				g2.drawString(text, printX, printY);
				g2.setColor(panel.mainColorA);
				g2.drawRect(panel.getVW(10), printY + panel.getVH(7), panel.getVW(80), panel.getVH(1));
				squareNum.draw(g2);
				
				// Done Button
				
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(5)) ));
				printX = panel.getWidth() /2;
				printY = panel.getVH(85);
				
				butDone.setDrawPos(printX, printY);
				butDone.draw(g2);	
				
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
