/**
 * 
 */
package notable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shubham
 *
 */

/**
 * Example DataStore class that provides access to user data.
 * Pretend this class accesses a database.
 */
public class DataStore {

	//Map of names to Patient instances.
	private Map<String, Patient> PatientMap = new HashMap();
	
	private Map<String, String> DoctorsMap = new HashMap<>();
	
	//this class is a singleton and should not be instantiated directly!
	private static DataStore instance = new DataStore();
	public static DataStore getInstance(){
		return instance;
	}

	//private constructor so people know to use the getInstance() function instead
	//key is the doctor name and values put will be the patients records
	private DataStore(){
		//dummy data
		PatientMap.put("Sterling", new Patient(null, null, null));
		PatientMap.put("Cyril", new Patient(null, null, null));
		PatientMap.put("Ray", new Patient(null, null, null));
		PatientMap.put("Lana", new Patient(null, null, null));
		PatientMap.put("Pam", new Patient(null, null, null));
	}

	public Patient getPerson(String name) {
		return PatientMap.get(name);
	}

	public void putPerson(Patient Patient) {
		PatientMap.put(Patient.getName(), Patient);
	}
}
