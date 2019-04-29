package framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	private File file;
	Logger() throws IOException{
		String fileSeparator = System.getProperty("file.separator");
		String filePath = "SystemLog"+fileSeparator+"log.csv";
        this.file = new File(filePath);
        if (!file.exists()) { 
        	file.createNewFile();
        	FileWriter fw = new FileWriter(file);
            fw.append("TIMESTAMP;USER;OPERATION;TARGET");
            fw.close();
        }
	}
	
	protected void write(String user, String operation, String target) throws IOException {
		Date date = new Date();
		FileWriter fw = new FileWriter(file, true);
        fw.append("\n"+date+";"+user+";"+operation+";"+target);
        fw.close();
	}
}