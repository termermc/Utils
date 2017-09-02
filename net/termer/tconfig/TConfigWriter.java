package net.termer.tconfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.termer.tconfig.exception.InvalidSyntaxException;
import net.termer.utils.Utils;

public class TConfigWriter {
	File c = null;
	boolean f = false;
	public TConfigWriter(File config, boolean ignoreFaults) throws FileNotFoundException {
		c = config;
		f = ignoreFaults;
		if(!c.exists()) {
			throw new FileNotFoundException("Config file not found");
		}
	}
	public void addField(String name, String value) throws InvalidSyntaxException, FileNotFoundException, IOException {
		boolean err = false;
		if(name.contains("#") || value.contains("#") || name.contains(":") || value.contains(":")) {
			if(!f) { 
				err = true;
				throw new InvalidSyntaxException("Name and value connot contain # or :");
			}
		}
		if(!err) {
			Utils.writeFile(c.getAbsolutePath(), new String[] {"",name.trim()+":"+value.trim()}, true);
		}
	}
	public void setField(String name, String value) throws FileNotFoundException, IOException {
		String[] lns = Utils.getFile(c);
		for(int i = 0; i < lns.length; i++) {
			
		}
	}
	
	public boolean getIgnoreFaults() {
		return f;
	}
}
