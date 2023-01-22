package uiElements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.KeyHandler;
import extra.Support;
import gameObjects.Squares.SquareType;
import panels.MainPanel;
import panels.MainPanel.PanelStateFlag;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused") //!!!

public class ButtonA implements MouseListener, MouseMotionListener{
	
	// VARS
	
		// General ------------------------------------------
		
		Font font;
		String sizeType;
		int fontSize;
		Color color;
		String text;
		int drawX;
		int drawY;
		
		MainPanel panel;
		PanelStateFlag whenClicked;
		PanelStateFlag afterClick;
		public boolean clickedOnce;
		
		// Animation ------------------------------------------
		
		Color defaultColor;
		Color hoverColor;
		long hoverSpeed; // in seconds
		double lastAnimationTime = 0;
		double lastInsideTime = 0;
		boolean hover = false;
		
		String animatedChar = "I";
		
		int animationPercentage = 0;
		
	// CONSTRUCTORS
		
		public ButtonA(String givnText, int givnDrawX, int givnDrawY, Font givnFont, Color givnColor,String givnSizeType, int givnFontSize, MainPanel givnPanel, Color givnHoverColor, double givnHoverSpeed, PanelStateFlag givnWhenClicked)
		{
			this.text = givnText;
			this.font = givnFont;
			this.sizeType = givnSizeType.toLowerCase();
			this.fontSize = givnFontSize;
			this.defaultColor = givnColor;
			this.color = givnColor;
			
			this.panel = givnPanel;
			
			this.hoverColor = givnHoverColor;
			
			this.hoverSpeed = (long) extra.Support.convertTime( (givnHoverSpeed/1000), "ms", "ns");
			
			this.drawX = givnDrawX;
			this.drawY = givnDrawY;
			
			this.whenClicked = givnWhenClicked;
			
			panel.addMouseListener(this);
			panel.addMouseMotionListener(this);
		}
	
	// FUNCTIONS
		
		public void setAfterClickedLink(PanelStateFlag link)
		{
			this.afterClick = link;
		}
		
		public void checkAfterClick()
		{
			if(this.clickedOnce == true && this.afterClick != null)
			{
				this.whenClicked = this.afterClick;
			}
		}
		
		public void update()
		{
			
		}
		
		public void draw(Graphics2D g2)
		{
			
			g2.setFont(font.deriveFont(Font.BOLD, (this.updatedFontSize()) ));
			g2.setColor(color);
			
			drawX -= getTextCenterX(text, g2);
			drawY -= getTextCenterY(text, g2);
			
			g2.drawString(text, drawX, drawY);
			
			// x animation
				
				// ANIMATION CODE
			
			if(this.hover == true)
			{
				if(this.animationPercentage < 100)
				{
					double curTime = System.nanoTime();
					
					if((curTime - lastAnimationTime) >= hoverSpeed)
					{
						this.animationPercentage += 10;
						
						lastAnimationTime = System.nanoTime();
					}
				}
				
			}
			else if(this.hover == false)
			{
				if(this.animationPercentage > 0)
				{
					double curTime = System.nanoTime();
					
					if((curTime - lastAnimationTime) >= hoverSpeed)
					{
						this.animationPercentage -= 10;
						
						lastAnimationTime = System.nanoTime();
					}
				}
				
			}
			
				//--------
			int animatedFontSize = (int) ( ( ( (double) this.updatedFontSize()) /100 ) * this.animationPercentage);
			g2.setFont(font.deriveFont(Font.BOLD, (animatedFontSize) ));
			
			drawX -= panel.getVW(2);
			int flipX = (panel.screenWidth - drawX) - panel.getVW(1.1);
			
			g2.drawString(animatedChar, drawX, drawY);
			g2.drawString(animatedChar, flipX, drawY);
			
				//--------
			animatedFontSize = (int) ( ( ( (double) this.updatedFontSize()) /100 ) * this.animationPercentage);
			g2.setFont(font.deriveFont(Font.BOLD, (animatedFontSize/2) ));
			
			drawX -= panel.getVW(1);
			flipX = (panel.screenWidth - drawX) - panel.getVW(0.6);
			int newY = drawY + getTextCenterY(animatedChar,g2);
			
			g2.drawString(animatedChar, drawX, newY);
			g2.drawString(animatedChar, flipX, newY);
			
				//--------
			animatedFontSize = (int) ( ( ( (double) this.updatedFontSize()) /100 ) * this.animationPercentage);
			g2.setFont(font.deriveFont(Font.BOLD, (animatedFontSize/3) ));
			
			drawX -= panel.getVW(1);
			flipX = (panel.screenWidth - drawX) - panel.getVW(0.4);
			newY = drawY + (getTextCenterY(animatedChar,g2)*2);
			
			g2.drawString(animatedChar, drawX, newY);
			g2.drawString(animatedChar, flipX, newY);
			
		}
		
		private int updatedFontSize()
		{
			
			if(this.sizeType == "vw")
			{
				return panel.getVW(this.fontSize);
			}
			else if(this.sizeType == "vmin")
			{
				return panel.getVMIN(this.fontSize);
			}
			else if(this.sizeType == "vmax")
			{
				return panel.getVMAX(this.fontSize);
			}
			else if(this.sizeType == "vh")
			{
				return panel.getVH(this.fontSize);
			}
			
			return this.fontSize;
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
		
		public void setDrawPos(int x, int y)
		{
			this.drawX = x;
			this.drawY = y;
		}
		
			// Mouse Events ------------------------------------------
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(mouseInsideObj(e,0,0) == true)
			{
				checkAfterClick();
				
				if(this.clickedOnce != true)
				{
					this.clickedOnce = true;
				}
					
				if(this.whenClicked != PanelStateFlag.quit)
				{
					panel.setGameState(this.whenClicked);
				}
				else
				{
					System.exit(0);
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
			if(mouseInsideObj(e,0,0) == true)
			{
				this.color = this.hoverColor;
				
				this.hover = true;
				
			}
			else if(this.hover == true)
			{
				this.color = this.defaultColor;
				
				this.hover = false;
				
			}
			
			
		}
		
		public boolean mouseInsideObj(MouseEvent e, int borderX, int borderY)
		{
			
			Graphics2D g2 = (Graphics2D) panel.getGraphics();
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(5)) ));
			
			int textSizeX = (int) g2.getFontMetrics().getStringBounds(this.text, g2).getWidth();
			int textSizeY = (int) g2.getFontMetrics().getStringBounds(this.text, g2).getHeight();
			
			int maxX = textSizeX + borderX;
			int maxY = textSizeY + borderY;
			
			int eX = e.getX();
			int eY = e.getY();
			
			int posXFixer = (int) (( (double) maxX)/2.6);
			int posYFixer = (int) (( (double) maxY)/2.6);
			
			//System.out.println("textSizeX:" + textSizeX + " | " + "textSizeY:" + textSizeY + " | " + Support.nL + "drawX:" + drawX + " | " + "drawY:" + drawY + " | " + Support.nL + "maxX:" + maxX + " | " + "maxY:" + maxY + " | " + Support.nL + "eX:" + eX + " | " + "eY:" + eY + " | " + Support.nL + Support.nL );
			
			if( ( eX >= (drawX) && eX <= (drawX+maxX+posXFixer) ) && ( eY >= (drawY-maxY+posYFixer) && eY <= (drawY) ))
			{
				return true;
				//System.out.println("TRUE");
			}
			
			return false;
		}
		
//?!?		
}	
