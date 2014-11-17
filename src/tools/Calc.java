package tools;

import java.awt.Point;
import java.awt.geom.Rectangle2D;




import Reference.References;

/**
 * This class provides access to general math and physics equations
 * @author Ben Bemis
 *
 */
public class Calc {
	
	/**
	 * Calculates the change in the velocity since last Calculation. Then adds the change
	 * back to the vel and return
	 * Physics Representation:
	 * y = y + gt
	 * @param vel
	 * @return
	 */
	public static double DeltaVelocity(double vel){ 
		return vel+= References.CHANGE_IN_TIME*References.GRAVITY; 
	}
	/**
	 * Calculates the change in distance since last Calculation and adds it back to the position and returned.
	 * Physics Representation:
	 * y = y + gt +.5gt^2
	 * @param vel
	 * @param position
	 * @return
	 */
	public static double DeltaHeight(double vel,int position){ 
		return position+=  (vel*References.CHANGE_IN_TIME) + (.5*References.GRAVITY*References.CHANGE_IN_TIME*References.CHANGE_IN_TIME);
	}
	
	/**
	 * Calculates the Area of a Rectangle
	 * @param r
	 * @return
	 */
	public static double getArea(Rectangle2D r){ return r.getWidth()*r.getHeight(); }
	
	public static double getDistance(Point p1, Point p2){ return Math.sqrt( Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2 ) ); }

}
