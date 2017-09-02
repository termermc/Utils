package net.termer.tconfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import net.termer.utils.Utils;

public class TConfig {
	
	@Deprecated
	public static void createNew(File file) throws IOException {
		file.createNewFile();
		Utils.writeFile(file.getAbsolutePath(), new String[] {"#TConfig File","#"+new Date().toString()}, false);
	}
	
	public static void initialize(File file) throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		Utils.writeFile(file.getAbsolutePath(), new String[] {"#TConfig File","#"+new Date().toString()}, false);
	}
	
	private TConfigReader r = null;
	private TConfigWriter w = null;
	
	public TConfig(File config, boolean ignoreFaults) {
		if(!config.exists()) {
			try {
				initialize(config);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				r = new TConfigReader(config, ignoreFaults);
				w = new TConfigWriter(config, ignoreFaults);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	public TConfigReader getReader() {
		return r;
	}
	public TConfigWriter getWriter() {
		return w;
	}
}