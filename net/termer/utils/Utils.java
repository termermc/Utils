package net.termer.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Utils {
	public static String[] getFile(File file) throws FileNotFoundException, IOException {
		ArrayList<String> lines = new ArrayList<String>();
		Scanner scn = new Scanner(file);
		while(scn.hasNext()) {
			lines.add(scn.nextLine());
		}
		scn.close();
		return lines.toArray(new String[0]);
	}
	public static boolean writeFile(String file, String[] lines, boolean append) throws FileNotFoundException, IOException {
		FileOutputStream output = new FileOutputStream(file, append);
		PrintWriter writer = new PrintWriter(output);
		for(int i = 0; i < lines.length; i++) {
			writer.println(lines[i]);
		}
		writer.close();
		return true;
	}
	public static void unzip(String file,String location) throws IOException {
		int BUFFER = 80000;
		BufferedOutputStream dest = null;
        FileInputStream fis = new 
	   FileInputStream(file);
        ZipInputStream zis = new 
	   ZipInputStream(new BufferedInputStream(fis));
        ZipEntry entry;
        while((entry = zis.getNextEntry()) != null) {
           int count;
           byte data[] = new byte[BUFFER];
           // write the files to the disk
           FileOutputStream fos = new 
	      FileOutputStream(location+entry.getName());
           dest = new 
             BufferedOutputStream(fos, BUFFER);
           while ((count = zis.read(data, 0, BUFFER)) 
             != -1) {
              dest.write(data, 0, count);
           }
           dest.flush();
           dest.close();
        }
        zis.close();
	}
	public static void download(String url, String name) throws IOException {
		String fileName = name;
		URL link = new URL(url);
		InputStream in = new BufferedInputStream(link.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf))) {
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(response);
		fos.close();
	}
}
