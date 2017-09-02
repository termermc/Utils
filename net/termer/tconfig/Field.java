package net.termer.tconfig;

public interface Field {
	public String getValue();
	public String getName();
	public int getLine();
	
	@Deprecated
	/**
	 * Does not set the value in the file, only in this object.
	 * Instead, you should use TConfigWriter.setValue()
	 */
	public void setValue(String value);
	
	@Deprecated
	/**
	 * Does not set the name in the file, only in this object.
	 */
	public void setName(String name);
}
