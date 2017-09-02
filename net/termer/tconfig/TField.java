package net.termer.tconfig;

public class TField implements Field {
	private String n = null;
	private String v = null;
	private int l = -1;
	
	protected TField(String name, String value, int line) {
		n = name;
		v = value;
		l = line;
	}
	
	public String getValue() {
		return v;
	}
	public String getName() {
		return n;
	}
	public int getLine() {
		return l;
	}
	
	@Deprecated
	/**
	 * Does not set the value in the file, only in this object.
	 * Instead, you should use TConfigWriter.setValue()
	 */
	public void setValue(String value) {
		v = value;
	}
	
	@Deprecated
	/**
	 * Does not set the name in the file, only in this object.
	 */
	public void setName(String name) {
		n = name;
	}
}
