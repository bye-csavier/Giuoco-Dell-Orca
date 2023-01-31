package uiElements;

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

public class SliderDot implements MouseListener, MouseMotionListener{

	// VARS
	
	int size;
	int x,y;
	MainPanel panel;
	boolean beignClick = false;
	int leftMax;
	int rightMax;
	
		// Sprites vars ------------------------------------------
	
	public BufferedImage sprites[]; 
	int spriteIndex = 0;
	
	// CONSTRUCTORS
	
	public SliderDot(MainPanel panelGivn)
	{
		this.panel = panelGivn;
		this.x = panel.screenWidth/2;
		setupObjectImage();
		
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		
		this.leftMax = panel.getVW(8);
		this.rightMax = panel.getVW(88);
	}
	
	// FUNCTIONS
	
	public void disableListeners() 
	{
		panel.removeMouseListener(this);
		panel.removeMouseMotionListener(this);
	}
	
	public void enableListeners() 
	{
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
		// Set&Get ------------------------------------------
	
	public void setPos(int x,int y)
	{
		this.x = x;
		this.y = y;
		//fixIfOutside();
		centerPosition();
		//setupObjectImage();
	}
	
	public void setX(int x)
	{
		this.x = x;
	
	}
	
	public void setY(int y)
	{
		this.y = y;
		
	}
	
	public void setupObjectImage() 
	{
		this.sprites = new BufferedImage[2];
		
		try {
			sprites[0] = ImageIO.read(getClass().getResourceAsStream("/SliderDotSprites/SliderDot_0.png"));
			sprites[1] = ImageIO.read(getClass().getResourceAsStream("/SliderDotSprites/SliderDot_1.png"));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
		// Draw & Update ------------------------------------------
		
	public void update(int screenWidth, int screenHeight)
	{
 
	}
	
	public void draw(Graphics2D g2)
	{
		this.size = panel.getVMIN(10);
		this.x = Support.clamp(leftMax,this.x,rightMax);
		g2.drawImage(sprites[spriteIndex],x, y,size,size, null);
	}
	
		// Mouse Events ------------------------------------------
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if(beignClick == true)
		{
//			if(mouseInsideObj(e,(size)) == true)
//			{
//				this.setPos(e.getX(),this.y);				
//			}	
			this.setPos(e.getX(),this.y);			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(mouseInsideObj(e,0) == true)
		{
			beignClick = true;
			this.spriteIndex = 1;
			//System.out.println("clicked");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		beignClick = false;
		this.spriteIndex = 0;
		//System.out.println("unclick");
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//		if(beignClick == true)
//		{
//			this.setPos(e.getX(),this.y);
//			System.out.print("inside");
//		}
		//System.out.print(".");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
		// Position Controls -------------------------------------
	
	public boolean mouseInsideObj(MouseEvent e, int borders)
	{

		int max = size + borders;
		int eX = e.getX();
		int eY = e.getY();
		
		if( (eX >= (x-borders) && eX <= (x+max)) && (eY >= (y-borders) && eY <= (y+max)))
		{
			return true;
		}
		
		return false;
	}
	
	public void centerPosition()
	{
		x -= (size/2);
		y -= (size/2);
	}
	
	public void fixIfOutside()
	{
		int halfTile = (size/2);
		
		if( x >= (panel.screenWidth - halfTile) )
		{
			x = panel.screenWidth - halfTile;
		}
		
		if( x <= 0)
		{
			x = 0;
		}
		
		if(y >= (panel.screenHeight - halfTile) )
		{
			y = panel.screenHeight - halfTile;
			
		}
		
		if(y <= 0)
		{
			y = 0;
			
		}
		
	}
	
}
