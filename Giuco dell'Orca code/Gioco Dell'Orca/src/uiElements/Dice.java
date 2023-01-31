package uiElements;

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

public class Dice {
	
	// VARS

		// General ------------------------------------------
	
	int tileSize;
	private int x,y;
	public int absX,absY;
	MainPanel panel;
	int scale;
	boolean show;
	boolean onceTrigger;
	
		// Sprites vars ------------------------------------------
	
	public BufferedImage numGlitch[]; 
	public BufferedImage num[];
	int spriteIndex = 0;
	double startTime = 0;
	double lastAnimationTime = 0;
	long animationSpeed = (long) extra.Support.convertTime(0.01, "s", "ns");
	long rollTime = (long) extra.Support.convertTime(1, "s", "ns");
	double outTime;
	int animationProgress = 0;
	public boolean triggerAnimation;
	public int finalNumber = 1;
	
	int fillAlpha = 200;
	
	// CONSTRUCTORS
	
	public Dice(MainPanel panelGivn, BufferedImage xNumGlitch[], BufferedImage xNum[])
	{
		this.panel = panelGivn;
		this.scale = 30;
		this.tileSize = panel.getVW(scale);
		this.setAbsPosition(x,y);
		this.setPosition(x,y);
		this.numGlitch = xNumGlitch;
		this.num = xNum;
	}
	
	// FUNCTIONS
	
		// Get & Set ------------------------------------------
	
	public void setNumIndex(int index)
	{
		this.reset();
		this.finalNumber = index;
	}
	
	public void reset()
	{
		this.lastAnimationTime = 0;
		this.animationProgress = 0;
		this.startTime = 0;
		this.outTime = 0;
		this.onceTrigger = false;
	}
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
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
	
	public void show()
	{
		this.show = true;
	}
	
	public void hide()
	{
		this.show = false;
	}
	
	public boolean visibility()
	{
		return this.show;
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
	
	
	
		// Game Loops ------------------------------------------
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g2) // TODO
	{
		if(this.show == true)
		{
			if(this.startTime <= 0 && this.show == true)
			{
				startTime = System.nanoTime();
			}
			
			g2.setColor(new Color(0,0,0,this.fillAlpha));
			g2.fillRect(0, 0, panel.screenWidth, panel.screenHeight);
			
			BufferedImage img = null;
			
			if((System.nanoTime() - startTime) < this.rollTime)
			{
				if((System.nanoTime() - lastAnimationTime) >= animationSpeed)
				{
					img = numGlitch[spriteIndex];
						
					spriteIndex = (spriteIndex + 1) % numGlitch.length;
					lastAnimationTime = System.nanoTime();
					
				}
				
				this.tileSize = panel.getVW(scale);
				this.setPosition(panel.screenWidth/2, panel.screenHeight/2);
				g2.drawImage(img, x, y,tileSize,tileSize, null);
			}
			else
			{
				this.tileSize = panel.getVW(scale);
				this.setPosition(panel.screenWidth/2, panel.screenHeight/2);
				g2.drawImage(num[finalNumber], x, y,tileSize,tileSize, null);
				
				if(outTime <= 0)
				{
					outTime = System.nanoTime();
				}
				
				if((System.nanoTime()-this.outTime) > Support.convertTime(200, "ms", "ns"))
				{
					Support.wait(200, false);

					this.hide();
				}
			}
			
		}
	}
}
