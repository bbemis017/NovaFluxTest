package runtime;

public class DriverAction implements Runnable{
	
	public static final int NEXT_PANEL = 0,STOP_PANEL = 1;
	
	private int nextPanel;
	private Driver d;
	private int action;
	
	public DriverAction(Driver d,int action, int nextPanel){
		this.nextPanel = nextPanel;
		this.d = d;
		this.action = action;
	}
	
	public DriverAction(Driver d,int action){
		this.d = d;
		this.action = action;
	}

	@Override
	public void run() {
		switch(action){
		case NEXT_PANEL:
			nextPanel();
			break;
		case STOP_PANEL:
			stopPanel();
			break;
		}
		
	}
	
	private void stopPanel() {
		GamePanel currentPanel = Driver.panels.get( Driver.CurrentPanel );
		
		currentPanel.stopPanel();

		while( currentPanel.isInRunLoop() );

		
	}

	private void nextPanel(){
		GamePanel currentPanel = Driver.panels.get( Driver.CurrentPanel );
		
		currentPanel.stopPanel();
		while( currentPanel.isInRunLoop() );
		//blocks thread until the current panel has stopped
		
		currentPanel.setVisible(false);
		Driver.game.remove(currentPanel);
		
		d.CurrentPanel = nextPanel;
		
		d.initPanel(nextPanel);
		GamePanel panel2 = d.panels.get(nextPanel);
		d.add( panel2 );
		panel2.setVisible(true);
		d.game.setVisible(true);
		
		panel2.startPanel();
	}

}
