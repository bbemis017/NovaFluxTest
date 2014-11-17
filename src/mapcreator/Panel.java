package mapcreator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;



public class Panel extends JPanel implements ActionListener{
	
	
	
	/**
	 * @author Ben Bemis
	 */
	private static final long serialVersionUID = 5242695194841664819L;

	protected CreatorApp ca;
	private Timer timer;
	
	public String IDSelected;
	public static TempThing Selected;
	public SubThings subSelected;
	public section Sectionselected;
	public boolean AddingPath = false;
	
	private ArrayList<section> sections = new ArrayList<section>();
	
	public Panel(CreatorApp ca){
		this.ca = ca;
		sections.add(  new Manipulator(this) );
		sections.add( new Objects(this) );
		sections.add( new Properties(this) );
		timer = new Timer(17, this);
	}
	
	
	
	public void Step(){
		for( int bbb = 0; bbb<sections.size(); bbb++)
			sections.get(bbb).Step();
	}
	
	@Override
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, CreatorApp.width, CreatorApp.height);
		
		
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).render(g);
	}
	
	
	
	
	
	public void mouseClicked(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mouseClicked(e);
		
	}


	
	public void mouseEntered(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mouseEntered(e);
		
	}



	public void mouseExited(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mouseExited(e);
		
	}


	
	public void mousePressed(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mousePressed(e);
		
	}


	
	public void mouseReleased(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mouseReleased(e);
		
	}


	
	public void mouseDragged(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mouseDragged(e);
		
	}


	
	public void mouseMoved(MouseEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).mouseMoved(e);
		
	}


	
	public void keyPressed(KeyEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).keyPressed(e);
		
	}


	
	public void keyReleased(KeyEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).keyReleased(e);
		
	}


	
	public void keyTyped(KeyEvent e) {
		for(int bbb =0; bbb<sections.size(); bbb++)
			sections.get(bbb).keyTyped(e);
		
	}
	



	@Override
	public void actionPerformed(ActionEvent e) {
		Step();
		repaint();
		
	}
	
	public void Start(){ timer.start(); }

}
