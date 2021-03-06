package notable;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class RestApiClient {

	public static void main(String[] args) throws IOException{
		
		
		List<String> doctor_list = new ArrayList<String>();
		
		doctor_list.add("Hibbert, Julius");
		doctor_list.add("Anderson, Justin");
		doctor_list.add("Bell, Rick");
		doctor_list.add("Cook, Tim");
		doctor_list.add("Jones, John");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the Patient Appoinment Scheduler");
		System.out.println("(Type 'get' to get the list of doctors or 'put' to schdule appointment now.)");
		String getOrPut = scanner.nextLine();
		if("get".equalsIgnoreCase(getOrPut)){
			System.out.println("Whose schedule do you want to get?");
			System.out.println("(Type a doctor's name now.)");
			String name = scanner.nextLine();
			
			//time and date list
			
			String jsonString = getData(name);
			JSONObject jsonObject = new JSONObject(jsonString);

			Date date = jsonObject.getInt("Time");
			System.out.println(name + "'s available at" + date + ".");
			
			String kind = jsonObject.getString("");
			System.out.println(kind);
		}
		else if("put".equalsIgnoreCase(getOrPut)){
			System.out.println("Which doctor's appointment do you want to schedule?");
			System.out.println("(Type a doctors's name now.)");
			String name = scanner.nextLine();
			
			if(doctor_list.contains(name)){
				System.out.println("What time?");
				Date date = scanner.next();
				//method to check if the selected date and time is available or not
				
				
				System.out.println("Is this your first appointment with doctor"+ /*doctor.name*/ +"?");
				System.out.println("(Type a sentence now.)");
				String kind = scanner.nextLine();
			}
			
			
		
			
			
			setPatientData(name, date, kind);
		}
		
		scanner.close();
		
		System.out.println("Have a wonerful day!.");
		
	}
	
	public static String getDoctorData(String name) throws IOException{

		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/doctor/" + name).openConnection();
		
		connection.setRequestMethod("GET");

		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			String response = "";
			Scanner scanner = new Scanner(connection.getInputStream());
			while(scanner.hasNextLine()){
				response += scanner.nextLine();
				response += "\n";
			}
			scanner.close();

			return response;
		}
		
		// an error happened
		return null;
	}
	
	public static void setPatientData(String name, Date date, String kind) throws IOException{
		HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/patient/" + name).openConnection();

		connection.setRequestMethod("POST");
		
		String postData = "name=" + URLEncoder.encode(name);
		postData += "&kind=" + URLEncoder.encode(kind);
		postData += "&date=" + date;
		
		connection.setDoOutput(true);
	    OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());
	    wr.write(postData);
	    wr.flush();
		
		int responseCode = connection.getResponseCode();
		if(responseCode == 200){
			System.out.println("POST was successful.");
		}
		else if(responseCode == 401){
			System.out.println("Service is not available");
		}
	}
}
