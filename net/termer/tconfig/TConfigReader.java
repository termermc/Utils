package net.termer.tconfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import net.termer.tconfig.exception.InvalidSyntaxException;
import net.termer.utils.Utils;

public class TConfigReader {
	File c = null;
	boolean f = false;
	public TConfigReader(File config, boolean ignoreFaults) throws FileNotFoundException {
		c = config;
		f = ignoreFaults;
		if(!c.exists()) {
			throw new FileNotFoundException("Config file not found");
		}
	}
	
	public File getFile() {
		return c;
	}
	
	public HashMap<String,String> getHashMap() throws FileNotFoundException, IOException, InvalidSyntaxException {
		HashMap<String,String> tmp = new HashMap<String,String>();
		String[] lns = Utils.getFile(c);
		for(int i = 0; i < lns.length; i++) {
			try {
				if(!lns[i].startsWith("#") && !lns[i].isEmpty()) {
					tmp.put(lns[i].split(":")[0].trim(), lns[i].split(":")[1].trim());
				}
			} catch(Exception e) {
				if(!f) {
					throw new InvalidSyntaxException("Invalid syntax at line "+Integer.toBinaryString(i)+" of file "+c.getName());
				}
			}
		}
		return tmp;
	}
	public HashSet<Field> getFields() throws InvalidSyntaxException, FileNotFoundException, IOException {
		HashSet<Field> tmp = new HashSet<Field>();
		String[] lns = Utils.getFile(c);
		for(int i = 0; i < lns.length; i++) {
			try {
				if(!lns[i].startsWith("#") && !lns[i].isEmpty()) {
					tmp.add((Field)new TField(lns[i].split(":")[0].trim(),lns[i].split(":")[1].trim(), i));
				}
			} catch(Exception e) {
				if(!f) {
					throw new InvalidSyntaxException("Invalid syntax at line "+Integer.toBinaryString(i)+" of file "+c.getName());
				}
			}
		}
		return tmp;
	}
	public HashSet<String> getValues() throws InvalidSyntaxException, FileNotFoundException, IOException {
		HashSet<String> tmp = new HashSet<String>();
		String[] lns = Utils.getFile(c);
		for(int i = 0; i < lns.length; i++) {
			try {
				if(!lns[i].startsWith("#") && !lns[i].isEmpty()) {
					tmp.add(lns[i].split(":")[1].trim());
				}
			} catch(Exception e) {
				if(!f) {
					throw new InvalidSyntaxException("Invalid syntax at line "+Integer.toBinaryString(i)+" of file "+c.getName());
				}
			}
		}
		return tmp;
	}
	public Field getField(String field) {
		Field tmp = null;
		Field[] lns;
		try {
			lns = getFields().toArray(new Field[0]);
			for(int i = 0; i < lns.length; i++) {
				if(lns[i].getName().equalsIgnoreCase(field)) {
					tmp = (Field)lns[i];
					break;
				}
			}
		} catch(Exception e) {}
		return tmp;
	}
}
