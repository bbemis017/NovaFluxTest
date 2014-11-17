package characters;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import main.LM;
import Graphic.Sprite;
import runtime.GamePanel;
import tools.Mask;

public class Blob extends tools.Character{
	
	private Sprite sprite;

	public Blob(int x, int y, GamePanel gp) {
		super(x, y, true, gp);
		sprite = new Sprite(1, 1, Sprite.right, LM.BLOB1,LM.BLOB2,LM.BLOB3,LM.BLOB4,LM.BLOB5);
		sprite.setFrameDuration(.17);
		mask = new Mask(this,0, 0, sprite.xscale*sprite.getWidth(), sprite.yscale*sprite.getHeight());
	}
	
	@Override
	public void Step() {
		super.Step();
		sprite.Step();
		super.gravity();
	}

	@Override
	public void render(Graphics g) {
		sprite.render(g, x, y);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch( e.getKeyCode() ){
		case KeyEvent.VK_DOWN:
			dy = 5;
			break;
		case KeyEvent.VK_UP:
			dy = -5;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 5;
			break;
		case KeyEvent.VK_LEFT:
			dx = -5;
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch( e.getKeyCode() ){
		case KeyEvent.VK_DOWN:
			dy = 0;
			break;
		case KeyEvent.VK_UP:
			dy = 0;
			break;
		case KeyEvent.VK_RIGHT:
			dx = 0;
			break;
		case KeyEvent.VK_LEFT:
			dx = 0;
			break;
		}
	}

	@Override
	public void destroy() {}
	
	
	public static Blob findBlob(GamePanel panel){
		ArrayList<tools.Character> characters = tools.Character.findCharacters(panel);
		for( int bbb = 0; bbb<characters.size(); bbb++)
			if( characters.get(bbb) instanceof Blob)
				return (Blob)characters.get(bbb);
		
		System.out.println("No Blob could be found in ArrayList stuff" );
		return null;
	}

}
