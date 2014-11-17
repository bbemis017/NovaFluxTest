package audio;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import runtime.Loader;
import newdawn.easyogg.OggClip;


/**
 * This class uses the .jogg, .jorbis, and newdawn.easyogg libraries that are included with this Library
 * 
 * This class Manages Ogg Clips. This allows you to control multiple Ogg files at once
 * 
 * @author Ben Bemis
 *	
 */
public class OggManager {
	
	private static Map<String,OggClip> oggMap = new HashMap<String,OggClip>();
	
	/**
	 * Adds a Ogg to the Map to be used later
	 * 
	 * @param id - String that is used to Identify Ogg from others
	 * @param path - String path that tells the program where the ogg is
	 */
	public static void addOgg(String id, String path){
		try {
			oggMap.put(id, new OggClip( Loader.getInputStream(path) ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param id - String Ogg id
	 * @return - OggClip for this id
	 * @return - null if this clip does not exists
	 */
	public static OggClip getOgg(String id){
		return oggMap.get(id);
	}
	
	/**
	 * plays Ogg file that is associated with this id once
	 * @param id
	 */
	public static void playOgg(String id){
		oggMap.get(id).play();
	}
	
	/**
	 * pauses Ogg file that is associated with this id
	 * @param id
	 */
	public static void pauseOgg(String id){
		oggMap.get(id).pause();
	}
	
	/**
	 * resumes Ogg file associated with this id
	 * @param id
	 */
	public static void resumeOgg(String id){
		oggMap.get(id).resume();
	}
	
	/**
	 * loops this Ogg file
	 * @param id
	 */
	public static void loopOgg(String id){
		oggMap.get(id).loop();
	}
	
	/**
	 * Stops this ogg file
	 * NOTICE: I have noticed some strange errors occasionally when this method is called be careful
	 * @param id
	 */
	public static void stopOgg(String id){
		oggMap.get(id).stop();
	}
	
	/**
	 * Closes all information associated with this Ogg file
	 * @param id
	 */
	public static void closeOgg(String id){
		oggMap.get(id).stop();
		oggMap.get(id).close();
		oggMap.remove(id);
	}
	
	/**
	 * Destroys all additional threads/bitstreams/Ogg's
	 * Should always be called before program termination
	 * The Engine automatically calls this method when you call Driver.destroy()
	 */
	public static void destroy(){
		
		for(OggClip o: oggMap.values() )
			o.stop();
		System.out.println("ALL BITSTREAMS CLOSED");
		
		oggMap.clear();

	}
	
	
	
	
}
