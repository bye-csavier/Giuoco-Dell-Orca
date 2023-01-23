package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.Support;
import gameObjects.*;
import panels.MainPanel.PanelStateFlag;

public class Game implements MouseWheelListener{
	
	// VAR ================================
	
		// Panel Vars ------------------------ 
	
	MainPanel panel;
	
	long lastEscPressTime = 0;
	long maxPress = (long) Support.convertTime(0.35, "s", "ns");
	int barProgress = 0;
	String barText = "";
	
	int xCenter;
	int yCenter;
	
		//Game Objects ------------------------
	
	Squares campo[];
	
		//Sprites
	
	public BufferedImage questionSquareSprites[]; 
	public BufferedImage alertSquareSprites[]; 
	
	// CONSTRUCTORS ================================
	
	public Game(MainPanel panelGivn)
	{
		this.panel =  panelGivn;
		xCenter = panel.screenWidth/2;
		yCenter = panel.screenHeight/2;
		
		panel.addMouseWheelListener(this);
		this.setupSprites();
	}
	
	// FUNCTIONS ================================
	
		//Funzioni di alliniamento gioco e GUI ------------------------
	
	private void setupSprites()
	{
		this.questionSquareSprites = new BufferedImage[12];
		
		try {
			questionSquareSprites[0] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_00.png"));
			questionSquareSprites[1] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_01.png"));
			questionSquareSprites[2] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_02.png"));
			questionSquareSprites[3] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_03.png"));
			questionSquareSprites[4] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_04.png"));
			questionSquareSprites[5] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_05.png"));
			questionSquareSprites[6] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_06.png"));
			questionSquareSprites[7] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_07.png"));
			questionSquareSprites[8] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_08.png"));
			questionSquareSprites[9] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_09.png"));
			questionSquareSprites[10] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_10.png"));
			questionSquareSprites[11] = ImageIO.read(getClass().getResourceAsStream("/QuestionSquareSprites/SquareQuestion_11.png"));

		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.alertSquareSprites = new BufferedImage[7];
		
		try {
			alertSquareSprites[0] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_0.png"));
			alertSquareSprites[1] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_1.png"));
			alertSquareSprites[2] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_2.png"));
			alertSquareSprites[3] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_3.png"));
			alertSquareSprites[4] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_4.png"));
			alertSquareSprites[5] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_5.png"));
			alertSquareSprites[6] = ImageIO.read(getClass().getResourceAsStream("/AlertSquareSprites/AlertSquare_6.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setupCampo(int squares)
	{
		int generalSize = 15;
		this.campo = new Squares[squares+2];
		int trueSize = panel.getVMIN(generalSize);
		int fixCenter = panel.getVMIN(0.8);
		
		int breakPoint = panel.getWidth()/trueSize;
		breakPoint++;
		boolean invert = false;
		
		int squareX,squareY;
		squareX = 0;
		squareY = 0;
		
		int shifter = 1;
		
		//System.out.println("trueSize : "+trueSize+ Support.nL +" | breakPoint : "+breakPoint+ Support.nL + "length : " +this.campo.length+ Support.nL +Support.nL);
		
		barText = "Creazione Caselle";
		
		for(int i=0; (i)<this.campo.length; i++)
		{
			if(((i+shifter)%breakPoint) != 0 || i == 0)
			{
				if(invert == false)
				{
					squareX = trueSize*((i+shifter)%breakPoint);
					squareX -= fixCenter;
				}
				else
				{
					squareX = breakPoint*trueSize;
					squareX -= trueSize*((i+shifter)%breakPoint);
					squareX -= fixCenter;
				}
			}
			
			if( ((i+shifter)%breakPoint) == 0 && i != 0)
			{
				if(invert == false)
				{
					invert = true;
				}
				else
				{
					invert = false;
				}
				
				squareY += trueSize;
				this.campo[i] = new Squares(panel,Squares.SquareType.domanda, generalSize, questionSquareSprites, alertSquareSprites,0, i, squareX, squareY);
				squareY += trueSize;
			}
			else
			{
				this.campo[i] = new Squares(panel,Squares.SquareType.domanda, generalSize, questionSquareSprites, alertSquareSprites,0, i, squareX, squareY);
			}
			
			//System.out.println("i : "+i+ Support.nL +"extraIndex : "+ Support.nL);
			
			panel.repaint();
	
			barProgress = Support.map(i,0,this.campo.length,0,78);
		}
		
		this.campo[0].setSprites(alertSquareSprites);
		this.campo[0].setForceSpriteIndex(-1);
		this.campo[campo.length-1].setSprites(alertSquareSprites);
		this.campo[campo.length-1].setForceSpriteIndex(-1);
		barText = "Completamento...";
		Support.wait(200, false);
		barProgress = 80;
		panel.repaint();
	}
	
		// Funzioni di loop GUI ------------------------
	
	public void update() // TODO Gestione gioco qua !!!
	{
		// Setup gioco ------------------------------
		
		if(panel.gameStarted == false)
		{
			setupCampo(panel.settingsPanel.numCaselle);
			panel.gameStarted = true;
		}
		
		// Sempre Attive -----------------------------
		
//		for(int i=0; i < this.campo.length; i++)
//		{
//			
//		}
		
		if(panel.keyLog.escape == true && lastEscPressTime <= 0)
		{
			lastEscPressTime = System.nanoTime();
		}
		else if(lastEscPressTime > 0 && panel.keyLog.escape == false)
		{
			lastEscPressTime = 0;
		}
		else if( (System.nanoTime() - lastEscPressTime) > this.maxPress && lastEscPressTime > 0 ) 
		{
			panel.setGameState(PanelStateFlag.menu);
		}
	}
	
	public void draw(Graphics2D g2)
	{
		if(panel.gameStarted == true)
		{
			
			for(int i=0; i < this.campo.length; i++)
			{
				campo[i].draw(g2);	
			}
			
			if(lastEscPressTime > 0)
			{
				g2.setFont(panel.standard.deriveFont(Font.BOLD, panel.getVW(2)));
				
				long pressTime = System.nanoTime() - lastEscPressTime;
				
				int alpha;
				
				if(pressTime < this.maxPress)
				{
					alpha = (int) Support.map(pressTime, 0, this.maxPress, 0, 255);
				}
				else
				{
					alpha = 255;
				}
				
				double Xpos = 2;
				double Ypos = 4;
				double temp = 0.4;
				
				g2.setColor(new Color(63,43,0,alpha));
				
				g2.drawString("MENU...", panel.getVMIN(Xpos-temp), panel.getVMIN(Ypos+temp));
				
				g2.setColor(new Color(190,129,0,alpha));
				
				g2.drawString("MENU...", panel.getVMIN(Xpos-(temp/2)), panel.getVMIN(Ypos+(temp/2)));
				
				g2.setColor(new Color(255,174,0,alpha));
				
				g2.drawString("MENU...", panel.getVMIN(Xpos), panel.getVMIN(Ypos));
			}
		}
		else
		{
			g2.setFont(panel.standard.deriveFont(Font.BOLD, (panel.getVW(4)) ));
			g2.setColor(panel.whiteA);
			g2.drawString(barText, xCenter-getTextCenterX(barText, g2),yCenter-getTextCenterY(barText, g2)-panel.getVH(5));
			g2.setColor(panel.mainColorA);
			g2.fillRect(panel.getVW(10), yCenter, panel.getVW(barProgress), panel.getVH(1));
		}
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

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		if(panel.getGameState() == PanelStateFlag.gamePlay)
		{
			if(e.getWheelRotation() > 0 && panel.scrolling > ( (-campo[campo.length-1].getAbsPosY()) + (panel.screenHeight-panel.getVMIN(12)) ) ) 
			{
				panel.scrolling -= panel.getVH(3);
			}
			else if(e.getWheelRotation() < 0 && panel.scrolling < panel.getVMIN(8))
			{
				panel.scrolling += panel.getVH(3);
			}
			
			for(int i=0; i < this.campo.length; i++)
			{
				campo[i].setPosition(campo[i].absX, campo[i].absY+panel.scrolling);
				
			}
		}
	}
		
}
