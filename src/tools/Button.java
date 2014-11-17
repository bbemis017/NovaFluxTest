package tools;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;


/**
 * This class is a simple outline for a Button
 * @author Ben Bemis
 *
 */
public class Button {
	
	public Rectangle rect;
	private boolean MouseInRegion = false;
	protected boolean Enabled;
	private boolean Selected =false;
	protected String text;
	protected int xOffset;
	protected int yOffset =0;
	
	protected Color hovering = Color.ORANGE,normal = Color.WHITE,disabled = Color.GRAY;
	
	protected Font font = new Font("Arial", Font.BOLD, 18);
	
	
	public Button(String text,Rectangle rect,int xOffset){
		
		this.rect = rect;
		this.text = text;
		this.xOffset = xOffset;
		this.Enabled = true;
	}
	/**
	 * Tells the Button if it is Enabled or not
	 * @param Enabled
	 */
	public void Enable(boolean Enabled){ this.Enabled = Enabled; }
	
	/**
	 * This checks if the Mouse has moved within the Button
	 * Should be called from a GamePanel
	 * @param e
	 */
	public void MouseMoved(MouseEvent e){
		Rectangle mouse = new Rectangle(e.getX(), e.getY()-10, 1, 1);
		if( Enabled && rect.intersects(mouse) )
			MouseInRegion = true;
		else
			MouseInRegion = false;
	}
	
	/**
	 * checks if the Mouse Click occured inside the Button Area
	 * Should be called from a GamePanel
	 * @param e
	 */
	public void MouseClicked(MouseEvent e){
		Rectangle mouse = new Rectangle(e.getX(), e.getY()-10, 1, 1);
		if( Enabled && rect.intersects(mouse) ){
			Selected = true;
			Clicked();
		}
		else
			Selected = false;
	}
	
	public boolean isSelected(){ return Selected; }
	
	/**
	 * This method should be Overriden for every Button
	 * This method is called when the Button has been clicked
	 */
	public void Clicked(){}
	
	/**
	 * renders the Button
	 * @param g
	 */
	public void render(Graphics g){
		g.setFont(font);
		if(!Enabled){
			g.setColor(disabled);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
			g.drawString(text,rect.x + xOffset,rect.y + 20 + yOffset);
		}
		else if(!MouseInRegion){
			g.setColor(normal);
			g.drawRect(rect.x, rect.y, rect.width, rect.height);
			g.drawString(text,rect.x + xOffset,rect.y + 20 + yOffset);
		}
		else if(MouseInRegion && Enabled){
			g.setColor(hovering);
			g.fillRect(rect.x, rect.y, rect.width, rect.height);
			g.setColor(Color.WHITE);
			g.drawString(text,rect.x + xOffset,rect.y + 20 + yOffset);
		}
	}

}
