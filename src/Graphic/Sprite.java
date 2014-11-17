package Graphic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Sprite {
	
	public static final int left = -1, right = 1,up =2,down = 3;
	/**
	 * This class allows you to make Sprites with animations multiple variables are accessible from outside
	 * the class
	 * 
	 * orientation
	 * xscale,yscale -double- control the size of the image
	 * freeze- boolean- tells the sprite to stop animating when freezeFrame is reached
	 * freezeFrame -int- represents the frame that the sprite should stop at when it is frozen
	 * frame -int- represents current Image being displayed
	 */
	public BufferedImage[] images;
	public int orientation =1;
	
	public double xscale,yscale;
	public boolean freeze = false;
	public int freezeFrame = 0;
	private double width,height;
	
	private int waitTime = 15;
	private int time = 0;
	
	public int frame;
	
	public Sprite( double xscale, double yscale, int orientation,BufferedImage...img){
		
		images = new BufferedImage[img.length];
		for (int bbb = 0; bbb < images.length; bbb++)
			images[bbb] = img[bbb];

		this.orientation = orientation;
		this.frame = 0;

		this.xscale = xscale;
		this.yscale = yscale;
		
		this.width = images[0].getWidth();
		this.height = images[0].getHeight();
	}
	
	/**
	 * This method is called so that the Sprite can keep track of time
	 */
	public void Step(){ ++time; }
	
	/**
	 * 
	 * @param duration
	 *            - milliseconds till next Image
	 * 
	 */
	public void setFrameDuration(double duration) {
		waitTime = (int) (duration / .02) - 1;
	}
	/**
	 * 
	 * @return-double- Width of Original Image
	 */
	public double getWidth(){ return width; }
	/**
	 * 
	 * @return-double- Height of Original Image
	 */
	public double getHeight(){return height;}
	
	/**
	 * Renders sprite to the screen
	 * @param g 
	 * @param x - coordinates to render sprite to
	 * @param y - coordinates to render sprite to
	 */
	public void render(Graphics g, int x, int y) {

		
		if (time > waitTime) {
			time = 0;
			if(!freeze ||(freeze && frame!=freezeFrame)){
				if (frame < images.length - 1)
					++frame;
				else
					frame = 0;
			}

		}

		if (orientation == right)
			g.drawImage(images[frame], x, y,(int) (xscale * images[frame].getWidth()) + x,(int) (yscale * images[frame].getHeight()) + y, 0, 0,images[frame].getWidth(), images[frame].getHeight(), null);
		else if (orientation == left)
			g.drawImage(images[frame],
					(int) (xscale * images[frame].getWidth()) + x, y, x,
					(int) (yscale * images[frame].getHeight()) + y, 0, 0,
					images[frame].getWidth(), images[frame].getHeight(), null);
		else if(orientation == up)
			g.drawImage(images[frame], x, (int) (yscale * images[frame].getHeight()) + y,(int) (xscale * images[frame].getWidth()) + x,y, 0, 0,images[frame].getWidth(), images[frame].getHeight(), null);
		else if(orientation == down)
			g.drawImage(images[frame], x, y,(int) (xscale * images[frame].getWidth()) + x,(int) (yscale * images[frame].getHeight()) + y, 0, 0,images[frame].getWidth(), images[frame].getHeight(), null);
			
	}
	/**
	 * renders a specific frame of the sprite to set location
	 * @param g
	 * @param x
	 * @param y
	 * @param frame
	 */
	public void render(Graphics g, int x, int y, int frame) {
		if (orientation == right)
			g.drawImage(images[frame], x, y,
					(int) (xscale * images[frame].getWidth()) + x,
					(int) (yscale * images[frame].getHeight()) + y, 0, 0,
					images[frame].getWidth(), images[frame].getHeight(), null);
		else if (orientation == left)
			g.drawImage(images[frame],
					(int) (xscale * images[frame].getWidth()) + x, y, x,
					(int) (yscale * images[frame].getHeight()) + y, 0, 0,
					images[frame].getWidth(), images[frame].getHeight(), null);

	}

}
