package mapcreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;







import tools.Path;

public class TempThing {
	
	String id;
	public int x,y;
	boolean visibility;
	ArrayList<SubThings> subs = new ArrayList<SubThings>();
	
	public TempThing(String id,int x, int y,boolean visible,String[] packets){
		this.id = id;
		this.x = x;
		this.y = y;
		visibility = visible;
		if(packets!=null){
			for(int bbb = 0; bbb<packets.length; bbb++)
				subs.add( new SubThings( packets[bbb] ) );
		}
	}
	
	public void Step(){
		
	}
	
	public void render(Graphics g){
		if(visibility)
			g.setColor(Color.BLACK);
		else
			g.setColor(Color.RED);
		
		g.fillRect(x*Manipulator.GridSpacing,Manipulator.y +( (y)*Manipulator.GridSpacing), Manipulator.GridSpacing, Manipulator.GridSpacing);
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 10) );
		g.drawString( String.valueOf( id.charAt(0) ) ,x*Manipulator.GridSpacing +2,Manipulator.y + (y*Manipulator.GridSpacing) + Manipulator.GridSpacing);
		
		if(Panel.Selected==this)
			for(int bbb = 0; bbb<subs.size(); bbb++)
				subs.get(bbb).render(g);
	}
	
	public void addPath(){
		subs.add(0, new SubThings() );
		subs.get(0).path = new Path();
	}
	
	public void addPoint(int Index,Point p){	subs.get(0).path.addPoint(Index, p); }
	public boolean pathExists(){ return subs.size()>0; }
	public void removePoint(int Index){subs.get(0).path.removePoint(Index); }
	public int getPointIndex(Point p){
		for(int bbb =0; bbb<subs.get(0).path.size(); bbb++)
			if(p == subs.get(0).path.getPoint(bbb) )
				return bbb;
		return -1;
	}
	
	public String toString(){
		String temp = id + " " + x + " " + y;
		if(visibility)
			temp += " true";
		else
			temp += " false";
		for(int bbb = 0; bbb<subs.size(); bbb++)
			temp+= " " + subs.get(bbb).toString();
		
		return temp;
	}

}
