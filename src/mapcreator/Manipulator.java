package mapcreator;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import Graphic.Camera2D;
import tools.Button;


public class Manipulator extends section{
	
	public static final int x = 0,y = 35, width = CreatorApp.width*4/5, height = CreatorApp.height-20;
	public static final int GridSpacing = 10;
	private boolean GridOn = true;

	private Panel p;
	private Camera2D camera;
	
	public static ArrayList<TempThing> things = new ArrayList<TempThing>();
	
	private Button save,open;
	
	public Manipulator(Panel p){
		this.p = p;
		save = new Save();
		open = new Open();
		
		camera = new Camera2D(0, 0 );
	}
	
	@Override
	public void Step(){
		
	}
	
	
	private void renderMap(Graphics g){
		
		if(GridOn){
			g.setColor(Color.black);
			for( int bbb = 0; bbb<=width/GridSpacing; bbb++)
				g.drawLine( (bbb*GridSpacing) + x, y , (bbb*GridSpacing) + x, y + height);
			for( int bbb = 0; bbb<= height/GridSpacing; bbb++)
				g.drawLine( (x), (bbb*GridSpacing) + y ,x + width,(bbb*GridSpacing) + y );
			
		}
		camera.Scroll(g);
		for(int bbb =0; bbb<things.size(); bbb++)
			things.get(bbb).render(g);
		
		camera.deScroll(g);
	}
	@Override
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x,y, width, height);
		
		save.render(g);
		open.render(g);
		
		renderMap(g);
	}
	
	
	public void GridClicked(MouseEvent e){
		int x = (int)(e.getX() - Manipulator.x - camera.getX() )/Manipulator.GridSpacing;
		int y = (int)(e.getY() - Manipulator.y - camera.getY() )/Manipulator.GridSpacing;
		if(!p.AddingPath){
			if(p.IDSelected!=null){
			
				
				
				if(e.getButton() == MouseEvent.BUTTON1){
				
					for(  int bbb = 0; bbb<things.size(); bbb++)
						if(things.get(bbb).x == x && things.get(bbb).y == y-3)//object already exists here
							things.remove(bbb);//remove sed object
			
					Manipulator.things.add( Panel.Selected = new TempThing(p.IDSelected, x, y-3,true,null) );
				}
				else if(e.getButton() == MouseEvent.BUTTON3){
					boolean found = false;
					for(  int bbb = 0; bbb<things.size(); bbb++){
						if(things.get(bbb).x == x && things.get(bbb).y == y-3){
							Panel.Selected = things.get(bbb);
							found = true;
						}
					}
					if(!found)
						Panel.Selected = null;
				}
			}
			p.subSelected = null;
		}
		else if(p.AddingPath){
			if(e.getButton() == MouseEvent.BUTTON1){
				if(!Panel.Selected.pathExists())
					Panel.Selected.addPath();
			
				Panel.Selected.addPoint(0,new Point(x, y-3) );
			}
			else if(e.getButton() == MouseEvent.BUTTON3){
				if(Panel.Selected.pathExists()){
					
					for(int bbb =0; bbb<Panel.Selected.subs.get(0).path.size();bbb++)
						if(Panel.Selected.subs.get(0).path.getPoint(bbb).x == x && Panel.Selected.subs.get(0).path.getPoint(bbb).y == y-3){
							Panel.Selected.subs.get(0).path.removePoint(bbb);
							
						}
				}
					
			}
				
		}
		
		
	}
	
	
	
	private class Save extends Button{

		public Save() {
			super("Save", new Rectangle(5, 5, 56, 30), 2);
			disabled = Color.gray;
			normal = Color.BLACK;
		}
		
		
		@Override
		public void Clicked() {
			super.Clicked();
			FileManager fm = new FileManager(p.ca);
			fm.SaveFile();
		}
		
		@Override
		public void render(Graphics g) {
			
			super.render(g);
		}
		
	}
	
	private class Open extends Button{

		public Open() {
			super("Open", new Rectangle(63, 5, 56, 30), 2);
			disabled = Color.gray;
			normal = Color.BLACK;
		}
		
		
		@Override
		public void Clicked() {
			super.Clicked();
			
			FileManager fm = new FileManager(p.ca);
			fm.LoadFile();
		}
		
		@Override
		public void render(Graphics g) {
			
			super.render(g);
		}
		
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		save.MouseClicked(e);
		open.MouseClicked(e);
		
		if( e.getX() >= x && e.getX()<= x + width ){
			if(e.getY()> y + 30 && e.getY()<= y + height){
				GridClicked(e);
				p.Sectionselected = this;
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		save.MouseMoved(e);
		open.MouseMoved(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(p.Sectionselected==this){
			int key = e.getKeyCode();
			if(key ==KeyEvent.VK_LEFT)
				camera.setCoord(camera.getX() - GridSpacing,camera.getY() );
			if(key ==KeyEvent.VK_RIGHT)
				camera.setCoord(camera.getX() + GridSpacing,camera.getY() );
			if(key ==KeyEvent.VK_UP)
				camera.setCoord(camera.getX(),camera.getY() -GridSpacing );
			if(key ==KeyEvent.VK_DOWN)
				camera.setCoord(camera.getX(),camera.getY()+GridSpacing );
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_DELETE && Panel.Selected!=null){
			things.remove(Panel.Selected);
			Panel.Selected = null;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

}
