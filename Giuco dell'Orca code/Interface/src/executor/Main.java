package executor;

//import java.awt.GridLayout;

import javax.swing.JFrame;

import panels.MainPanel;

public class Main {
	
	public static void main(String[] args)
	{
		
		JFrame window = new JFrame();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("Interface test");
		
		MainPanel gamePanel = new MainPanel();
		window.add(gamePanel);
		
//		window.setLayout(new GridLayout());
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
	
}
