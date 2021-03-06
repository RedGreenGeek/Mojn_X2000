package framework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class ParticipationList {
	public ParticipationList(LinkedList<Person> ll, boolean department, boolean birthday, boolean address, boolean tribe) throws IOException{
		String s = this.makeString(ll,department, birthday, address, tribe);
		DateFormat dateFormat = new SimpleDateFormat("dd_MM_yy_HH_mm");
		Date date = new Date();
        String absoluteFilePath = System.getProperty("user.home") + System.getProperty("file.separator") + "ParticipationLists"+System.getProperty("file.separator")+dateFormat.format(date)+".csv";
        File file = new File(absoluteFilePath);
        file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.append(s); 
        fw.close();
	}

	private String makeString(LinkedList<Person> ll, boolean department, boolean birthday, boolean address, boolean tribe) {
		String result = "ID;Name"; 
		if (department) {
			result += ";Department";
		}
		if (birthday) {
			result += ";Birthday";
		}
		if (address) {
			result += ";Address";
		}
		if (tribe) {
			result += ";Tribe";
		}
		Person p;
		while (!ll.isEmpty()) {
			p = ll.removeFirst();
			result += "\n"+p.getID()+";"+p.getLastName()+", "+p.getFirstName(); 
			if (department) {
				result += ";"+p.getDepartment();
			}
			if (birthday) {
				result += ";"+p.getBirthday();
			}
			if (address) {
				result += ";"+p.getAdress();
			}
			if (tribe) {
				result += ";"+p.getTribe();
			}
		}
		return result;
	}
}
