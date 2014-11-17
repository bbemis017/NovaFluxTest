package mapcreator;



import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.util.ArrayList;

import javax.swing.JFrame;



public class CreatorApp extends JFrame  implements KeyListener,MouseMotionListener,MouseListener{

	/**
	 * @author Ben Bemis
	 */
	private static final long serialVersionUID = 3820251631633799971L;
	public static int width = 900,height;
	public static ArrayList<String> Types = new ArrayList<String>();
	
	Panel panel;
	
	/**
	 * This method should be called to Start the CreatorApp from the main method
	 * 
	 * @param ThingTypes- this is a class file of enumerations that represent all of your objects
	 */
	public <E extends Enum<E>>CreatorApp(Class<E> ThingTypes ) {
		super();
		
		E[] types = ThingTypes.getEnumConstants();
		for(int bbb = 0; bbb<types.length; bbb++)	
			Types.add(types[bbb].toString());
		
		
		
		System.out.println("Enumeration Loaded");
		
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setSize(width, height = width/4*3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFocusable(true);
		add( panel = new Panel(this) );
		panel.setVisible(true);
		setVisible(true);
		
		
		panel.Start();
	}
	
	

	
	

	@Override
	public void mouseClicked(MouseEvent e) {
		panel.mouseClicked(e);
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		panel.mouseEntered(e);
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		panel.mouseExited(e);
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		panel.mousePressed(e);
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		panel.mouseReleased(e);
		
	}


	@Override
	public void mouseDragged(MouseEvent e) {
		panel.mouseDragged(e);
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		panel.mouseMoved(e);
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		panel.keyPressed(e);
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		panel.keyReleased(e);
	}


	@Override
	public void keyTyped(KeyEvent e) {
		panel.keyTyped(e);
		
	}
	
	
	
}
