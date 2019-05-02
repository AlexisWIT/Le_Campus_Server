package leCampusServer.utility;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import leCampusServer.repository.MemberRepository;
import leCampusServer.service.MemberService;

public class JSONValidator {
	
	// Check if String input is valid for parsing to JSON
	//public boolean isValidJSON (String inputString, Gson gson, ObjectMapper mapper) {
	public boolean isValidJSON (String inputString, Gson gson) {
		try {
			gson.fromJson(inputString, Object.class);		
			return true;
			
		} catch (Exception e1) {
			System.out.println("Invalid Input String");
			return false;	
		}
	}
	
	public boolean isJSONArray (String input) {
		try {
			new JSONArray(input);
			System.out.println("Input String can be parsed to JSON Array");
			return true;
			
		} catch (JSONException e2) {
			return false;
		}
	}
	
	public boolean isJSONObject (String input) {
		try {
			new JSONObject(input);
			System.out.println("Input String can be parsed to JSON Object");
			return true;
			
		} catch (JSONException e3) {
			return false;
		}
	}
	
	public boolean isValidDate (Integer dateInput, SimpleDateFormat dateFormat) {
//		Date date;
		LocalDate date;
		if (dateInput!=null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				date = LocalDate.parse(dateInput.toString(), formatter);	
				return true;
				
			} catch (DateTimeParseException e) {
				
				System.out.println("Invalid Date input: "+e.getMessage());
				return false;
				
			}
			
		} else {
			return true;
			
		}
		
	}

}