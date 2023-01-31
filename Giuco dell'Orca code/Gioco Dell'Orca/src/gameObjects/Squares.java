package gameObjects;

import java.awt.BasicStroke;
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
	
	public enum Imprevisti {
		NORMALE,
		PUNTIDOPPI,
		PUNTIMEZZI,
		AVANZA,
		INDIETREGGIA;
		
		public static Imprevisti random()
		{
			int rand = Support.randInt(0, 100);
			int range = 30;
			range /= 2;
			
			if(rand < (50-range) || rand > (50+range))
			{
				return Imprevisti.NORMALE;
			}
			else
			{
				return randomImp();
			}
			
		}
		
		public static Imprevisti randomImp()
		{
			int rand = Support.randInt(1, 4);
			
			switch(rand)
			{
				
				case 1:{
					return Imprevisti.PUNTIDOPPI;
				}
				
				case 2:{
					return Imprevisti.PUNTIMEZZI;
				}
				
				case 3:{
					return Imprevisti.AVANZA;
				}
				
				case 4:{
					return Imprevisti.INDIETREGGIA;
				}
			}
			
			return null;
		}
		
		public String toString()
		{
			switch(this)
			{
				
				case PUNTIDOPPI:{
					return "PUNTIDOPPI";
				}
				
				case PUNTIMEZZI:{
					return "PUNTIMEZZI";
				}
				
				case AVANZA:{
					return "AVANZA";
				}
				
				case INDIETREGGIA:{
					return "INDIETREGGIA";
				}
				
				default:{
					return "none";
				}
			}
		}
	}

	
	// VARS
	
	int tileSize;
	private int x,y;
	public int absX,absY;
	public Imprevisti type;
	MainPanel panel;
	boolean beignClick = false;
	public int scale;
	String squareIndex;
	boolean overlay = false;
	
		// Sprites vars ------------------------------------------
	
	public BufferedImage squareSprites[]; 
	int spriteIndex = 0;
	double lastAnimationTime = 0;
	long animationSpeed;
	int forceSpriteIndex = -1;
		
	// CONSTRUCTORS
	
	public Squares(MainPanel panelGivn,Imprevisti typeGivn, int tileSizeGivn, int forceSpriteIndex, int squareIndex, int x, int y)
	{
		this.panel = panelGivn;
		this.scale = tileSizeGivn;
		this.tileSize = panel.getVMIN(scale);
		this.type = typeGivn;
		this.setAbsPosition(x,y);
		this.setPosition(x,y);
		setupObjectSettings();
		lastAnimationTime = System.nanoTime();
		this.squareIndex = Integer.toString(squareIndex);
		this.forceSpriteIndex = forceSpriteIndex;
		
	}
	
	// FUNCTIONS
	
	public void disableListeners() 
	{
		panel.removeMouseListener(this);
		panel.removeMouseMotionListener(this);
		this.beignClick = false;
	}
	
	public void enableListeners() 
	{
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
		// Get & Set ------------------------------------------
	
	public void setSprites(BufferedImage sprites[])
	{
		this.squareSprites = sprites;
	}
	
	public void setSprites(BufferedImage sprite)
	{
		this.squareSprites = new BufferedImage[1];
		this.squareSprites[0] = sprite;
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
	
	public void setForceSpriteIndex(int val)
	{
		this.forceSpriteIndex = val;
	}
	
	public void setupObjectSettings() 
	{
		if(this.type == Squares.Imprevisti.NORMALE)
		{	
			this.animationSpeed = (long) extra.Support.convertTime(0.15, "s", "ns");	
		}
		else
		{
			this.animationSpeed = (long) extra.Support.convertTime(0.18, "s", "ns");	
		}
		
	}
	
	public void enableOverlay()
	{
		this.overlay = true;
	}
	
	public void disableOverlay()
	{
		this.overlay = false;
	}
	
		// +++ ------------------------------------------
	
	public void update(int screenWidth, int screenHeight)
	{
		
	}
	
	public void draw(Graphics2D g2)
	{
		
		if(this.insideScreen() == true)
		{
			if(this.forceSpriteIndex >= 0)
			{
				BufferedImage img = squareSprites[forceSpriteIndex];
				
				double curTime = System.nanoTime();
				
				if((curTime - lastAnimationTime) >= animationSpeed)
				{
					spriteIndex = (spriteIndex + 1) % squareSprites.length;
					lastAnimationTime = System.nanoTime();
				}
				
				this.tileSize = panel.getVMIN(scale);
				g2.drawImage(img, x, y,tileSize,tileSize, null);
			}
			else 
			{
				BufferedImage img = squareSprites[spriteIndex];
				
				double curTime = System.nanoTime();
				
				if((curTime - lastAnimationTime) >= animationSpeed)
				{
					spriteIndex = (spriteIndex + 1) % squareSprites.length;
					lastAnimationTime = System.nanoTime();
				}
				
				this.tileSize = panel.getVMIN(scale);
				g2.drawImage(img, x, y,tileSize,tileSize, null);
			}
			
			g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(panel.clamp(1, scale/10, 4))));
			
			int temp = panel.getVMIN(scale*0.05);
			int shiftX = panel.getVMIN(scale*0.75);
			int shiftY = panel.getVMIN(scale*0.86);
			
			g2.setColor(new Color(63,43,0,150));
			
			g2.drawString(squareIndex, (x-temp+shiftX), (y+temp+shiftY));
			
			g2.setColor(new Color(190,129,0,200));
			
			g2.drawString(squareIndex, (x-(temp/2)+shiftX),(y+(temp/2)+shiftY));
			
			g2.setColor(new Color(255,174,0,255));
			
			g2.drawString(squareIndex, x+shiftX, y+shiftY);
			
			if(this.overlay == true)
			{
				g2.setColor(new Color(0,0,0,100));
				g2.fillRect(x, y, tileSize, tileSize);
			}
			
			if(panel.gamePanel.scambioCasella == true && beignClick == true)
			{
				g2.setColor(new Color(255,0,0,230));
				g2.setStroke(new BasicStroke(5));
				g2.drawRect(x, y,tileSize,tileSize);
			}
			
		}
		
	}
	
	public void centerPosition()
	{
		x -= (tileSize/2);
		y -= (tileSize/2);
	}
	
	public int getCenterX()
	{
		return (x - (tileSize/2));
	}
	
	public int getCenterY()
	{
		return (y - (tileSize/2));
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
		
		int curIndex = Integer.valueOf(this.squareIndex);
		
		if(mouseInsideObj(e,0) == true && curIndex != 0 && curIndex != (panel.gamePanel.campo.length -1))
		{
			if(panel.gamePanel.indexToSwapHasSpace() == true)
			{
				if(beignClick == false)
				{
					beignClick = true;
					panel.gamePanel.indexToSwapAdd(curIndex);
				}
				else
				{
					beignClick = false;
					panel.gamePanel.indexToSwapRemove(curIndex);
				}
			}
			else
			{
				if(beignClick == true)
				{
					beignClick = false;
					panel.gamePanel.indexToSwapRemove( Integer.valueOf(this.squareIndex) );
				}
			}
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		
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
	public void mouseMoved(MouseEvent e) {
		
	}
	
//	
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		
//	}
//	
//	@Override
//	public void mouseDragged(MouseEvent e)
//	{
//		if(beignClick == true)
//		{
//			if(mouseInsideObj(e,(tileSize/4)) == true)
//			{
//				//System.out.println("Inside OBJ |");
//				this.setPosition(e.getX(),e.getY());
//			}
//			
//		}
//		
////		if(mouseInsideObj(e,(tileSize/4)) == true)
////		{
////			//System.out.println("Inside OBJ |");
////			this.setPosition(e.getX(),e.getY());
////		}
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		if(mouseInsideObj(e,0) == true)
//		{
//			beignClick = true;
//			//System.out.println("click");
//		}
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		beignClick = false;
//		//System.out.println("unclick" + Support.nL);
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		
//	}
//	
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		
//	}
//	
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

	
//?!?	
}
