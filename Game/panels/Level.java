package panels;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import Graphic.Camera2D;
import main.LM;
import characters.Blob;

import runtime.Driver;
import runtime.GamePanel;


@SuppressWarnings("serial")
public class Level extends GamePanel{
	
	private Blob blob;
	
	public Level(Driver game) {
		super(game);
		
		LM.LEVEL1.BuildMapOnPanel(this);
		
		blob = Blob.findBlob(this);
		camera = new Camera2D(this, blob, 0, 0, Camera2D.NormalScroll);
		
		
	}
	
	
	@Override
	public void Step() {
		// TODO Auto-generated method stub
		super.Step();
		camera.Step();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		blob.keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		blob.keyReleased(e);
	}

}
