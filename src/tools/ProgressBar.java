package tools;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Basic Progress Bar
 * @author Ben Bemis
 *
 */

public class ProgressBar {
	
	private boolean finished;
	private double Progress;
	private Color FullColor, EmptyColor;
	private int x,y,width,height;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param FullColor - Color When Progress Bar is full
	 * @param EmptyColor - Color When Progress Bar is empty
	 */
	public ProgressBar(int x, int y, int width, int height, Color FullColor,Color EmptyColor){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		finished = false;
		Progress = 0;
		this.FullColor = FullColor;
		this.EmptyColor = EmptyColor;
		
	}
	/**
	 * Updates Progress Bar
	 * @param Progress
	 */
	public void Update(double Progress){ this.Progress = Progress; }
	
	/**
	 * 
	 * @return-double- progress towards completion
	 */
	public double getProgress(){ return Progress; }
	
	/**
	 * 
	 * @return- true- if Progress is finished
	 * false- if still in Progress
	 */
	public boolean isFinished(){ 
		if(Progress >=1.0)
			finished = true;
		return finished; 
	}
	
	/**
	 * Renders Progress in Progress Bar form
	 * @param g
	 */
	public void render(Graphics g){
		g.setColor(EmptyColor);
		g.fillRect(x, y, width, height);
		g.setColor(FullColor);
		g.fillRect(x, y, (int)(Progress * width), height);
		
		
	}
	
	

}
