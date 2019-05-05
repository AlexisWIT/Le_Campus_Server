package leCampusServer.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import leCampusServer.domain.BuildingGeoFence;
import leCampusServer.domain.CrimeGeoFence;
import leCampusServer.domain.EmergencyRequest;
import leCampusServer.domain.Footprint;
import leCampusServer.domain.JSONResponse;
import leCampusServer.domain.JSONResponse;
import leCampusServer.domain.Member;
import leCampusServer.domain.PublicEvent;
import leCampusServer.repository.BuildingGeoFenceRepository;
import leCampusServer.repository.CrimeGeoFenceRepository;
import leCampusServer.repository.EmergencyRequestRepository;
import leCampusServer.repository.FootprintRepository;
import leCampusServer.repository.MemberRepository;
import leCampusServer.repository.PublicEventRepository;
import leCampusServer.repository.UserRepository;
import leCampusServer.service.BuildingGeoFenceService;
import leCampusServer.service.CrimeGeoFenceService;
import leCampusServer.service.EmergencyRequestService;
import leCampusServer.service.FootprintService;
import leCampusServer.service.MemberService;
import leCampusServer.service.PublicEventService;
import leCampusServer.service.UserService;
import leCampusServer.utility.DateFormatter;

@Controller
@RequestMapping("/")
public class InterfaceController {
	
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;
	
	@Autowired BuildingGeoFenceService buildingGeoFenceService;
	@Autowired CrimeGeoFenceService crimeGeoFenceService;
	@Autowired FootprintService footprintService;
	@Autowired PublicEventService publicEventService;
	@Autowired UserService userService;
	@Autowired EmergencyRequestService emergencyRequestService;
	
