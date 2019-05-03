package framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private File file = null;
	private static Logger instance = null;
	public static synchronized Logger getInstance() {
		if (instance==null) {
			instance = new Logger();
		}
		return instance;
	}
	
	private Logger() {
		String fileSeparator = System.getProperty("file.separator");
		String filePath = "ParticipationLists"+fileSeparator+"log.csv";
        this.file = new File(filePath);
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
		FileWriter fw = null;
		try {
			fw = new FileWriter(this.file, true);
			fw.append("\n"+date+";"+user+";"+operation+";"+target);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}