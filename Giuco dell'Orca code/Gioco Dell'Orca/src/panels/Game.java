package panels;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import extra.Support;
import gameObjects.*;
import gameObjects.Squares.Imprevisti;
import logic.*;
import panels.MainPanel.PanelStateFlag;
import uiElements.*;

@SuppressWarnings("unused") //!!!

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
	
		//Draw Vars ------------------------
	
			// turno player
	
	int xSpacingPlayers;
	int boxXSize;
	int boxYSize;
	
		//Game Objects ------------------------
	
	public Squares campo[];
	public Player players[];
	
	int turno = 0;
	public int triggerSwitchTurno = 0;
	private int turnoReapeter = 1;
	public boolean pause = false; // TODO
	public boolean endGame = false;
	
	public boolean scambioCasella = false;
	public int indexToSwap[] = new int[2];
	
	Question question;
	Domandiere domandiere;
	Dice dadoUI;
	GameInfo tabInfo;
	Fight fight;
	public Alert alert;
	
		//Sprites
	
	public BufferedImage questionSquareSprites[]; 
	public BufferedImage alertSquareSprites[]; 
	public BufferedImage finalSprites[];
	
	public BufferedImage playerASprites[]; 
	public BufferedImage playerBSprites[]; 
	public BufferedImage playerCSprites[]; 
	public BufferedImage playerDSprites[]; 
	
	public BufferedImage numSprites[]; 
	public BufferedImage numGlitchSprites[]; 
	
	// CONSTRUCTORS ================================
	
	public Game(MainPanel panelGivn)
	{
		this.panel =  panelGivn;
		xCenter = panel.screenWidth/2;
		yCenter = panel.screenHeight/2;
		
		panel.addMouseWheelListener(this);
		this.setupSprites();
		
		try {
			domandiere = new Domandiere();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		this.xSpacingPlayers = -panel.getVW(1.2);
		
		question = new Question(panel);
		dadoUI = new Dice(panel, numGlitchSprites, numSprites);
		tabInfo = new GameInfo(panel);
		fight = new Fight(panel);
		alert = new Alert(panel);
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
		
		this.numSprites = new BufferedImage[6];
		
		try {
			numSprites[0] = ImageIO.read(getClass().getResourceAsStream("/Num/Numbers_0.png"));
			numSprites[1] = ImageIO.read(getClass().getResourceAsStream("/Num/Numbers_1.png"));
			numSprites[2] = ImageIO.read(getClass().getResourceAsStream("/Num/Numbers_2.png"));
			numSprites[3] = ImageIO.read(getClass().getResourceAsStream("/Num/Numbers_3.png"));
			numSprites[4] = ImageIO.read(getClass().getResourceAsStream("/Num/Numbers_4.png"));
			numSprites[5] = ImageIO.read(getClass().getResourceAsStream("/Num/Numbers_5.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.numGlitchSprites = new BufferedImage[12];
		
		try {
			numGlitchSprites[0] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_00.png"));
			numGlitchSprites[1] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_01.png"));
			numGlitchSprites[2] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_02.png"));
			numGlitchSprites[3] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_03.png"));
			numGlitchSprites[4] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_04.png"));
			numGlitchSprites[5] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_05.png"));
			numGlitchSprites[6] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_06.png"));
			numGlitchSprites[7] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_07.png"));
			numGlitchSprites[8] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_08.png"));
			numGlitchSprites[9] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_09.png"));
			numGlitchSprites[10] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_10.png"));
			numGlitchSprites[11] = ImageIO.read(getClass().getResourceAsStream("/NumGlitch/NumbersGlitch_11.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.playerASprites = new BufferedImage[14];
		
		try {
			playerASprites[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_00.png"));
			playerASprites[1] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_01.png"));
			playerASprites[2] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_02.png"));
			playerASprites[3] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_03.png"));
			playerASprites[4] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_04.png"));
			playerASprites[5] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_05.png"));
			playerASprites[6] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_06.png"));
			playerASprites[7] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_07.png"));
			playerASprites[8] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_08.png"));
			playerASprites[9] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_09.png"));
			playerASprites[10] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_10.png"));
			playerASprites[11] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_11.png"));
			playerASprites[12] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_12.png"));
			playerASprites[13] = ImageIO.read(getClass().getResourceAsStream("/PlayerA/PlayerA_13.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.playerBSprites = new BufferedImage[14];
		
		try {
			playerBSprites[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_00.png"));
			playerBSprites[1] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_01.png"));
			playerBSprites[2] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_02.png"));
			playerBSprites[3] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_03.png"));
			playerBSprites[4] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_04.png"));
			playerBSprites[5] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_05.png"));
			playerBSprites[6] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_06.png"));
			playerBSprites[7] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_07.png"));
			playerBSprites[8] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_08.png"));
			playerBSprites[9] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_09.png"));
			playerBSprites[10] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_10.png"));
			playerBSprites[11] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_11.png"));
			playerBSprites[12] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_12.png"));
			playerBSprites[13] = ImageIO.read(getClass().getResourceAsStream("/PlayerB/PlayerB_13.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.playerCSprites = new BufferedImage[14];
		
		try {
			playerCSprites[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_00.png"));
			playerCSprites[1] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_01.png"));
			playerCSprites[2] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_02.png"));
			playerCSprites[3] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_03.png"));
			playerCSprites[4] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_04.png"));
			playerCSprites[5] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_05.png"));
			playerCSprites[6] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_06.png"));
			playerCSprites[7] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_07.png"));
			playerCSprites[8] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_08.png"));
			playerCSprites[9] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_09.png"));
			playerCSprites[10] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_10.png"));
			playerCSprites[11] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_11.png"));
			playerCSprites[12] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_12.png"));
			playerCSprites[13] = ImageIO.read(getClass().getResourceAsStream("/PlayerC/PlayerC_13.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.playerDSprites = new BufferedImage[14];
		
		try {
			playerDSprites[0] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_00.png"));
			playerDSprites[1] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_01.png"));
			playerDSprites[2] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_02.png"));
			playerDSprites[3] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_03.png"));
			playerDSprites[4] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_04.png"));
			playerDSprites[5] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_05.png"));
			playerDSprites[6] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_06.png"));
			playerDSprites[7] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_07.png"));
			playerDSprites[8] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_08.png"));
			playerDSprites[9] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_09.png"));
			playerDSprites[10] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_10.png"));
			playerDSprites[11] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_11.png"));
			playerDSprites[12] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_12.png"));
			playerDSprites[13] = ImageIO.read(getClass().getResourceAsStream("/PlayerD/PlayerD_13.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		this.finalSprites = new BufferedImage[2];
		
		try {
			finalSprites[0] = ImageIO.read(getClass().getResourceAsStream("/FinalSprites/finalSprites_0.png"));
			finalSprites[1] = ImageIO.read(getClass().getResourceAsStream("/FinalSprites/finalSprites_1.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setupCampo(int squares, int playersAmt)
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
		
		barText = "Creazione Caselle...";
		
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
				this.campo[i] = new Squares(panel,Squares.Imprevisti.random(), generalSize, 0, i, squareX, squareY);
				
				if(this.campo[i].type == Imprevisti.NORMALE)
				{
					this.campo[i].setSprites(questionSquareSprites);
					this.campo[i].setForceSpriteIndex(0);
				}
				else
				{
					this.campo[i].setSprites(alertSquareSprites);
					this.campo[i].setForceSpriteIndex(-1);
				}
				
				squareY += trueSize;
			}
			else
			{
				this.campo[i] = new Squares(panel,Squares.Imprevisti.random(), generalSize,0, i, squareX, squareY);
				
				if(this.campo[i].type == Imprevisti.NORMALE)
				{
					this.campo[i].setSprites(questionSquareSprites);
					this.campo[i].setForceSpriteIndex(0);
				}
				else
				{
					this.campo[i].setSprites(alertSquareSprites);
					this.campo[i].setForceSpriteIndex(-1);
				}
			}
			
			//System.out.println("i : "+i+ Support.nL +"extraIndex : "+ Support.nL);
			
			panel.repaint();
	
			barProgress = Support.map(i,0,this.campo.length,0,70);
		}
		
		barText = "Setup Giocatori...";
		this.players = new Player[playersAmt];
		this.turnoReapeter = playersAmt;
		generalSize = generalSize-5;
				
		for(int i=0; i<this.players.length; i++)
		{
			players[i] = new Player(panel, generalSize, panel.namesPanel.players[i], -500, 0);
			
			if(i==0)
			{
				players[i].setSprites(playerASprites);
			}
			else if(i==1)
			{
				players[i].setSprites(playerBSprites);
			}
			else if(i==2)
			{
				players[i].setSprites(playerCSprites);
			}
			else if(i==3)
			{
				players[i].setSprites(playerDSprites);
			}
			
			
		}
		
		
		this.campo[0].setSprites(finalSprites[0]);
		this.campo[0].setForceSpriteIndex(0);
		this.campo[campo.length-1].setSprites(finalSprites[1]);
		this.campo[campo.length-1].setForceSpriteIndex(0);
		barText = "Completamento...";
		Support.wait(200, false);
		barProgress = 80;
		panel.repaint();
	}
	
		// Funzioni di gioco
	
	public boolean checkTriggerFight(int index, int skipCase)
	{
		for(int i=0; i<players.length; i++)
		{
			if(index == players[i].squareIndex && i != skipCase)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public int getFighter(int index)
	{
		for(int i=0; i<players.length; i++)
		{
			if(index == players[i].squareIndex && i != turno)
			{
				return i;
			}
		}
		
		return -1;
	}
	
		// Funzioni di loop GUI ------------------------
	
	public void isPaused()
	{
		boolean temp1;
		
		if(turno - 1 < 0)
		{
			temp1 = this.pause = players[turnoReapeter-1].triggerAnimation;
		}
		else
		{
			temp1 = this.pause = players[turno-1].triggerAnimation;
		}
		
		if(temp1 == true || question.show == true || dadoUI.visibility() == true || tabInfo.visibility() == true || fight.visibility() == true || alert.visibility() == true || scambioCasella == true)
		{
			pause = true;
		}
		else
		{
			pause = false;
		}
	}
	
	public void indexToSwapAdd(int index)
	{
		int i;
		
		for(i=0; i<indexToSwap.length; i++)
		{
			if(indexToSwap[i] == -1)
			{
				indexToSwap[i] = index;
				break;
			}
		}
	}
	
	public boolean indexToSwapHasSpace()
	{
		int i;
		
		for(i=(indexToSwap.length-1); i>-1; i--)
		{
			if(indexToSwap[i] == -1)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void indexToSwapRemove(int index)
	{
		int i;
		
		for(i=(indexToSwap.length-1); i>-1; i--)
		{
			if(indexToSwap[i] == index)
			{
				indexToSwap[i] = -1;
				break;
			}
		}
	}
	
	public void scambioCasella()
	{
		scambioCasella = true;
		
		this.indexToSwap[0] = -1;
		this.indexToSwap[1] = -1;
		
		for(int i=0; i < this.campo.length; i++)
		{
			campo[i].enableListeners();
		}
	}
	
	public void update() // TODO Gestione gioco qua !!!
	{
			
		// Setup gioco ------------------------------
		
		if(panel.gameStarted == false)
		{
			setupCampo(panel.settingsPanel.numCaselle, panel.settingsPanel.numGiocatori);
			panel.gameStarted = true;
		}
		
		// Gestion Principale Gioco -----------------------------
		
		// Sempre Attive -----------------------------
		
			// Condizioni di pausa
	
//		for(int i=0; i < this.players.length; i++)
//		{
//			players[i].update();	
//		}
		
			// cheat dado
		
		this.isPaused();
		
		if(panel.keyLog.backSlash == true && (tabInfo.visibility() == false))
		{
			tabInfo.show();
		}
		
		if(panel.keyLog.enter == true)
		{
			if(scambioCasella == true)
			{
				scambioCasella = false;
				
				for(int i=0; i < this.campo.length; i++)
				{
					campo[i].disableListeners();
				}
				
				Imprevisti temp = campo[indexToSwap[0]].type;
				campo[indexToSwap[0]].type = campo[indexToSwap[1]].type;
				
				if(campo[indexToSwap[0]].type == Imprevisti.NORMALE)
				{
					campo[indexToSwap[0]].setSprites(questionSquareSprites);
					campo[indexToSwap[0]].setForceSpriteIndex(0);
				}
				else 
				{
					campo[indexToSwap[0]].setSprites(alertSquareSprites);
					campo[indexToSwap[0]].setForceSpriteIndex(-1);
				}
				
				campo[indexToSwap[1]].type = temp;
				
				if(campo[indexToSwap[1]].type == Imprevisti.NORMALE)
				{
					campo[indexToSwap[1]].setSprites(questionSquareSprites);
					campo[indexToSwap[1]].setForceSpriteIndex(0);
				}
				else 
				{
					campo[indexToSwap[1]].setSprites(alertSquareSprites);
					campo[indexToSwap[1]].setForceSpriteIndex(-1);
				}
				
			}
		}
		
		if(panel.keyLog.space == true && this.pause == false)
		{
			int rand = Support.randInt(1, 6);
			this.dadoUI.setNumIndex(rand-1);
			this.dadoUI.show();
			
			int index = players[turno].squareIndex + rand;
			
			if(index >= campo.length)
			{
				index = (campo.length-1) - (index%(campo.length-1));
			}
			//System.out.println(turno);
			if(checkTriggerFight(index,turno) == true)
			{
				fight.show();
				fight.setInfo(turno,getFighter(index));
			}
			else if(index == (campo.length-1) && players[turno].getPoints() <= 0)
			{
				players[turno].setSquareIndex(0);
				players[turno].moveTo(campo[0].getPosX(),campo[0].getPosY(),true);
			}
			else
			{
				players[turno].setSquareIndex(index);
				players[turno].moveTo(campo[index].getPosX(),campo[index].getPosY(),true);
				
				if(campo[index].type == Imprevisti.NORMALE)
				{
					question.show();
					
					if(index != (campo.length-1))
					{
						question.setQuestion(domandiere.random(),turno);
					}
					else
					{
						Domanda finale = new Domanda();
						finale.setDomanda("Chi ha vinto il Giuoco Dell'orca?");
						
						for(int i=0; i<players.length; i++)
						{
							finale.aggiungiRisposta(players[i].playerName, i);
						}
						
						int winner = 0;
						int max = 0;
						
						for(int i=0; i<players.length; i++)
						{
							if(players[i].getPoints() > max)
							{
								max = players[i].getPoints();
								winner = i;
							}
						}
						
						finale.setIndiceCorretta(winner);
						
						this.endGame = true;
						question.setQuestion(finale,turno);		
					}
				}
				else
				{
					players[turno].setImprevisto(campo[index].type);
				}
			}
			
			turno++;
			turno = turno % turnoReapeter;
			
			Support.wait(75, false);
		}
		
		if(panel.keyLog.ctrl == true && this.pause == false)
		{
			if(panel.keyLog.numPressed() == true)
			{
				int negative = 1;
				int num = 0;
				
				if(panel.keyLog.shift == true)
				{
					negative = -1;
				}
				
				if(panel.keyLog.n0 == true)
				{
				
				}
				else if(panel.keyLog.n1 == true)
				{
					num = 1;
				}
				else if(panel.keyLog.n2 == true)
				{
					num = 2;
				}
				else if(panel.keyLog.n3 == true)
				{
					num = 3;
				}
				else if(panel.keyLog.n4 == true)
				{
					num = 4;
				}
				else if(panel.keyLog.n5 == true)
				{
					num = 5;
				}
				else if(panel.keyLog.n6 == true)
				{
					num = 6;
				}
				else if(panel.keyLog.n7 == true)
				{
					num = 7;
				}
				else if(panel.keyLog.n8 == true)
				{
					num = 8;
				}
				else if(panel.keyLog.n9 == true)
				{
					num = 9;
				}
				//------------------------------------------------------------
				int index = players[turno].squareIndex + (num*negative);
				
				if(index >= campo.length)
				{
					index = (campo.length-1) - (index%(campo.length-1));
				}
				//System.out.println(turno);
				if(checkTriggerFight(index,turno) == true)
				{
					fight.show();
					fight.setInfo(turno,getFighter(index));
				}
				else
				{
					players[turno].setSquareIndex(index);
					players[turno].moveTo(campo[index].getPosX(),campo[index].getPosY(),true);
					
					if(campo[index].type == Imprevisti.NORMALE)
					{
						question.show();
						question.setQuestion(domandiere.random(),turno);
					}
					else
					{
						players[turno].setImprevisto(campo[index].type);
					}
				}
				
				turno++;
				turno = turno % turnoReapeter;
				
				Support.wait(75, false);
				
			}
		}
		
			// altro
		
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
	
	public void draw(Graphics2D g2) // TODO
	{
		
		if(panel.gameStarted == true)
		{
			
			for(int i=0; i < this.campo.length; i++)
			{
				campo[i].draw(g2);	
			}
			
			for(int i=0; i < this.players.length; i++)
			{
				players[i].draw(g2);	
			}
			
			dadoUI.draw(g2);
			question.draw(g2);
			alert.draw(g2);
			fight.draw(g2);
			tabInfo.draw(g2);
			
			// player turn;
			
			if(this.pause == false)
			{
				triggerSwitchTurno = turno;
			}
			
			int playerSpriteSize = panel.getVW(6);
			this.boxXSize = (players.length * (xSpacingPlayers + playerSpriteSize)) + (-Support.percentage(xSpacingPlayers, 40));
			this.boxYSize = (playerSpriteSize/2) + panel.getVH(1.3);
			
			g2.setColor(new Color(10,10,10, 190));
			g2.fillRect(0, panel.getHeight() - boxYSize, boxXSize, boxYSize);
			
			int xTemp = xSpacingPlayers;
			int addY = 0;
			for(int i=0; i<players.length; i++)
			{
				if(i != triggerSwitchTurno)
				{
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
					addY = 0;
				}
				else
				{
					g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
					addY = 0;
					addY = panel.getVH(0.85);
				}
				
				if(i==0)
				{
					g2.drawImage(this.playerASprites[0], xTemp, panel.getHeight()-(playerSpriteSize/2)-addY,playerSpriteSize,playerSpriteSize, null);
				}
				else if(i==1)
				{
					g2.drawImage(this.playerBSprites[0], xTemp, panel.getHeight()-(playerSpriteSize/2)-addY,playerSpriteSize,playerSpriteSize, null);
				}
				else if(i==2)
				{
					g2.drawImage(this.playerCSprites[0], xTemp, panel.getHeight()-(playerSpriteSize/2)-addY,playerSpriteSize,playerSpriteSize, null);
				}
				else if(i==3)
				{
					g2.drawImage(this.playerDSprites[0], xTemp, panel.getHeight()-(playerSpriteSize/2)-addY,playerSpriteSize,playerSpriteSize, null);
				}
				
				xTemp += xSpacingPlayers + playerSpriteSize;
				
			}
			
			this.theEnd.draw(g2);
			
			//Exit
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
				
				// Overlays
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
		
		if( (panel.getGameState() == PanelStateFlag.gamePlay && this.pause == false) || panel.gamePanel.scambioCasella == true)
		{
			if(e.getWheelRotation() > 0 && panel.scrolling > ( (-campo[campo.length-1].getAbsPosY()) + (panel.screenHeight-panel.getVMIN(campo[0].scale+(campo[0].scale/2))) ) ) 
			{
				panel.scrolling -= panel.getVH(3);
			}
			else if(e.getWheelRotation() < 0 && panel.scrolling < panel.getVMIN(campo[0].scale))
			{
				panel.scrolling += panel.getVH(3);
			}
			
			for(int i=0; i < this.campo.length; i++)
			{
				campo[i].setPosition(campo[i].absX, campo[i].absY+panel.scrolling);
			}
			
			for(int i=0; i < this.players.length; i++)
			{
				players[i].moveTo(campo[players[i].squareIndex].getPosX(),campo[players[i].squareIndex].getPosY(),false);
			}
			
		}
	}
		
}
