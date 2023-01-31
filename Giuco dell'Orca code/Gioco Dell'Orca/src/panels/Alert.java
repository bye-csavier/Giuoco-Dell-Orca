package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import logic.*;
import extra.Support;
import gameObjects.Squares.Imprevisti;
import panels.MainPanel.PanelStateFlag;
import uiElements.*;

@SuppressWarnings("unused")

public class Alert {

	// VAR ================================
	
		// Panel Vars ------------------------ 
	
	MainPanel panel;
	boolean show;
	
	int textSize;
	int textAlpha = 255;
	int xSize;
	int ySize;
	
	int centerX, centerY;
	
	String text;
	
		// Animation Variables ------------------------
	
	long inTime = (long) extra.Support.convertTime(0.3, "s", "ns");
	long outTime = (long) extra.Support.convertTime(0.3, "s", "ns");
	long midTime = (long) Support.convertTime(0.8, "s", "ns");
	double inTimeLog;
	double outTimeLog;
	int aniProgress = 0;
	int outaniProgress = 0;
		
	// CONSTRUCTORS ================================
	
	public Alert(MainPanel panelGivn)//TODO
	{
		this.panel = panelGivn;
		this.centerX = panel.getVW(50);
		this.centerY = panel.getVH(50);
		this.xSize = panel.getWidth();
		this.ySize = panel.getVH(40);
		this.reset();
	}
	
	// FUNCTIONS ================================
	
		// +++ ------------------------
	
	public void reset()
	{
		this.outTimeLog = 0;
		this.inTimeLog = 0;
		this.aniProgress = 0;
		this.outaniProgress = 0;
	}
	
	public void set(Imprevisti imprevisto)
	{
		this.text = imprevisto.toString();
	}
	
	public void show()
	{
		this.show = true;
		this.reset();
	}
	
	public void hide()
	{
		this.show = false;
	}
	
	public boolean visibility()
	{
		return this.show;
	}
	
		// Scale & Position calculators ------------------------
	
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
		
		// Funzioni di loop GUI ------------------------
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g2) // TODO
	{
		
		if(this.show == true /* && panel.gamePanel.players[panel.gamePanel.triggerSwitchTurno].triggerAnimation == false && panel.gamePanel.dadoUI.visibility() == false */)
		{
			//System.out.println(this.aniProgress);
			//System.out.println((System.nanoTime() - this.inTimeLog) + " | " + this.inTimeLog);
			
			if(this.inTimeLog <= 0)
			{
				this.inTimeLog = System.nanoTime();
				//this.ySize = panel.getVH(35);
				this.xSize = panel.getWidth();
			}
			
			if((System.nanoTime() - this.inTimeLog) <= this.inTime)
			{
				this.aniProgress = (int) Support.map((System.nanoTime() - this.inTimeLog), 0, this.inTime, 0, 100);
				//System.out.print("!");
				//this.xSize = (int) Support.map(Support.clamp(0, this.aniProgress, 95), 0, 95, 0, panel.getWidth());
				this.ySize = (int) Support.map(Support.clamp(0, this.aniProgress, 95), 0, 95, 0, panel.getVH(30));
				this.textSize = (int) Support.map(Support.clamp(0, this.aniProgress, 95), 0, 95, panel.getVW(4), panel.getVW(7));
			}
//			else
//			{
//				this.xSize = panel.getWidth();
//				this.ySize = panel.getVH(40);
//			}
				
			g2.setColor(new Color(10,10,10,170));
			g2.fillRect( centerX-(Support.percentage(xSize, 50)) , centerY - Support.percentage(ySize, 50), xSize, ySize);
			
			g2.setColor(new Color(255,53,0, Support.percentage(this.textAlpha, aniProgress)));
			g2.setFont(this.panel.standard.deriveFont(Font.BOLD, Support.percentage(this.textSize, this.textSize)));
			int drawX = centerX - getTextCenterX(this.text, g2);
			int drawY = centerY - getTextCenterY(this.text, g2);
			g2.drawString(this.text, drawX, drawY);
			
			if(outTimeLog == 0 && (System.nanoTime() - this.inTimeLog) > (this.inTime + this.midTime) )
			{
				outTimeLog = System.nanoTime();
			}
				
			if(outTimeLog != 0)
			{
				if((System.nanoTime()-this.outTimeLog) > this.outTime)
				{
					this.hide();
				}
				else
				{
					this.aniProgress = (int) Support.map((System.nanoTime() - this.outTimeLog), 0, this.outTime, 100,0);
					//System.out.print("!!");
					this.ySize = (int) Support.map(this.aniProgress, 0, 100, 0, panel.getVH(30));
					this.textSize = (int) Support.map(this.aniProgress, 0, 100, panel.getVW(4), panel.getVW(7));
				}
				
			}
			
		}
	}
}
