package Graphic;


import java.awt.Graphics;
import java.awt.Graphics2D;

import runtime.Driver;
import runtime.GamePanel;
import tools.Character;

/**
 * 
 * This class allows for graphical objects to be scrolled 2Dimensionally
 * 
 * 
 * @author Ben Bemis
 *
 */
public class Camera2D {
	
	
	public static final int Tweening = 0, NormalScroll = 1, SideScroll = 2, TweeningSideScroll = 3,CustomScroll = 4;
	
	private float x,y;
	private Character player;
	private int ScrollType;

	private int xOffset = 0,yOffset = 0;
	
	/**
	 * This class allows for graphical objects to be scrolled 2Dimensionally relative to a Character object
	 * 
	 * @param gp - The GamePanel object
	 * @param player - Character object the Camera is to follow
	 * @param x - Starting x position of Camera
	 * @param y - Starting y position of Camera
	 * @param ScrollType
	 */
	public  Camera2D(GamePanel gp,Character player,float x, float y,int ScrollType){
		this.x = x;
		this.y = y; 

		this.player = player; 
		this.ScrollType = ScrollType;
	}
	
	/**
	 * This Constructor allows the Camera to be controlled by something other than a Character object
	 * and is forced to use the CustomScroll
	 * @param x - Starting x position of camera
	 * @param y - Starting y position of camera
	 */
	public  Camera2D(float x, float y){
		this.x = x;
		this.y = y; 

		 
		this.ScrollType = Camera2D.CustomScroll;
	}
	
	/**
	 * This method should be called with every step the program makes
	 * It updates the Camera's position relative to the Character position
	 */
	public void Step(){
		switch(ScrollType){
		case Tweening:
			TweaningScroll();
			break;
		case NormalScroll:
			Normal2DScroll();
			break;
		case SideScroll:
			sideScroll();
			break;
		case TweeningSideScroll:
			TweaningSideScroll();
			break;
		}
		
	}
	
	/**
	 * By Default the Camera keeps the Character in about the Center of the Screen
	 * The Offset is how far away from the default position the camera should follow the Character
	 * @param xOffset
	 * @param yOffset
	 */
	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	/**
	 * This method should be called before any objects you do not want to be relative to the frame
	 * are painted
	 * This method is called automatically in the GamePanel class
	 * 	-if the 2d camera has been instantiated
	 * 	-if the object is in the stuff Array
	 * @param g
	 */
	public void Scroll(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(x, y);
	}
	
	/**
	 * this method should be called before any objects you want to be relative to the frame are painted
	 * @param g
	 */
	public void deScroll(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.translate(-x, -y);
	}
	
	
	private void TweaningScroll(){
		x += ((-player.x + Driver.WIDTH /2 + xOffset) - x) * .0275f;
		y += ((-player.y + Driver.WIDTH /2 + yOffset) - y) * .0275f;
	}
	
	private void TweaningSideScroll(){
		x += ((-player.x + Driver.WIDTH /2 + xOffset) - x) * .0275f;
	}
	
	private void Normal2DScroll(){
		if(player!=null){
			this.x = -player.x + Driver.WIDTH /2 + xOffset;
			this.y = -player.y + Driver.HEIGHT /2 + yOffset;
		}
	}
	/**
	 * This method is made for the customScroll it must be defined by future programmers and it is
	 * not called automatically
	 */
	protected void CustomScroll(){}
	
	private void sideScroll(){
		this.x = -player.x + Driver.WIDTH /2 + xOffset;
	}
	
	/**
	 * 
	 * @return -int x position of Camera
	 */
	public double getX(){ return x; }
	/**
	 * 
	 * @return -int y position of Camera
	 */
	public double getY(){ return y; }
	
	/**
	 * sets coordinates of Camera
	 * @param d
	 * @param e
	 */
	public void setCoord(double d, double e){
		this.x = (float) d;
		this.y = (float) e;
	}
	
	
	
	
	

}
