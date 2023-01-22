package executor;

//import java.awt.GridLayout;

import javax.swing.JFrame;
import java.awt.Toolkit;
import panels.MainPanel;

public class Main {
	
	public static void main(String[] args)
	{
		
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setResizable(true);
		window.setTitle("Gioco Dell'Orca");
		window.setUndecorated(true);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit(); 
		int xSize = (int) toolkit.getScreenSize().getWidth();
		int ySize = (int) toolkit.getScreenSize().getHeight();
		
		window.setSize(xSize,ySize);
		
		MainPanel gamePanel = new MainPanel(xSize,ySize);
		window.add(gamePanel);
		
//		window.setLayout(new GridLayout());
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
			
	}
	
}
