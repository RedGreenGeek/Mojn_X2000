package framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private String filePath;
	
	public Logger(String folder) {
		this.filePath = System.getProperty("user.home") + System.getProperty("file.separator") +folder+System.getProperty("file.separator")+"log.csv";
		File file = new File(filePath);
        if (!file.exists()) { 
        	try {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.append("TIMESTAMP;USER;OPERATION;TARGET");
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
	}
	
	protected void write(String user, String operation, String target) {
		Date date = new Date();
		File file = new File(filePath);
		try {
			FileWriter fw = new FileWriter(file,true);
			fw.append("\n"+date+";"+user+";"+operation+";"+target);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}