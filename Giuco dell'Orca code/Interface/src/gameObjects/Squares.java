package gameObjects;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.KeyHandler;
import panels.MainPanel;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused") //!!!

public class Squares extends Object implements MouseListener, MouseMotionListener{
	
	// ENUM
	
	public enum SquareType{
		domanda,
		imprevisto;
	}
	
	// VARS
	
	int tileSize;
	SquareType type;
	MainPanel panel;
//	boolean beignClick = false;
	
		// Sprites vars ------------------------------------------
	
	public BufferedImage squareSprites[]; 
	int spriteIndex = 0;
	double lastAnimationTime = 0;
	long animationSpeed;
		
	// CONSTRUCTORS
	
	public Squares(MainPanel panelGivn,SquareType typeGivn, int tileSizeGivn)
	{
		this.panel = panelGivn;
		this.tileSize = tileSizeGivn;
		this.type = typeGivn;
		getObjectImage();
		lastAnimationTime = System.nanoTime();
		
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	// FUNCTIONS
	
		// Get & Set ------------------------------------------
	
	public void setPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
		fixIfOutside();
		centerPosition();
	}
	
	public void setAbsPosition(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int getPosX()
	{
		return this.x;
	}
	
	public int getPosY()
	{
		return this.y;
	}
	
	public void getObjectImage() 
	{
		if(this.type == SquareType.domanda)
		{
			this.squareSprites = new BufferedImage[12];
			this.animationSpeed = (long) extra.Support.convertTime(0.15, "s", "ns");
			
			try {
				squareSprites[0] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_00.png"));
				squareSprites[1] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_01.png"));
				squareSprites[2] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_02.png"));
				squareSprites[3] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_03.png"));
				squareSprites[4] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_04.png"));
				squareSprites[5] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_05.png"));
				squareSprites[6] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_06.png"));
				squareSprites[7] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_07.png"));
				squareSprites[8] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_08.png"));
				squareSprites[9] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_09.png"));
				squareSprites[10] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_10.png"));
				squareSprites[11] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_11.png"));
	
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(this.type == SquareType.imprevisto)
		{
			this.squareSprites = new BufferedImage[7];
			this.animationSpeed = (long) extra.Support.convertTime(0.18, "s", "ns");
			
			try {
				squareSprites[0] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_0.png"));
				squareSprites[1] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_1.png"));
				squareSprites[2] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_2.png"));
				squareSprites[3] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_3.png"));
				squareSprites[4] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_4.png"));
				squareSprites[5] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_5.png"));
				squareSprites[6] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_6.png"));
				
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
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
//		g.setColor(Color.white);
//		g.fillRect(x, y, tileSize, tileSize);
		
		BufferedImage img = squareSprites[spriteIndex];
		
		double curTime = System.nanoTime();
		
		if((curTime - lastAnimationTime) >= animationSpeed)
		{
			spriteIndex = (spriteIndex + 1) % squareSprites.length;
			lastAnimationTime = System.nanoTime();
		}
		
		//(if(mouseInsideObj(panel.getMousePosition()) == true)
		fixIfOutside();
		this.tileSize = panel.getVMIN()*10;
		g2.drawImage(img, x, y,tileSize,tileSize, null);
	}
	
	public void centerPosition()
	{
		x -= (tileSize/2);
		y -= (tileSize/2);
	}
	
	public void fixIfOutside()
	{
		int halfTile = (tileSize/2);
		
		if( x >= (panel.screenWidth - halfTile) )
		{
			x = panel.screenWidth - halfTile;
			//x--;
			//System.out.println("! INSIDE X !");
		}
		
		if( x <= 0)
		{
			x = 0;
			//x--;
			//System.out.println("! INSIDE X !");
		}
		
		//System.out.println("ScreenX = " + panel.screenWidth + " | ObjX = " + x);
		
		if(y >= (panel.screenHeight - halfTile) )
		{
			y = panel.screenHeight - halfTile;
			//y--;
			//System.out.println("! INSIDE Y !");
		}
		
		if(y <= 0)
		{
			y = 0;
			//y--;
			//System.out.println("! INSIDE Y !");
		}
		
		//System.out.println("ScreenY = " + panel.screenHeight + " | ObjY = " + y + Support.nL);
		
	}
	
		// Mouse Events ------------------------------------------
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
//		if(mouseInsideObj(e,0) == true)
//		{
//			beignClick = true;
//			System.out.println("cliecked");
//		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
//		if(beignClick == true)
//		{
//			if(mouseInsideObj(e,(tileSize/4)) == true)
//			{
//				//System.out.println("Inside OBJ |");
//				this.setPosition(e.getX(),e.getY());
//			}
//			
//			System.out.println("cliecked long");
//		}
		
		if(mouseInsideObj(e,(tileSize/4)) == true)
		{
			//System.out.println("Inside OBJ |");
			this.setPosition(e.getX(),e.getY());
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//beignClick = false;
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
