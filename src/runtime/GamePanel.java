package runtime;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Graphic.Camera2D;
import Reference.*;
import tools.*;



public class GamePanel extends JPanel{
	
	/**
	 * This Class controls the Game Loop and most major parts of the game
	 * 
	 * @author Ben Bemis
	 */
	private static final long serialVersionUID = -2380805794631269689L;
	public ArrayList<Thing> stuff = new ArrayList<Thing>();
	public ArrayList<Integer> Alarms = new ArrayList<Integer>();
	public ArrayList<Boolean> AlarmStatus = new ArrayList<Boolean>();
	
	public BufferedImage background;
	
	public Driver game;
	
	
	public PanelState state = PanelState.STOPPED;
	private boolean running = false;
	private boolean inRunLoop = false;
	
	private Stepping[] stepping = new Stepping[4];
	private Thread[] threads = new Thread[4];
	
	protected int SPS,FPS; //SPS - Steps in the last Second - Steps per second
						 //FPS - number of renders in the last second - frames per second
	
	protected Camera2D camera;
	
	/**
	 * Creates new GamePanel
	 * @param game
	 */
	public GamePanel(Driver game){
		
		setBackground(Color.WHITE);
		setDoubleBuffered(true);
		
		this.game = game;
		
		
	}
	
	/**
	 * Called when panel State is Running
	 * if camera object is initialized the camera will be stepped before anything else happens
	 */
	public void Step() {
		
		
		
		if(camera!=null){
			camera.Step();
			Driver.frame.setLocation(-(int)camera.getX(), -(int)camera.getY() );
		}

		
		for (int bbb = 0; bbb < AlarmStatus.size(); bbb++) {

			if (AlarmStatus.get(bbb)) {
				if (Alarms.get(bbb) > 0)
					Alarms.add(bbb, Alarms.get(bbb) - 1);
				else if (Alarms.get(bbb) <= 0) {
					AlarmStatus.add(bbb, false);
					AlarmActive(bbb);
				}
			}

		}
		
		for(int bbb = 0; bbb < stuff.size(); bbb++){
			Thing thing = stuff.get(bbb);
			if(thing.mask != null){
				if(thing.mask.getRect().intersects( Driver.frame ) )
					thing.setRenderBoolean(true);
				else
					thing.setRenderBoolean(false);	
			}else if(thing.mask == null)
				thing.setRenderBoolean(true);
		}
	}
	
	/**
	 * Called when panel state is Running and needs to be painted
	 * if camera is initialized the camera will be scrolled before anything else is painted
	 */
	public void paint(Graphics g){
		super.paint(g);
		
		if(camera!=null)
			camera.Scroll(g);
		
		
		for (int bbb = 0; bbb < stuff.size(); bbb++)
			if( stuff.get(bbb).renderMe() )
				((Thing) stuff.get(bbb)).render(g);

		
		
	}
	
	
	

	
	/**
	 * Called when panel state is Paused
	 * if camera is initialized camera will be stepped
	 */
	public void PausedStep() {
		
		if(camera!=null)
			camera.Step();
		
	}
	/**
	 * 
	 * Called when panel state is Paused
	 * if camera is initialized camera will be scrolled first
	 */
	public void PausedPaint(Graphics g) {
		super.paint(g);
		if(camera!=null)
			camera.Scroll(g);
	}
	
	/**
	 * Called When an Alarm goes off
	 */
	public void AlarmActive(int position){}

	
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	
	/**
	 * Starts the infinite loop for the GamePanel should be the last to be
	 * called from the driver
	 */
	public void startPanel(){ state = PanelState.RUNNING; start(); }
	
	/**
	 * Stops the infinite loop for the GamePanel should only be called within a
	 * separate thread.
	 * 
	 * Use the DriverAction class to call this method
	 */
	public void stopPanel() { stop(); state = PanelState.STOPPED;  }
	
	
	
	public void start(){
		running = true;
		run2();
	}
	
	private void stop(){
		running = false;
	}
	
	public void run2(){
		
		while(running){
			inRunLoop = true;
			boolean threadStarted = false;
			//separate into threads
			Stepping[] stepping = new Stepping[4];
			Thread[] threads = new Thread[4];
			int length = stuff.size() / 4;
			for ( int thread = 0; thread < 4; thread++){
				stepping[thread] = new Stepping( Stepping.getSection(stuff, thread*length, (thread+1)*length) );
				threads[thread] = new Thread( stepping[thread] );
			}
			
			if(state == PanelState.RUNNING){
				
				//step through all threads
				threadStarted = true;
				//start threads
				for ( int thread = 0; thread< threads.length; thread++)
					threads[thread].start();
				
				Step();
			}
			
			else if(state == PanelState.PAUSED)
				PausedStep();
			else if(state == PanelState.STOPPED)
				return;
				
			else
				System.out.println("Panel Run State Undefined under Engine.GamePanel.run: " + state.toString() );
			
			
			if ( threadStarted){
				//wait till all threads are finished
				for( int thread = 0; thread< threads.length; thread++){
					try {
						threads[thread].join();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
			//repaint screen
			repaint();
			
			//limit speed of game loop
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		inRunLoop = false;
		
		stop();
	}
	
	
	
	
	/**
	 * NEVER ALTER THIS METHOD
	 * NEVER CALL THIS METHOD IN A SUBCLASS
	 * test change
	 */
	public void run() {
		
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;
		double n = 1000000000 / numTicks;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
			long currentTime = System.nanoTime();
			delta +=(currentTime - lastTime)/n;
			lastTime = currentTime;
			
			if(delta >=1){
				if(state == PanelState.RUNNING)
					Step();
				
				else if(state == PanelState.PAUSED)
					PausedStep();
				else if(state == PanelState.STOPPED)
					return;
					
				else
					System.out.println("Panel Run State Undefined under Engine.GamePanel.run: " + state.toString() );
				ticks++;
				delta--;
			}
			
			if(state == PanelState.RUNNING || state ==PanelState.PAUSED)
				repaint();
			else if(state == PanelState.PAUSED)
				PausedPaint( super.getGraphics() );
			else if(state == PanelState.STOPPED)
				return;
			else
				System.out.println("Panel Paint State Undefined under Engine.GamePanel.run: " + state.toString() );
			

			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer+=1000;
//				System.out.println(ticks + " Ticks, FPS: " + frames);
//				game.setTitle( "        Ticks: " + ticks + "    FPS: " + frames);
				//ticks are the number of Iterations in the last second
				//FPS is the number of renders in the last second
				this.SPS = ticks;
				this.FPS = frames;
				ticks = 0;
				frames = 0;
			}
		}
		
		stop();
		
	}
	
	public boolean isInRunLoop(){ return inRunLoop; }
	


	
}
