package main;

import java.awt.image.BufferedImage;

import ref.Ref;
import runtime.Loader;
import tools.ProgressBar;

public class LM extends Loader{
	
	public static BufferedImage BLOB1,BLOB2,BLOB3,BLOB4,BLOB5,ICECUBE;
	public static MapLoader LEVEL1;

	public LM(ProgressBar pb) {
		super(pb);
		
	}

	@Override
	protected void init() {
		pb.Update(.01);
		
	}

	@Override
	protected void LoadingSegments() throws Exception {
		
		pb.Update(.02);
		
		BLOB1 = Loader.Image(Ref.BLOB_LOC + "A.png");
		BLOB2 = Loader.Image(Ref.BLOB_LOC + "B.png");
		BLOB3 = Loader.Image(Ref.BLOB_LOC + "C.png");
		BLOB4 = Loader.Image(Ref.BLOB_LOC + "D.png");
		BLOB5 = Loader.Image(Ref.BLOB_LOC + "E.png");
		ICECUBE = Loader.Image(Ref.RES_LOC + "IceCube.png");
		pb.Update(.1);
		LEVEL1 = new MapLoader(Ref.RES_LOC + "levelMap.txt");
		pb.Update(1.0);
	}

}