	@Autowired BuildingGeoFenceRepository buildingGeoFenceRepository;
	@Autowired CrimeGeoFenceRepository crimeGeoFenceRepository;
	@Autowired FootprintRepository footprintRepository;
	@Autowired PublicEventRepository publicEventRepository;
	@Autowired UserRepository userRepository;
	@Autowired EmergencyRequestRepository emergencyRequestRepository;
	
	
	RESTfulController restController;
	InputChecker inputChecker = new InputChecker();
	JSONObject jsonResponse = new JSONObject();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		System.out.println("IndexPage");
		return "IndexPage";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/LeCampusServer/BuildingGeoFence", method=RequestMethod.GET)
	public @ResponseBody JSONResponse showBuildingGeoFence() {	
		JSONResponse buildingGeoFenceResponse = new JSONResponse();
		List<BuildingGeoFence> buildingGeoFenceList = new ArrayList<>();
		
		System.out.println("Retrieving building geofence, please wait...");
		buildingGeoFenceList = (List<BuildingGeoFence>) buildingGeoFenceService.findAllBuildingGeoFences();
		
		buildingGeoFenceResponse.setBuildingGeoFenceList(buildingGeoFenceList);
		return buildingGeoFenceResponse;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/LeCampusServer/EmergencyRequest", method=RequestMethod.GET)
	@ResponseBody
	public JSONResponse showEmergencyRequest() {
		JSONResponse emergencyRequestResponse = new JSONResponse();
		List<EmergencyRequest> emergencyRequestList = new ArrayList<>();
		
		emergencyRequestList = (List<EmergencyRequest>) emergencyRequestService.findAllEmergencyRequests();
		
		emergencyRequestResponse.setEmergencyRequestList(emergencyRequestList);
		return emergencyRequestResponse;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/LeCampusServer/Footprint", method=RequestMethod.GET)
	@ResponseBody
	public JSONResponse showFootprint() {
		JSONResponse footprintResponse = new JSONResponse();
		List<Footprint> footprintList = new ArrayList<>();
		
		footprintList = (List<Footprint>) footprintService.findAllFootprints();
		
		footprintResponse.setFootprintList(footprintList);
		return footprintResponse;
		
	}
	
	// add building by JSON
	@RequestMapping(value="/LeCampusServer/Building", method=RequestMethod.PATCH)
	@ResponseBody
	public Object addMultiBuilding(@RequestBody String buildingJSONString) throws ParseException {
		System.out.println("Received request for adding builing ");
		JSONResponse buildingGeoFenceResponse = new JSONResponse();
		
		final Gson gson = new Gson();
		if (StringUtils.isBlank(buildingJSONString)) {
			System.out.println("String entered is blank");
			buildingGeoFenceResponse.setResult("false");
			buildingGeoFenceResponse.setMessage("String entered is blank");
			return buildingGeoFenceResponse;

		} else if (inputChecker.isValidJSON(buildingJSONString, gson)) {
				
			JSONArray jsonArray = new JSONArray(buildingJSONString);
			int progress = 0;
			for (int i=0; i<jsonArray.length(); i++) {
				JSONObject buildingJSON = jsonArray.getJSONObject(i);
				JSONObject properties = buildingJSON.getJSONObject("properties");
				JSONObject geometry = buildingJSON.getJSONObject("geometry");
				String baseUrl = "https://www.le.ac.uk/maps/assets/";
				
				try {
					BuildingGeoFence buildingGeoFence = new BuildingGeoFence();
					buildingGeoFence.setBuilding(properties.getString("building"));
					buildingGeoFence.setAddress(properties.getString("address"));
					buildingGeoFence.setImageURL("images/photos/"+properties.getString("photo"));
					buildingGeoFence.setWebsiteURL(properties.getString("urlOne"));
					buildingGeoFence.setCollegeName(properties.getString("collegeCorporate"));
					buildingGeoFence.setDirectionPoint(properties.getString("directionsButton").replace("//", ""));
					buildingGeoFence.setNodeList(geometry.getJSONArray("coordinates").toString());
					
					buildingGeoFenceService.saveBuildingGeoFence(buildingGeoFence);
					
				} catch (JSONException e) {
					buildingGeoFenceResponse.setResult("false");
					buildingGeoFenceResponse.setMessage("Error occured at building ["+i+"]: "+e.getMessage());
					return buildingGeoFenceResponse;
					
				}	
				
			}
			buildingGeoFenceResponse.setResult("true");
			buildingGeoFenceResponse.setMessage("Data of "+jsonArray.length()+"Buildings has been added to Database. ");
			System.out.println("Data of "+jsonArray.length()+"Buildings has been added to Database ");
			return buildingGeoFenceResponse;
			
		} else {
			buildingGeoFenceResponse.setResult("false");
			buildingGeoFenceResponse.setMessage("Error: Invalid JSON Data");
			return buildingGeoFenceResponse;
			
		}
		
	}
	
	// edit & update building geofence
	@RequestMapping(value="/LeCampusServer/Building", method=RequestMethod.PATCH)
	@ResponseBody
	public Object editGeoFence(@RequestBody String geoFence) throws ParseException {
		System.out.println("Received request for editing GeoFence :"+geoFence);
		final Gson gson = new Gson();
		JSONParser jsonParser = new JSONParser();
		JSONResponse jsonEditResponse = new JSONResponse();
		
		if (inputChecker.isValidJSON(geoFence, gson)) {
			
		} else {
			jsonEditResponse.setResult("false");
			jsonEditResponse.setMessage("Invalid input format!");
			return jsonEditResponse;
		}
		
		JSONObject jsonObject = (JSONObject) jsonParser.parse(geoFence);
		
		Integer serverId = Integer.valueOf((String) jsonObject.get("serverId"));
		
		//TODO finish if possible
		
		jsonEditResponse.setResult("true");
		jsonEditResponse.setMessage("GeoFence info has been updated.");
		return jsonEditResponse;
		
	}
	
	// Get all crime data
	@RequestMapping(value = "/LeCampusServer/Crime", method = RequestMethod.GET)
	public @ResponseBody JSONResponse showCrimeGeoFence() {	
		JSONResponse crimeGeoFenceResponse = new JSONResponse();
		List<CrimeGeoFence> crimeGeoFenceList = new ArrayList<>();
		
		System.out.println("Retrieving Crime geofence, please wait...");
		crimeGeoFenceList = (List<CrimeGeoFence>) crimeGeoFenceService.findAllCrimeGeoFences();
		
		crimeGeoFenceResponse.setCrimeGeoFenceList(crimeGeoFenceList);
		return crimeGeoFenceResponse;
	}
		
		
	// Add crime data by JSON
	@RequestMapping(value = "/LeCampusServer/Crime", method = RequestMethod.POST)
	public @ResponseBody JSONResponse addCrimeGeoFence(@RequestBody String crimeDataJSONString) {	
		JSONResponse crimeGeoFenceResponse = new JSONResponse();
		
		final Gson gson = new Gson();
		if (StringUtils.isBlank(crimeDataJSONString)) {
			System.out.println("String entered is blank");
			crimeGeoFenceResponse.setResult("false");
			crimeGeoFenceResponse.setMessage("String entered is blank");
			return crimeGeoFenceResponse;

		} else if (inputChecker.isValidJSON(crimeDataJSONString, gson)) {
				
			JSONArray jsonArray = new JSONArray(crimeDataJSONString);
			int progress = 0;
			for (int i=0; i<jsonArray.length(); i++) {
				JSONObject crimeJSON = jsonArray.getJSONObject(i);
				
				try {
					String category = crimeJSON.getString("category");
					String latitude = crimeJSON.getJSONObject("location").getString("latitude");
					String longitude = crimeJSON.getJSONObject("location").getString("longitude");
					String location = crimeJSON.getJSONObject("location").getJSONObject("street").getString("name");
					String timeInMonth = crimeJSON.getString("month");
					
					CrimeGeoFence crimeGeoFence = new CrimeGeoFence(category, latitude, longitude, location, timeInMonth);
					crimeGeoFenceService.saveCrimeGeoFence(crimeGeoFence);
					
					if (progress == jsonArray.length()*0.1*progress) {
						System.out.print(">");
						progress++;
					}
					
				} catch (JSONException e) {
					crimeGeoFenceResponse.setResult("false");
					crimeGeoFenceResponse.setMessage("Error occured at crime ["+i+"]: "+e.getMessage());
					return crimeGeoFenceResponse;
				}	
				
			}
			crimeGeoFenceResponse.setResult("true");
			crimeGeoFenceResponse.setMessage("Data of "+jsonArray.length()+"Crimes has been added to Database ");
			System.out.println("Data of "+jsonArray.length()+"Crimes has been added to Database ");
			return crimeGeoFenceResponse;
			
		} else {
			crimeGeoFenceResponse.setResult("false");
			crimeGeoFenceResponse.setMessage("Error: Invalid JSON Data");
			return crimeGeoFenceResponse;
			
		}
		
	}
	
	// Add public event data by JSON
	@RequestMapping(value = "/LeCampusServer/PublicEvent", method = RequestMethod.POST)
	public @ResponseBody JSONResponse addPublicEvents(@RequestBody String publicEventJSONString) {	
		JSONResponse publicEventResponse = new JSONResponse();
		DateFormatter dateFormatter = new DateFormatter();
		
		final Gson gson = new Gson();
		if (StringUtils.isBlank(publicEventJSONString)) {
			System.out.println("String entered is blank");
			publicEventResponse.setResult("false");
			publicEventResponse.setMessage("String entered is blank");
			return publicEventResponse;

		} else if (inputChecker.isValidJSON(publicEventJSONString, gson)) {
				
			JSONArray jsonArray = new JSONArray(publicEventJSONString);
			int progress = 0;
			for (int i=0; i<jsonArray.length(); i++) {
				JSONObject eventJson = jsonArray.getJSONObject(i);
				
				try {
					PublicEvent publicEvent = new PublicEvent();
					publicEvent.setHoldBy("Other");
					publicEvent.setEventType(eventJson.getString("@type"));
					publicEvent.setEventTitle(eventJson.getString("name"));
					publicEvent.setEventDesc(eventJson.getString("description"));
					publicEvent.setLocation(eventJson.getJSONObject("location").getString("name"));
					publicEvent.setAddress(""+eventJson.getJSONObject("location").getJSONObject("address").get("streetAddress").toString());
					try {
						publicEvent.setPostCode(eventJson.getJSONObject("location").getJSONObject("address").getString("postalCode"));
					
					} catch (JSONException e) { 
						publicEvent.setRegion(""+eventJson.getJSONObject("location").getJSONObject("address").get("addressRegion").toString());
					}
					
					publicEvent.setCity(""+eventJson.getJSONObject("location").getJSONObject("address").get("addressLocality").toString());
					try {
						publicEvent.setLat(String.valueOf(eventJson.getJSONObject("location").getJSONObject("geo").getDouble("latitude")));
						publicEvent.setLon(String.valueOf(eventJson.getJSONObject("location").getJSONObject("geo").getDouble("longitude")));
						
					} catch (JSONException e) {
						publicEvent.setLat(eventJson.getJSONObject("location").getJSONObject("geo").getString("latitude"));
						publicEvent.setLon(eventJson.getJSONObject("location").getJSONObject("geo").getString("longitude"));
					}
					
					String startTime = dateFormatter.convertToServerString(eventJson.getString("startDate"), true);
					String endTime = dateFormatter.randomEndTime(startTime);
					publicEvent.setStartTime(startTime);
					publicEvent.setEndTime(endTime);
					try {
						publicEvent.setHost(eventJson.getJSONObject("organizer").getString("name"));
						
					} catch (JSONException e) {}
					
					publicEvent.setDetailUrl(eventJson.getString("url"));
					try {
						publicEvent.setImageUrl(eventJson.getString("image"));
						
					} catch (JSONException e) {
						publicEvent.setImageUrl(eventJson.getJSONObject("image").getString("url"));
					}
					
					publicEventService.saveEvent(publicEvent);
					
					if (progress == jsonArray.length()*0.1*progress) {
						System.out.print(">");
						progress++;
					}
					
				} catch (JSONException e) {
					publicEventResponse.setResult("false");
					publicEventResponse.setMessage("Error occured at event ["+i+"]: "+e.getMessage());
					return publicEventResponse;
				}	
				
			}
			publicEventResponse.setResult("true");
			publicEventResponse.setMessage("Data of "+jsonArray.length()+"Crimes has been added to Database ");
			return publicEventResponse;
			
		} else {
			publicEventResponse.setResult("false");
			publicEventResponse.setMessage("Error: Invalid JSON Data");
			return publicEventResponse;
			
		}
		
	}

}
