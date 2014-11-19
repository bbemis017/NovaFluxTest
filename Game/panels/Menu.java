package panels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import main.LM;
import runtime.Driver;
import runtime.DriverAction;
import runtime.GamePanel;
import tools.Button;
import tools.ProgressBar;

public class Menu extends GamePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Button play,exit;
	private ProgressBar pb;

	public Menu(Driver game) {
		super(game);
		
		new LM( pb = new ProgressBar(50, Driver.HEIGHT - 100, 500, 50, Color.GREEN, Color.GRAY) );
		
		play = new Play();
		exit = new Exit();
	}
	
	
	
	@Override
	public void Step() {
		super.Step();
		if(pb.isFinished())
			play.Enable(true);
		
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Driver.WIDTH, Driver.HEIGHT);
		pb.render(g);
		play.render(g);
		exit.render(g);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
		play.MouseMoved(e);
		exit.MouseMoved(e);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		play.MouseClicked(e);
		exit.MouseClicked(e);
	}
	
	
	private class Play extends Button{

		public Play() {
			super("Play", new Rectangle(50, 50, 100, 100), 40);
			Enabled = false;
		}
		
		@Override
		public void Clicked() {
			super.Clicked();
			

			DriverAction da = new DriverAction(game,DriverAction.NEXT_PANEL, 1);
			Thread t = new Thread(da);
			t.start();
		}
		
	}
	
	private class Exit extends Button{
		public Exit(){
			super("Exit",new Rectangle(50,200,100,100),40);
		}
		
		@Override
		public void Clicked() {
			super.Clicked();
			game.destroy();
		}
	}

}
