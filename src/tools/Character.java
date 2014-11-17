package tools;



import java.awt.event.KeyEvent;
import java.util.ArrayList;

import runtime.GamePanel;

/**
 * This class is a subclass of a Thing and provides general tools that every character/player needs
 * @author Ben Bemis
 *
 */
public abstract class Character extends Thing{
	
	protected boolean CameraFollow = false;
	/**
	 * Creates a new Character
	 * @param x
	 * @param y
	 * @param CameraFollow - Tells the camera weather or not it should follow this character
	 * @param gp
	 */
	public Character(int x, int y,boolean CameraFollow, GamePanel gp) {
		super(x, y, gp);
		mask = new Mask(this, 0, 0, 50, 50);
		this.CameraFollow = CameraFollow;
	}
	
	public boolean doesCameraFollow(){ return CameraFollow; }
	
	/**
	 * Changes the characters position based off dx and dy
	 */
	@Override
	public void Step() {
		x+=dx;
		y+=dy;
	}
	
	/**
	 * Use this method to find all Characters on a GamePanel
	 * I would recomend you override this method in any subclass
	 * so that you can find any instances of any characters
	 * @param panel
	 * @return ArrayList<Character> - all characters on GamePanel
	 */
	public static ArrayList<Character> findCharacters(GamePanel panel){
		
		ArrayList<Character> characters = new ArrayList<Character>();
		for(int bbb = 0; bbb<panel.stuff.size(); bbb++){
			Thing thing = panel.stuff.get(bbb);
			if( thing instanceof Character)
				characters.add((Character)thing);
		}
		if(characters.size()<1)
			System.out.println("No Character could be found in ArrayList stuff\n" +" on GamePanel " + panel.toString() );
		return characters;
	}
	
	public abstract void keyPressed(KeyEvent e);
	
	public  abstract void keyReleased(KeyEvent e);

}
