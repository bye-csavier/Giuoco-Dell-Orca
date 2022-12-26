package test;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
	
	// KEY VAR
	public boolean upPress; // W
	public boolean downPress; // S
	public boolean leftPress; // A
	public boolean rightPress; // D
	
	// FUNCTIONS
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		//System.out.println("keyPressed " + keyCode);
		
		if(keyCode == KeyEvent.VK_W) {
			upPress = true;
		}
		if(keyCode == KeyEvent.VK_S) {
			downPress = true;
		}
		if(keyCode == KeyEvent.VK_A) {
			leftPress = true;
		}
		if(keyCode == KeyEvent.VK_D) {
			rightPress = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		//System.out.println("keyReleased " + keyCode);
		
		if(keyCode == KeyEvent.VK_W) {
			upPress = false;
		}
		if(keyCode == KeyEvent.VK_S) {
			downPress = false;
		}
		if(keyCode == KeyEvent.VK_A) {
			leftPress = false;
		}
		if(keyCode == KeyEvent.VK_D) {
			rightPress = false;
		}
	}
	
}
