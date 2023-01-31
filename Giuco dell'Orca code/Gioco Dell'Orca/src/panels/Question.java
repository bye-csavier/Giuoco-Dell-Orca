package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import logic.*;
import extra.Support;
import panels.MainPanel.PanelStateFlag;
import uiElements.*;

@SuppressWarnings("unused")

public class Question {
	
	// VAR ================================
	
		// Panel Vars ------------------------ 
	
	MainPanel panel;
	boolean show;
		
		// Question Vars ------------------------ 
	
	int rightAns = -1;
	int rightAnsPoints = 1;
	int indexToAct;
	
		// Animation Variables ------------------------
	
	long inTime = (long) extra.Support.convertTime(0.6, "s", "ns");
	long outTime = (long) extra.Support.convertTime(0.7, "s", "ns");
	double inTimeLog;
	double outTimeLog;
	int aniProgress = 0;
	
		// UI Elements ------------------------
	
	QuestionBox question;
	
	AnswerBox qA;
	AnswerBox qB;
	AnswerBox qC;
	AnswerBox qD;
	
	// CONSTRUCTORS
	
	public Question(MainPanel panelGivn)//TODO
	{
		this.panel = panelGivn;
		
		double qBoxShiftX = 3;
		double qBoxShiftY = 2.5;
		int qBoxX = 100-( (int) (qBoxShiftX*2));
		int qBoxY = 50;
		
		int ansBoxX = qBoxX/2;
		int ansBoxY = 20;
		double ansBoxShiftX = 2;
		double ansBoxShiftY = 2;
		ansBoxX -= ansBoxShiftX/2;
		ansBoxY -= ansBoxShiftY/2;
				
		this.question = new QuestionBox(panel);
		this.question.setPos(panel.getVW(qBoxShiftX), panel.getVH(qBoxShiftY));
		this.question.setSize(panel.getVW(qBoxX), panel.getVH(qBoxY));
		
		this.qA = new AnswerBox(panel); // top left
		this.qA.setPos(panel.getVW( qBoxShiftX ), panel.getVH( qBoxY + qBoxShiftY + (ansBoxShiftY*2) ));
		this.qA.setSize(panel.getVW( ansBoxX ), panel.getVH( ansBoxY ));
		
		this.qB = new AnswerBox(panel); // top right
		this.qB.setPos(panel.getVW( ansBoxX + qBoxShiftX + ansBoxShiftX ), panel.getVH( qBoxY + qBoxShiftY + (ansBoxShiftY*2) ));
		this.qB.setSize(panel.getVW( ansBoxX ), panel.getVH( ansBoxY ));
		
		this.qC = new AnswerBox(panel); // bottom left
		this.qC.setPos(panel.getVW( qBoxShiftX ), panel.getVH( qBoxY + qBoxShiftY + (ansBoxShiftY*2) + ansBoxY + ansBoxShiftY ));
		this.qC.setSize(panel.getVW(ansBoxX), panel.getVH(ansBoxY));
		
		this.qD = new AnswerBox(panel); // bottom right
		this.qD.setPos(panel.getVW( ansBoxX + qBoxShiftX + ansBoxShiftX ), panel.getVH( qBoxY + qBoxShiftY + (ansBoxShiftY*2) + ansBoxY + ansBoxShiftY ));
		this.qD.setSize(panel.getVW(ansBoxX), panel.getVH(ansBoxY));
	}
	
	// FUNCTIONS ================================
	
		// +++ ------------------------
	
	public void reset()
	{
		this.outTimeLog = 0;
		this.inTimeLog = 0;
		this.question.shiftY = 0;
		this.qA.shiftX = 0;
		this.qB.shiftX = 0;
		this.qC.shiftX = 0;
		this.qD.shiftX = 0;
		this.aniProgress = 0;
	}
	
	public void setQuestion(Domanda obj, int index)
	{
		this.reset();
		question.setText(obj.getDomanda());
		this.indexToAct = index;
		
		qA.setText(obj.getRisposte()[0]);
		qB.setText(obj.getRisposte()[1]);
		qC.setText(obj.getRisposte()[2]);
		qD.setText(obj.getRisposte()[3]);
		
		qA.reset();
		qB.reset();
		qC.reset();
		qD.reset();
		
		this.rightAns = obj.getIndiceCorretta();
		
		if(this.rightAns == 1)
		{
			qA.switchRight();
		}
		else if(this.rightAns == 2)
		{
			qB.switchRight();
		}
		else if(this.rightAns == 3)
		{
			qC.switchRight();
		}
		else if(this.rightAns == 4)
		{
			qD.switchRight();
		}
		
	}
	
