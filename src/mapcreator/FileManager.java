package mapcreator;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;



public class FileManager {
	
	private CreatorApp ca;
	
	public static String FileName;
	public static String Dir;
	
	public FileManager(CreatorApp ca){
		this.ca = ca;
	}
	
	public void LoadFile(){
		FileDialog fd = new FileDialog(ca,"Load Map",FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		fd.setVisible(true);
		FileName = fd.getFile();
		Dir = fd.getDirectory();
		if(Dir!=null)
			for(int bbb = 0; bbb<Manipulator.things.size(); bbb++)
				Manipulator.things.remove(bbb);
		
		System.out.println(Dir + FileName);
		
		try {
			
				File map = new File(Dir + FileName);
				if(map.exists()){
					Scanner read = new Scanner(map);
					
					while(read.hasNextLine()){
						String line = read.nextLine();
						if(!line.isEmpty()){
							String[] tokens = line.split(Pattern.quote(" ") );
							int x=0,y=0;
							String id="";
							boolean visible = false;
							ArrayList<String> obj = new ArrayList<String>();
							for(int i = 0; i<tokens.length; i++){
								switch(i){
								case 0:
									id = tokens[i];
									break;
								case 1:
									x = Integer.parseInt(tokens[i]);
									break;
								case 2:
									y = Integer.parseInt(tokens[i]);
									break;
								case 3:
									if(tokens[i].equalsIgnoreCase("true") )
										visible = true;
									else
										visible = false;
									break;
								default:
									obj.add(tokens[i]);
									break;
								}
							}
						
							String[] packets = new String[obj.size()];
							for(int bbb = 0; bbb<packets.length; bbb++)
								packets[bbb] = obj.get(bbb);

							Manipulator.things.add( new TempThing(id, x, y,visible,packets) );
						}
					}
					
					read.close();
				}
			
			
			
			
		}catch(IOException e){
			System.out.println("Error Loading File");
			e.printStackTrace();
		}
	}
	
	public void SaveFile(){
		
		FileDialog fd = new FileDialog(ca, "Save Map", FileDialog.SAVE);
		fd.setFilenameFilter(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		if(FileName==null)
			fd.setFile("UntitledMap.txt");
		else
			fd.setFile(FileName);
		
		fd.setVisible(true);
		FileName = fd.getFile();
		System.out.println("File: "+ FileName );
		Dir = fd.getDirectory();
		System.out.println("Directory: " + Dir );
		
		if(FileName!=null && Dir!=null){//Save Map
			File file = new File(Dir + FileName);
			if(!file.exists() ){
				try {
					file.createNewFile();
				} catch (IOException e) {
					System.out.println("Failed to Create new Text File");
					e.printStackTrace();
				}
			}
			
			try {
				
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				String data = "";
				for( int bbb = 0; bbb<Manipulator.things.size(); bbb++)
					data += "\n" + Manipulator.things.get(bbb).toString();
				fw.write(data);
				fw.close();
				
			} catch (IOException e) {
				System.out.println("Could not load File");
				e.printStackTrace();
			}
			
		}
	}

}
