package panels;

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


public class GameInfo {
	
	// VARS

		// General ------------------------------------------
	
	MainPanel panel;
	boolean show;
	int centerY;
	
		// Sprites & Time & Animation vars ------------------------------------------
	
		long inTime = (long) extra.Support.convertTime(0.4, "s", "ns");
		long outTime = (long) extra.Support.convertTime(0.4, "s", "ns");
		double inTimeLog;
		double outTimeLog;
		double animationProgress = 0;
		
		Color fillColor;
		Color mainColor;
		int shiftX,shiftY;
		
	// CONSTRUCTORS
	
	public GameInfo(MainPanel panelGivn)
	{
		this.panel = panelGivn;
		this.reset();
		this.centerY = panel.getVH(50);
	}
	
	// FUNCTIONS
	
	public void reset()
	{
		inTimeLog = 0;
		outTimeLog = 0;
		animationProgress = 0;
	}
	
	public void show()
	{
		if((System.nanoTime() - outTimeLog) >= this.outTime)
		{
			this.show = true;
			this.reset();
		}
	}
	
	public void hide()
	{
		this.show = false;
	}
	
	public boolean visibility()
	{
		return this.show;
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
	
	public void centerPosition(int x, int y, int tileSize)
	{
		x -= (tileSize/2);
		y -= (tileSize/2);
	}
	
	// Game Loops ------------------------------------------
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g2) // TODO
	{
		if(this.show == true)
		{
			if(this.inTimeLog <= 0)
			{
				this.inTimeLog = System.nanoTime();
			}
						
			if((System.nanoTime() - inTimeLog) < this.inTime)
			{
				this.animationProgress = Support.map(System.nanoTime() - inTimeLog, 0, this.inTime, 0, 100);
				
				int fillAlpha = (int) Support.map(this.animationProgress, 0, 100, 0, 190);
				int divColor = (int) Support.map(this.animationProgress, 0, 100, 0, 255);
				
				fillColor = new Color(0,0,0, fillAlpha);
				mainColor = new Color(255,174,0,divColor);
				
				shiftX = (int) Support.map(this.animationProgress, 100, 0, 0, (panel.screenHeight/3));
				shiftY = (int) Support.map(this.animationProgress, 100, 0, 0, panel.screenHeight);
			}
			else if(outTimeLog <= 0)
			{
				fillColor = new Color(0,0,0, 190);
				mainColor = new Color(255,174,0,255);
				
				shiftX = 0;
				shiftY = 0;
			}
			
			g2.setColor(fillColor);
			g2.fillRect(0,0, panel.screenWidth, panel.screenHeight);
			
			String text;
			//BufferedImage questSprite = panel.gamePanel.questionSquareSprites[0];
			
			BufferedImage playerA = panel.gamePanel.playerASprites[0];
			BufferedImage playerB = panel.gamePanel.playerBSprites[0];
			BufferedImage playerC = panel.gamePanel.playerCSprites[0];
			BufferedImage playerD = panel.gamePanel.playerDSprites[0];
			
			// Player 1
			
			int centerX = panel.getVW(12.5);
			
			text = panel.gamePanel.players[0].playerName;
			g2.setColor(Color.WHITE);
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(18)/text.length())));
			g2.drawString(text, 0+(centerX-getTextCenterX(text, g2))-shiftX, centerY-panel.getVW(20)-getTextCenterY(text, g2));
			
			int tileSize = panel.getVW(20);
			g2.drawImage(playerA, 0+(centerX-(tileSize/2))-shiftX, centerY-(tileSize/2),tileSize,tileSize, null);
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(8)));
			text = panel.gamePanel.players[0].getPointsString();
			g2.drawString(text, 0+(centerX-getTextCenterX(text, g2))-shiftX, centerY+panel.getVW(20)-getTextCenterY(text, g2));
			
			int baseX = panel.getVW(25);
			g2.setColor(mainColor);
			g2.fillRect(baseX, 0, panel.getVW(0.2), panel.getHeight());
			
			// Player 2
			
			text = panel.gamePanel.players[1].playerName;
			g2.setColor(Color.WHITE);
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(18)/text.length())));
			g2.drawString(text, baseX+(centerX-getTextCenterX(text, g2)), centerY-panel.getVW(20)-getTextCenterY(text, g2)+shiftY);
			
			tileSize = panel.getVW(20);
			g2.drawImage(playerB, baseX+(centerX-(tileSize/2)), centerY-(tileSize/2)+shiftY,tileSize,tileSize, null);
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(8)));
			text = panel.gamePanel.players[1].getPointsString();
			g2.drawString(text, baseX+(centerX-getTextCenterX(text, g2)), centerY+panel.getVW(20)-getTextCenterY(text, g2)+shiftY);
			
			baseX += panel.getVW(25);
			g2.setColor(mainColor);
			g2.fillRect(panel.getVW(50), 0, panel.getVW(0.2), panel.getHeight());
			
			// Player 3
			
			if(panel.gamePanel.players.length <= 2)
			{
				text = "???";
				g2.setColor(mainColor);
			}
			else
			{
				text = panel.gamePanel.players[2].playerName;
			}
			
			g2.setColor(Color.WHITE);
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(18)/text.length())));
			g2.drawString(text, baseX+(centerX-getTextCenterX(text, g2)), centerY-panel.getVW(20)-getTextCenterY(text, g2)-shiftY);
			
			tileSize = panel.getVW(20);
			g2.drawImage(playerC, baseX+(centerX-(tileSize/2)), centerY-(tileSize/2)-shiftY,tileSize,tileSize, null);
			
			if(panel.gamePanel.players.length <= 2)
			{
				text = "?";
			}
			else
			{
				text = panel.gamePanel.players[2].getPointsString();
			}
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(8)));
			g2.drawString(text, baseX+(centerX-getTextCenterX(text, g2)), centerY+panel.getVW(20)-getTextCenterY(text, g2)-shiftY);
			
			if(panel.gamePanel.players.length <= 2)
			{
				g2.setColor(fillColor);
				g2.fillRect(baseX, 0, panel.getVW(25), panel.getHeight());
			}
			
			baseX += panel.getVW(25);
			g2.setColor(mainColor);
			g2.fillRect(panel.getVW(75), 0, panel.getVW(0.2), panel.getHeight());
			
			// Player 4
			
			if(panel.gamePanel.players.length <= 3)
			{
				text = "???";
				g2.setColor(mainColor);
			}
			else
			{
				text = panel.gamePanel.players[3].playerName;
			}
			
			g2.setColor(Color.WHITE);
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(18)/text.length())));
			g2.drawString(text, baseX+(centerX-getTextCenterX(text, g2))+shiftX, centerY-panel.getVW(20)-getTextCenterY(text, g2));
			
			tileSize = panel.getVW(20);
			g2.drawImage(playerD, baseX+(centerX-(tileSize/2))+shiftX, centerY-(tileSize/2),tileSize,tileSize, null);
			
			if(panel.gamePanel.players.length <= 3)
			{
				text = "?";
			}
			else
			{
				text = panel.gamePanel.players[3].getPointsString();
			}
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(8)));
			g2.drawString(text, baseX+(centerX-getTextCenterX(text, g2))+shiftX, centerY+panel.getVW(20)-getTextCenterY(text, g2));
			
			if(panel.gamePanel.players.length <= 3)
			{
				g2.setColor(fillColor);
				g2.fillRect(baseX, 0, panel.getVW(25), panel.getHeight());
			}
			
			// end
			
			if(panel.keyLog.backSlash == true && outTimeLog == 0 && (System.nanoTime() - inTimeLog) > this.inTime)
			{
				this.outTimeLog = System.nanoTime();
			}
			
			if(outTimeLog != 0)
			{
				if((System.nanoTime() - outTimeLog) > this.outTime)
				{
					this.hide();
				}
				else
				{
					this.animationProgress = Support.map(System.nanoTime() - outTimeLog, this.outTime, 0, 0, 100);
					
					int fillAlpha = (int) Support.map(this.animationProgress, 0, 100, 0, 190);
					int divColor = (int) Support.map(this.animationProgress, 0, 100, 0, 255);
					
					fillColor = new Color(0,0,0, fillAlpha);
					mainColor = new Color(255,174,0,divColor);
					
					shiftX = (int) Support.map(this.animationProgress, 100, 0, 0, (panel.screenHeight/3));
					shiftY = (int) Support.map(this.animationProgress, 100, 0, 0, panel.screenHeight);
				}
			}
			
		}
		
	}
//?!?	
}
