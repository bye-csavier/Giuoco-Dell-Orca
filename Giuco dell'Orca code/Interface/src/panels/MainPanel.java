package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import java.io.IOException;
import java.io.InputStream;

import extra.KeyHandler;
import extra.Support;
import gameObjects.*;

@SuppressWarnings("unused") //!!!

public class MainPanel extends JPanel implements Runnable,MouseListener {
	
	// VAR ================================
	
		//Enumerations ------------------------
	
		public enum PanelStateFlag{
			menu,
			menuGameSettings,
			gamePlay;
		}
		
		//Screen settings ------------------------
	
			// Sizes
	
			public final int tileSize = 48; // square 32 * 1.5 = 48
			
			final int maxScreenX = 16;
			final int maxScreenY = 12;
			public int screenWidth = tileSize * maxScreenX;
			public int screenHeight = tileSize * maxScreenY;
			
		//Thread ------------------------
	
		Thread gameThread;
		
		//Key Handler ------------------------
		
		KeyHandler keyLog = new KeyHandler();
		
		//Other Vars ------------------------
		
		final int FPS = 60;
		private PanelStateFlag panelStatus = PanelStateFlag.menu;
		
		Font maruMonica, minecraft;
		
		//Game Panels ------------------------
		
		Game gamePanel;
		Menu menuPanel;
		
		
	// CONSTRUCTORS ================================
	
	public MainPanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyLog);		
		this.setFocusable(true);
		
		// Fonts
		
		try
		{
			InputStream is = getClass().getResourceAsStream("/Fonts/MaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
			
			is = getClass().getResourceAsStream("/Fonts/Minecraft.ttf");
			minecraft = Font.createFont(Font.TRUETYPE_FONT, is);
			
		}
		catch(FontFormatException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		// Panels
		
		gamePanel = new Game(this);
		menuPanel = new Menu(this);
		
	}
	
	// FUNCTIONS ================================
	
		//Panel Related ------------------------
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
//	@Override
//	public void run()
//	{
//		
//		double nextDrawTime = System.nanoTime() + drawInterval;
//		
//		while(gameThread != null) {
//			
//			update();
//			
//			repaint();
//			
//			
//			try {
//				
//				double remainingTime = nextDrawTime - System.nanoTime();	
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch(InterruptedException e) {
//				
//				e.printStackTrace();
//			}
//			
//			
//		}
//	}
	
	@Override
	public void run()
	{
		final double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long curTime;
		
		// for displayFPS vvv
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			curTime = System.nanoTime();
			delta += (curTime - lastTime) / drawInterval;
			timer += (curTime - lastTime);
			lastTime = curTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount += 10;
				
				this.screenWidth = this.getWidth();
				//this.screenHeight = (this.getHeight() * 10218)/10000;
				this.screenHeight = this.getHeight();	
			}
			
			if(timer >= 100000000)
			{
//				System.out.println(Support.newLines(3));
//				System.out.println("X = " + this.screenWidth + " / " + (this.screenWidth/2) );
//				System.out.println("Y = " + this.screenHeight  + " / " + (this.screenHeight/2));
//				System.out.println("FPS | "+ drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
		if(panelStatus == PanelStateFlag.menu)
		{
			menuPanel.update();
		}
		else if(panelStatus == PanelStateFlag.menuGameSettings)
		{
			
		}
		else if(panelStatus == PanelStateFlag.gamePlay)
		{
			gamePanel.update();
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		//--------------------------------
		
		if(panelStatus == PanelStateFlag.menu)
		{
			menuPanel.draw(g2);
		}
		else if(panelStatus == PanelStateFlag.menuGameSettings)
		{
			
		}
		else if(panelStatus == PanelStateFlag.gamePlay)
		{
			gamePanel.draw(g2);
		}
		
		//--------------------------------
		g2.dispose();
	}
	
		// Gestione Stato Panello ------------------------ 
	
	public void setGameState(PanelStateFlag givnState)
	{
		this.panelStatus = givnState;
	}
	
		//Responsive Sizes ------------------------ 
	
	public int getVW()
	{
		return (screenWidth/100);
	}
	
	public int getVH()
	{
		return (screenHeight/100);
	}
	
	public int getVMIN()
	{
		if(screenWidth>screenHeight)
		{
			return (screenHeight/100);
		}
		else
		{
			return (screenWidth/100);
		}
	}
	
	public int getVMAX()
	{
		if(screenWidth>screenHeight)
		{
			return (screenWidth/100);
		}
		else
		{
			return (screenHeight/100);
		}
	}
	
		//Mouse Handlers ------------------------ 
	
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
	
}
