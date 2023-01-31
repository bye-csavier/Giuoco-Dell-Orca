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

public class Fight {
	
	// VARS

			// General ------------------------------------------
		
		MainPanel panel;
		boolean show;
		int centerX,centerY;
		
			// Sprites & Time & Animation vars ------------------------------------------
		
			long inTime = (long) extra.Support.convertTime(1, "s", "ns");
			long outTime = (long) extra.Support.convertTime(0.35, "s", "ns");
			double inTimeLog;
			double outTimeLog;
			double animationProgress = 0;
			
			double leftAlphaAni;
			double rightAlphaAni;
			long alphaAniTime = (long) extra.Support.convertTime(50, "ms", "ns");
			
			Color leftColor = new Color(255,15,15);
			Color rightColor = new Color(15,15,255);
			Color fillColor;
			
			int addSize;
			int addAlpha;
			int shiftX;
			int shiftYProgress;
			
			int trueRightSize = 0;
			int trueLeftSize = 0;
			int tempLeftSize = 0;
			int tempRightSize = 0;
			int sizesAniMult;
			
			boolean qPressLock;
			boolean pPressLock;
			int xClickAdd;
			
			int playerA;
			int playerB;
			
			int leftAlpha;
			int rightAlpha;
			
			String playerAName;
			String playerBName;
			
			int shadowShift;
			int textSize;
			int addTxtSize;
			int txtX;
			int txtY;
			int leftShiftX;
			int rightShiftX;
			
		// CONSTRUCTORS
		
		public Fight(MainPanel panelGivn)
		{
			this.panel = panelGivn;
			this.reset();
			this.centerY = panel.getVH(50);
			this.centerX = panel.getVW(50);
			this.xClickAdd = panel.getVW(2);
			this.textSize = panel.getVW(5);
			this.shadowShift = panel.getVW(0.2);
		}
		
		// FUNCTIONS
		
		public void reset()
		{
			this.inTimeLog = 0;
			this.outTimeLog = 0;
			this.animationProgress = 0;
			this.trueRightSize = this.centerX;
			this.trueLeftSize = this.trueRightSize;
			this.sizesAniMult = 0;
			this.leftShiftX = 0;
			this.rightShiftX = 0;
			this.shiftYProgress = panel.getVW(10);
		}
		
