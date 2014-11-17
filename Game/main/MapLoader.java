package main;

import characters.Blob;
import characters.IceCube;
import tools.Block;
import tools.Path;
import tools.Thing;

public class MapLoader extends Graphic.TextMap{

	public MapLoader(String path) {
		super(path);
		
	}

	@Override
	protected Thing IDAssignor(String ID, int x, int y, boolean visibility,String[] objs) {
		
		switch(	ref.ID.valueOf(ID) ){
		case BLOB:
			return (Thing)new Blob(x, y, null) ;
			
		case BLOCK:
			return (Thing)new Block(x, y, visibility, null) ;
			
		case ICECUBE:
			return (Thing)new IceCube( new Path(objs[0])   ) ;
			
		default:
			System.out.println("ID undefined: " + ID);
			return null;
		}
		
		
	}

}
