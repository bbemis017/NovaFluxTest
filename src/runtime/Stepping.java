package runtime;

import java.util.ArrayList;

import tools.Thing;

/**
 * This class accepts an ArrayList of things and is designed to Step through
 * a section of the elements in the array in a separate thread
 * 
 * @author Benjamin Bemis
 *
 */
public class Stepping implements Runnable{
	
	private ArrayList<Thing> things;
	private boolean finished;
	
	public Stepping(ArrayList<Thing> things){
		this.things = things;
		this.finished = false;
	}
	
	public boolean isFinished(){ return finished; }

	@Override
	public void run() {
		
		for (int bbb = 0; bbb < things.size(); bbb++)
			((Thing) things.get(bbb)).Step();
		
		finished = true;
		
	}
	
	public static ArrayList<Thing> getSection(ArrayList<Thing> things,int start, int stop){
		ArrayList<Thing> temp  = new ArrayList<Thing>();
		for( int i = start; i< stop; i++)
			temp.add( things.get(i) );
		
		return temp;
	}

}
