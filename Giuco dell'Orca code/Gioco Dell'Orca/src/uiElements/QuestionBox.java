package uiElements;

import java.awt.BasicStroke;
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
import gameObjects.Squares.Imprevisti;
import panels.MainPanel;
import panels.MainPanel.PanelStateFlag;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused") //!!!

public class QuestionBox implements MouseListener, MouseMotionListener{
	
	// VAR ==============================================================================================

		// Panel Vars ----------------------------------------------------------------------------------- 
	
	MainPanel panel;
	int x,y;
	int xSize,ySize;
	public int shiftY = 0;
	
	int borderAlpha = 255;
	int fillAlpha = 230;
	
	Color dark = new Color(0,0,0,fillAlpha);
	Color light = new Color(250,250,250);
	Color mainColor;
	
	Color fillColor = dark;
	Color borderColor;
	
		// Question Vars ----------------------------------------------------------------------------------- 
	
	String text;
	boolean isRight = false;
	boolean breakStringDone = false;
	int lines;
	
	Font font;
	int fontSize;
	
	int textAlpha = 255;
	Color textColor = light;
	
		// Animation Variables -----------------------------------------------------------------------------------
	
		// UI Elements -----------------------------------------------------------------------------------
	
	
	// CONSTRUCTORS ===========================================================================================
	
	public QuestionBox(MainPanel panelGivn)
	{
		this.panel = panelGivn;
		this.font = panel.standard;
		
		this.mainColor = panel.mainColorA;
		this.borderColor = panel.mainColorA;
		
		//this.fontSize = panel.getVW(5);
		
	}
	
	// FUNCTIONS ==============================================================================================
	
		// +++ -----------------------------------------------------------------------------------
	
	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setSize(int x, int y)
	{
		this.xSize = x;
		this.ySize = y;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getSizeY()
	{
		return this.ySize;
	}
	
	public int getSizeX()
	{
		return this.xSize;
	}
	
	public void setText(String txt)
	{
		this.text = txt;
	}
	
	public void switchRight()
	{
		if(this.isRight == false)
		{
			this.isRight = true;
		}
		else
		{
			this.isRight = false;
		}
	}
	
	public void disableListeners() 
	{
		
	}
	
	public void enableListeners() 
	{
		
	}
	
		// Scale & Position calculators -----------------------------------------------------------------------------------
	
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
	
		// Funzioni di loop GUI -----------------------------------------------------------------------------------
	
	public void update()
	{
	
	}
	
	public void draw(Graphics2D g2)
	{
		

		int drawX, drawY;
		
		drawX = this.x;
		drawY = this.y + this.shiftY;
		
		// Box di stampa
		
		g2.setColor(fillColor);
		g2.fillRoundRect(drawX, drawY, xSize, ySize, 35, 35);
		
		g2.setColor(borderColor);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(drawX, drawY, xSize-5, ySize-5, 25, 25);
		
		// Stampa testo
		
		g2.setColor(textColor);
		//this.fontSize = (int) ((double) Support.percentage(xSize, 200) / this.text.length());
		this.fontSize = panel.getVW(6);
		this.fontSize = Support.clamp(panel.getVW(1), this.fontSize, panel.getVW(6));
		g2.setFont(this.font.deriveFont(Font.BOLD, this.fontSize));
		
		if(g2.getFontMetrics().getStringBounds(this.text, g2).getWidth() > Support.percentage(xSize, 80) || g2.getFontMetrics().getStringBounds(this.text, g2).getHeight() > Support.percentage(ySize, 90))
		{
			this.fontSize = (int) ((double) Support.percentage(xSize, 200) / this.text.length());
			g2.setFont(this.font.deriveFont(Font.BOLD, this.fontSize));
		}
		
		drawX += (xSize/2) - getTextCenterX(this.text, g2);
		drawY += (ySize/2) - getTextCenterY(this.text, g2);
		g2.drawString(this.text, drawX, drawY);
		
//		g2.setColor(textColor);
//		int each = 55;
//		
//		if(this.text.length() < each)
//		{
//			this.fontSize = (int) ((double) xSize / this.text.length());
//		}
//		else
//		{
//			this.fontSize = (int) ((double) xSize / each);
//		}
//		
//		g2.setFont(this.font.deriveFont(Font.BOLD, this.fontSize) );
//		int spacing = ( (int) g2.getFontMetrics().getStringBounds("A", g2).getHeight()) + panel.getVH(2);
//	
//		drawY += (ySize/2) + getTextCenterY(text, g2) - (spacing * (this.text.length()/each));
//		
//		String temp;
//		
//		int lastPrint = 0;
//		int i;
//		
//		for(i=0; i< this.text.length(); i++)
//		{
//			if((i%each) == 0)
//			{
//				temp = this.text.substring(lastPrint,i);
//				drawX = this.x + ((xSize/2)-getTextCenterX(temp, g2));
//				g2.drawString(temp, drawX, drawY);
//				lastPrint = i;
//				drawY += spacing;
//			}
//		}
//		
//		if(lastPrint < this.text.length())
//		{
//			temp = this.text.substring(lastPrint,i);
//			drawY += getTextCenterY(temp, g2);
//			drawX = this.x + ((xSize/2)-getTextCenterX(temp, g2));
//			g2.drawString(temp, drawX, drawY);
//		}
//		
	}
	
	// Mouse Events -----------------------------------------------------------------------------------
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
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
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public boolean mouseInsideObj(MouseEvent e, int borderX, int borderY)
	{
		
		//Graphics2D g2 = (Graphics2D) panel.getGraphics();
		
		/*
		g2.setFont(panel.standard.deriveFont(Font.BOLD, fontSize);
		
		int textSizeX = (int) g2.getFontMetrics().getStringBounds(this.text, g2).getWidth();
		int textSizeY = (int) g2.getFontMetrics().getStringBounds(this.text, g2).getHeight();
		*/
		
		int maxX = xSize + borderX;
		int maxY = ySize + borderY;
		
		int eX = e.getX();
		int eY = e.getY();
		
		/*
		int posXFixer = (int) (( (double) maxX)/2.6);
		int posYFixer = (int) (( (double) maxY)/2.6);
		*/
		
		//System.out.println("textSizeX:" + textSizeX + " | " + "textSizeY:" + textSizeY + " | " + Support.nL + "drawX:" + drawX + " | " + "drawY:" + drawY + " | " + Support.nL + "maxX:" + maxX + " | " + "maxY:" + maxY + " | " + Support.nL + "eX:" + eX + " | " + "eY:" + eY + " | " + Support.nL + Support.nL );
		
		if( ( eX >= (x) && eX <= (x+maxX) ) && ( eY >= (y) && eY <= (y+maxY) ))
		{
			return true;
			//System.out.println("TRUE");
		}
		
		return false;
	}
			
	//?!?			
}