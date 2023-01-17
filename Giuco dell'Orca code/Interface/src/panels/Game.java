package panels;

import java.awt.Graphics2D;

import gameObjects.*;

public class Game {
	
	// VAR ================================
	
		// Panel Vars ------------------------ 
	
	MainPanel panel;
	
		//Game Objects ------------------------
	
	Squares campo[] = new Squares[2];
	
	// CONSTRUCTORS ================================
	
	public Game(MainPanel panelGivn)
	{
		this.panel =  panelGivn;
		setupCampo(panel.getVMIN() * 15);
	}
	
	// FUNCTIONS ================================
	
		//Funzioni di alliniamento gioco e GUI ------------------------
	
	private void setupCampo(int size)
	{
		this.campo[0] = new Squares(panel,Squares.SquareType.domanda, size );
		this.campo[0].setPosition(100, 100);
		
		this.campo[1] = new Squares(panel,Squares.SquareType.imprevisto, size );
		this.campo[1].setPosition(panel.getHeight()/2, panel.getWidth()/2);
	}
	
		// Funzioni di loop GUI ------------------------
	
	public void update()
	{
		for(int i=0; i < this.campo.length; i++)
		{
			campo[i].update(panel.screenWidth,panel.screenHeight);	
		}	
	}
	
	public void draw(Graphics2D g2)
	{
		for(int i=0; i < this.campo.length; i++)
		{
			campo[i].draw(g2);	
		}
	}
		
}
