package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import extra.Support;
import panels.MainPanel.PanelStateFlag;
import uiElements.*;

@SuppressWarnings("unused")

public class Names {
	
	// VAR ================================
	
		// Panel Vars ------------------------ 
	
	MainPanel panel;
	String players[];
	int nameIndex;
	
	long lastEscPressTime = 0;
	long maxPress = (long) Support.convertTime(0.35, "s", "ns");
	
	long lastKeyInput = 0;
	long delayInput = (long) Support.convertTime(0.1, "s", "ns");
	
	int maxNameLength = 10;
	
		//UI Elements ------------------------
	
	// CONSTRUCTORS ================================
	
	public Names(MainPanel panelGivn)
	{
		this.panel =  panelGivn;
		nameIndex = 0;
		setupNames();
	}
	
	// FUNCTIONS ================================
	
	public void setupNames()
	{
		this.players = new String[panel.settingsPanel.numGiocatori];
		
		for(int i=0; i<players.length; i++)
		{
			players[i] = "";
		}
		
		nameIndex = 0;
	}
	
	private boolean isCurNameOk()
	{
		if(players[nameIndex].length() <= 0)
		{
			return false;
		}
		
		char[] temp = players[nameIndex].toCharArray();
		
		for(int i=0; i<players[nameIndex].length(); i++)
		{
			if(temp[i] != ' ')
			{
				return true;
			}
		}
		
		return false;
	}
	
		// Funzioni di loop GUI ------------------------
	
	public void update()
	{
		if(panel.clearNames == true)
		{
			setupNames();
			panel.clearNames = false;
		}
		
		if(panel.keyLog.escape == true && lastEscPressTime <= 0)
		{
			lastEscPressTime = System.nanoTime();
		}
		else if(lastEscPressTime > 0 && panel.keyLog.escape == false)
		{
			lastEscPressTime = 0;
		}
		else if( (System.nanoTime() - lastEscPressTime) > this.maxPress && lastEscPressTime > 0 ) 
		{
			panel.menuPanel.butPlayGame.clickedOnce = false;
			panel.setGameState(PanelStateFlag.menu);
		}
		
			// Keys Input 
		
		if((System.nanoTime() - lastKeyInput) > delayInput )
		{
			
			if(panel.keyLog.enter == true && isCurNameOk() == true)
			{
				if(nameIndex+1 < players.length)
				{
					nameIndex++;
				}
				else
				{
					panel.setGameState(PanelStateFlag.gamePlay);
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.delete == true)
			{
				if(players[nameIndex].length() > 0)
				{
					players[nameIndex] = Support.deleteLastStringChar(players[nameIndex]);
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.space == true && players[nameIndex].length() < maxNameLength)
			{
				players[nameIndex] += " ";
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.q == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "q";
				}
				else
				{
					players[nameIndex] += "Q";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.w == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "w";
				}
				else
				{
					players[nameIndex] += "W";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.e == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "e";
				}
				else
				{
					players[nameIndex] += "E";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.r == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "r";
				}
				else
				{
					players[nameIndex] += "R";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.t == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "t";
				}
				else
				{
					players[nameIndex] += "T";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.y == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "y";
				}
				else
				{
					players[nameIndex] += "Y";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.u == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "u";
				}
				else
				{
					players[nameIndex] += "U";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.i == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "i";
				}
				else
				{
					players[nameIndex] += "I";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.o == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "o";
				}
				else
				{
					players[nameIndex] += "O";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.p == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "p";
				}
				else
				{
					players[nameIndex] += "P";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.a == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "a";
				}
				else
				{
					players[nameIndex] += "a";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.s == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "s";
				}
				else
				{
					players[nameIndex] += "S";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.d == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "d";
				}
				else
				{
					players[nameIndex] += "D";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.f == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "f";
				}
				else
				{
					players[nameIndex] += "F";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.g == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "g";
				}
				else
				{
					players[nameIndex] += "G";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.h == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "h";
				}
				else
				{
					players[nameIndex] += "H";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.j == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "j";
				}
				else
				{
					players[nameIndex] += "J";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.k == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "k";
				}
				else
				{
					players[nameIndex] += "K";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.l == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "l";
				}
				else
				{
					players[nameIndex] += "L";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.z == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "z";
				}
				else
				{
					players[nameIndex] += "Z";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.x == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "x";
				}
				else
				{
					players[nameIndex] += "X";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.c == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "c";
				}
				else
				{
					players[nameIndex] += "C";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.v == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "v";
				}
				else
				{
					players[nameIndex] += "V";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.b == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "b";
				}
				else
				{
					players[nameIndex] += "B";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.n == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "n";
				}
				else
				{
					players[nameIndex] += "N";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
			if(panel.keyLog.m == true && players[nameIndex].length() < maxNameLength)
			{
				if(panel.keyLog.shift == false)
				{
					players[nameIndex] += "m";
				}
				else
				{
					players[nameIndex] += "M";
				}
				
				lastKeyInput = System.nanoTime();
			}
			
		}
	}
	
	public void draw(Graphics2D g2)
	{
		g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(4)) ));
		g2.setColor(Color.white);
		
		String text = "Inserisci il tuo nickname giocatore " + (nameIndex+1);
		int printX = (panel.getWidth() /2) - getTextCenterX(text, g2);
		int printY = panel.getVH(6) - getTextCenterY(text, g2);
		
		g2.drawString(text, printX, printY);
		
		// Input
		
		printX = (panel.getWidth() /2) - getTextCenterX(players[nameIndex], g2);
		printY = (panel.getHeight() /2)  - getTextCenterY(players[nameIndex], g2);
		
		g2.drawString(players[nameIndex], printX, printY);
		
		
		// Exit to menu
		
		if(lastEscPressTime > 0)
		{
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(2)));
			
			long pressTime = System.nanoTime() - lastEscPressTime;
			
			int alpha;
			
			if(pressTime < this.maxPress)
			{
				alpha = (int) Support.map(pressTime, 0, this.maxPress, 0, 255);
			}
			else
			{
				alpha = 255;
			}
			
			double Xpos = 2;
			double Ypos = 4;
			double temp = 0.4;
			
			g2.setColor(new Color(63,43,0,alpha));
			
			g2.drawString("MENU...", panel.getVMIN(Xpos-temp), panel.getVMIN(Ypos+temp));
			
			g2.setColor(new Color(190,129,0,alpha));
			
			g2.drawString("MENU...", panel.getVMIN(Xpos-(temp/2)), panel.getVMIN(Ypos+(temp/2)));
			
			g2.setColor(new Color(255,174,0,alpha));
			
			g2.drawString("MENU...", panel.getVMIN(Xpos), panel.getVMIN(Ypos));
		}
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
