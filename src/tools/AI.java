package tools;

import java.awt.Graphics;
import java.awt.Point;
//import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import runtime.GamePanel;

/**
 * This class is still a work in progress, but eventually it will provide support for 
 * random AI's, path following AI's, and character following AI's
 * But for now use at your own risk
 * @author Ben Bemis
 *
 */
public class AI extends Thing{
	
	public static final int left = 0, right = 1, up = 2, down = 3,stop = -1;
	protected int DIRIndexX,DIRIndexY,PathDIRX,PathDIRY;

	protected boolean followPath,followCharacter;
	protected Path path;
	protected Mask detectionMask;
	protected ArrayList<Character> characters;
	protected double NormDx,NormDy,MaxDx,MaxDy;
	
	private double hspeed,yspeed;
	
	public AI(int Startx,int Starty,int xDirection,int yDirection){
		super(Startx,Starty);
		super.mask = new Mask(this, 0, 0, 20, 20);
		followPath = false;
		followCharacter = false;
		DIRIndexX =xDirection;
		DIRIndexY = yDirection;
		NormDx = 5;
		NormDy = 5;
		
	}
	
	public AI(Path path,double NormDx,double NormDy, double MaxDx,double MaxDy) {
		super(path.getPoint(0).x, path.getPoint(0).y);
		mask = new Mask(this, 0, 0, 20, 20);
		followPath = true;
		followCharacter = false;
		this.path = path;
		this.x = path.getPoint(0).x;
		this.y = path.getPoint(0).y;
		this.NormDx = NormDx;
		this.NormDy = NormDy;
		this.MaxDx = MaxDx;
		this.MaxDy = MaxDy;
	}
	
//	public AI(Path path,Mask detectionMask,double NormDx,double NormDy, double MaxDx,double MaxDy){
//		super(path.getPoint().x, path.getPoint().y);
//		mask = new Mask(this, 0, 0, 20, 20);
//		this.detectionMask = detectionMask;
//		this.detectionMask.setThing(this);
//		followPath = true;
//		followCharacter = true;
//		this.path = path;
//		this.x = path.getPoint().x;
//		this.y = path.getPoint().y;
//		this.NormDx = NormDx;
//		this.NormDy = NormDy;
//		this.MaxDx = MaxDx;
//		this.MaxDy = MaxDy;
//	}
	
	public void blockCollision(int side){
		
		if(!followPath && !followCharacter){
			Random generate = new Random();
			DIRIndexX =  generate.nextInt(2);
			DIRIndexY =  generate.nextInt(2) + 2;
		}
	}
	
	protected void followingPath(){
		if(path.isPositive() ){
			moveTo( path.getNextPoint() );
			
			if(DIRIndexX==stop && DIRIndexY == stop){
				
				path.StepIndex();
				
			}
	
		}
		else{
			moveTo( path.getLastPoint() );
			
			if(DIRIndexX==stop && DIRIndexY == stop){
				
				path.StepIndex();
				
			}
		}
		
	}
	
	protected void followingPathWithGravity(){
		
	}
	
	private void moveTo(Point p){
		if(new Point(x,y) == p){
			DIRIndexX = stop;
			DIRIndexY = stop;
			
		}
		else{
			if(Math.abs(this.x - p.x) <hspeed)
				dx = 1;
			else if(Math.abs(this.x - p.x) >= hspeed)
				dx = hspeed;
			
			if(Math.abs(this.y - p.y) <yspeed)
				dy = 1;
			else if(Math.abs(this.y - p.y) >= yspeed)
				dy = yspeed;
			
			
			if(this.x > p.x){
				DIRIndexX = left;
//				dx = dx;
			}
			else if(this.x < p.x){
				DIRIndexX = right;
//				dx = dx;
			}
			else{
				DIRIndexX = stop;
				dx = 0;
			}
			
			if(this.y> p.y){
				DIRIndexY = up;
//				dy = dy;
			}
			else if(this.y< p.y){
				DIRIndexY = down;
//				dy = dy;
			}
			else{
				DIRIndexY= stop;
				dy = 0;
			}
				
			
		}
	}
	
	

	@Override
	public void Step() {
		
		
		
		
		switch(DIRIndexX){
		case AI.right:
			hspeed = NormDx;
			break;
		case AI.left:
			hspeed= -NormDy;
			break;
		case AI.stop:
			hspeed = 0;
			break;
		}
		switch(DIRIndexY){
		case AI.down:
			yspeed = NormDy;
			break;
		case AI.up:
			yspeed = -NormDy;
			break;
		case AI.stop:
			yspeed = 0;
			break;
		}
		
		if(followPath && !followCharacter)
			followingPath();

		x+=dx;
		y+=dy;
		
		
	}


	@Override
	public void render(Graphics g) {
		
		
	}

	@Override
	public void destroy() {
	gp.stuff.remove(this);	
		
	}
	
	public Mask getMask(){ return super.mask; }
	
	
	
	@Override
	public void addPanelRef(GamePanel gp) {
		super.addPanelRef(gp);
		characters = Character.findCharacters(gp);
	}

}
