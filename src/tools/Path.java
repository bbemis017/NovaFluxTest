package tools;

import java.awt.Point;
import java.util.ArrayList;
import java.util.regex.Pattern;

import Graphic.TextMap;


/**
 * This Class creates a path that an AI can follow
 * 
 * NOTE: The AI class will always skip the next point after an Endpoint
 * so you should always put two points at both sides of the path
 * 
 * @author Ben Bemis
 *
 */
public class Path{
	
	public static final String ID = Reference.ID.PATH.toString();
	
	private Point[] points;
	private int Index;
	private boolean positive;
	
	/**
	 * Creates the path
	 * @param Index - Point AI should start at
	 * @param points - Points in a path
	 */
	public Path(int Index, Point...points) {
		this.points = points;
		
		
		this.Index = Index;
		positive = isNextPoint();
	}
	/**
	 * Creates the path w/o assigning any Points
	 * should only be used if the points will be assigned later
	 */
	public Path(){this.points = new Point[0];}
	
	/**
	 * Adds a point to the Path
	 * @param Index - resets the starting Index
	 * @param p
	 */
	public void addPoint(int Index,Point p){
		
		ArrayList<Point> temp = new ArrayList<Point>();
		for(int bbb =0; bbb<points.length; bbb++)
			temp.add( points[bbb] );
		
		temp.add(p);
		points = new Point[temp.size()];
		for(int bbb = 0;bbb<temp.size(); bbb++)
			points[bbb] = temp.get(bbb);
		
		this.Index = Index;
		positive = isNextPoint();
	}
	/**
	 * removes Point from path
	 * @param Index
	 */
	public void removePoint(int Index){
		Point[] temp = new Point[points.length - 1];
		int tempIndex = 0;
		for(int bbb =0; bbb<points.length; bbb++)
			if(Index!=bbb){
				temp[tempIndex] = points[bbb];
				++tempIndex;
			}
		
		points = temp;
	}
	/**
	 * Creates path based on String
	 * String must follow this format
	 * PATH/index/x/y/x/y/x/y/x/y and so on
	 * @param InputString
	 */
	public Path(String InputString){
		
		if(InputString.startsWith(Path.ID) ){
			
			String data = InputString.substring(Path.ID.length() );
			String[] tokens = data.split(Pattern.quote("/") );
			boolean Xvalue = true;
			ArrayList<Point> temp = new ArrayList<Point>();
			int tempIndex = 0;
			for(int bbb = 1; bbb<tokens.length;bbb++)
				System.out.println(tokens[bbb]);
			
			for(int bbb = 1; bbb<tokens.length; bbb++){
				if(bbb==1)
					this.Index = Integer.parseInt( tokens[bbb].trim() );
				else{
					if(Xvalue){
						temp.add(new Point() );
						temp.get(tempIndex).x = Integer.parseInt( tokens[bbb] ) * TextMap.GRIDCONSTANT;
						Xvalue = false;
					}else{
						temp.get(tempIndex).y = Integer.parseInt( tokens[bbb] ) * TextMap.GRIDCONSTANT;
						++tempIndex;
						Xvalue = true;
					}
				}
			}
			
			this.points = new Point[temp.size()];
			for(int i = 0; i<points.length; i++)
				this.points[i] = temp.get(i);
			
			positive = isNextPoint();
			
		}else{
			System.out.println("Invalid Path Instantiation: " + InputString);
		}
	}
	

	/**
	 * 
	 * @return true- if there is a last point
	 * @return false- if there is not a last Point
	 */
	public boolean isLastPoint(){
		if(Index != 0)
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @return true if there is a next point
	 * @return false- if there is not a next point
	 */
	public boolean isNextPoint(){
		if(Index != points.length - 1)
			return true;
		else
			return false;
	}
	
	/**
	 * Steps the index of path forward if the path is positive
	 * -if not it calls StepBack()
	 */
	public void StepIndex(){
		
		if(positive)
			StepForward();
		else
			StepBack();
				
		
		
	}
	
	/**
	 * Steps Backward
	 */
	private void StepBack(){
		Index-=1;
		if(Index == 0){
			positive = true;
			
			StepForward();
		}
	}
	/**
	 * Steps Forward
	 */
	private void StepForward(){
		Index +=1;
		if(Index == points.length-1){
			positive = false;
			
			StepBack();
		}
	}
	
	public Point getPoint(int index){ return points[index];	}
	public Point getLastPoint(){ return points[Index-1]; }
	public Point getNextPoint(){return points[Index+1]; }
	public int getIndex(){return Index; }
	public boolean isPositive(){ return positive; }
	public int size(){ return points.length; }
	

	/**
	 * @return String- representation of this path
	 * formatted like so:
	 * PATH/index/x/y/x/y/x/y
	 */
	public String toString() {
		String temp = ID + "/" + Index;
		for(int bbb = 0; bbb<points.length; bbb++){
			temp+="/"+points[bbb].x;
			temp+="/"+points[bbb].y;
		}
		return temp;
	}

}
