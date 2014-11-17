package Graphic;



import java.awt.image.BufferedImage;
import java.util.ArrayList;

import runtime.GamePanel;
import tools.Thing;

/**
 * The Color decryptor reads the color of pixels and performs instructions
 * 
 * 
 *
 */

public abstract class ColorDecryptor {
	
	protected int Index = 0;
	protected ArrayList<Thing> stuff = new ArrayList<Thing>();
	
	protected static int GRIDCONSTANT = 32;
	
	/**
	 * Decodes the image and performs instructions under ColorCodeChecker() for every pixel in the
	 * image
	 * @param ImgMap
	 */
	public ColorDecryptor(BufferedImage ImgMap){
		
		int w = ImgMap.getWidth();
		int h = ImgMap.getHeight();
		
		
		
		for(int x = 0; x<w; x++){
			for(int y = 0; y<h; y++){
				int pixel = ImgMap.getRGB(x, y);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				ColorCodeChecker(red,green,blue,x*GRIDCONSTANT,y*GRIDCONSTANT);
			}
		}
		
		
	}
	
	/**
	 * Adds this Array of stuff to the indicated GamePanel
	 * @param gp
	 */
	public void BuildMapOnPanel(GamePanel gp){
		
		
		for( int MapIndex = 0, PanelIndex = gp.stuff.size();MapIndex < this.stuff.size(); MapIndex++ ){
			gp.stuff.add(PanelIndex + MapIndex, this.stuff.get(MapIndex) );
		}
		
		for(int bbb = 0; bbb<gp.stuff.size(); bbb++)
			(gp.stuff.get(bbb) ).addPanelRef(gp);
	}
	
	/**
	 * This method will be iterated for every pixel in  the image
	 * 
	 * NOTE: the x and y are not relative to the position on the image they are multipled
	 * by the GRIDCONSTANT before being passed as a parameter
	 * 
	 * @param red
	 * @param green
	 * @param blue
	 * @param x
	 * @param y
	 */
	public abstract void ColorCodeChecker( int red, int green, int blue,int x, int y);
}
