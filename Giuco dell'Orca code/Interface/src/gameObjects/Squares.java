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
import panels.MainPanel;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused") //!!!

public class Squares implements MouseListener, MouseMotionListener{
	
	// ENUM
	
	public enum SquareType{
		domanda,
		imprevisto;
	}
	
	// VARS
	
	int tileSize;
	private int x,y;
	public int absX,absY;
	SquareType type;
	MainPanel panel;
	boolean beignClick = false;
	String squareIndex;
	
		// Sprites vars ------------------------------------------
	
	public BufferedImage squareSprites[]; 
	int spriteIndex = 0;
	double lastAnimationTime = 0;
	long animationSpeed;
		
	// CONSTRUCTORS
	
	public Squares(MainPanel panelGivn,SquareType typeGivn, int tileSizeGivn,BufferedImage questionSquareSprites[], BufferedImage alertSquareSprites[], int squareIndex, int x, int y)
	{
		this.panel = panelGivn;
		this.tileSize = tileSizeGivn;
		this.type = typeGivn;
		this.setAbsPosition(x,y);
		this.setPosition(x,y);
		setupObjectSettings();
		lastAnimationTime = System.nanoTime();
		this.squareIndex = Integer.toString(squareIndex);
		
		if(this.type == SquareType.domanda)
		{
			squareSprites = questionSquareSprites;
		}
		else
		{
			squareSprites = alertSquareSprites;
		}
		
	}
	
	// FUNCTIONS
	
		// Get & Set ------------------------------------------
	
	public void setSprites(BufferedImage sprites[])
	{
		this.squareSprites = sprites;
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
	
	public void setupObjectSettings() 
	{
		if(this.type == SquareType.domanda)
		{	
			this.animationSpeed = (long) extra.Support.convertTime(0.15, "s", "ns");	
		}
		else if(this.type == SquareType.imprevisto)
		{
			this.animationSpeed = (long) extra.Support.convertTime(0.18, "s", "ns");	
		}
		
	}
	
	public void enableDragging()
	{
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	public void disableDragging()
	{
		panel.removeMouseListener(this);
		panel.removeMouseMotionListener(this);
	}
	
		// +++ ------------------------------------------
	
	public void update(int screenWidth, int screenHeight)
	{
//		
//		int moveSpeed = 40;
//		
//		if(keyLog.upPress == true) {
//			
//			y -= moveSpeed;
//			
//			if(y < (0-tileSize) )
//			{
//				y = screenHeight + tileSize; 
//			}
//		}		
//		if(keyLog.downPress == true) {
//			
//			y += moveSpeed;
//			
//			if(y > screenHeight+tileSize)
//			{
//				y = 0 - tileSize; 
//			}
//		}		
//		if(keyLog.leftPress == true) {
//			
//			x -= moveSpeed;
//			
//			if(x < 0-tileSize)
//			{
//				x = screenWidth + tileSize; 
//			}
//		}
//		if(keyLog.rightPress == true) {
//			
//			x += moveSpeed;
//			
//			if(x > screenWidth + tileSize)
//			{
//				x = 0-tileSize; 
//			}
//		}
//		
//		x = (screenWidth / 2 - 30);
//		y = (screenHeight / 2) - 30;
	}
	
	public void draw(Graphics2D g2)
	{
		
		if(this.insideScreen() == true)
		{
			BufferedImage img = squareSprites[spriteIndex];
			
			double curTime = System.nanoTime();
			
			if((curTime - lastAnimationTime) >= animationSpeed)
			{
				spriteIndex = (spriteIndex + 1) % squareSprites.length;
				lastAnimationTime = System.nanoTime();
			}
			
			this.tileSize = panel.getVMIN(10);
			g2.drawImage(img, x, y,tileSize,tileSize, null);
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(1)));
			
			int temp = panel.getVMIN(0.5);
			int shiftX = panel.getVMIN(7.5);
			int shiftY = panel.getVMIN(8.6);
			
			g2.setColor(new Color(63,43,0,150));
			
			g2.drawString(squareIndex, (x-temp+shiftX), (y+temp+shiftY));
			
			g2.setColor(new Color(190,129,0,200));
			
			g2.drawString(squareIndex, (x-(temp/2)+shiftX),(y+(temp/2)+shiftY));
			
			g2.setColor(new Color(255,174,0,255));
			
			g2.drawString(squareIndex, x+shiftX, y+shiftY);
			
		}
		
	}
	
	public void centerPosition()
	{
		x -= (tileSize/2);
		y -= (tileSize/2);
	}
	
	public void fixIfOutside()
	{
		int halfTile = (tileSize/2);
		
		if( this.x >= (panel.screenWidth - halfTile) )
		{
			x = panel.screenWidth - halfTile;
			//x--;
			//System.out.println("! INSIDE X !");
		}
		
		if( this.x <= 0)
		{
			x = 0;
			//x--;
			//System.out.println("! INSIDE X !");
		}
		
		//System.out.println("ScreenX = " + panel.screenWidth + " | ObjX = " + x);
		
		if(this.y >= (panel.screenHeight - halfTile) )
		{
			y = panel.screenHeight - halfTile;
			//y--;
			//System.out.println("! INSIDE Y !");
		}
		
		if(this.y <= 0)
		{
			y = 0;
			//y--;
			//System.out.println("! INSIDE Y !");
		}
		
		//System.out.println("ScreenY = " + panel.screenHeight + " | ObjY = " + y + Support.nL);
		
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
	
		// Mouse Events ------------------------------------------
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(beignClick == true)
		{
			if(mouseInsideObj(e,(tileSize/4)) == true)
			{
				//System.out.println("Inside OBJ |");
				this.setPosition(e.getX(),e.getY());
			}
			
		}
		
//		if(mouseInsideObj(e,(tileSize/4)) == true)
//		{
//			//System.out.println("Inside OBJ |");
//			this.setPosition(e.getX(),e.getY());
//		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(mouseInsideObj(e,0) == true)
		{
			beignClick = true;
			//System.out.println("click");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		beignClick = false;
		//System.out.println("unclick" + Support.nL);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	public boolean mouseInsideObj(MouseEvent e, int borders)
	{
//		int max = tileSize / 2;
//		int eX = e.getX();
//		int eY = e.getY();
//		
//		if( (eX >= (x-max) && eX <= (x+max)) && (eY >= (y-max) && eY <= (y+max)))
//		{
//			return true;
//		}
//		
//		return false;
		
		int max = tileSize + borders;
		int eX = e.getX();
		int eY = e.getY();
		
		if( (eX >= (x) && eX <= (x+max)) && (eY >= (y) && eY <= (y+max)))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	
//?!?	
}
