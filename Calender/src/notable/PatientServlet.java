package notable;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class PatientServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("/patient/".length());
		
		Patient Patient = DataStore.getInstance().getPerson(name);
		
		if(Patient != null){
			String json = "{\n";
			json += "\"name\": " + JSONObject.quote(Patient.getName()) + ",\n";
			json += "\"date\": " + JSONObject.quote(Patient.getDate()) + ",\n";
			json += "\"kind\": " + JSONObject.quote(Patient.getKind()) + ",\n";
			json += "}";
			response.getOutputStream().println(json);
		}
		else{
			//That person wasn't found, so return an empty JSON object. We could also return an error.
			response.getOutputStream().println("Patient record not found}");
		}
	}
	
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		Date date = request.getParameter("date");
		String kind = Integer.parseInt(request.getParameter("kind"));
		
		DataStore.getInstance().putPerson(new Patient(name, date, kind));
	}
}