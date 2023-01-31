package gameObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.KeyHandler;
import extra.Support;
import gameObjects.Squares.Imprevisti;
import panels.MainPanel;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused") //!!!

public class Player {
	
	// VARS
	
		// General ------------------------------------------
	
	int tileSize;
	private int x,y;
	public int absX,absY;
	MainPanel panel;
	int scale;
	public String playerName;
	int playerNameSize = 2; // IN VW
	
		// Related to campo ------------------------------------------
	
	public int squareIndex = 0;
	private int punteggio = 0;
	private int []domandeFatte = new int[0];
	public boolean activeImp = false;
	public Imprevisti imprevisto;

		// Sprites vars ------------------------------------------
	
	public BufferedImage sprites[]; 
	int spriteIndex = 0;
	double lastAnimationTime = 0;
	long animationSpeed = (long) extra.Support.convertTime(0.08, "s", "ns");
	public boolean triggerAnimation;
	int aniX, aniY;
	
	double delayTrigger = 0;
	long delayTime = (long) extra.Support.convertTime(1.15, "s", "ns");
	
	// CONSTRUCTORS
	
	public Player(MainPanel panelGivn, int tileSizeGivn, String playerName, int x, int y)
	{
		this.panel = panelGivn;
		this.scale = tileSizeGivn;
		this.tileSize = panel.getVMIN(scale);
		this.setAbsPosition(x,y);
		this.setPosition(x,y);
		this.playerName = playerName;
	}
	
	// FUNCTIONS 
	
		// Get & Set ------------------------------------------
	
	public void addQuestionIndex()
	{
		int temp[] = new int[domandeFatte.length+1];
		
		for(int i=0; i<domandeFatte.length; i++)
		{
			temp[i] = domandeFatte[i];
		}
		
		domandeFatte = temp;
	}
	
	public boolean controllaDomanda(int indice) {
		for(int i : domandeFatte) {
			if(i == indice) {
				return true;
			}
		}
		return false;
	}
	
	public void addPoints(int p)
	{
		this.punteggio += p;
	}
	
	public void setPoints(int p)
	{
		this.punteggio = p;
	}
	
	public void removePoints(int p)
	{
		this.punteggio -= p;
	}
	
	public void divPoints()
	{
		this.punteggio /= 2;
	}
	
	public int getPoints()
	{
		return this.punteggio;
	}
	
	public String getPointsString()
	{
		return Integer.toString(this.punteggio);
	}
	
//	public void setSquareIndexNormal(int index)
//	{
//		this.squareIndex = index;
//	}
	
	public void setSquareIndex(int index)
	{
		panel.gamePanel.campo[this.squareIndex].disableOverlay();
		this.squareIndex = index;
		panel.gamePanel.campo[index].enableOverlay();
	}
	
	public void setSprites(BufferedImage sprites[])
	{
		this.sprites = sprites;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		//fixIfOutside();
		centerPosition();
	}
	
	public void setAbsPosition(int x, int y)
	{
		this.absX = x;
		this.absY = y;
	}
	
	public int getPosX()
	{
		return this.x;
	}
	
	public int getPosY()
	{
		return this.y;
	}
	
	public int getAbsPosY()
	{
		return this.absY;
	}
	
	public void setPosY(int val)
	{
		this.y = val;
	}
	
		// Positions & Sizes Calcuators ------------------------------------------
	
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
	
	public void centerPosition()
	{
		x -= (tileSize/2);
		y -= (tileSize/2);
	}
	
	public boolean insideScreen()
	{
		
		if( this.x >= (panel.screenWidth + tileSize + 50) )
		{
			return false;
		}
		
		if( this.x <= (-tileSize-50))
		{
			return false;
		}
		
		if(this.y >= (panel.screenHeight + tileSize + 50) )
		{
			return false;
		}
		
		if(this.y <= (-tileSize-50))
		{
			return false;
		}
		
		return true;
	}
	
		// Funzioni di gioco ------------------------------------------
	
	public void moveTo(int newX, int newY, boolean ani)
	{
	
		if(ani == false)
		{
			this.x = newX + panel.getVMIN(scale*0.2);
			this.y = newY + panel.getVMIN(scale*0.2);	
			this.absX = this.x;
			this.absY = this.y;
		}
		else
		{
			delayTrigger = 0;
			triggerAnimation = true;
			this.aniX = newX + panel.getVMIN(scale*0.2);
			this.aniY = newY + panel.getVMIN(scale*0.2);
		}
	}
	
	public void setImprevisto(Imprevisti imprevisto)
	{
		this.activeImp = true;
		this.imprevisto = imprevisto;
	}
	
