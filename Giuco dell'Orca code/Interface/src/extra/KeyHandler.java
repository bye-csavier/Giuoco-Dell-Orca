package extra;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
	
	//KeyVars
		// Letters
	public boolean q;
	public boolean w;
	public boolean e;
	public boolean r;
	public boolean t;
	public boolean y;
	public boolean u;
	public boolean i;
	public boolean o;
	public boolean p;
	public boolean a;
	public boolean s;
	public boolean d;
	public boolean f; 
	public boolean g;
	public boolean h;
	public boolean j;
	public boolean k;
	public boolean l;
	public boolean z;
	public boolean x;
	public boolean c;
	public boolean v; 
	public boolean b; 
	public boolean n;
	public boolean m;
	
		//Action Keys
	public boolean escape;
	public boolean enter;
	public boolean shift;
	public boolean maiusc;
	public boolean space;
	public boolean delete;
	
	// FUNCTIONS
	
	@Override
	public void keyTyped(KeyEvent e) {
		
//		int keyCode = e.getKeyCode();
//		
//		// Letters
//		if(keyCode == KeyEvent.VK_Q) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_W) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_E) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_R) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_T) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_Y) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_U) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_I) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_O) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_P) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_A) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_S) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_D) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_F) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_G) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_H) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_J) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_K) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_L) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_Z) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_X) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_C) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_V) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_B) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_N) {
//			q = true;
//		}
//		if(keyCode == KeyEvent.VK_M) {
//			q = true;
//		}
//		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
		
		// Letters
		if(keyCode == KeyEvent.VK_Q) {
			q = true;
		}
		if(keyCode == KeyEvent.VK_W) {
			w = true;
		}
		if(keyCode == KeyEvent.VK_E) {
			e = true;
		}
		if(keyCode == KeyEvent.VK_R) {
			r = true;
		}
		if(keyCode == KeyEvent.VK_T) {
			t = true;
		}
		if(keyCode == KeyEvent.VK_Y) {
			y = true;
		}
		if(keyCode == KeyEvent.VK_U) {
			u = true;
		}
		if(keyCode == KeyEvent.VK_I) {
			i = true;
		}
		if(keyCode == KeyEvent.VK_O) {
			o = true;
		}
		if(keyCode == KeyEvent.VK_P) {
			p = true;
		}
		if(keyCode == KeyEvent.VK_A) {
			a = true;
		}
		if(keyCode == KeyEvent.VK_S) {
			s = true;
		}
		if(keyCode == KeyEvent.VK_D) {
			d = true;
		}
		if(keyCode == KeyEvent.VK_F) {
			f = true;
		}
		if(keyCode == KeyEvent.VK_G) {
			g = true;
		}
		if(keyCode == KeyEvent.VK_H) {
			h = true;
		}
		if(keyCode == KeyEvent.VK_J) {
			j = true;
		}
		if(keyCode == KeyEvent.VK_K) {
			k = true;
		}
		if(keyCode == KeyEvent.VK_L) {
			l = true;
		}
		if(keyCode == KeyEvent.VK_Z) {
			z = true;
		}
		if(keyCode == KeyEvent.VK_X) {
			x = true;
		}
		if(keyCode == KeyEvent.VK_C) {
			c = true;
		}
		if(keyCode == KeyEvent.VK_V) {
			v = true;
		}
		if(keyCode == KeyEvent.VK_B) {
			b = true;
		}
		if(keyCode == KeyEvent.VK_N) {
			n = true;
		}
		if(keyCode == KeyEvent.VK_M) {
			m = true;
		}
		
		// Action Keys
		
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			escape = true;
		}
		if(keyCode == KeyEvent.VK_SHIFT)
		{
			shift = true;
			maiusc = true;
		}
		if(keyCode == KeyEvent.VK_ENTER)
		{
			enter = true;
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			space = true;
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE) {
			delete = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
		
		int keyCode = event.getKeyCode();
			
		// Letters
		if(keyCode == KeyEvent.VK_Q) {
			q = false;
		}
		if(keyCode == KeyEvent.VK_W) {
			w = false;
		}
		if(keyCode == KeyEvent.VK_E) {
			e = false;
		}
		if(keyCode == KeyEvent.VK_R) {
			r = false;
		}
		if(keyCode == KeyEvent.VK_T) {
			t = false;
		}
		if(keyCode == KeyEvent.VK_Y) {
			y = false;
		}
		if(keyCode == KeyEvent.VK_U) {
			u = false;
		}
		if(keyCode == KeyEvent.VK_I) {
			i = false;
		}
		if(keyCode == KeyEvent.VK_O) {
			o = false;
		}
		if(keyCode == KeyEvent.VK_P) {
			p = false;
		}
		if(keyCode == KeyEvent.VK_A) {
			a = false;
		}
		if(keyCode == KeyEvent.VK_S) {
			s = false;
		}
		if(keyCode == KeyEvent.VK_D) {
			d = false;
		}
		if(keyCode == KeyEvent.VK_F) {
			f = false;
		}
		if(keyCode == KeyEvent.VK_G) {
			g = false;
		}
		if(keyCode == KeyEvent.VK_H) {
			h = false;
		}
		if(keyCode == KeyEvent.VK_J) {
			j = false;
		}
		if(keyCode == KeyEvent.VK_K) {
			k = false;
		}
		if(keyCode == KeyEvent.VK_L) {
			l = false;
		}
		if(keyCode == KeyEvent.VK_Z) {
			z = false;
		}
		if(keyCode == KeyEvent.VK_X) {
			x = false;
		}
		if(keyCode == KeyEvent.VK_C) {
			c = false;
		}
		if(keyCode == KeyEvent.VK_V) {
			v = false;
		}
		if(keyCode == KeyEvent.VK_B) {
			b = false;
		}
		if(keyCode == KeyEvent.VK_N) {
			n = false;
		}
		if(keyCode == KeyEvent.VK_M) {
			m = false;
		}
		
		// Action Keys
		
		if(keyCode == KeyEvent.VK_ESCAPE)
		{
			escape = false;
		}
		if(keyCode == KeyEvent.VK_SHIFT)
		{
			shift = false;
			maiusc = false;
		}
		if(keyCode == KeyEvent.VK_ENTER)
		{
			enter = false;
		}
		if(keyCode == KeyEvent.VK_SPACE) {
			space = false;
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE) {
			delete = false;
	
		}
	}
}
