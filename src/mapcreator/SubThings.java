package mapcreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.regex.Pattern;

import tools.Path;

public class SubThings {
	
	Rectangle rect;
	Path path;
	String id;
	
	public SubThings(String s){
		
		String[] data = s.split(Pattern.quote("/") );
		id = data[0];
		switch(id){
		case "PATH":
			path = new Path(s);
			break;
		case "RECTANGLE":
			rect = new Rectangle(Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]) );
			break;
		
		}
	}
	
	public SubThings(){
		
	}
	
	
	public String toString(){
		if(path!=null)
			return path.toString();
		else if(rect!=null)
			return id + "/" + rect.x +"/"+ rect.y +"/"+ rect.width +"/"+ rect.height;
		
		return null;
	}
	
	public void render(Graphics g){
		if(path!= null){
			
			for(int bbb = 0; bbb<path.size(); bbb++)
				if(path.getPoint(bbb)!=null){
					g.setColor(Color.blue);
					g.fillRect(path.getPoint(bbb).x*Manipulator.GridSpacing,Manipulator.y +( path.getPoint(bbb).y*Manipulator.GridSpacing), Manipulator.GridSpacing,Manipulator.GridSpacing );
					g.setFont(new Font("Arial", Font.BOLD, 10) );
					g.setColor(Color.WHITE);
					g.drawString( "" + bbb ,path.getPoint(bbb).x*Manipulator.GridSpacing +2,Manipulator.y + (path.getPoint(bbb).y*Manipulator.GridSpacing) + Manipulator.GridSpacing);
				}
			}
	}

}
