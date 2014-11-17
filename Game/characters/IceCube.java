package characters;

import java.awt.Graphics;

import main.LM;
import Graphic.Sprite;
import tools.AI;
import tools.Block;
import tools.Mask;
import tools.Path;

public class IceCube extends AI{
	
	private Sprite sprite;

	public IceCube(Path path) {
		super(path,3,3,6,6);
		sprite = new Sprite(.1, .1, Sprite.left, LM.ICECUBE);
		mask = new Mask(this, 0, 0, sprite.xscale*sprite.getWidth(), sprite.yscale*sprite.getHeight() );
		DIRIndexX = AI.right;
	}
	
	
	
	@Override
	public void Step() {
		super.Step();
		sprite.Step();
		collisionCheck();
	}
	
	private void collisionCheck(){
//		for( int bbb = 0; bbb<gp.stuff.size(); bbb++)
//			if( gp.stuff.get(bbb) instanceof Block)
//				if(this.mask.collidesWith( gp.stuff.get(bbb).mask ) )
//					System.out.println("block");
					
	}
	
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		sprite.render(g, x, y);
	}

}
