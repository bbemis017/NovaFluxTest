package runtime;

import java.awt.image.BufferedImage;

import java.io.IOException;
import java.io.InputStream;

import java.net.URL;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.SwingWorker;

import tools.ProgressBar;

/**
 * This class should be used to load any resources into the game
 * I would recommend that a subclass should be made and used as a Loading Manifest
 * so that all files can be loaded before the game gets going and can be 
 * accessed statically
 * 
 * @author Ben Bemis
 *
 */

public abstract class Loader extends SwingWorker<Object, Object>{
	
	protected double Progress = 0;
	protected static ProgressBar pb;
	protected static Long threadId;
	
	/**
	 * When this constructor is called everything  in the init() method is done first and a progressbar
	 * is assigned to this class. Then a worker thread is started. Everything you want accomplished
	 * in the worker thread should be done in the LoadingSegments()
	 * @param pb
	 */
	public Loader(ProgressBar pb){
		Loader.pb = pb;
		init();
		execute();
		
	}
	/**
	 * 
	 * @return-ProgressBar- associated with Loader thread if one exists
	 */
	public static ProgressBar getProgressBar(){ return pb; }
	
	
	/**
	 * Updates Progress of Loading
	 * @param Progress
	 */
	protected void UpdateProgress(double Progress){
		this.Progress = Progress;
		if(pb!=null)
			pb.Update(this.Progress);
	}
	/**
	 * This method is called before the worker thread is started.
	 * If there are any Items that need to be loaded Immediately this is the place
	 */
	protected abstract void init();
	
	/**
	 * is called within worker thread. most items to be loaded should be put here
	 * @throws Exception
	 */
	protected abstract void LoadingSegments() throws Exception;
	
	@Override
	protected Object doInBackground() throws Exception {
		
		threadId = Thread.currentThread().getId();
		Thread.sleep(200);
		LoadingSegments();
		
		
		return null;
	}
	/**
	 * Early Termination of Worker Thread
	 */
	@SuppressWarnings("deprecation")
	public static void End(){
		
		Thread[] threads = new Thread[Thread.activeCount()];
		Thread.enumerate(threads);
		for (Thread t: threads) { 
		    if (t.getId() == threadId) { t.stop();}
		}
	}
	/**
	 * Use this method to load a BufferedImage
	 * @param name
	 * @return
	 */
	public static BufferedImage Image(String path){
		
		try{
			BufferedImage Bi =  ImageIO.read( Loader.class.getResourceAsStream(path) );
			if(Bi==null)
				System.out.println("Invalid Image path: "  + path );
			return Bi;
		}catch(IOException e ){
			e.printStackTrace();
			System.out.println("Invalid Image path: "  + path );
		}
		return null;
	}
	
	/**
	 * This should no longer be necessary and will be removed in the near future
	 * @param path
	 * @return
	 */
	public static URL Audio(String path){//TODO:
		URL u = Loader.class.getResource( path);
		if(u==null)
			System.out.println("Invalid Audio path: " + path.toString() );
		return u;
	}
	
	/**
	 * This method returns a inputStream from the path
	 * @param path
	 * @return - InputStream normally used for loading Ogg files
	 */
	public static InputStream getInputStream(String path){
		return Loader.class.getResourceAsStream(path);
	}
	/**
	 * 
	 * @param path
	 * @return Scanner- this is normally used for loading text Files
	 */
	public static Scanner getFileScanner(String path){
		return new Scanner(Loader.class.getResourceAsStream(path) );
		
	}

	
}
