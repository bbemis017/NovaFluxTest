package main;

import mapcreator.CreatorApp;
import panels.Level;
import panels.Menu;
import runtime.Driver;

@SuppressWarnings("serial")
public class Game extends Driver{
	
	public Game(){
		super();
		set4_3Ratio( Driver.WIDTH = 800);
		setSize(Driver.WIDTH, Driver.HEIGHT);
		initPanel(CurrentPanel = 0);
		
	}
	
	@Override
	public void initPanel(int NextPanel) {
		switch(NextPanel){
		case 0:
			panels.add(0, new Menu( (Driver)this) );
			break;
		case 1:
			panels.add(1, new Level( (Driver)this ) );
			break;
		}
	}
	
	public static void main(String[] args){
		
		game =  new Game();
		game.add( panels.get(CurrentPanel) );
		( panels.get(CurrentPanel) ).setVisible(true);
		game.setVisible(true);
		
		panels.get(CurrentPanel).startPanel();
		
//		new CreatorApp(ref.ID.class);
	}

}
