package mapcreator;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class section {
	
	public abstract void Step();
	
	public abstract void render(Graphics g);
	
	public abstract void mouseClicked(MouseEvent e) ;


	
	public abstract void mouseEntered(MouseEvent e) ;



	public abstract void mouseExited(MouseEvent e) ;


	
	public  abstract void mousePressed(MouseEvent e) ;


	
	public abstract void mouseReleased(MouseEvent e) ;


	
	public abstract void mouseDragged(MouseEvent e) ;


	
	public abstract void mouseMoved(MouseEvent e);


	
	public abstract void keyPressed(KeyEvent e);


	
	public abstract void keyReleased(KeyEvent e);


	
	public abstract void keyTyped(KeyEvent e);
}