	public int isAnswered()
	{
		if(qA.getAnswred() == true)
		{
			if(qA.isRight() == true)
			{
				return this.rightAnsPoints;
			}
			else
			{
				return 0;
			}
		}
		
		if(qB.getAnswred() == true)
		{
			if(qB.isRight() == true)
			{
				return this.rightAnsPoints;
			}
			else
			{
				return 0;
			}
		}
		
		if(qC.getAnswred() == true)
		{
			if(qC.isRight() == true)
			{
				return this.rightAnsPoints;
			}
			else
			{
				return 0;
			}
		}
		
		if(qD.getAnswred() == true)
		{
			if(qD.isRight() == true)
			{
				return this.rightAnsPoints;
			}
			else
			{
				return 0;
			}
		}
		
		return -1;
	}
	
	public void show()
	{
		this.show = true;
	}
	
	public void hide()
	{
		this.show = false;
	}
	
	public boolean visibility()
	{
		return this.show;
	}
	
	public void disableListeners() 
	{
		qA.disableListeners();
		qB.disableListeners();
		qC.disableListeners();
		qD.disableListeners();
	}
	
	public void enableListeners() 
	{
		
	}
	
		// Scale & Position calculators ------------------------
	
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
		
		// Funzioni di loop GUI ------------------------
	
	public void update()
	{
		
	}
	
	public void draw(Graphics2D g2) // TODO
	{
		
		if(this.show == true && panel.gamePanel.players[panel.gamePanel.triggerSwitchTurno].triggerAnimation == false && panel.gamePanel.dadoUI.visibility() == false)
		{
			
			//System.out.println((System.nanoTime() - this.inTimeLog) + " | " + this.inTimeLog);
			
			if(this.inTimeLog <= 0)
			{
				this.inTimeLog = System.nanoTime();
			}
			
			if((System.nanoTime() - this.inTimeLog) <= this.inTime)
			{
				this.aniProgress = (int) Support.map((System.nanoTime() - this.inTimeLog), 0, this.inTime, 0, 100);
				//System.out.println(aniProgress + "!");
				this.aniProgress = Support.clamp(0, this.aniProgress, 95);
				this.question.shiftY = - (Support.map(this.aniProgress, 0, 95, this.question.getSizeY()+panel.getVH(8) , 0));
				
				this.qA.shiftX = - (Support.map(this.aniProgress, 0, 95, this.question.getSizeX(), 0));
				this.qC.shiftX = - (Support.map(this.aniProgress, 0, 95, this.question.getSizeX(), 0));
				this.qB.shiftX = Support.map(this.aniProgress, 0, 95, this.question.getSizeX(), 0);
				this.qD.shiftX = Support.map(this.aniProgress, 0, 95, this.question.getSizeX(), 0);
				
//				System.out.println("shiftX A:" + this.qA.shiftX );
//				System.out.println("shiftX B:" + this.qB.shiftX );
//				System.out.println("shiftX C:" + this.qC.shiftX );
//				System.out.println("shiftX D:" + this.qD.shiftX );
			}
			
			question.draw(g2);
			qA.draw(g2);
			qB.draw(g2);
			qC.draw(g2);
			qD.draw(g2);
			
			if(this.isAnswered() >= 0)
			{
				if(outTimeLog == 0)
				{
					outTimeLog = System.nanoTime();
				}
				
				if((System.nanoTime()-this.outTimeLog) > this.outTime)
				{
					panel.gamePanel.players[indexToAct].addPoints(this.isAnswered());
					
					if(this.isAnswered() > 0)
					{
						panel.gamePanel.scambioCasella();
					}
					
					if(panel.gamePanel.endGame == true)
					{
						panel.gamePanel.theEnd.show();
					}
					
					this.disableListeners();
					this.hide();
				}
				else
				{
					this.aniProgress = (int) Support.map((System.nanoTime() - this.outTimeLog), 0, this.outTime, 100,0);
					//System.out.print(aniProgress + "!!");
					this.question.shiftY = - Support.map(this.aniProgress, 0, 100, this.question.getSizeY()+panel.getVH(8) , 0);
					
					this.qA.shiftX = - Support.map(this.aniProgress, 0, 100, this.question.getSizeX()+panel.getVW(15) , 0);
					this.qC.shiftX = - Support.map(this.aniProgress, 0, 100, this.question.getSizeX()+panel.getVW(15) , 0);
					this.qB.shiftX = Support.map(this.aniProgress, 0, 100, this.question.getSizeX()+panel.getVW(15) , 0);
					this.qD.shiftX = Support.map(this.aniProgress, 0, 100, this.question.getSizeX()+panel.getVW(15) , 0);
				}
				
			}
			
		}
	}
		
}