	public void doImprevisto()
	{
		panel.gamePanel.alert.show();
		panel.gamePanel.alert.set(this.imprevisto);
		
		this.activeImp = false;
		if(this.imprevisto == Imprevisti.AVANZA)
		{
			int tempIndex = this.squareIndex + 1;
			while(panel.gamePanel.checkTriggerFight(tempIndex, -1) == true)
			{
				tempIndex++;
				
				if(tempIndex >= panel.gamePanel.campo.length)
				{
					tempIndex = (panel.gamePanel.campo.length-1) - (tempIndex%(panel.gamePanel.campo.length-1));
				}
			}
			
			this.moveTo(panel.gamePanel.campo[tempIndex].getPosX(),panel.gamePanel.campo[tempIndex].getPosY(),true);
			this.setSquareIndex(tempIndex);
		}
		else if(this.imprevisto == Imprevisti.INDIETREGGIA)
		{
			int tempIndex = this.squareIndex - 1;
			while(panel.gamePanel.checkTriggerFight(tempIndex, -1) == true)
			{
				tempIndex--;
				
				if(tempIndex <= 0)
				{
					tempIndex = 0;
					break;
				}
			}
			
			this.moveTo(panel.gamePanel.campo[tempIndex].getPosX(),panel.gamePanel.campo[tempIndex].getPosY(),true);
			this.setSquareIndex(tempIndex);
		}
		else if(this.imprevisto == Imprevisti.PUNTIMEZZI)
		{
			this.punteggio /= 2;
		}
		else if(this.imprevisto == Imprevisti.PUNTIDOPPI)
		{
			this.punteggio *= 2;
		}
	}
	
		// Game Loops ------------------------------------------
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g2) //TODO
	{
		
		if(this.insideScreen() == true)
		{
			if(delayTrigger == 0 && triggerAnimation == true)
			{
				delayTrigger = System.nanoTime();
			}
			
			if(triggerAnimation == true && (System.nanoTime()-delayTrigger) > delayTime)
			{
				BufferedImage img = sprites[spriteIndex];
				
				double curTime = System.nanoTime();
				
				if((curTime - lastAnimationTime) >= animationSpeed)
				{
					spriteIndex = (spriteIndex + 1) % sprites.length;
					lastAnimationTime = System.nanoTime();
				}
				
				this.tileSize = panel.getVMIN(scale);
				g2.drawImage(img, x, y,tileSize,tileSize, null);
				
				if(spriteIndex == 7)
				{
//					if(this.aniY != this.y)
//					{
//						panel.scrolling -= (this.aniY+this.y) - (panel.getHeight());
//						
//						for(int i=0; i < panel.gamePanel.campo.length; i++)
//						{
//							panel.gamePanel.campo[i].setPosition(panel.gamePanel.campo[i].absX, panel.gamePanel.campo[i].absY+panel.scrolling);
//						}
//						
//						for(int i=0; i < panel.gamePanel.players.length; i++) // panel.gamePanel.players[i] = player corrente, praicamente prediamo la casella in cui sta il player corrente e dopo avere aggiornato la posizione della casella riallineamo i giocatori
//						{
//							panel.gamePanel.players[i].moveTo(panel.gamePanel.campo[panel.gamePanel.players[i].squareIndex].getPosX(),panel.gamePanel.campo[panel.gamePanel.players[i].squareIndex].getPosY(),false);
//						}
//						
//					}
//					else
//					{
//						this.x = this.aniX;
//						this.y = this.aniY;	
//						this.absX = this.aniX;
//						this.absY = this.aniY;
//					}
//					
					this.x = this.aniX;
					this.y = this.aniY;	
					this.absX = this.aniX;
					this.absY = this.aniY;
				}
				
				if((spriteIndex + 1) >= sprites.length)
				{
					this.triggerAnimation = false;
					this.spriteIndex = 0;
				}
			}
			else
			{
				if(activeImp == true && (System.nanoTime()-lastAnimationTime) < Support.convertTime(0.5, "s", "ns"))
				{
					this.doImprevisto();
				}
				
				BufferedImage img = sprites[spriteIndex];
				
				this.tileSize = panel.getVMIN(scale);
				g2.drawImage(img, x, y,tileSize,tileSize, null);
				
				// player name
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(playerNameSize)) ));
				int textX = x+(tileSize/2)-getTextCenterX(playerName, g2)+panel.getVMIN(0.04);
				int textY = y+(tileSize/2)-getTextCenterY(playerName, g2);
				int shadowShift = panel.getVMIN(scale*0.04);
				
				g2.setColor(new Color(50,50,50,100));
				g2.drawString(playerName, textX, textY+shadowShift);
				
				g2.setColor(new Color(10,10,10,175));
				g2.drawString(playerName, textX, textY+(shadowShift/2));
				
				g2.setColor(new Color(255,255,255));
				g2.drawString(playerName, textX, textY);
			}
			
		}
		
	}
	
}