		public void setInfo(int playerA, int playerB)
		{
			this.playerA = playerA;
			this.playerB = playerB;
			this.playerAName = panel.gamePanel.players[playerA].playerName;
			this.playerBName = panel.gamePanel.players[playerB].playerName;
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
		
		public int getTextWidth(String text, Graphics2D g2)
		{
			int centerX = (int) g2.getFontMetrics().getStringBounds(text,g2).getWidth();
			
			return centerX;
		}
		
		public int getTextHeight(String text, Graphics2D g2)
		{
			int centerX = (int) g2.getFontMetrics().getStringBounds(text,g2).getHeight();
			
			return centerX;
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
			
			if(this.show == true && panel.gamePanel.dadoUI.visibility() == false)
			{
				if(this.inTimeLog <= 0)
				{
					this.inTimeLog = System.nanoTime();
				}
				
				if((System.nanoTime() - inTimeLog) < this.inTime)
				{
					this.animationProgress = Support.map(System.nanoTime() - inTimeLog, 0, this.inTime, 0, 100);
					
					int fillAlpha = (int) Support.map(this.animationProgress, 0, 100, 0, 255);
					
					if((System.nanoTime() - inTimeLog) < (this.inTime-(Support.percentage(this.inTime, 20))))
					{
						this.shiftX = (int) Support.map(System.nanoTime() - inTimeLog, 0,this.inTime-(Support.percentage(this.inTime, 20)), -panel.getVW(5), panel.getVW(35));
					}
					else
					{
						this.shiftX = panel.getVW(35);
					}
					
					fillColor = new Color(0,0,0, fillAlpha);
					
					int fastTrigger = 15;
					if((System.nanoTime() - inTimeLog) > (this.inTime-(Support.percentage(this.inTime, fastTrigger))))
					{
						this.sizesAniMult = (int) Support.map(System.nanoTime() - inTimeLog, this.inTime-(Support.percentage(this.inTime, fastTrigger)), this.inTime, 0, 100);
						this.shiftYProgress = (int) Support.map(System.nanoTime() - inTimeLog, this.inTime-(Support.percentage(this.inTime, fastTrigger)), this.inTime, panel.getVW(7), 0);
					}
					
				}
				else if(outTimeLog <= 0)
				{
					fillColor = new Color(0,0,0, 255);
					this.sizesAniMult = 100;
				
				}
				
				//System.out.println(this.sizesAniMult);
				
				if(leftAlpha > 0 && (System.nanoTime()-leftAlphaAni) > this.alphaAniTime)
				{
					leftAlpha -= 10;
					leftAlphaAni = System.nanoTime();
				}
				if(rightAlpha > 0 && (System.nanoTime()-rightAlphaAni) > this.alphaAniTime)
				{
					rightAlpha -= 10;
					rightAlphaAni = System.nanoTime();
				}
				
				if((System.nanoTime() - inTimeLog) < this.inTime)
				{
					g2.setColor(fillColor);
					g2.fillRect(0,0, panel.screenWidth, panel.screenHeight);
					
					// Sprites ani
					
					BufferedImage playerASprites = panel.gamePanel.players[this.playerA].sprites[0];
					BufferedImage playerBSprites = panel.gamePanel.players[this.playerB].sprites[0];
					
					int tileSize = panel.getVW(20);
					
					g2.drawImage(playerASprites, shiftX-(tileSize/2), centerY-(tileSize/2),tileSize,tileSize, null);
					g2.drawImage(playerBSprites, (panel.getWidth()-shiftX)-(tileSize/2), centerY-(tileSize/2),tileSize,tileSize, null);
					
				}
				else if(outTimeLog <= 0)
				{
					g2.setColor(this.rightColor);
					g2.fillRect(0,0, panel.screenWidth, panel.screenHeight);
				}
				
				// Left Side
				
				if(panel.keyLog.q == false)
				{
					qPressLock = false;
				}
				if(panel.keyLog.q == true && qPressLock == false && outTimeLog == 0)
				{
					qPressLock = true;
					this.trueLeftSize += this.xClickAdd;
					this.trueRightSize -= this.xClickAdd;
					this.leftAlpha = 80;
				}
				
				this.tempLeftSize = Support.percentage(trueLeftSize, this.sizesAniMult);
				g2.setColor(leftColor);
				g2.fillRect(0,0, this.tempLeftSize, panel.screenHeight);
				g2.setColor(new Color(255,255,255,leftAlpha));
				g2.fillRect(0,0, this.tempLeftSize, panel.screenHeight);
				
				// Right Side
				
				if(panel.keyLog.p == false)
				{
					pPressLock = false;
				}
				if(panel.keyLog.p == true && pPressLock == false && outTimeLog == 0)
				{
					pPressLock = true;
					this.trueLeftSize -= this.xClickAdd;
					this.trueRightSize += this.xClickAdd;
					this.rightAlpha = 80;
				}
				
				
				this.tempRightSize = Support.percentage(trueRightSize, this.sizesAniMult);
				g2.setColor(rightColor);
				g2.fillRect(this.panel.getWidth()-this.tempRightSize,0, this.tempRightSize, panel.screenHeight);
				g2.setColor(new Color(255,255,255,rightAlpha));
				g2.fillRect(this.panel.getWidth()-this.tempRightSize,0, this.tempRightSize, panel.screenHeight);
				
				// players names
				
				// Name A
				
				int aX = panel.getVW(1);
				int aY = panel.getVW(7);
				
				g2.setColor(new Color(10,10,10, 100));
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (this.textSize+this.addTxtSize)));
				g2.drawString(this.playerAName, 0 + aX - (this.shadowShift*2), 0 + aY + getTextCenterY(this.playerAName,g2) - (this.shadowShift*2) - this.shiftYProgress);
				
				g2.setColor(new Color(10,10,10, 200));
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (this.textSize+this.addTxtSize)));
				g2.drawString(this.playerAName, 0 + aX - (this.shadowShift), 0 + aY + getTextCenterY(this.playerAName,g2) - (this.shadowShift) - this.shiftYProgress);
				
				g2.setColor(new Color(255,255,255));
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (this.textSize+this.addTxtSize)));
				g2.drawString(this.playerAName, 0 + aX, 0 + aY + getTextCenterY(this.playerAName,g2) - this.shiftYProgress);
				
				
				// Name B
				
				int bX = panel.getVW(1.5);
				int bY = panel.getVW(4);
				
				g2.setColor(new Color(10,10,10, 100));
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (this.textSize+this.addTxtSize)));
				g2.drawString(this.playerBName, panel.getWidth() - bX - getTextWidth(this.playerBName, g2) + (this.shadowShift*2), panel.getHeight() - bY - getTextCenterY(this.playerBName,g2) + (this.shadowShift*2) + this.shiftYProgress);
				
				g2.setColor(new Color(10,10,10, 200));
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (this.textSize+this.addTxtSize)));
				g2.drawString(this.playerBName, panel.getWidth() - bX - getTextWidth(this.playerBName, g2) + (this.shadowShift), panel.getHeight() - bY - getTextCenterY(this.playerBName,g2) + (this.shadowShift) + this.shiftYProgress);
				
				g2.setColor(new Color(255,255,255));
				g2.setFont(panel.standard.deriveFont(Font.BOLD, (this.textSize+this.addTxtSize)));
				g2.drawString(this.playerBName, panel.getWidth() - bX - getTextWidth(this.playerBName, g2), panel.getHeight() - bY - getTextCenterY(this.playerBName,g2) + this.shiftYProgress);
				
				// outro
				
				if((this.trueLeftSize >= panel.getWidth() || this.trueRightSize >= panel.getWidth()) && (System.nanoTime() - inTimeLog) > this.inTime && outTimeLog == 0)
				{
					this.outTimeLog = System.nanoTime();
					
					if(this.trueLeftSize >= panel.getWidth())
					{
						panel.gamePanel.players[playerA].moveTo(panel.gamePanel.campo[panel.gamePanel.players[playerB].squareIndex].getPosX(), panel.gamePanel.campo[panel.gamePanel.players[playerB].squareIndex].getPosY(), false);
						panel.gamePanel.players[playerB].moveTo(panel.gamePanel.campo[panel.gamePanel.players[playerA].squareIndex].getPosX(), panel.gamePanel.campo[panel.gamePanel.players[playerA].squareIndex].getPosY(), false);
						int temp = panel.gamePanel.players[playerA].squareIndex;
						panel.gamePanel.players[playerA].setSquareIndex(panel.gamePanel.players[playerB].squareIndex);
						panel.gamePanel.players[playerB].setSquareIndex(temp);
						panel.gamePanel.players[playerA].addPoints(panel.gamePanel.players[playerB].getPoints());
						panel.gamePanel.players[playerB].setPoints(0);
					}
					else if(this.trueRightSize >= panel.getWidth())
					{
						panel.gamePanel.players[playerB].addPoints(panel.gamePanel.players[playerA].getPoints());
						panel.gamePanel.players[playerA].setPoints(0);
					}
				}
				
				if(outTimeLog != 0)
				{
					if((System.nanoTime() - outTimeLog) > this.outTime)
					{	
						this.hide();
					}
					else
					{
						
						int fillAlpha = 0;
						
						fillColor = new Color(0,0,0, fillAlpha);
						
						this.sizesAniMult = (int) Support.map(System.nanoTime() - outTimeLog,0, this.outTime, 100, 0);
						this.shiftYProgress = (int) Support.map(System.nanoTime() - outTimeLog,0, this.outTime, 0, panel.getVW(5));
					}
				}
				
			}
		}
}
