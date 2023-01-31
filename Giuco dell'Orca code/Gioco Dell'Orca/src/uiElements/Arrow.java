package uiElements;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.KeyHandler;
import gameObjects.Squares.Imprevisti;
import panels.MainPanel;

import java.awt.image.BufferedImage;

@SuppressWarnings("unused") //!!!

public class Arrow implements MouseListener, MouseMotionListener{

	// VARS
	
		// Enumerations ------------------------------------------
		
	public enum ArrowAngle{
		right,
		left;
	}
	
		// general ------------------------------------------
	
	private ArrowAngle arrowDirection = ArrowAngle.right;
	int tileSize;
	int x,y;
	MainPanel panel;
	
		// animation & sprite ------------------------------------------
	
	public BufferedImage squareSprites[] = new BufferedImage[12]; 
	int spriteIndex = 0;
	double lastAnimationTime = 0;
	
	// CONSTRUCTORS
	
	public Arrow(MainPanel panelGivn, ArrowAngle givnArrowDirection, int tileSizeGivn)
	{
		this.panel = panelGivn;
		this.tileSize = tileSizeGivn;
		this.arrowDirection = givnArrowDirection;
		//getObjectImage();
		lastAnimationTime = System.nanoTime();
		
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
	}
	
	// FUNCTIONS
	
		// Mouse Events ------------------------------------------
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

}
