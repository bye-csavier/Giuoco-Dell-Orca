package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	
	// VAR ================================
	
		//Screen settings ------------------------
	
			// Sizes
	
			final int ogTileSize = 16; // square ?x?
			final int scale = 3;
			final int tileSize = ogTileSize * scale;
			
			final int maxScreenX = 16;
			final int maxScreenY = 12;
			final int screenWidth = tileSize * maxScreenX;
			final int screenHeight = tileSize * maxScreenY;
	
		//Thread ------------------------
	
		Thread gameThread;
		
		//Key Handler ------------------------
		
		KeyHandler keyLog = new KeyHandler();
		
		//Other Vars ------------------------
		
		final int FPS = 60;
		
		int playerX = 100;
		int playerY = 100;
		
		private int moveSpeed = 20;
		
		final String nL= System.getProperty("line.separator");
		
	// CONSTRUCTORS ================================
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyLog);		
		this.setFocusable(true);
	}
	
	// FUNCTIONS ================================
	
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
//		long timer = 0;
//		int drawCount = 0;
		
		while(gameThread != null) {
			
			curTime = System.nanoTime();
			delta += (curTime - lastTime) / drawInterval;
//			timer += (curTime - lastTime);
			lastTime = curTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
//				drawCount++;
			}
			
//			if(timer >= 1000000000)
//			{
//				System.out.println(nL + nL + nL + nL + nL);
//				System.out.println("FPS | "+ drawCount + nL + nL);
//				drawCount = 0;
//				timer = 0;
//			}
			
		}
	}
	
	public void update() {
		
		if(keyLog.upPress == true) {
			
			playerY -= moveSpeed;
			
			if(playerY < (0-tileSize) )
			{
				playerY = screenHeight + tileSize; 
			}
		}		
		if(keyLog.downPress == true) {
			
			playerY += moveSpeed;
			
			if(playerY > screenHeight+tileSize)
			{
				playerY = 0 - tileSize; 
			}
		}		
		if(keyLog.leftPress == true) {
			
			playerX -= moveSpeed;
			
			if(playerX < 0-tileSize)
			{
				playerX = screenWidth + tileSize; 
			}
		}
		if(keyLog.rightPress == true) {
			
			playerX += moveSpeed;
			
			if(playerX > screenWidth + tileSize)
			{
				playerX = 0-tileSize; 
			}
		}
		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}
	
}
