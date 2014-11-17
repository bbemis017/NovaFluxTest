package tools;

import java.awt.Graphics;
import java.util.ArrayList;

import runtime.GamePanel;

/**
 * This class is the CoreObject,Entity, or whatever name you have heard describe
 * the most basic object for the game Every one of these should be put in the
 * stuff Array on the GamePanel
 * 
 * @author Ben Bemis
 * 
 */
public abstract class Thing {

	public int x, y;
	public double dx, dy;
	protected GamePanel gp;
	protected ArrayList<Thing> solids;
	public Mask mask;

	private boolean renderMe = false;

	/**
	 * Creates a new Thing
	 * 
	 * @param x
	 * @param y
	 * @param gp
	 */
	public Thing(int x, int y, GamePanel gp) {

		this.x = x;
		this.y = y;
		this.gp = gp;
		this.mask = new Mask(this, 0, 0, 10, 10);
	}

	/**
	 * Creates a new Thing w/o a GamePanel assigned NOTE: you will get massive
	 * errors if you do not call addPanelRef before the Gameloop starts
	 * 
	 * @param x
	 * @param y
	 */
	public Thing(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Adds a reference to the GamePanel should be overrided if you have more
	 * objects that require a reference to the GamePanel
	 * 
	 * @param gp
	 */
	public void addPanelRef(GamePanel gp) {
		this.gp = gp;
	}

	/**
	 * Every Thing needs a Step method this is where you do the bulk of your
	 * code do as much as possible here and do as little as possible under the
	 * render()
	 */
	public abstract void Step();

	/**
	 * mimics the motion of gravity
	 */
	public void gravity() {
		dy = Calc.DeltaVelocity(dy);
		y = (int) (Calc.DeltaHeight(dy, y));

	}

	/**
	 * resets coordinates of Thing
	 * 
	 * @param x
	 * @param y
	 */
	public void setCoord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This is your render() method this should have a little code as possible
	 * and should only do what is necessary to render the object
	 * 
	 * @param g
	 */
	public abstract void render(Graphics g);

	/**
	 * Sets render value
	 * 
	 * @param boolean renderMe true - if this object's render method should be
	 *        called false - if this object's render method should not be called
	 */
	public void setRenderBoolean(boolean renderMe) {
		this.renderMe = renderMe;
	}

	/**
	 * @return boolean true- if this object is set to be rendered
	 * @return boolean false- if this object is not set to be rendered
	 */
	public boolean renderMe() {
		return this.renderMe;
	}

	/**
	 * Every Thing needs to have a way to destroy itself not that every Thing
	 * will need to be destroyed the java Garbage collector takes care of most
	 * of it but if a thing needs to be destroyed Immedately this is where you
	 * code how to do it
	 * 
	 * P.S I am considering getting rid of this method since I do not ever
	 * destroy Thing objects from the Engine stand point
	 */
	public abstract void destroy();

}
