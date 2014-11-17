package tools;

import java.awt.Graphics;

import Graphic.Sprite;
import runtime.GamePanel;

/**
 * This class is what a Standard block would do in a Game
 * if you are making a block you should probably make a subclass of
 * this so that it is exactly what you want
 * 
 * @author Ben Bemis
 *
 */
public class Block extends Thing {
	

	protected boolean Visible;
	protected Sprite sprite;
	
	/**
	 * Creates a 32 by 32 Block with the following characteristic's
	 * @param x
	 * @param y
	 * @param Visible
	 * @param gp
	 */
	public Block(int x, int y,boolean Visible, GamePanel gp) {
		super(x, y, gp);
		
		this.Visible = Visible;
		
		append(32,32,null);
	}
	
	
	/**
	 * Appends this block object with the following characteristics
	 * @param width
	 * @param height
	 * @param sprite
	 */
	public void append(int width, int height, Sprite sprite) {
		mask = new Mask(this, 0, 0, width, height);
		this.sprite = sprite;	
	}
	
	/**
	 * This method checks if a subclass of a Character is colliding with it
	 * 	-if so- it calls the collision method of this class
	 * then checks if a subclass of AI is colliding with it
	 *  - if so- it calls the blockCollision method from the AI class
	 */
	@Override
	public void Step() {
		
		for(int bbb = 0; bbb<gp.stuff.size(); bbb++){
			
			Thing thing = gp.stuff.get(bbb);
			if( thing instanceof Character)
				if( thing.mask!=null && this.mask.collidesWith(thing.mask) )
					Collision(thing);
//			if(thing instanceof AI){
//				if(this.mask.collidesWith( ((AI) thing).getMask() ) ){
//					
//					((AI)thing).blockCollision(Collision(thing));
//				}
//			}
		}
				
		

	}
	
	/**
	 * This method performs standard operations that stop the colliding object from moving through
	 * the block
	 * @param thing
	 * @return
	 */
	public int Collision(Thing thing){
		
		boolean[] side = this.mask.collisionSide(thing.mask);
		if(side[Mask.left] ){
			thing.dx = 0;
			thing.setCoord(this.mask.getRect().x + this.mask.getRect().width, thing.y);
			return Mask.left;
		}
		else if(side[Mask.top]){
			thing.dy = 0;
			thing.setCoord(thing.x, this.y + this.mask.getRect().height );
			return Mask.top;
		}
		else if(side[Mask.right]){
			thing.dx = 0;
			thing.setCoord(this.x - thing.mask.getRect().width, thing.y);
			return Mask.right;
		}
		else if(side[Mask.bottom]){
			thing.dy = 0;
			thing.setCoord(thing.x, this.y - thing.mask.getRect().height);
			return Mask.bottom;
		}
		return -1;
	}


	/**
	 * This method controls how the block looks
	 */
	@Override
	public void render(Graphics g) {
		if(Visible){
			if(sprite!=null)
				sprite.render(g, x, y);
			else{
				g.fillRect(mask.getRect().x, mask.getRect().y, mask.getRect().width, mask.getRect().height);
			}
				
		}

	}
	
	/**
	 * This method will remove this block from the GamePanel
	 */
	@Override
	public void destroy() {
		gp.stuff.remove(this);

	}

	

}
