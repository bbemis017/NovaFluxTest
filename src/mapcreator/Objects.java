package mapcreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


import Graphic.Camera2D;
import tools.Button;

public class Objects extends section{
	
	private Panel p;

	private int x = CreatorApp.width*4/5, y = 35,width = CreatorApp.width/5,height = CreatorApp.height/2 -35;
	
	protected button[] types;
	
	Camera2D camera;
	
	public Objects(Panel p){
		this.p = p;
		camera = new Camera2D(0, 0);
		types = new button[CreatorApp.Types.size()];
		for(int bbb = 0; bbb<types.length; bbb++){
			types[bbb] = new button(CreatorApp.Types.get(bbb), new Rectangle(x, (bbb*25) + y, width, 25), 5);
			
		}
	}
	
	
	
	@Override
	public void Step() {
		
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, width, height);
		camera.Scroll(g);
		for(int bbb = 0; bbb<types.length; bbb++)
			if(types[bbb].rect.y < y + height - 5)
				types[bbb].render(g);
		
		camera.deScroll(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		e.translatePoint((int)camera.getX(), (int)camera.getY());
		for(int bbb = 0; bbb<types.length; bbb++)
			types[bbb].MouseClicked(e);
		
		if( e.getX() >= x && e.getX()<= x + width ){
			if(e.getY()> y + 30 && e.getY()<= y + height){
				
				p.Sectionselected = this;
			}
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		e.translatePoint(-(int)camera.getX(), -(int)camera.getY() );
		for(int bbb = 0; bbb<types.length; bbb++)
			types[bbb].MouseMoved(e);
		
	}
	
	
	private class button extends Button{
		
		public String id;
		public boolean selected = false;

		public button(String id, Rectangle rect, int xOffset) {
			super(id, rect, xOffset);
			this.id = id;
			disabled = Color.gray;
			normal = Color.BLACK;
			font = new Font("Times New Roman", Font.PLAIN, 10);
		}
		
		@Override
		public void Clicked() {
			super.Clicked();
			for(int bbb = 0; bbb<types.length; bbb++)
				if(types[bbb].selected)
					types[bbb].selected = false;
			
			selected = true;
			p.IDSelected = id;
		}
		
		@Override
		public void render(Graphics g) {
			if(!selected)
				super.render(g);
			else{
				g.setColor(Color.MAGENTA);
				g.fillRect(rect.x, rect.y, rect.width, rect.height);
				g.setColor(Color.WHITE);
				g.drawString(text,rect.x + xOffset,rect.y + 20 + yOffset);
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
	public void keyPressed(KeyEvent e) {
		if(p.Sectionselected==this){
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_UP)
				camera.setCoord(camera.getX(),camera.getY()-25 );
			else if(key==KeyEvent.VK_DOWN)
				camera.setCoord(camera.getX(),camera.getY() +25 );
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
